package se.lexicon;

import se.lexicon.dao.CourseDaoImpl;
import se.lexicon.dao.StudentDaoImpl;
import se.lexicon.dataseed.SeedData;

public class Main {
    public static void main(String[] args) {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        CourseDaoImpl courseDao = new CourseDaoImpl();

        SeedData.loadData(studentDao, courseDao);


    }

}