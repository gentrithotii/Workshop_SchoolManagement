package se.lexicon.dao;

import se.lexicon.dao.interfaces.ICourseDAO;
import se.lexicon.model.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements ICourseDAO {
    private static volatile CourseDAOImpl instance;
    private final List<Course> courses;

    private CourseDAOImpl() {
        this.courses = findAll() == null ? new ArrayList<>() : findAll();
    }

    public static CourseDAOImpl getInstance() {

        CourseDAOImpl result = instance;
        if (result != null) {
            return result;
        }
        synchronized (CourseDAOImpl.class) {
            if (instance == null) {
                instance = new CourseDAOImpl();
            }
            return instance;
        }
    }


    @Override
    public Course save(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course can't be null");
        }
        findAll().add(course);
        return course;
    }

    @Override
    public Course findById(int id) {
        for (Course courseItem : findAll()) {
            if (courseItem.getId() == id) {
                return courseItem;
            }
        }
        return null;
    }

    @Override
    public List<Course> findByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        List<Course> byNameList = new ArrayList<>();
        for (Course courseItem : findAll()) {
            if (courseItem.getCourseName().equalsIgnoreCase(name)) {
                byNameList.add(courseItem);
            }
        }
        return byNameList;
    }

    @Override
    public List<Course> findByDate(LocalDate date) {
        List<Course> byDateList = new ArrayList<>();
        for (Course courseItem : findAll()) {
            if (courseItem.getStartDate().equals(date)) {
                byDateList.add(courseItem);
            }
        }
        return byDateList;
    }

    @Override
    public List<Course> findAll() {
        return courses;
    }

    @Override
    public boolean delete(Course course) {
        return findAll().remove(course);
    }
}
