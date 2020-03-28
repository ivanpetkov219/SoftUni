package hiberspring.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "branches")
public class Branch extends BaseEntity {

    private String name;
    private Town town;

    public Branch() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
