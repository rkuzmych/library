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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class BooksController {
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;
    private final AuthorRepository authorRepository;

    @Autowired
    public BooksController(GenreRepository genreRepository, BookRepository bookRepository, BookService bookService, AuthorRepository authorRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/books")
    public String createBook(Model model) {
        Iterable<Genre> genres = genreRepository.findAll();
        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "createBook";
    }

    @PostMapping("/books")
    public String saveBook(
            @RequestParam("author") String authorName,
            @RequestParam("name") String name,
            @RequestParam("genre") String genre_name,
            @RequestParam("pageCount") Integer pageCount,
            @RequestParam("publishYear") Integer publishYear,
            @RequestParam("photo") MultipartFile file
    ) throws IOException {
        Genre genre = genreRepository.findByType(genre_name);
        Author author = authorRepository.findByName(authorName);

        Book book = new Book(author, name, genre, pageCount, publishYear);
        bookService.saveFile(book, file);
        bookRepository.save(book);

        return "redirect:index";
    }
}
