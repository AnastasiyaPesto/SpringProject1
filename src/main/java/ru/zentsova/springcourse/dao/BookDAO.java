package ru.zentsova.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.zentsova.springcourse.models.Book;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year_of_publication) VALUES(?, ?, ?)",
                book.getName().trim(), book.getAuthor().trim(), book.getYearOfPublication());
    }

    public Optional<Book> findByNameAndAuthorAndYearOfPublication(String name, String author, int yearOfPublication) {
        List<Book> books = jdbcTemplate.query("SELECT * FROM Book WHERE name=? AND author=? AND (year_of_publication=? OR year_of_publication=0)",
                new Object[]{name, author, yearOfPublication}, new BeanPropertyRowMapper<>(Book.class));
        return books.stream().findAny();
    }

    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book findById(int id) {
        return jdbcTemplate
                .query("SELECT * FROM Book WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void update(int id, Book book) {
        jdbcTemplate
                .update("UPDATE Book SET name=?, author=?, year_of_publication=? WHERE book_id=?",
                        book.getName(), book.getAuthor(), book.getYearOfPublication(), book.getBookId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }
}
