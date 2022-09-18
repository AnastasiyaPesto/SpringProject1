package ru.zentsova.springcourse.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Book {

    private int bookId;
    @NotEmpty(message = "Поле \"Название книги\" не должен быть пустым")
    private String name;
    //@Pattern(regexp = "[А-Я]+ [А-Я]+]", message = "Формат ввода автора \"Имя Фамилия\"")
    @NotEmpty(message = "Поле \"Автор\" не должен быть пустым")
    private String author;
    @Min(value = 0, message = "Поле \"Год публикации\" не должно быть отрицательным")
    @Max(value = 2022, message = "Поле \"Год публикации\" не должно больше, чем 2022")
    private int yearOfPublication;

    public Book() {
    }

    public Book(int bookId, String name, String author, int yearOfPublication) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

}
