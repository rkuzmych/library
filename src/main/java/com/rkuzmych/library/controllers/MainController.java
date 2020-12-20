package com.rkuzmych.library.controllers;

import com.rkuzmych.library.Service.BookService;
import com.rkuzmych.library.domain.Author;
import com.rkuzmych.library.domain.Book;
import com.rkuzmych.library.domain.Genre;
import com.rkuzmych.library.repository.AuthorRepository;
import com.rkuzmych.library.repository.BookRepository;
import com.rkuzmych.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final BookService bookService;

    @Autowired
    public MainController(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.bookService = bookService;
    }


    private static Iterable<Genre> genres;
    private static Iterable<Author> authors;

    @RequestMapping(value={"", "/", "/index"}, method = RequestMethod.GET)
    public String getMainPage(
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(required = false) String genreType,
            @RequestParam(required = false) String authorName,
            Model model
    ) {
        genres = genreRepository.findAll();
        authors = authorRepository.findAll();

        Genre genre = genreRepository.findByType(genreType);
        Author author = authorRepository.findByName(authorName);

        Iterable<Book> books = bookService.getBooks(filter, genre, author);

        model.addAttribute("books", books);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        model.addAttribute("filter", filter);

        return "index";
    }


}
