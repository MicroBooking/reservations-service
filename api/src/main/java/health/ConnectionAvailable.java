package health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

@Liveness
@ApplicationScoped
public class ConnectionAvailable implements HealthCheck {

    private static final String url = System.getenv("CONNECTION_TEST_URL");

    private static final Logger LOG = Logger.getLogger(ConnectionAvailable.class.getSimpleName());

    @Override
    public HealthCheckResponse call() {
        try {
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