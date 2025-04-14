package se.lexicon.dao.interfaces;

import se.lexicon.model.Course;

import java.time.LocalDate;
import java.util.List;

public interface ICourseDAO {
    Course save(Course course);

    Course findById(int id);

    List<Course> findByName(String name);

    List<Course> findByDate(LocalDate date);

    List<Course> findAll();

    Course updateCourse(Course course);

    boolean delete(Course course);
}
