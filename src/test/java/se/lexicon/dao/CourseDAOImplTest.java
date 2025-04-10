package se.lexicon.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Course;
import se.lexicon.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseDAOImplTest {
    private CourseDAOImpl courseDAO;

    @BeforeEach
    void setUp() {
        courseDAO = new CourseDAOImpl();
    }

    @Test
    @DisplayName("Check if course is added")
    void save() {
        //Arrange
        Course newCourse = new Course("Java", LocalDate.now(), 12); //Expected
        //Assert
        assertEquals(newCourse, courseDAO.save(newCourse));
    }

    @Test
    @DisplayName("Test for null parameter")
    void saveTestNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> courseDAO.save(null));
    }

    @Test
    @DisplayName("Test if the right person is found")
    void findById() {
        //Arrange
        Course expected = new Course("Java", LocalDate.now(), 12);
        //Act
        courseDAO.save(expected);
        //Assert
        assertEquals(expected, courseDAO.findById(2));
    }

    @Test
    void findByName() {
        //Arrange
        Course testData1 = new Course("Java", LocalDate.now(), 40);
        Course testData2 = new Course("Java", LocalDate.now(), 40);
        Course testData3 = new Course("Java", LocalDate.now(), 40);

        //Act
        List<Course> expected = new ArrayList<>();
        courseDAO.save(testData1);
        courseDAO.save(testData2);
        courseDAO.save(testData3);
        expected.add(testData1);
        expected.add(testData2);
        expected.add(testData3);
        //Assert
        assertEquals(expected, courseDAO.findByName("Java"));
    }

    @Test
    void findByDate() {
        //Arrange
        //Act
        //Assert
    }

    @Test
    void findAll() {
        //Arrange
        //Act
        //Assert
    }

    @Test
    void delete() {
        //Arrange
        //Act
        //Assert
    }
}