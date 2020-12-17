package com.rkuzmych.library.controllers;

import com.rkuzmych.library.domain.Book;
import com.rkuzmych.library.domain.Genre;
import com.rkuzmych.library.domain.UserRole;
import com.rkuzmych.library.repository.BookRepository;
import com.rkuzmych.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public MainController(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }


    @RequestMapping(value={"", "/", "/index"}, method = RequestMethod.GET)
    public String getMainPage(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        Iterable<Genre> genres = genreRepository.findAll();

        model.addAttribute("genres", genres);
        model.addAttribute("books", books);

        return "index";
    }
}
