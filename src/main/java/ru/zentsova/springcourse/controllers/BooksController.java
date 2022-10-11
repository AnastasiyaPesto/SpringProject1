package ru.zentsova.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zentsova.springcourse.models.Book;
import ru.zentsova.springcourse.models.Person;
import ru.zentsova.springcourse.services.BooksService;
import ru.zentsova.springcourse.services.PeopleService;
import ru.zentsova.springcourse.utils.BookValidator;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;
    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService, BookValidator bookValidator) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookValidator = bookValidator;
    }

    @GetMapping
    public String index(@RequestParam Map<String, String> allParameters, Model model) {
        model.addAttribute("books", booksService.findAllPaginationAndSort(allParameters));
        return "books/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        Book foundBook = booksService.findOneById(id);
        model.addAttribute("book", foundBook);

        Person owner = Objects.nonNull(foundBook) ? foundBook.getOwner() : null;
        if (Objects.nonNull(owner))
            model.addAttribute("owner", owner);
        else
            model.addAttribute("people", peopleService.findAll());

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findOneById(id));
        return "books/edit";
    }

    @PostMapping
    public String addNewBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult)
    {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book updatedBook, BindingResult bindingResult, @PathVariable("id") int id) {
        bookValidator.validate(updatedBook, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";
        booksService.update(id, updatedBook);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        booksService.assign(id, person);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        booksService.release(id);
        return "redirect:/books/" + id;
    }

    @GetMapping(value = "/search")
    public String search() {
        return "books/search";
    }

    @PostMapping(value = "/search")
    public String resetPassword(@RequestParam(value = "title", required = false) String title, Model model) {
        model.addAttribute("books", booksService.findByTitleStartingWith(title));
        return "books/search";
    }

}
