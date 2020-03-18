package springdata.automapping.models.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "games")
@NoArgsConstructor
@AllArgsConstructor
public class Game extends BaseEntity {

    private String title;
    private String trailer;
    private String imageThumbnail;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;
    @ManyToMany(mappedBy = "games")
    private Set<User> users;
    @ManyToMany(mappedBy = "games")
    private Set<Order> orders;

}
