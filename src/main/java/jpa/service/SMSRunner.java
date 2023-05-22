package jpa.service;

import jpa.dao.CourseDAOImpl;
import jpa.dao.StudentDAOImpl;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;


public class SMSRunner {

    private final Scanner sin;
    private final StringBuilder sb;

    private final CourseDAOImpl courseDAOImpl;
    private final StudentDAOImpl studentDAOImpl;
    private Student currentStudent;

    public SMSRunner() {
        sin = new Scanner(System.in);
        sb = new StringBuilder();
        courseDAOImpl = new CourseDAOImpl();
        studentDAOImpl = new StudentDAOImpl();
    }


    public static void main(String[] args) {

        SMSRunner sms = new SMSRunner();
        sms.run();
    }

    private void run() {
        // Login or quit
        switch (menu1()) {
            case 1:
                if (studentLogin()) {
                    registerMenu();

                }
                break;
            case 2:
                out.println("Goodbye!");
                break;

            default:

        }

    }

    private int menu1() {

        sb.append("Are you a(n)\n1.Student\n2. quit\nPlease, enter 1 or 2: ");
        out.print(sb);
        sb.delete(0, sb.length());

        return sin.nextInt();
    }

    private boolean studentLogin() {
        boolean retValue = false;
        out.print("Enter your email address: ");
        String email = sin.next();
        out.print("Enter your password: ");
        String password = sin.next();

        Student students = studentDAOImpl.getStudentByEmail(email);
        if (students != null) {
            currentStudent = students;
        }

        if (currentStudent != null) {

            if (currentStudent.getPassword().equals(password)) {


                List<Course> courses = studentDAOImpl.getStudentCourses(email);
                out.println("MyClasses");
                for (Course course : courses) {
                    out.println(course);
                }
                retValue = true;
            }
        } else {
            out.println("User Validation failed. GoodBye!");
        }
        return retValue;
    }

    private void registerMenu() {
        sb.append("\n1.Register a class\n2. Logout\nPlease Enter Selection: ");
        out.print(sb);
        sb.delete(0, sb.length());

        switch (sin.nextInt()) {
            case 1:
                List<Course> allCourses = courseDAOImpl.getAllCourses();
                List<Course> studentCourses = studentDAOImpl.getStudentCourses(currentStudent.getEmail());
                allCourses.removeAll(studentCourses);
                out.printf("%5s%15S%15s\n", "ID", "Course", "Instructor");
                for (Course course : allCourses) {
                    out.println(course);
                }
                out.println();
                out.print("Enter Course Number: ");
                int number = sin.nextInt();
                Course newCourse = courseDAOImpl.getAllCourses().get(number);

                if (newCourse != null) {
                    if (studentDAOImpl.registerStudentToCourse(currentStudent.getEmail(), number)) {
                        Student temp = studentDAOImpl.getStudentByEmail(currentStudent.getEmail());
                        out.println("MyClasses");
                        for (Course course : temp.getsCourses()) {
                            out.println(course);
                        }
                    } else {

                        Student temp = studentDAOImpl.getStudentByEmail(currentStudent.getEmail());
                        out.println("Already registered for this course");
                        out.println("MyClasses");
                        for (Course course : temp.getsCourses()) {
                            out.println(course);

                        }
                    }
                    out.println("Goodbye!");

                }
                break;
            case 2:
            default:
                out.println("Goodbye!");
        }
    }
}

