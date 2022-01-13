package health;

import config.RestProperties;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

@Liveness
@ApplicationScoped
public class ConnectionAvailable implements HealthCheck {

    @Inject
    private RestProperties restProperties;

    private static final Logger LOG = Logger.getLogger(ConnectionAvailable.class.getSimpleName());

    @Override
    public HealthCheckResponse call() {
        try {
            String url = restProperties.getConnCheck();
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");

            if (connection.getResponseCode() == 200) {
                return HealthCheckResponse.up(ConnectionAvailable.class.getSimpleName());
            }
        } catch (Exception exception) {
            LOG.severe(exception.getMessage());
        }
        return HealthCheckResponse.down(ConnectionAvailable.class.getSimpleName());
    }
}