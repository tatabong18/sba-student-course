package jpa.entitymodels;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @OneToMany
    private List<Course> sCourses;

    public Student() {
    }

    public Student(String email, String name,
                   String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.sCourses = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getsCourses() {
        return sCourses;
    }

    public void setsCourses(List<Course> sCourses) {
        this.sCourses = sCourses;
    }
    public void addCourse(Course course) {
        this.sCourses.add(course);
    }

    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sCourses=" + sCourses +
                '}';
    }
    @Override
    public int hashCode() {
        return Objects.hash(sCourses, email, name, password);
    }
}

