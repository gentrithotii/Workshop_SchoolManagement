package se.lexicon;

import se.lexicon.dao.CourseDAOImpl;
import se.lexicon.dao.StudentDAOImpl;
import se.lexicon.dataseed.SeedData;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentDAOImpl studentDao = new StudentDAOImpl();
        CourseDAOImpl courseDao = new CourseDAOImpl();

        SeedData.loadData(studentDao, courseDao);


    }

}