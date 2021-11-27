import beans.ReservationsBean;
import classes.Reservation;
import restclients.ListingsApiClient;
import restclients.interfaces.ListingsApi;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservationsServiceApi {
    private Logger log = Logger.getLogger(ReservationsServiceApi.class.getName());

    @Inject
    private ReservationsBean reservationsBean;

    @GET
    public Response getAllReservations() {
        List<Reservation> reservation = reservationsBean.getReservations();
        return Response.status(Response.Status.OK).entity(reservation).build();

    }

    @POST
    public Response createReservation(Reservation reservation) {
        if(reservation.getOwnerId() == null || reservation.getReserverId() == null || reservation.getStartDate() == null
                || reservation.getEndDate() == null || reservation.getListingId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            reservation = reservationsBean.createReservation(reservation);
        }

        ListingsApiClient listingsApiClient = new ListingsApiClient();
        listingsApiClient.reserveListing(reservation.getListingId());
        return Response.status(Response.Status.OK).entity(reservation).build();
    }




}
