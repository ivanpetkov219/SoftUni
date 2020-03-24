package xmlprocessing.productshop.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {


    private int age;
    private String firstName;
    private String lastName;
    private Set<Product> sold;
    private Set<Product> bought;
    private Set<User> friends;

    public User() {
    }


    @Column(name = "age", nullable = true)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToMany(mappedBy = "seller")
    public Set<Product> getSold() {
        return sold;
    }

    public void setSold(Set<Product> sold) {
        this.sold = sold;
    }

    @OneToMany(mappedBy = "buyer")
    public Set<Product> getBought() {
        return bought;
    }

    public void setBought(Set<Product> bought) {
        this.bought = bought;
    }

    @OneToMany
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
}
