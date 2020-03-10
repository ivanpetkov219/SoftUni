package entitys.uiversity_system;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Teacher")
public class Teacher extends Person {
    private String email;
    private double salaryPerHour;
    private Set<Course> course;

    public Teacher() {
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "salary_per_hour")
    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }
    @OneToMany(mappedBy = "teacher",targetEntity = Course.class)
    public Set<Course> getCourse() {
        return course;
    }

    public void setCourse(Set<Course> course) {
        this.course = course;
    }
}
