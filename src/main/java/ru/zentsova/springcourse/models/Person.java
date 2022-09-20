package ru.zentsova.springcourse.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Person {

    private int personId;
    @NotEmpty(message = "Поле \"ФИО\" не должно быть пустым")
    private String fio;
    @Min(value = 1900, message = "Год рождения не может быть меньше 1900")
    @Max(value = 2012, message = "Минимальный возраст пользователя должен быть больше или равно 10 лет")
    private int yearOfBorn;

    public Person() {
    }

    public Person(int personId, String fio, int yearOfBorn) {
        this.personId = personId;
        this.fio = fio;
        this.yearOfBorn = yearOfBorn;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getYearOfBorn() {
        return yearOfBorn;
    }

    public void setYearOfBorn(int yearOfBorn) {
        this.yearOfBorn = yearOfBorn;
    }

}