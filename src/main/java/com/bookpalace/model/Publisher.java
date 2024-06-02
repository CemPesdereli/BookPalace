package com.bookpalace.model;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Publisher {

    private String name;
    private LocalDate createDate;
    private int id;
    //private List<Book> bookList; //model olarak bunu yapmadık.

}
