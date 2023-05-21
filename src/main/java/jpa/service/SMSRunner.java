
package jpa.service;

import jpa.dao.CourseDAOImpl;
import jpa.dao.StudentDAOImpl;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;


public class SMSRunner {

    private Scanner scanner;
    private StringBuilder sb;

    private CourseDAOImpl courseDAOImpl;
    private StudentDAOImpl studentDAOImpl;
    private Student currentStudent;

    public SMSRunner() {
        scanner = new Scanner(System.in);
        sb = new StringBuilder();
        courseDAOImpl = new CourseDAOImpl();
        studentDAOImpl = new StudentDAOImpl();
    }

    public static void main(String[] args) {

        SMSRunner sms = new SMSRunner();
        sms.run();
    }

    private void run() {
        System.out.println(menu1().toString());
        List<Integer> myMenu = new ArrayList<Integer>();
        myMenu.add(1);
        myMenu.add(2);

        scanner = new Scanner(System.in);
        int x = scanner.nextInt();

        if(myMenu.get(0) == x){
            studentLogin();
        }else if(myMenu.get(1) == x){
            System.exit(0);
        }else{
            out.println("Invalid option");
            return;
        }

    }

    private StringBuilder menu1() {
        sb.append("\n1.Student Login\n2. Quit Application\nPlease Enter Selection: ");
        //out.print(sb.toString());
        //sb.delete(0, sb.length());

        return sb;
    }

    private boolean studentLogin() {
        boolean retValue = false;
        out.print("Enter your email address: ");
        String email = scanner.next();
        out.print("Enter your password: ");
        String password = scanner.next();

        Student students = studentDAOImpl.getStudentByEmail(email);
        if (students != null) {
            currentStudent = students;
        }

        if (currentStudent != null) {
            System.out.println(currentStudent.getPassword().equals(password));
            if (currentStudent.getPassword().equals(password)) {
                List<Course> courses = studentDAOImpl.getStudentCourses(email);
                System.out.println("MyClasses");
                for (Course course : courses) {
                    out.println(course);
                }
                retValue = true;
            }
            registerMenu();
        } else {
            System.out.println("User Validation failed. GoodBye!");
        }
        return retValue;
    }

    private void registerMenu() {
        sb.append("\n1.Register a class\n2. Logout\nPlease Enter Selection: ");
        out.print(sb.toString());
        sb.delete(0, sb.length());

        switch (scanner.nextInt()) {
            case 1:
                List<Course> allCourses = courseDAOImpl.getAllCourses();
                List<Course> studentCourses = studentDAOImpl.getStudentCourses(currentStudent.getEmail());
                //allCourses.removeAll(studentCourses);
                System.out.printf("%5s%15S%15s\n", "ID", "Course", "Instructor");
                for (Course course : allCourses) {
                    System.out.println(course);
                }

                System.out.println(studentCourses.toString());
//                System.out.println();
//                System.out.print("Enter Course Number: ");
//                int number = scanner.nextInt();
//               // System.out.println(courseDAOImpl.getAllCourses());
//                Course newCourse = courseDAOImpl.getAllCourses().get(number);
//
//                if (newCourse != null) {
//
//                    if (studentDAOImpl.registerStudentToCourse(currentStudent.getEmail(), number)) {
//                        Student temp = studentDAOImpl.getStudentByEmail(currentStudent.getEmail());
//                        out.println("MyClasses");
//                        for (Course course : temp.getsCourses()) {
//                            out.println(course);
//                        }
//                    } else {
//                        //Student temp = studentDAOImpl.getStudentByEmail(currentStudent.getEmail());
//                        out.println("Already registered for this course");
//                        out.println("MyClasses");
//                        for (Course course : studentCourses ) {
//                            out.println(course);
//
//                        }
//                    }
//                    out.println("Goodbye!");

               // }
                break;
            case 2:
                System.out.println("Logout!");
                break;
            default:
                System.out.println("Goodbye!");

        }
    }
}
