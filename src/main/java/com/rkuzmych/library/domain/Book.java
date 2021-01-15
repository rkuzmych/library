package com.rkuzmych.library.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "book", catalog = "library")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @NotBlank(message = "Please fill the name of book")
    @Length(max = 100, message = "Name too long (more than 100)")
    private String name;

    @ManyToOne
    @JoinColumn
    private Author author;

    @ManyToOne
    @JoinColumn
    private Genre genre;

    @Column(name = "page_count")
    @NotNull(message = "Please fill the pages count")
    @Range(min = 1, max = Integer.MAX_VALUE, message = "Quantity of pages can not be smaller than 1 and bigger than int")
    private Integer pageCount;

    @Column(name = "publish_year")
    @NotNull(message = "Please fill the pages count")
    @Range(min = -3000, max = 2021, message = "Do you think it is correct year?")
    private Integer publishYear;

    @Column(name = "descr")
    private String description;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "book_pdf_name")
    private String pdfName;

    public Book(Author author, String name, Genre genre, Integer pageCount, Integer publishYear) {
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.pageCount = pageCount;
        this.publishYear = publishYear;
    }

    public Book() {

    }
}
