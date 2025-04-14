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

    public Student registerStudentToCourse(Scanner sc) {
        System.out.print("Enter the Email of the Student you want ");
        String userEmail = sc.nextLine();
        System.out.println("\n");
        Student foundStudent = getStudentDAO().findByEmail(userEmail);
        if (foundStudent == null) {
            System.out.println("User not Found");
            return null;
        }

        System.out.println("Enter the Course id to register the Student: ");
        int courseForStudent = sc.nextInt();
        Course course = getCourseDAO().findById(courseForStudent);
        if (course == null) {
            System.out.println("Course not found");
            return null;
        }

        course.register(foundStudent);

        return foundStudent;
    }

    public Student unRegisterStudentFromCourse(Scanner sc) {
        System.out.print("Enter the course id that you want to remove the Student from:  ");
        int courseId = sc.nextInt();
        Course foundCourse = getCourseDAO().findById(courseId);
        sc.nextLine();

        System.out.println("Enter the Student Email that you want to kick from the course:  ");
        String studentEmail = sc.nextLine();

        Student foundStudent = getStudentDAO().findByEmail(studentEmail);
        foundCourse.unregister(foundStudent);

        return foundStudent;
    }

    public <T> T searchForStudentOrCourse(Scanner sc) {
        System.out.println("1. To find a Student");
        System.out.println("2. To find a Course");
        System.out.print("Do you want to search for an Student or a course: ");
        String userInput = sc.nextLine();

        switch (userInput) {
            case "1":
                System.out.println("Enter the Email of the Student you want to find: ");
                String searchStudentEmail = sc.nextLine();
                Student foundUser = getStudentDAO().findByEmail(searchStudentEmail);
                return (T) foundUser;
            case "2":
                System.out.println("Enter the  course id: ");
                int searchCourseById = Integer.parseInt(sc.nextLine());
                Course foundCourse = getCourseDAO().findById(searchCourseById);
                sc.nextLine();
                return (T) foundCourse;
            default:
                System.out.println("Wrong Input");
                break;
        }

        return null;
    }
}
