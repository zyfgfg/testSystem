package com.ObjectDemo;

import javax.swing.*;
import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {
    private int Id;
    private String name;
    private String password;
    private int scores;


    public Student() {
    }

    public Student(int id, String name, String password, int scores) {
        Id = id;
        this.name = name;
        this.password = password;
        this.scores = scores;
    }

    public Student(String name, String password) {
        this.name = name;
        this.password = password;
    }
public Student(String name, int scores) {
        this.name = name;
        this.scores = scores;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(int id, String name, String password) {
        Id = id;
        this.name = name;
        this.password = password;
    }

    public Student(int id, String name, int scores) {
        Id = id;
        this.name = name;
        this.scores = scores;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", scores=" + scores +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return scores == student.scores &&
                Objects.equals(Id, student.Id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(password, student.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, password, scores);
    }
}
