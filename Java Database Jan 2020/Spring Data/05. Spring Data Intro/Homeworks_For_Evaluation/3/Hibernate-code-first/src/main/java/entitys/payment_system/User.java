package entitys.payment_system;

import entitys.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Billing_Detail> billing_details;


    public User() {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "user", targetEntity = Billing_Detail.class)
    public Set<Billing_Detail> getBilling_details() {
        return billing_details;
    }

    public void setBilling_details(Set<Billing_Detail> billing_details) {
        this.billing_details = billing_details;
    }
}
