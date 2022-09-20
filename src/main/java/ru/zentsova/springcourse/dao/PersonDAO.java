package ru.zentsova.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.zentsova.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fio, year_of_born) VALUES(?, ?)", person.getFio().trim(), person.getYearOfBorn());
    }

    public Person findById(int id) {
        return jdbcTemplate
                .query("SELECT * FROM person WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET fio=?, year_of_born=? WHERE person_id=?",
                person.getFio(), person.getYearOfBorn(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }

    public Optional<Person> findByFio(String fio) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE fio=?", new Object[] {fio}, new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny();
    }

}