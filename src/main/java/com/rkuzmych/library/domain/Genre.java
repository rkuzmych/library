package com.rkuzmych.library.domain;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "genre", catalog = "library")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Genre {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Basic
    private String type;
}
