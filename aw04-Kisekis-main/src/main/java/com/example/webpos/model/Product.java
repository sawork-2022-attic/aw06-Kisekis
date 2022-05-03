package com.example.webpos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private String id;

    private String name;

    private double price;

    private String image;

    private String main_cat;

    private String title;

    private List<String> category;

    private List<String> imageURLHighRes;
}
