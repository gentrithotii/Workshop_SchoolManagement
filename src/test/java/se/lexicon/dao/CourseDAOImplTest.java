package se.lexicon.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseDAOImplTest {
    private CourseDAOImpl courseDAO;

    //TODO Needs Checking since implementing Singleton

    @BeforeEach
    void setUp() {
        courseDAO = CourseDAOImpl.getInstance();
    }

    @Test
    @DisplayName("Check if course is added")
    void saveTest() {
        //Arrange
        Course newCourse = new Course("Java", LocalDate.now(), 12); //Expected
        //Assert
        assertEquals(newCourse, courseDAO.save(newCourse));
    }

    @Test
    @DisplayName("Test for null parameter")
    void saveTestNull() {
        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> courseDAO.save(null));
    }

    @Test
    @DisplayName("Test if the right person is found")
    void findByIdTest() {
        // Arrange
        Course expected = new Course("Java", LocalDate.now(), 12);
        // Act
        courseDAO.save(expected);
        // Assert
        Assertions.assertEquals(expected, courseDAO.findById(expected.getId()));
    }

    @Test
    @DisplayName("Finds people by name")
    void findByNameTest() {
        //Arrange
        Course testData1 = new Course("Testing", LocalDate.now(), 40);
        Course testData2 = new Course("Java", LocalDate.now(), 40);
        Course testData3 = new Course("Java", LocalDate.now(), 40);
        Course testData4 = new Course("C#", LocalDate.now(), 40);
        Course testData5 = new Course("C", LocalDate.now(), 40);

        //Act
        List<Course> expected = new ArrayList<>();
        courseDAO.save(testData1);
        courseDAO.save(testData2);
        courseDAO.save(testData3);
        courseDAO.save(testData4);
        courseDAO.save(testData5);

        expected.add(testData2);
        expected.add(testData3);

        //Assert
        assertEquals(expected, courseDAO.findByName("Java"));
    }

    @Test
    void findByDateTest() {
        //Arrange
        Course testData1 = new Course("Testing", LocalDate.of(2024, 1, 22), 40);
        Course testData2 = new Course("Java", LocalDate.now(), 40);
        Course testData3 = new Course("Data", LocalDate.of(2024, 1, 22), 40);
        //Act
        courseDAO.save(testData1);
        courseDAO.save(testData2);
        courseDAO.save(testData3);
        List<Course> expected = new ArrayList<>();
        expected.add(testData1);
        expected.add(testData3);

        //Assert
        assertEquals(expected, courseDAO.findByDate(LocalDate.of(2024, 1, 22)));
    }

    @Test
    void findAllTest() {
        //Arrange
        Course testData1 = new Course("Testing", LocalDate.of(2024, 1, 22), 40);
        Course testData2 = new Course("Java", LocalDate.now(), 40);
        //Act
        courseDAO.save(testData1);
        courseDAO.save(testData2);

        List<Course> expected = new ArrayList<>();
        expected.add(testData1);
        expected.add(testData2);
        //Assert
        assertEquals(expected, courseDAO.findAll());
    }

    @Test
    @DisplayName("Checks if it returns true deleted and checks the list if it got removed")
    void deleteTest() {
        //Arrange
        Course testData1 = new Course("Testing", LocalDate.of(2024, 1, 22), 40);
        Course testData2 = new Course("Java", LocalDate.now(), 40);
        //Act
        courseDAO.save(testData1);
        courseDAO.save(testData2);

        List<Course> expected = new ArrayList<>();
        expected.add(testData2);

        //Assert
        assertTrue(courseDAO.delete(testData1));
        assertEquals(expected, courseDAO.findAll());
    }

    @Test
    @DisplayName("Should throw exception when course name is empty")
    void saveEmptyNameTest() {

        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Course("", LocalDate.now(), 10);
        });
    }

    @Test
    @DisplayName("Should throw exception when course name is null")
    void saveNullNameTest() {
        // Arrange & Act
        assertThrows(IllegalArgumentException.class, () -> {
            new Course(null, LocalDate.now(), 10);
        }, "Course name can't be null");
    }

    @Test
    @DisplayName("Should throw exception when duration is 0 or negative")
    void saveInvalidDurationTest() {

        //Assert
        assertThrows(IllegalArgumentException.class, () -> new Course("Java", LocalDate.now(), 0));
        assertThrows(IllegalArgumentException.class, () -> new Course("Java", LocalDate.now(), -5));
    }

    @Test
    @DisplayName("Should throw exception when name is null or empty")
    void findByNameNullOrEmptyTest() {
        assertThrows(IllegalArgumentException.class, () -> courseDAO.findByName(null));
        assertThrows(IllegalArgumentException.class, () -> courseDAO.findByName(""));
    }

    @Test
    @DisplayName("Should return empty list when date is null")
    void findByDateNullTest() {
        //Assert
        assertTrue(courseDAO.findByDate(null).isEmpty());
    }

    @Test
    @DisplayName("Should return null when ID is not found")
    void findByIdNotFoundTest() {
        //Assert
        assertNull(courseDAO.findById(999));
    }

    @Test
    @DisplayName("Should return false when trying to delete a non-existing course")
    void deleteNonExistingCourseTest() {
        //Arrange
        Course course = new Course("Non-existing", LocalDate.now(), 40);
        //Assert
        assertFalse(courseDAO.delete(course));
    }
}
