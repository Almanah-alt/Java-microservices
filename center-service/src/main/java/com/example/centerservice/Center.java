package com.example.centerservice;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;

}
