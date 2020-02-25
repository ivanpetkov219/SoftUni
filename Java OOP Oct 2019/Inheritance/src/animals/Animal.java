package animals;

public abstract class Animal {
    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    public void setName(String name) {
        this.validateString(name);
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }

    public void setGender(String gender) {
        this.validateString(gender);
        this.gender = gender;
    }
    public String getGender(){
        return this.gender;
    }

    private void validateString(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    @Override
    public String toString() {
        return  String.format("%s%n%s %d %s%n%s",
                this.getClass().getSimpleName(),
                this.getName(), this.getAge(),
                this.getGender(),
                this.produceSound());

    }

    public abstract String produceSound();


}
