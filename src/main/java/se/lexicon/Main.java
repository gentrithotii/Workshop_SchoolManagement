package se.lexicon;

import se.lexicon.dao.CourseDAOImpl;
import se.lexicon.dao.StudentDAOImpl;
import se.lexicon.dataseed.SeedData;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        MenuUi menuUi = new MenuUi();
        menuUi.startSystemMenu();
    }

}