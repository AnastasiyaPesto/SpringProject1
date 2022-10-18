package ru.zentsova.springcourse.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.zentsova.springcourse.models.Book;
import ru.zentsova.springcourse.services.BooksService;

@Component
public class BookValidator implements Validator {

    private final BooksService booksService;

    @Autowired
    public BookValidator(BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;

//        if (bookDAO.findByNameAndAuthorAndYearOfPublication(book.getTitle(), book.getAuthor(), book.getYearOfPublication()).isPresent())
//            errors.rejectValue("title", "", "Такая книга уже есть в библиотеке");
    }

}
