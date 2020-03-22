package spring.map.spingautomapping.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    private Set<Game> games;
    private User user;

    public Order() {
    }

    @ManyToMany
    @JoinTable(name = "orders_ordered_games",
            joinColumns = @JoinColumn(name = "order_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ordered_games_id", referencedColumnName = "id"))
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
