package ru.zentsova.springcourse.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.zentsova.springcourse.models.Person;
import ru.zentsova.springcourse.services.PeopleService;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        Optional<Person> personFromDB = peopleService.getPersonByFullName(person.getFullName().trim());
        if (personFromDB.isPresent() && person.getId() != personFromDB.get().getId())
            errors.rejectValue("fullName", "", "Такой человек уже существует");
    }

}