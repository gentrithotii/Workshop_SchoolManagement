package se.lexicon.ui;

import se.lexicon.data.SchoolSystemManager;

import java.util.Scanner;

public class MenuUi {
    private final Scanner userInput;
    private boolean menuLoop;
    private final SchoolSystemManager schoolSystemManager;

    public MenuUi() {
        this.userInput = new Scanner(System.in);
        this.schoolSystemManager = new SchoolSystemManager();
    }

    private Scanner getUserInput() {
        return userInput;
    }

    private SchoolSystemManager getSchoolSystemManager() {
        return schoolSystemManager;
    }

    private boolean isMenuLoop() {
        return menuLoop;
    }

    private void setMenuLoop(boolean menuLoop) {
        this.menuLoop = menuLoop;
    }

    public void startSystemMenu() {
        setMenuLoop(true);
        while (isMenuLoop()) {
            printUserChoices();
            menuOperations();
        }
    }

    private void printUserChoices() {
        System.out.println("1. To Add a New Course");
        System.out.println("2. To Add a New Student");
        System.out.println("3. To Register a student to course");
        System.out.println("4. To Remove a registered student from course");
        System.out.println("5. To Search for Students or Courses");
        System.out.println("6. To Update Student or Course");
        System.out.println("0. To Exit the program");

    }

    private int userMenuChoice() {
        System.out.println(" ");
        System.out.print("Enter your choice: ");
        return getUserInput().nextInt();
    }

    private void menuOperations() {

        int choice = userMenuChoice();
        getUserInput().nextLine();

        switch (choice) {
            case 1:
                getSchoolSystemManager().addCourseToList(getUserInput());
                break;
            case 2:
                getSchoolSystemManager().addStudentToList(getUserInput());
                break;
            case 3:
                getSchoolSystemManager().registerStudentToCourse(getUserInput());
                break;
            case 4:
                getSchoolSystemManager().unRegisterStudentFromCourse(getUserInput());
                break;
            case 5:
                String result = getSchoolSystemManager().searchForStudentOrCourse(getUserInput());
                System.out.println(result);
                break;
            case 6:
                String updatedStudentOrCourse = getSchoolSystemManager().updateStudentOrCourse(getUserInput());
                System.out.println(updatedStudentOrCourse);
                break;
            case 0:
                setMenuLoop(false);
                break;
            default:
                System.out.println("Wrong choice");
                break;
        }
    }
}
