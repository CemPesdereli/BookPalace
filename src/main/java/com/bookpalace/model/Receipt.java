package com.bookpalace.model;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Receipt {
    private LocalDateTime createDateTime;
    private double total;

}
