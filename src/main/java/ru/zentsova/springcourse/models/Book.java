package ru.zentsova.springcourse.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Book {

    private int bookId;
    @NotEmpty(message = "Поле \"Название книги\" не должно быть пустым")
    private String title;
    @NotEmpty(message = "Поле \"Автор\" не должно быть пустым")
    private String author;
    @Min(value = 1600, message = "Поле \"Год публикации\" не должно меньше, чем 1600 ")
    @Max(value = 2022, message = "Поле \"Год публикации\" не должно больше, чем 2022")
    private int yearOfPublication;
    private int isAssigned;

    public Book() {
    }

    public Book(int bookId, String title, String author, int yearOfPublication, int isAssigned) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.isAssigned = isAssigned;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
