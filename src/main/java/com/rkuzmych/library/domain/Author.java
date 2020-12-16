package com.rkuzmych.library.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "author", catalog = "library")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Author {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "fio")
    private String name;

    @Basic
    private Date birthday;

    @OneToMany(mappedBy = "author")
    private List<Book> books;


    @Override
    public String toString() {
        return name;
    }
}
