import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class App {
    public static final String GRINGOTTS_PU = "gringotts";
    public static final String SALES_PU = "sales";
    public static void main(String[] args) throws IOException {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(SALES_PU);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        Engine engine = new Engine(entityManager);

//        engine.run();
    }
}
