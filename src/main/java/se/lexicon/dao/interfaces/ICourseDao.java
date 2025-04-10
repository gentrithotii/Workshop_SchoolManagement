package se.lexicon.dao.interfaces;

import se.lexicon.model.Course;

import java.time.LocalDate;
import java.util.List;

public interface ICourseDao {
    public Course save(Course course);

    public Course findById(int id);

    public List<Course> findByName(String name);

    public List<Course> findByDate(LocalDate date);

    public List<Course> findAll();

    public boolean delete(Course course);
}
