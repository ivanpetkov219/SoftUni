import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class App {
    public static final String GRINGOTTS_PU = "gringotts";
    public static final String SALES_PU = "sales";
    public static final String UNIVERSITY_PU = "university_system";
    public static final String HOSPITAL_PU = "hospital";
    public static void main(String[] args) throws IOException {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(HOSPITAL_PU);

        //успях да направя само първите четири задачи

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Engine engine = new Engine(entityManager);

        engine.run();
    }
}
