package restclients;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import config.RestProperties;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import restclients.interfaces.ListingsApi;
import restclients.models.Listing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@ApplicationScoped
public class ListingsApiClient {

    @Inject
    @DiscoverService(value = "listings-service", environment = "dev", version = "1.0.0")
    private URL listingsServiceUrl;

    @Inject
    private RestProperties restProperties;

    public Listing reserveListing(Integer listingId, Integer reservationId){
        ListingsApi listingsApi = null;
        try {
            listingsApi = RestClientBuilder
                    .newBuilder()
                    .baseUrl(listingsServiceUrl)
                    .build(ListingsApi.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(restProperties.getListingsServiceUrl());

            listingsApi.reserveListing(listingId, reservationId);

            return null;
    }

    public List <Listing> getListings() throws MalformedURLException {
        ListingsApi listingsApi = RestClientBuilder
                .newBuilder()
                .baseUrl(new URL(restProperties.getListingsServiceUrl()))
                .build(ListingsApi.class);


        return listingsApi.getListings();
    }
}
