import beans.ReservationsBean;
import classes.Reservation;
import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import restclients.ListingsApiClient;
import restclients.interfaces.ListingsApi;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("reservations")
public class ReservationsServiceApi {
    private Logger log = Logger.getLogger(ReservationsServiceApi.class.getName());

    @Inject
    private ReservationsBean reservationsBean;

    @Inject
    private ListingsApiClient listingsApiClient;

    @Operation(description = "Get all reservations.", summary = "Get all reservations.")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "List of all reservations",
                    content = @Content(schema = @Schema(implementation = Reservation.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
            )})
    @GET
    public Response getAllReservations() {
        List<Reservation> reservation = reservationsBean.getReservations();
        return Response.status(Response.Status.OK).entity(reservation).build();

    }

    @Operation(description = "Get a listing by id.", summary = "Get listing by id")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Get listing by id",
                    content = @Content(
                            schema = @Schema(implementation = Reservation.class))
            )})
    @GET
    @Path("/{reservationId}")
    public Response getReservationById(@PathParam("reservationId") Integer reservationId) {
        Reservation reservation = reservationsBean.getReservationById(reservationId);
        return Response.status(Response.Status.OK).entity(reservation).build();
    }

    @Operation(description = "Add reservation.", summary = "Add reservation")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Reservation successfully added."
            ),
            @APIResponse(responseCode = "400", description = "Validation error. Check request parameters")
    })
    @POST
    public Response createReservation(Reservation reservation) {
        if(reservation.getOwnerId() == null || reservation.getReserverId() == null || reservation.getStartDate() == null
                || reservation.getEndDate() == null || reservation.getListingId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            reservation = reservationsBean.createReservation(reservation);
        }
        
        listingsApiClient.reserveListing(reservation.getListingId(), reservation.getReservationId());
        return Response.status(Response.Status.OK).entity(reservation).build();
    }
}
