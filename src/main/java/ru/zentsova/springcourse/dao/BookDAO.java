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
        jdbcTemplate.update("INSERT INTO Book(title, author, year_of_publication) VALUES(?, ?, ?)",
                book.getTitle().trim(), book.getAuthor().trim(), book.getYearOfPublication());
    }

    public Optional<Book> findByNameAndAuthorAndYearOfPublication(String title, String author, int yearOfPublication) {
        List<Book> books = jdbcTemplate.query("SELECT * FROM Book WHERE title=? AND author=? AND (year_of_publication=? OR year_of_publication=0)",
                new Object[] {title, author, yearOfPublication}, new BeanPropertyRowMapper<>(Book.class));
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
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year_of_publication=? WHERE book_id=?",
                        book.getTitle(), book.getAuthor(), book.getYearOfPublication(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }
}
