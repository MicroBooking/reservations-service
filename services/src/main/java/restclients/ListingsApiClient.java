package restclients;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.discovery.enums.AccessType;
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
    private RestProperties restProperties;

    public Listing reserveListing(Integer listingId, Integer reservationId){
        System.out.println(listingsServiceUrl);
        ListingsApi listingsApi = null;
        try {
            listingsApi = RestClientBuilder
                    .newBuilder()
                    .baseUrl(new URL(restProperties.getListingsServiceUrl()))
                    .build(ListingsApi.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
