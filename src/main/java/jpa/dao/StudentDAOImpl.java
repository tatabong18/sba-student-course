package jpa.dao;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public class StudentDAOImpl implements StudentDAOInterface {

    private SessionFactory factory;
    private Session session;
    private Transaction transaction;

    public StudentDAOImpl() {
    }

    @Override
    public List<Student> getAllStudents() {
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        String hql = "from Student";
        Query<Student> query = session.createQuery(hql, Student.class);
        List<Student> students = query.list();
        transaction.commit();
        session.close();
        factory.close();
        return students;

    }

    @Override
    public Student getStudentByEmail(String email) {

        Student student = null;
        try {
            factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            student = session.get(Student.class, email);
            transaction.commit();
            factory.close();
            session.close();

        } catch (Error e) {
            System.out.println("User not found");
            System.out.println(e.getMessage());
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        }
        return student;
    }

    @Override
    public boolean validateStudent(String email, String password) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
            transaction = session.beginTransaction();
            Query<Student> query = session.createQuery("select s from Student s where email=:email && password=:password", Student.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            List<Student> students = query.getResultList();
            if (students.isEmpty()) {
                System.out.println("User not found");
                transaction.commit();
                session.close();
                factory.close();
                return false;
            }
            for (Student student : students) {
                System.out.println(student);
            }
            transaction.commit();
            session.close();
            factory.close();
            return true;
        } catch (Exception e) {
            System.out.println("User not found");
            System.out.println(e.getMessage());
            e.printStackTrace();
            transaction.commit();
            session.close();
            factory.close();
            return false;
        }


    }

    @Override
    public boolean registerStudentToCourse(String email, int id) {
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        Query<Student> query = session.createQuery("from Student where email = :email", Student.class);
        query.setParameter("email", email);
        Student student = query.getSingleResult();

        if (student == null) {

            transaction.rollback();
            session.close();
            factory.close();
            return false;
        }

        Query<Course> query2 = session.createQuery("from Course where id= :id", Course.class);
        query2.setParameter("id", id);
        Course course = query2.getSingleResult();

        if (course == null) {
            transaction.rollback();
            session.close();
            factory.close();
            return false;
        }
        if (student.getsCourses().contains(course)) {
            return false;
        }
        student.addCourse(course);
        transaction.commit();
        session.close();

        factory.close();
        return true;
    }


    @Override
    public List<Course> getStudentCourses(String email) {
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        String hql = "From Student Where email=:email";

        Query<Student> query = session.createQuery(hql, Student.class);
        query.setParameter("email", email);
        Student student = query.getSingleResult();
        Hibernate.initialize(student.getsCourses());
        List<Course> courses = student.getsCourses();

        transaction.commit();
        session.close();
        factory.close();

        return courses;
    }


}