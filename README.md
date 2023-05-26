In this project I used RDBMS to store tables from my entities(model classes) by using Hibernate.

Business Requirements are:
Create a basic School Management System where students can register for courses, and view the course assigned to them.

Work-Flow:

Only students with the right credentials can log in. Otherwise, a message is displayed stating: “Wrong Credentials”. 

Valid students are able to see the courses they are registered for and any course in the system as long as they are not already registered.

Every Model class must contain the following general two requirements:

The first constructor takes no parameters and initializes every member to an initial value.
The second constructor initialized every private member with a parameter provided to the constructor.
I created a class Student with the private member variables specified in TABLE 1. These private members must have GETTERS and SETTERS methods.

The purpose of the Student class is to carry data related to one student.

I created a class Course with the private member variables specified in TABLE 2. These private members must have GETTERS and SETTERS methods.

The purpose of the Course class is to carry data related to one Course.

I used Data Access Object (DAO) and Services (Implementation) 
I Handled all possible exceptions and included appropriate commenting. 
I also Tested at least one of my methods using Junit.

