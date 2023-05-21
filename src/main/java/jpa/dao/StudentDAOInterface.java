package jpa.dao;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import java.util.List;

public interface StudentDAOInterface {

    List<Student> getAllStudents();

    Student getStudentByEmail(String email);

    boolean validateStudent(String email, String password);

    boolean registerStudentToCourse(String email, int courseId);

    List<Course> getStudentCourses(String email);
}
