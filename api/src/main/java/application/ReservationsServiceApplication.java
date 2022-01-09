package application;

import com.kumuluz.ee.discovery.annotations.RegisterService;
import com.kumuluz.ee.streaming.common.annotations.StreamListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.logging.Logger;

@OpenAPIDefinition(info = @Info(title = "Listings API", version = "v1",
        contact = @Contact(email = "bp3201@student.uni-lj.si"),
        license = @License(name = "dev"), description = "API for managing reservations."),
        servers = @Server(url = "http://206.189.249.190/reservations-service/"))
@RegisterService
@ApplicationPath("v1")
public class ReservationsServiceApplication extends Application {
}
