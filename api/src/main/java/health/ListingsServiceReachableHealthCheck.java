package health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import restclients.ListingsApiClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class ListingsServiceReachableHealthCheck implements HealthCheck {
    @Inject
    private ListingsApiClient listingsApiClient;

    @Override
    public HealthCheckResponse call() {
        try {
            listingsApiClient.getListings();
            return HealthCheckResponse.up(ListingsServiceReachableHealthCheck.class.getSimpleName());
        } catch (Exception e){
            return HealthCheckResponse.down(ListingsServiceReachableHealthCheck.class.getSimpleName());
        }
    }
    
}