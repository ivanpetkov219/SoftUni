package Hospital;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "visitations")
public class Visitation extends BaseEntity {

    private String visitation;
    private LocalDate date;
    private String comments;

    public Visitation() {
    }

    @Column(name = "visitation")
    public String getVisitation() {
        return visitation;
    }

    public void setVisitation(String visitation) {
        this.visitation = visitation;
    }

    @Column(name = "date")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
