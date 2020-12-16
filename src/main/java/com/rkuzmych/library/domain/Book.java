package com.rkuzmych.library.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book", catalog = "library")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
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
