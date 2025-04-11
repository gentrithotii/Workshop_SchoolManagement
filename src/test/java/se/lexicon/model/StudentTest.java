package se.lexicon.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    @DisplayName("Check if student is created properly")
    void createStudentTest() {
        // Arrange & Act
        Student student = new Student("Gentrit", "gentrit@example.com", "Borås");

        // Assert
        assertEquals("Gentrit", student.getName());
        assertEquals("gentrit@example.com", student.getEmail());
        assertEquals("Borås", student.getAddress());
    }

    @Test
    @DisplayName("Should throw exception if name is null or empty")
    void invalidNameTest() {
        assertThrows(IllegalArgumentException.class, () -> new Student("", "test@mail.com", "Borås"));
        assertThrows(IllegalArgumentException.class, () -> new Student(null, "test@mail.com", "Borås"));
    }

    @Test
    @DisplayName("Should throw exception if email is null or empty")
    void invalidEmailTest() {
        assertThrows(IllegalArgumentException.class, () -> new Student("Gentrit", "", "Borås"));
        assertThrows(IllegalArgumentException.class, () -> new Student("Gentrit", null, "Borås"));
    }

    @Test
    @DisplayName("Check equals and hashCode based on id")
    void equalsAndHashCodeTest() {
        Student student1 = new Student("Name1", "email1@example.com", "Address1");
        Student student2 = new Student("Name2", "email2@example.com", "Address2");

        // Since IDs are auto-incremented, they shouldn't match
        assertNotEquals(student1, student2);
        assertNotEquals(student1.hashCode(), student2.hashCode());
    }
}
