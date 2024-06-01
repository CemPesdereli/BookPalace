package com.bookpalace.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book extends Product {

    private Author author;

    public Book(String name, BigDecimal amount, String description, Publisher publisher, Author author, String category) {
        super(name, amount, description, publisher,category);
        this.author = author;
    }

}
