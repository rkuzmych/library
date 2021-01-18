package com.rkuzmych.library.service;

import com.google.common.collect.Lists;
import com.rkuzmych.library.controller.UtilsController;
import com.rkuzmych.library.domain.Author;
import com.rkuzmych.library.domain.Book;
import com.rkuzmych.library.domain.Genre;
import com.rkuzmych.library.repository.AuthorRepository;
import com.rkuzmych.library.repository.BookRepository;
import com.rkuzmych.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    @Value("${upload.path}")
    private String uploadPath;

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    private static Iterable<Author> authors;
    private static Iterable<Genre> genres;

    @Autowired
    public BookService(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    private boolean isFileExist(MultipartFile file) {
        return file != null && !file.getOriginalFilename().isEmpty();
    }

    public void saveFile(Book book, MultipartFile file, String key) throws IOException {
        if (isFileExist(file)) {
            File uploadDir = new File(uploadPath);

            File imgDir = new File(uploadPath + "/img");
            File pdfDir = new File(uploadPath + "/pdf");

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            if (!imgDir.exists()) {
                imgDir.mkdir();
            }
            if (!pdfDir.exists()) {
                pdfDir.mkdir();
            }



            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            if (key == "photo") {
                file.transferTo(new File(uploadPath + "/img/" + resultFilename));
                book.setFileName(resultFilename);
            } else if (key == "pdf") {
                file.transferTo(new File(uploadPath + "/pdf/" + resultFilename));
                book.setPdfName(resultFilename);
            }
        }
    }

    public Book saveBook(Book book, String name, Integer pageCount, Integer publishYear) {
        book.setName(name);
        book.setPageCount(pageCount);
        book.setPublishYear(publishYear);
        bookRepository.save(book);
        return book;
    }

    public void setAuthorAndGenre(Book book, Author author, Genre genre) {
        book.setAuthor(author);
        book.setGenre(genre);
    }

    public Page<Book> getBooks(String filter, Genre genre, Author author, Pageable pageable) {
        Page<Book> books;
        if (filter != null && !filter.isEmpty()) {
            books = bookRepository.findByName(filter, pageable);
        } else if (genre != null && author != null) {
            books = bookRepository.findByGenreAndAuthor(genre, author, pageable);
        } else if (genre != null) {
            books = bookRepository.findByGenre(genre, pageable);
        } else if (author != null) {
            books = bookRepository.findByAuthor(author, pageable);
        } else {
            books = bookRepository.findAll(pageable);
        }
        return books;
    }


    public boolean validateBook(Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = UtilsController.getErrors(bindingResult);
            model.addAllAttributes(errors);
            return false;
        }
        return true;
    }

    public boolean validateNewBook(Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = UtilsController.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("book", book);

            authors = authorRepository.findAll();
            genres = genreRepository.findAll();

            /* we cast Iterable to list, because we want to have current genre in head of the section, we remove duplicate*/
            List<Genre> genreList = Lists.newArrayList(genres);
            List<Author> authorList = Lists.newArrayList(authors);

            removeDuplicatesFromLists(book, authorList, genreList);

            fillModelInBookEditor(book, model, genreList, authorList, false);
            return false;
        }
        return true;
    }

    public void updateBook(Long id, Model model) {
        Optional<Book> book = bookRepository.findById(id);
        Book currentBook = book.get();

        authors = authorRepository.findAll();
        genres = genreRepository.findAll();

        /* we cast Iterable to list, because we want to have current genre in head of the section, we remove duplicate*/
        List<Genre> genreList = Lists.newArrayList(genres);
        List<Author> authorList = Lists.newArrayList(authors);

        removeDuplicatesFromLists(currentBook, authorList, genreList);

        fillModelInBookEditor(currentBook, model, genreList, authorList, true);
    }

    private void removeDuplicatesFromLists(Book book, List<Author> authorList, List<Genre> genreList) {
        genreList.remove(book.getGenre());
        authorList.remove(book.getAuthor());
    }

    private void fillModelInBookEditor(Book book, Model model, List<Genre> genreList, List<Author> authorList, boolean isEditForm) {
        model.addAttribute("book", book);
        model.addAttribute("genres", genreList);
        model.addAttribute("authors", authorList);
        model.addAttribute("genreId", book.getGenre().getId());
        model.addAttribute("authorId", book.getAuthor().getId());
        if (isEditForm) {
            model.addAttribute("isEditorForm", true);
        }
    }

    public void genreAndAuthorPagination(String genreType, String authorName, Model model) {
        if (!StringUtils.isEmpty(genreType) && genreType != null) {
            model.addAttribute("genreType", genreType);
        }

        if (!StringUtils.isEmpty(authorName) && authorName != null) {
            model.addAttribute("authorName", authorName);
        }
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
