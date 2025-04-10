package se.lexicon.dataseed;

import se.lexicon.dao.CourseDaoImpl;
import se.lexicon.dao.StudentDaoImpl;
import se.lexicon.model.Course;
import se.lexicon.model.Student;

import java.time.LocalDate;

public class SeedData {


    public static void loadData(StudentDaoImpl studentDao, CourseDaoImpl courseDao) {
        //Students
        Student student1 = studentDao.save(new Student("John Doe", "john.doe@example.com", "123 Main St"));
        Student student2 = studentDao.save(new Student("Jane Smith", "jane.smith@example.com", "456 Elm St"));
        Student student3 = studentDao.save(new Student("Alice Johnson", "alice.johnson@example.com", "789 Oak St"));
        Student student4 = studentDao.save(new Student("Bob Brown", "bob.brown@example.com", "101 Pine St"));
        Student student5 = studentDao.save(new Student("Charlie Davis", "charlie.davis@example.com", "202 Maple St"));
        Student student6 = studentDao.save(new Student("Diana Clark", "diana.clark@example.com", "303 Cedar St"));
        Student student7 = studentDao.save(new Student("Eve Wilson", "eve.wilson@example.com", "404 Birch St"));
        Student student8 = studentDao.save(new Student("Frank Lee", "frank.lee@example.com", "505 Willow St"));
        Student student9 = studentDao.save(new Student("Grace Hall", "grace.hall@example.com", "606 Cherry St"));
        Student student10 = studentDao.save(new Student("Hank Allen", "hank.allen@example.com", "707 Redwood St"));

        //Courses
        Course course1 = courseDao.save(new Course("Java 101", LocalDate.of(2025, 5, 4), 9));
        Course course2 = courseDao.save(new Course("Python Basics", LocalDate.of(2025, 5, 3), 7));
        Course course3 = courseDao.save(new Course("Web Development", LocalDate.of(2025, 4, 27), 15));
        Course course4 = courseDao.save(new Course("Data Science", LocalDate.of(2025, 5, 2), 10));
        Course course5 = courseDao.save(new Course("Mobile App Development", LocalDate.of(2025, 5, 6), 8));
        Course course6 = courseDao.save(new Course("Machine Learning", LocalDate.of(2025, 4, 22), 14));
        Course course7 = courseDao.save(new Course("AI Fundamentals", LocalDate.of(2025, 4, 19), 6));
        Course course8 = courseDao.save(new Course("Cloud Computing", LocalDate.of(2025, 4, 12), 6));
        Course course9 = courseDao.save(new Course("DevOps Essentials", LocalDate.of(2025, 4, 13), 13));
        Course course10 = courseDao.save(new Course("Cybersecurity", LocalDate.of(2025, 4, 25), 11));
    }


}

