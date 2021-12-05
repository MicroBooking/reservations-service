package restclients.interfaces;

import restclients.models.Listing;

import javax.ws.rs.*;
import java.util.List;

@Path("v1/listings")
public interface ListingsApi {
    @GET
    @Path("/")
    List<Listing> getListings();

    @POST
    @Path("/reserveListing/{listingId}")
    Listing reserveListing(@PathParam("listingId") Integer listingId);
}
