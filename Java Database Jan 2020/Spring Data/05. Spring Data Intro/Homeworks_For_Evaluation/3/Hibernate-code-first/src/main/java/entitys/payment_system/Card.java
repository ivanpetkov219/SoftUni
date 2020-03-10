package entitys.payment_system;

import entitys.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "card")
public class Card extends BaseEntity {
    private String type;
    private String expiration_month;
    private String expiration_year;
    private Billing_Detail billing_detail;

    public Card() {
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "expiration_month")
    public String getExpiration_month() {
        return expiration_month;
    }

    public void setExpiration_month(String expiration_month) {
        this.expiration_month = expiration_month;
    }

    @Column(name = "expiration_year")
    public String getExpiration_year() {
        return expiration_year;
    }

    public void setExpiration_year(String expiration_year) {
        this.expiration_year = expiration_year;
    }

    @ManyToOne
    @JoinColumn(name = " billing_detail_id",referencedColumnName = "id")
    public Billing_Detail getBilling_detail() {
        return billing_detail;
    }

    public void setBilling_detail(Billing_Detail billing_detail) {
        this.billing_detail = billing_detail;
    }
}
