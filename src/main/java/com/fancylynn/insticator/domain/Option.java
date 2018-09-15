package com.fancylynn.insticator.domain;

import javax.persistence.*;

/**
 * Created by Lynn on 2018/9/15.
 */
@Entity
public class Option {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="option_id", nullable = false, updatable = false)
    private Long option_id;

    private String option_content;


}
