package se.lexicon.dao;

import se.lexicon.dao.interfaces.IStudentDao;
import se.lexicon.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentDaoImpl implements IStudentDao {
    private List<Student> students;

    @Override
    public Student save(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cant be null");
        }
        return student;
    }

    @Override
    public Student findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email can't be null or empty");
        }

        for (Student studentItem : findAll()) {
            if (studentItem.getEmail().equals(email)) {
                return studentItem;
            }
        }
        return null;
    }

    @Override
    public List<Student> findByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }

        List<Student> studentListByName = new ArrayList<>();
        for (Student studentItem : findAll()) {
            if (studentItem.getName().equals(name)) {
                studentListByName.add(studentItem);
            }
        }
        return studentListByName;
    }

    @Override
    public Student findById(int id) {
        for (Student studentItem : findAll()) {
            if (studentItem.getId() == id) {
                return studentItem;
            }
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        return this.students;
    }

    @Override
    public boolean delete(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Can't delete a non existing student");
        }
        return findAll().remove(student);
    }
}
