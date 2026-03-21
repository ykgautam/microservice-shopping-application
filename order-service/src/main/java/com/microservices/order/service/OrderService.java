package com.microservices.order.service;

import com.microservices.order.client.InventoryClient;
import com.microservices.order.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import com.microservices.order.model.Order;
import org.springframework.stereotype.Service;
import com.microservices.order.repository.OrderRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {

        boolean isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (isProductInStock) {
            // map orderRequest to Order object
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderNumber(orderRequest.orderNumber());
            order.setSkuCode(orderRequest.skuCode());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());

            //save Order using order repository
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Product with skuCode: " + orderRequest.skuCode() + " is not present");
        }
    }
}
