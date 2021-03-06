package animals;

public class Tomcat extends Cat {
    private static  final String GENDER = "Male";

    public Tomcat(String name, int age) {
        super(name, age, GENDER);
    }

    public static String getGENDER() {
        return GENDER;
    }

    @Override
    public String produceSound() {
        return "MEOW";
    }
}
