package com.softuni.xmlparsingexerciseone.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User  extends BaseEntity{
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;

    @Column
    private int age;

    @OneToMany(mappedBy = "buyer",fetch = FetchType.EAGER)
    private Set<Product>bought;

    @OneToMany(mappedBy = "seller",fetch = FetchType.EAGER)
    private Set<Product>sold;

    @ManyToMany
    @JoinTable(
            name = "users_friends",
            joinColumns = { @JoinColumn(name = "user_id",referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "friend_id",referencedColumnName = "id") })
    private Set<User>friends;

    @Override
    public String toString() {
        return firstName+" "+lastName;
    }
}
