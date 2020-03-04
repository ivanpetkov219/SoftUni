package Sales;

import jdk.jfr.Enabled;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "store_lacations")
public class StoreLocation extends BaseEntity {
    private String locationName;

    public StoreLocation() {
    }

    @Column(name = "location_name")
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
