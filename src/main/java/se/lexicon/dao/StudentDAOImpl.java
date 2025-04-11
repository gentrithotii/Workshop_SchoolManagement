package se.lexicon.dao;

import se.lexicon.dao.interfaces.IStudentDAO;
import se.lexicon.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    private final List<Student> students;

    public StudentDAOImpl() {
        this.students = findAll() == null ? new ArrayList<>() : findAll();
    }

    @Override
    public Student save(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cant be null");
        }
        findAll().add(student);
        return student;
    }

    @Override
    public Student findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email can't be null or empty");
        }

        for (Student studentItem : findAll()) {
            if (studentItem.getEmail().equalsIgnoreCase(email)) {
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
            if (studentItem.getName().equalsIgnoreCase(name)) {
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
        return students;
    }

    @Override
    public boolean delete(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Can't delete a non existing student");
        }
        return findAll().remove(student);
    }
}
