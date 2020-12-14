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

    @Column(name = "page_count")
    private Integer pageCount;

    @Basic
    private String isbn;

    @ManyToOne
    @JoinColumn
    private Genre genre;

    @ManyToOne
    @JoinColumn
    private Author author;

    @ManyToOne
    @JoinColumn
    private Publisher publisher;

    @Column(name = "publish_year")
    private Integer publishYear;

    @Column(name = "descr")
    private String description;
}
