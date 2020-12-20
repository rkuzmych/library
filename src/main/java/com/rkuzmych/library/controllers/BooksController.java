package com.rkuzmych.library.controllers;

import com.google.common.collect.Lists;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    private static Iterable <Author> authors;
    private static Iterable <Genre> genres;

    private static Genre genre;
    private static Author author;

    @GetMapping("/books")
    public String createBook(Model model) {
        genres = genreRepository.findAll();
        authors = authorRepository.findAll();

        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "createBook";
    }

    @PostMapping("/books")
    public String saveBook(
            @RequestParam(required = false, name = "author") String authorName,
            @RequestParam("name") String name,
            @RequestParam(required = false, name = "genre") String genre_name,
            @RequestParam("pageCount") Integer pageCount,
            @RequestParam("publishYear") Integer publishYear,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("pdf") MultipartFile pdf,
            Model model
    ) throws IOException {
        genre = genreRepository.findByType(genre_name);
        author = authorRepository.findByName(authorName);

        boolean authorIsEmpty = author == null;
        boolean genreIsEmpty = genre == null;

        Book book = new Book(author, name, genre, pageCount, publishYear);
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
        authors = authorRepository.findAll();
        genres = genreRepository.findAll();

        Optional<Book> book = bookRepository.findById(id);
        Book currentBook = book.get();

       /* we cast Iterable to list, because we want to have current genre in head of the section, we remove duplicate*/
        List<Genre> genreList = Lists.newArrayList(genres);
        List<Author> authorList = Lists.newArrayList(authors);

        genreList.remove(currentBook.getGenre());
        authorList.remove(currentBook.getAuthor());

        model.addAttribute("isEditorForm", true);
        model.addAttribute("book", currentBook);
        model.addAttribute("genres", genreList);
        model.addAttribute("authors", authorList);
        return "createBook";
    }

    @PostMapping("/edit/{id}")
    public String saveEditedBook(
            @PathVariable Long id,
            @RequestParam("id") Book book,
            @RequestParam("name") String name,
            @RequestParam("pageCount") Integer pageCount,
            @RequestParam("publishYear") Integer publishYear,
            @RequestParam("author") String authorName,
            @RequestParam("genre") String genre_name,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("pdf") MultipartFile pdf,
            Model model
    ) throws IOException {
        bookService.getAuthorAndGenreByNames(book, author, genre, authorName, genre_name);
        bookService.setAuthorAndGenre(book, author, genre);

        bookService.setNameAndCountAndYear(book, name, pageCount, publishYear);

        bookService.saveFile(book, photo, "photo");
        bookService.saveFile(book, pdf, "pdf");

        bookRepository.save(book);
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
