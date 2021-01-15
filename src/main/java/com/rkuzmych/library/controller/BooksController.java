package com.rkuzmych.library.controller;

import com.rkuzmych.library.domain.Author;
import com.rkuzmych.library.domain.Book;
import com.rkuzmych.library.domain.Genre;
import com.rkuzmych.library.repository.AuthorRepository;
import com.rkuzmych.library.repository.BookRepository;
import com.rkuzmych.library.repository.GenreRepository;
import com.rkuzmych.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
public class BooksController {
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;
    private final AuthorRepository authorRepository;

    private static Iterable<Author> authors;
    private static Iterable<Genre> genres;

    private static Genre genre;
    private static Author author;

    @Autowired
    public BooksController(GenreRepository genreRepository, BookRepository bookRepository, BookService bookService, AuthorRepository authorRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.authorRepository = authorRepository;
    }


    @GetMapping("/books")
    public String createBook(Model model) {
        genres = genreRepository.findAll();
        authors = authorRepository.findAll();

        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "createBook";
    }


    /*@PostMapping("/books")
    public String createBook(
            @Valid Book book,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("pdf") MultipartFile pdf,
            BindingResult bindingResult,
            Model model
    ) throws IOException {

        boolean validationSuccessful = bookService.validateBook(book, bindingResult, model);
        if (validationSuccessful) {
            bookService.saveFile(book, photo, "photo");
            bookService.saveFile(book, pdf, "pdf");

            bookRepository.save(book);
        } else {
            model.addAttribute("book", book);
            return "createBook";
        }


        return "redirect:index";
    }*/

    @PostMapping("/books")
    public String add(
            @Valid Book book,
            BindingResult bindingResult,
            Model model,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("pdf") MultipartFile pdf
    ) throws IOException {

        boolean validateNewBook = bookService.validateNewBook(book, bindingResult, model);
        if (!validateNewBook) {
            return "createBook";
        }

        bookService.saveFile(book, photo, "photo");
        bookService.saveFile(book, pdf, "pdf");

        bookRepository.save(book);

        return "redirect:index";
    }


    @GetMapping("/edit/{id}")
    public String update(
            @PathVariable Long id,
            Model model
    ) {
        bookService.updateBook(id, model);
        return "createBook";
    }

    @PostMapping("/edit/{id}")
    public String saveEditedBook(
            @RequestParam("id") Book currentBook,
            @Valid Book book,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("pdf") MultipartFile pdf
    ) throws IOException {
        bookService.saveFile(currentBook, photo, "photo");
        bookService.saveFile(currentBook, pdf, "pdf");

        bookService.saveBook(currentBook, book.getName(), book.getPageCount(), book.getPublishYear());
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(
            @PathVariable Long id
    ) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        bookRepository.delete(optionalBook.get());
        return "redirect:/";
    }
}
