import javax.persistence.EntityManager;

public class Engine implements Runnable {
    private final EntityManager entityManegr;

    public Engine(EntityManager entityManegr) {
        this.entityManegr = entityManegr;
    }

    @Override
    public void run() {

    }
}
