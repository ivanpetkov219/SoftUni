import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
   private static final String PERSISTENCE_NAME = "tssst";
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Engine engine = new Engine(entityManager);

        engine.run();
    }
}
