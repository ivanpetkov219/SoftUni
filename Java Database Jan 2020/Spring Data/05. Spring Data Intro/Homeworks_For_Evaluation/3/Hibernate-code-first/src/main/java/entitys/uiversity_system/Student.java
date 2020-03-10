package entitys.uiversity_system;

import entitys.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student extends Person {

    private double grade;
    private double attendance;
    private Set<Course> course;

    public Student() {
    }

    @Column(name = "garde")
    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Column(name = "attendance")
    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    @OneToMany(mappedBy = "student",targetEntity = Course.class)
    public Set<Course> getCourse() {
        return course;
    }

    public void setCourse(Set<Course> course) {
        this.course = course;
    }
}
