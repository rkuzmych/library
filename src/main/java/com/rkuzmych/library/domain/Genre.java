package com.rkuzmych.library.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "genre", catalog = "library")
public class Genre {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Basic
    private String type;
}
