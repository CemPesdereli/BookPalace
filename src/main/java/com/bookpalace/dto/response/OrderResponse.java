package com.bookpalace.dto.response;

import com.bookpalace.model.Product;
import com.bookpalace.model.Receipt;
import com.bookpalace.model.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private LocalDateTime createDate;
    private List<Product> productList;
    private String orderCode; //-ordercode generate
    private OrderStatus orderStatus;
    private Receipt receipt;
}
