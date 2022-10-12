package ru.zentsova.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zentsova.springcourse.models.Book;
import ru.zentsova.springcourse.models.Person;
import ru.zentsova.springcourse.repositories.BooksRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    public static final String S_BOOKS_PER_PAGE = "books_per_page";
    public static final String S_YEAR_OF_PUBLICATION = "yearOfPublication";
    public static final String S_PAGE = "page";
    public static final String S_SORT_BY_YEAR = "sort_by_year";

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public List<Book> findAllPaginationAndSort(Map<String, String> allRequestParameters) {
        boolean isPagination = allRequestParameters.containsKey(S_PAGE) && allRequestParameters.containsKey(S_BOOKS_PER_PAGE);
        boolean isSortByYear = allRequestParameters.containsKey(S_SORT_BY_YEAR) && Boolean.parseBoolean(allRequestParameters.get(S_SORT_BY_YEAR));
        List<Book> result;
        if (isPagination && isSortByYear) {
            result = booksRepository.findAll(PageRequest.of(
                            Integer.parseInt(allRequestParameters.get(S_PAGE)),
                            Integer.parseInt(allRequestParameters.get(S_BOOKS_PER_PAGE)),
                            Sort.by(S_YEAR_OF_PUBLICATION)))
                    .getContent();
        } else if (isPagination) {
            result = booksRepository.findAll(PageRequest.of(
                            Integer.parseInt(allRequestParameters.get(S_PAGE)),
                            Integer.parseInt(allRequestParameters.get(S_BOOKS_PER_PAGE))))
                    .getContent();
        } else if (isSortByYear) {
            result =  booksRepository.findAll(Sort.by(S_YEAR_OF_PUBLICATION));
        } else {
            result = booksRepository.findAll();
        }
        return result;
    }

    public Book findOneById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public List<Book> findByTitleStartingWith(String titleStartingWith) {
        return booksRepository.findByTitleStartingWith(titleStartingWith);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int idBookToUpdate, Book updatedBook) {
        updatedBook.setId(idBookToUpdate);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void assign(int id, Person owner) {
        Optional<Book> foundBook = booksRepository.findById(id);
        foundBook.ifPresent(book -> {
            book.setOwner(owner);
            book.setTimeTaken(new Date());
        });
    }

    @Transactional
    public void release(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        foundBook.ifPresent(book -> {
            book.setOwner(null);
            book.setTimeTaken(null);
        });
    }

}
