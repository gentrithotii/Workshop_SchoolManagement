package se.lexicon.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    private static int sequencer = 1;
    private final int id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private final List<Student> students;

    public Course(String courseName, LocalDate startDate, int weekDuration) {
        this.id = getNextId();
        setCourseName(courseName);
        setStartDate(startDate);
        setWeekDuration(weekDuration);
        this.students = getStudents() == null ? new ArrayList<>() : getStudents();
    }

    private int getNextId() {
        return sequencer++;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        if (courseName == null || courseName.isEmpty()) {
            throw new IllegalArgumentException("Username can't be null");
        }
        this.courseName = courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("Username can't be null");
        }
        this.startDate = startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(int weekDuration) {
        this.weekDuration = weekDuration;
    }

    public int getId() {
        return id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void register(Student student) {
        getStudents().add(student);
        System.out.println("Student added");
    }

    public void unregister(Student student) {
        getStudents().remove(student);
        System.out.println("Student unregistered");
    }

//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        Course course = (Course) o;
//        return getId() == course.getId();
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hashCode(getId());
//    }

    @Override
    public String toString() {
        return "Course{" + "id=" + getId() + ", courseName='" + getCourseName() + '\'' + ", startDate=" + getStartDate() + ", weekDuration=" + getWeekDuration() + ", students=" + getStudents() + '}';
    }
}


