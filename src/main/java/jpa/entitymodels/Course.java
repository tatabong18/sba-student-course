package jpa.entitymodels;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "courses")
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "Instructor")
    private String Instructor;


    public Course() {
    }

    public Course(int id, String name, String Instructor) {
        this.id = id;
        this.name = name;
        this.Instructor = Instructor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return Instructor;
    }

    public void setInstructor(String instructor) {
        Instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Instructor='" + Instructor + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Course course = (Course) obj;
        return id == course.id &&
                Objects.equals(name, course.name) &&
                Objects.equals(Instructor, course.Instructor);
    }

}


