package entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class IdGenerator {
    private long id;

    public IdGenerator() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
