package se.lexicon.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Student;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class StudentDAOImplTest {
    private StudentDAOImpl studentDAO;


    //TODO Needs Checking since implementing Singleton
    @BeforeEach
    void setUp() {
        studentDAO = StudentDAOImpl.getInstance();
    }

    @Test
    @DisplayName("Test saving a student")
    void save() {
        // Arrange
        Student student = new Student("John Doe", "john@example.com", "123 Main St");

        // Act
        Student savedStudent = studentDAO.save(student);

        // Assert
        assertNotNull(savedStudent);
        assertEquals("John Doe", savedStudent.getName());
        assertEquals("john@example.com", savedStudent.getEmail());
        assertEquals("123 Main St", savedStudent.getAddress());
    }

    @Test
    @DisplayName("Test saving a null student throws IllegalArgumentException")
    void saveNullStudent() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> studentDAO.save(null));
    }

    @Test
    @DisplayName("Test finding a student by email")
    void findByEmail() {
        // Arrange
        Student student = new Student("John Doe", "john@example.com", "123 Main St");
        studentDAO.save(student);

        // Act
        Student foundStudent = studentDAO.findByEmail("john@example.com");

        // Assert
        assertNotNull(foundStudent);
        assertEquals("john@example.com", foundStudent.getEmail());
    }

    @Test
    @DisplayName("Test finding a student by email when not found")
    void findByEmailNotFound() {
        // Act
        Student foundStudent = studentDAO.findByEmail("nonexistent@example.com");

        // Assert
        assertNull(foundStudent);
    }

    @Test
    @DisplayName("Test finding a student by email with null or empty email")
    void findByEmailWithNullOrEmptyEmail() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> studentDAO.findByEmail(null));
        assertThrows(IllegalArgumentException.class, () -> studentDAO.findByEmail(""));
    }

    @Test
    @DisplayName("Test finding students by name")
    void findByName() {
        // Arrange
        Student student1 = new Student("John Doe", "john@example.com", "123 Main St");
        Student student2 = new Student("Jane Doe", "jane@example.com", "456 Main St");
        studentDAO.save(student1);
        studentDAO.save(student2);

        // Act
        List<Student> studentsByName = studentDAO.findByName("John Doe");

        // Assert
        assertEquals(3, studentsByName.size());
        assertEquals("John Doe", studentsByName.get(0).getName());
    }

    @Test
    @DisplayName("Test finding students by name when not found")
    void findByNameNotFound() {
        // Act
        List<Student> studentsByName = studentDAO.findByName("Nonexistent Name");

        // Assert
        assertTrue(studentsByName.isEmpty());
    }

    @Test
    @DisplayName("Test finding students by name with null or empty name")
    void findByNameWithNullOrEmptyName() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> studentDAO.findByName(null));
        assertThrows(IllegalArgumentException.class, () -> studentDAO.findByName(""));
    }

    @Test
    @DisplayName("Test finding a student by ID")
    void findById() {
        // Arrange
        Student student = new Student("John Doe", "john@example.com", "123 Main St");
        studentDAO.save(student);

        // Act
        Student foundStudent = studentDAO.findById(student.getId());

        // Assert
        assertNotNull(foundStudent);
        assertEquals(student.getId(), foundStudent.getId());
    }

    @Test
    @DisplayName("Test finding a student by non-existent ID")
    void findByIdNotFound() {
        // Act
        Student foundStudent = studentDAO.findById(999);

        // Assert
        assertNull(foundStudent);
    }

    @Test
    @DisplayName("Test finding all students")
    void findAll() {
        // Arrange
        Student student1 = new Student("John Doe", "john@example.com", "123 Main St");
        Student student2 = new Student("Jane Doe", "jane@example.com", "456 Main St");
        studentDAO.save(student1);
        studentDAO.save(student2);

        // Act
        List<Student> allStudents = studentDAO.findAll();

        // Assert
        assertEquals(2, allStudents.size());
    }

    @Test
    @DisplayName("Test deleting an existing student")
    void delete() {
        // Arrange
        Student student = new Student("John Doe", "john@example.com", "123 Main St");
        studentDAO.save(student);

        // Act
        boolean isDeleted = studentDAO.delete(student);

        // Assert
        assertTrue(isDeleted);
        assertNull(studentDAO.findById(student.getId()));
    }

    @Test
    @DisplayName("Test deleting a non-existing student throws IllegalArgumentException")
    void deleteNonExistingStudent() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> studentDAO.delete(null));
    }
}
