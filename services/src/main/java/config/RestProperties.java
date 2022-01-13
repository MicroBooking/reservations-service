package config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ConfigBundle("rest-properties")
@ApplicationScoped
public class RestProperties {

    @ConfigValue(watch = true)
    private Boolean maintenanceMode;

    private Boolean broken;

    @ConfigValue(watch = true)
    private String listingsServiceUrl;

    @ConfigValue(watch = true)
    private String connCheck;


    public Boolean getMaintenanceMode() {
        return this.maintenanceMode;
    }

    public void setMaintenanceMode(final Boolean maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
    }

    public Boolean getBroken() {
        return broken;
    }

    public void setBroken(final Boolean broken) {
        this.broken = broken;
    }

    public String getListingsServiceUrl() {
        return listingsServiceUrl.replace("\"", "");
    }

    public void setListingsServiceUrl(String listingServiceUrl) {

        this.listingsServiceUrl = listingServiceUrl;
    }

    public String getConnCheck() {
        return connCheck;
    }

    public void setConnCheck(String connCheck) {
        this.connCheck = connCheck;
    }
}
