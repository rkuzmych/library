package com.rkuzmych.library.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "genre", catalog = "library")
public class Genre {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String type;
}
