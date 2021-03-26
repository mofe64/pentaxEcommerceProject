package com.pentax.ecommerce.repository;

import com.pentax.ecommerce.models.Address;
import com.pentax.ecommerce.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
     Optional<Order> findOrderByOrderID(String orderID);
    void deleteOrderByOrderID(String orderId);
    List<Order> findOrdersByUserId(String userid);
    Optional<Order> findOrderByDeliveryAddress (Address deliveryAddress);
}
