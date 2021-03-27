package com.pentax.ecommerce.services;

import com.pentax.ecommerce.dtos.OrderDTO;
import com.pentax.ecommerce.exceptions.OrderException;
import com.pentax.ecommerce.models.Address;
import org.springframework.stereotype.Service;


@Service
public interface OrderService {
    OrderDTO findOrderById(String orderId) throws OrderException;
    OrderDTO findOrderByDeliveryAddress(Address deliveryAddress) throws OrderException;
    OrderDTO updateOrderDetails(String orderId,OrderDTO updatedInformation) throws OrderException;
    void cancelOrderByOrderId(String orderId) throws OrderException;
}
