package restclients;

import config.RestProperties;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import restclients.interfaces.ListingsApi;
import restclients.models.Listing;

import javax.inject.Inject;
import java.net.MalformedURLException;
import java.net.URL;

@ApplicationScoped
public class ListingsApiClient {

    @Inject
    private RestProperties restProperties;

    public Listing reserveListing(Integer listingId){
        try {
            ListingsApi listingsApi = RestClientBuilder
                    .newBuilder()
                    .baseUrl(new URL(restProperties.getListingsServiceUrl()))
                    .build(ListingsApi.class);

            listingsApi.reserveListing(listingId);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
