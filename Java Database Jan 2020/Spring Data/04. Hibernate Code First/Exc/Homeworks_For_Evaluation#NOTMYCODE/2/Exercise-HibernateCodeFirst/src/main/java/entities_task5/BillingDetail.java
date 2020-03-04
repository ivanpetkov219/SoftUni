package entities_task5;

import entities.IdGenerator;

import javax.persistence.*;

@Entity
@Table(name = "billing_details")
public class BillingDetail extends IdGenerator {
   private String number;
   private User owner;


    public BillingDetail() {
    }

    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
