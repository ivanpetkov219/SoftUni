package entitys.sales;

import entitys.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "sale")
public class Sale extends BaseEntity {

    private Product product;
    private Customer customer;
    private StoreLocation storeLocation;
    private Date LocalDate;

    public Sale() {
    }

    @ManyToOne
    @JoinColumn(name = "porduct_id",referencedColumnName = "id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @ManyToOne
    @JoinColumn(name = "store_location_id",referencedColumnName = "id")
    public StoreLocation getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
    }

    @Column(name = "local_data")
    public Date getLocalDate() {
        return LocalDate;
    }

    public void setLocalDate(Date localDate) {
        LocalDate = localDate;
    }
}
