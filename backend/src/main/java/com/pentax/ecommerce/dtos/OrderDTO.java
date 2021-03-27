package com.pentax.ecommerce.dtos;

import com.pentax.ecommerce.models.Address;
import com.pentax.ecommerce.models.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @NotBlank
    @NotNull
    private String orderID;

    @NotBlank(message = "Address field cannot be blank")
    @NotNull
    private Address deliveryAddress;

    @NotNull
    private BigDecimal orderTotal;

    public static OrderDTO packOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setDeliveryAddress(order.getDeliveryAddress());
        orderDTO.setOrderTotal(order.getOrderTotal());
        orderDTO.setOrderID(order.getOrderID());
        return orderDTO;
    }

    public static Order unpackOrderDTO(OrderDTO orderDTO){
        Order order = new Order();
        order.setDeliveryAddress(orderDTO.getDeliveryAddress());
        order.setOrderTotal(orderDTO.getOrderTotal());
        order.setOrderID(orderDTO.getOrderID());
        return order;
    }

}