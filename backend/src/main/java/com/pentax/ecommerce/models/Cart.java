package com.pentax.ecommerce.models;

import com.pentax.ecommerce.exceptions.CartException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    private String id;
    private Map<String, Item> items = new HashMap<>();
    private BigDecimal total;


    public void addItem(Item item, int quantity){
        String productId = item.getProduct().getId();
        if(items.containsKey(productId)){
            items.get(productId).increaseQuantity(quantity);
        } else {
            items.put(productId, item);
        }
    }

    public void removeItem(String productId) {
        items.remove(productId);
    }
    public void removeItem(String productId, int quantity) throws CartException {
        if(items.containsKey(productId)){
            items.get(productId).decreaseQuantity(quantity);
        } else {
            throw new CartException("Item not in cart");
        }
    }
}
