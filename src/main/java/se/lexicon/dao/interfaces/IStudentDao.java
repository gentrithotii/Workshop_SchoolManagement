package se.lexicon.dao.interfaces;

import se.lexicon.model.Student;

import java.util.List;

public interface IStudentDao {
    public Student save(Student student);

    public Student findByEmail(String email);

    public List<Student> findByName(String name);

    public Student findById(int id);

    public List<Student> findAll();

    public boolean delete(Student student);
}
