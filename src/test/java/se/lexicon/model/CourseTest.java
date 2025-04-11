package se.lexicon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    private Course course;
    private Student student;

    @BeforeEach
    void setUp() {
        course = new Course("Java Programming", LocalDate.of(2025, 4, 1), 10);
        student = new Student("Gentrit", "gentrit@example.com", "Borås");
    }

    @Test
    @DisplayName("Check if course is created properly")
    void createCourseTest() {
        assertEquals("Java Programming", course.getCourseName());
        assertEquals(LocalDate.of(2025, 4, 1), course.getStartDate());
        assertEquals(10, course.getWeekDuration());
        assertNotNull(course.getStudents());
        assertTrue(course.getStudents().isEmpty());
    }

    @Test
    @DisplayName("Check if student is registered")
    void registerStudentTest() {
        // Act
        course.register(student);

        // Assert
        assertEquals(1, course.getStudents().size());
        assertTrue(course.getStudents().contains(student));
    }

    @Test
    @DisplayName("Check if student is unregistered")
    void unregisterStudentTest() {
        // Arrange
        course.register(student);

        // Act
        course.unregister(student);

        // Assert
        assertFalse(course.getStudents().contains(student));
    }

    @Test
    @DisplayName("Should throw exception when course name is empty or null")
    void invalidCourseNameTest() {
        assertThrows(IllegalArgumentException.class, () -> new Course("", LocalDate.now(), 5));
        assertThrows(IllegalArgumentException.class, () -> new Course(null, LocalDate.now(), 5));
    }

    @Test
    @DisplayName("Should throw exception when startDate is null")
    void invalidStartDateTest() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java", null, 5));
    }

    @Test
    @DisplayName("Should throw exception when weekDuration is zero or negative")
    void invalidWeekDurationTest() {
        assertThrows(IllegalArgumentException.class, () -> new Course("Java", LocalDate.now(), 0));
        assertThrows(IllegalArgumentException.class, () -> new Course("Java", LocalDate.now(), -2));
    }
}
