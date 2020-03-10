package entitys.payment_system;

import entitys.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "bank_account")

public class Bank_Account extends BaseEntity {
    private String name;
    private String SWIFT_code;
    private Billing_Detail billing_detail;

    public Bank_Account() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSWIFT_code() {
        return SWIFT_code;
    }

    public void setSWIFT_code(String SWIFT_code) {
        this.SWIFT_code = SWIFT_code;
    }

    @ManyToOne
    @JoinColumn(name = "billing_detail",referencedColumnName = "id")
    public Billing_Detail getBilling_detail() {
        return billing_detail;
    }

    public void setBilling_detail(Billing_Detail billing_detail) {
        this.billing_detail = billing_detail;
    }
}
