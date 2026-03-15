package service;

import dto.OrderRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import model.Order;
import org.springframework.stereotype.Service;
import repository.OrderRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        // map orderRequest to Order object
        Order order= new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderNumber(orderRequest.orderNumber());
        order.setSkuCode(orderRequest.skuCode());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());

        //save Order using order repository
        orderRepository.save(order);
    }
}
