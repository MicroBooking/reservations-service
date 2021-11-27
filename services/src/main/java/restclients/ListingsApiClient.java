package restclients;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import restclients.interfaces.ListingsApi;
import restclients.models.Listing;

import java.net.MalformedURLException;
import java.net.URL;

public class ListingsApiClient {
    public Listing reserveListing(Integer listingId){
        try {
            ListingsApi listingsApi = RestClientBuilder
                    .newBuilder()
                    .baseUrl(new URL("http://listings-service:8080/v1/listings/reserveListing/" + listingId.toString()))
                    .build(ListingsApi.class);

            listingsApi.reserveListing(listingId);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
