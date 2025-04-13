package se.lexicon;

import se.lexicon.data.SchoolSystemManager;

import java.util.Scanner;

public class MenuUi {
    private final Scanner userInput;
    private boolean menuLoop = true;
    private final SchoolSystemManager schoolSystemManager;

    public MenuUi() {
        this.userInput = new Scanner(System.in);
        this.schoolSystemManager = new SchoolSystemManager();
    }

    public Scanner getUserInput() {
        return userInput;
    }

    public SchoolSystemManager getSchoolSystemManager() {
        return schoolSystemManager;
    }

    public boolean isMenuLoop() {
        return menuLoop;
    }

    public void setMenuLoop(boolean menuLoop) {
        this.menuLoop = menuLoop;
    }

    public void startSystemMenu() {
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
        System.out.println("0. To Exist the program");

    }

    private int userMenuChoice() {
        System.out.println(" ");
        System.out.print("Enter your choice: ");
        return getUserInput().nextInt();
    }

    public void menuOperations() {

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
                System.out.println("3 Choice");

                break;
            case 4:
                System.out.println("4 Choice");

                break;

            case 5:
                System.out.println("5 Choice");

                break;
            case 6:
                System.out.println("6 Choice");

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
