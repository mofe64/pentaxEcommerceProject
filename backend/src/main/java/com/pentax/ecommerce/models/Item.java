package com.pentax.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Product product;
    private int quantity =1;
    private BigDecimal itemTotal;

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
        itemTotal = product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }

    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
        itemTotal = product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }


}
