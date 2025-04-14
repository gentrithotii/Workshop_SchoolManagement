package se.lexicon.data;

import se.lexicon.dao.CourseDAOImpl;
import se.lexicon.dao.StudentDAOImpl;
import se.lexicon.dataseed.SeedData;
import se.lexicon.model.Course;
import se.lexicon.model.Student;

import java.time.LocalDate;
import java.util.Scanner;

public class SchoolSystemManager {
    private final StudentDAOImpl studentDAO;
    private final CourseDAOImpl courseDAO;

    public SchoolSystemManager() {
        this.studentDAO = StudentDAOImpl.getInstance();
        this.courseDAO = CourseDAOImpl.getInstance();
        SeedData.loadData(studentDAO, courseDAO);
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

    public String searchForStudentOrCourse(Scanner sc) {
        System.out.println("1. To find a Student");
        System.out.println("2. To find a Course");
        System.out.print("Do you want to search for an Student or a course: ");
        String userInput = sc.nextLine();

        switch (userInput) {
            case "1":
                System.out.println("Enter the Email of the Student you want to find: ");
                String searchStudentEmail = sc.nextLine();
                Student foundUser = getStudentDAO().findByEmail(searchStudentEmail);
                return foundUser.toString();
            case "2":
                System.out.println("Enter the  course id: ");
                int searchCourseById = Integer.parseInt(sc.nextLine());
                Course foundCourse = getCourseDAO().findById(searchCourseById);
                sc.nextLine();
                return foundCourse.toString();
            default:
                System.out.println("Wrong Input");
                break;
        }
        return null;
    }

    public String updateStudentOrCourse(Scanner sc) {
        System.out.println("1. To update a Student");
        System.out.println("2. To update a Course");
        System.out.print("Do you want to Update a Student or a course: ");
        String userInput = sc.nextLine();

        switch (userInput) {
            case "1":

                return choiceUpdateOperationChoice(sc, TypeOfModel.STUDENT);
            case "2":
                return choiceUpdateOperationChoice(sc, TypeOfModel.COURSE);
            default:
                System.out.println("Wrong Input");
                break;
        }
        return null;
    }

    private Student getToUpdateStudent(Scanner sc) {
        System.out.println("Enter the Email of the Student you want to update: ");
        String searchStudentEmail = sc.nextLine();
        Student foundUser = getStudentDAO().findByEmail(searchStudentEmail);
        choiceUpdateOperationChoice(sc, TypeOfModel.STUDENT);
        return foundUser;
    }

    private Course getCourseToUpdate(Scanner sc) {
        System.out.println("Enter the  course id you want to update: ");
        String userInput = sc.nextLine();
        Course foundCourse = getCourseDAO().findById(Integer.parseInt(userInput));
        //Care here
        choiceUpdateOperationChoice(sc, TypeOfModel.COURSE);
        return foundCourse;
    }

    private String choiceUpdateOperationChoice(Scanner sc, TypeOfModel type) {
        displayChoices(type);
        System.out.print("Your choice: ");
        String userInput = sc.nextLine();
        if (type == TypeOfModel.STUDENT) {

            Student studentToUpdate = getToUpdateStudent(sc);

            switch (userInput) {
                case "1":
                    studentToUpdate.setName(userInput);
                    break;
                case "2":
                    studentToUpdate.setEmail(userInput);
                    break;
                case "3":
                    studentToUpdate.setAddress(userInput);
                    break;
                default:
                    System.out.println("Wrong choice: ");
                    break;
            }
            return studentToUpdate.toString();

        } else {

            Course courseToUpdate = getCourseToUpdate(sc);
            switch (userInput) {
                case "1":
                    courseToUpdate.setCourseName(userInput);
                    break;
                case "2":
                    LocalDate date = LocalDate.parse(userInput);
                    courseToUpdate.setStartDate(date);
                    break;
                case "3":
                    courseToUpdate.setWeekDuration(Integer.parseInt(userInput));
                    sc.nextLine();
                    break;
                default:
                    System.out.println("Wrong choice: ");
                    break;
            }
            return courseToUpdate.toString();
        }
    }

    private void displayChoices(TypeOfModel type) {
        if (type == TypeOfModel.STUDENT) {
            System.out.println("1. To update name: ");
            System.out.println("2. To update email: ");
            System.out.println("3. To update address: ");
            System.out.println("\n");
        } else {
            System.out.println("1. To update Course name: ");
            System.out.println("2. To update Course Date: ");
            System.out.println("3. To update Week Duration: ");
            System.out.println("\n");
        }

    }
}
