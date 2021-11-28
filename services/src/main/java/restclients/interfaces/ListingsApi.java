package restclients.interfaces;

import restclients.models.Listing;

import javax.ws.rs.*;

@Path("v1/listings")
public interface ListingsApi {
    @POST
    @Path("/reserveListing/{listingId}")
    Listing reserveListing(@PathParam("listingId") Integer listingId);
}
