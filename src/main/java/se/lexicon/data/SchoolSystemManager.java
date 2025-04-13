package se.lexicon.data;

import se.lexicon.dao.CourseDAOImpl;
import se.lexicon.dao.StudentDAOImpl;
import se.lexicon.model.Course;
import se.lexicon.model.Student;

import java.time.LocalDate;
import java.util.Scanner;

public class SchoolSystemManager {
    private final StudentDAOImpl studentDAO;
    private final CourseDAOImpl courseDAO;

    public SchoolSystemManager() {
        studentDAO = new StudentDAOImpl();
        courseDAO = new CourseDAOImpl();
    }

    private CourseDAOImpl getCourseDAO() {
        return courseDAO;
    }

    private StudentDAOImpl getStudentDAO() {
        return studentDAO;
    }

    public Course addCourseToList(Scanner sc) {
        System.out.print("Enter course name: ");
        String courseName = sc.nextLine();
        System.out.println("\n");
        System.out.println("Enter the date in the following format Example: (2018-05-05) : ");
        String userDate = sc.nextLine();
        LocalDate date = LocalDate.parse(userDate);
        System.out.print("Enter Course duration in weeks: ");
        int weekDuration = Integer.parseInt(sc.nextLine());
        Course newCourse = new Course(courseName, date, weekDuration);

        getCourseDAO().save(newCourse);

        return newCourse;
    }

    public Student addStudentToList(Scanner sc) {
        System.out.print("Enter Student name: ");
        String studentName = sc.nextLine();
        System.out.println("\n");
        System.out.println("Enter Student email: ");
        String studentEmail = sc.nextLine();
        System.out.println("\n");
        System.out.print("Enter Student Address: ");
        String studentAddress = sc.nextLine();

        Student newStudent = new Student(studentName, studentEmail, studentAddress);

        getStudentDAO().save(newStudent);

        return newStudent;
    }

}
