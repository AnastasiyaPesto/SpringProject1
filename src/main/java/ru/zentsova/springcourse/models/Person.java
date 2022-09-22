package ru.zentsova.springcourse.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class Person {

    private int personId;
    @NotEmpty(message = "Поле \"ФИО\" не должно быть пустым")
    private String fullName;
    @Min(value = 1900, message = "Год рождения не может быть меньше 1900")
    @Max(value = 2012, message = "Минимальный возраст пользователя должен быть больше или равно 10 лет")
    private int yearOfBorn;

    public Person() {
    }

    public Person(int personId, String fullName, int yearOfBorn) {
        this.personId = personId;
        this.fullName = fullName;
        this.yearOfBorn = yearOfBorn;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBorn() {
        return yearOfBorn;
    }

    public void setYearOfBorn(int yearOfBorn) {
        this.yearOfBorn = yearOfBorn;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;

        if (otherObject == null) return false;

        if (getClass() != otherObject.getClass()) return false;

        Person other = (Person) otherObject;

        return Objects.equals(fullName, other.fullName);
    }

}