package com.bookpalace.model;

import com.bookpalace.model.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    private LocalDateTime createDate;
    private List<Product> productList;
    private String orderCode; //-ordercode generate
    private OrderStatus orderStatus;

}
