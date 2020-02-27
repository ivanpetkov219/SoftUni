import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class App {
    public static final String SOFT_UNI_PU = "soft_uni";
    public static void main(String[] args) throws IOException {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(SOFT_UNI_PU);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Engine engine = new Engine(entityManager);

        engine.run();
    }
}
