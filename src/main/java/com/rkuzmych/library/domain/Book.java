package com.rkuzmych.library.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book", catalog = "library")
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Basic
    private String name;

    @ManyToOne
    @JoinColumn
    private Author author;

    @ManyToOne
    @JoinColumn
    private Genre genre;

    @Column(name = "page_count")
    private Integer pageCount;


    @Column(name = "publish_year")
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
