package se.lexicon.dao.interfaces;

import se.lexicon.model.Course;
import se.lexicon.model.Student;

import java.util.List;

public interface IStudentDAO {
    Student save(Student student);

    Student findByEmail(String email);

    List<Student> findByName(String name);

    Student findById(int id);

    List<Student> findAll();

    Student updateStudent(Student student);

    boolean delete(Student student);
}
