package com.ObjectDemo;

import java.io.Serializable;
import java.util.Objects;

public class Teacher implements Serializable {
    private String num;
    private String name;
    private String password;

    public Teacher(String num, String name, String password) {
        this.num = num;
        this.name = name;
        this.password = password;
    }

    public Teacher(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Teacher() {
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "num='" + num + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(num, teacher.num) &&
                Objects.equals(name, teacher.name) &&
                Objects.equals(password, teacher.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, name, password);
    }
}
