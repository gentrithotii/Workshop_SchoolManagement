package se.lexicon.model;

import java.util.Objects;

public class Student {
    private static int sequencer;
    private final int id;
    private String name;
    private String email;
    private String address;

    public Student(String name, String email, String address) {
        this.id = getNextId();
        setName(name);
        setEmail(email);
        setAddress(address);
    }


    private int getNextId() {
        return sequencer++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getId() == student.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", address='" + address + '\'' + '}';
    }
}
