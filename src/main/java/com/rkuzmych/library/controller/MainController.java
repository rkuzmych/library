package com.rkuzmych.library.controller;

import com.rkuzmych.library.domain.Author;
import com.rkuzmych.library.domain.Book;
import com.rkuzmych.library.domain.Genre;
import com.rkuzmych.library.repository.AuthorRepository;
import com.rkuzmych.library.repository.BookRepository;
import com.rkuzmych.library.repository.GenreRepository;
import com.rkuzmych.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    private Iterable<Genre> genres;
    private Iterable<Author> authors;

    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String getMainPage(
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(required = false) String genreType,
            @RequestParam(required = false) String authorName,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 6) Pageable pageable,
            Model model
    ) {
        genres = genreRepository.findAll();
        authors = authorRepository.findAll();

        Genre genre = genreRepository.findByType(genreType);
        Author author = authorRepository.findByName(authorName);

        Page<Book> page = bookService.getBooks(filter, genre, author, pageable);
        bookService.filterPagination(genreType, authorName, model);

        model.addAttribute("page", page);
        model.addAttribute("url", "/index");

        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        model.addAttribute("filter", filter);

        model.addAttribute("isMainPage", true);
        return "index";
    }

}
