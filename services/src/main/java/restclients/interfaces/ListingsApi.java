package restclients.interfaces;

import restclients.models.Listing;

import javax.ws.rs.*;

@Path("v1/listings")
public interface ListingsApi {
    @POST
    @Path("/reserveListing")
    Listing reserveListing(Integer listingId);
}
