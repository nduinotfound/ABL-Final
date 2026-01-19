package com.randu.query_service.query;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional()
public class OrderReadModelService {
    
    private final OrderReadModelRepository orderRepository;
    private final OrderItemReadModelRepository itemRepository;
    
    public OrderReadModelService(OrderReadModelRepository orderRepository,
                               OrderItemReadModelRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }
    
    public List<OrderReadModel> findAllOrders() {
        List<OrderReadModel> orders = orderRepository.findAll();
        // Populate items for each order
        orders.forEach(order -> {
            List<OrderItemReadModel> items = itemRepository.findByOrderId(order.getOrderId());
            order.setItems(items);
        });
        return orders;
    }
    
    public Optional<OrderReadModel> findOrderById(String orderId) {
        Optional<OrderReadModel> orderOpt = orderRepository.findById(orderId);
        orderOpt.ifPresent(order -> {
            List<OrderItemReadModel> items = itemRepository.findByOrderId(orderId);
            order.setItems(items);
        });
        return orderOpt;
    }
    
    public List<OrderReadModel> findOrdersByCustomerId(String customerId) {
        List<OrderReadModel> orders = orderRepository.findByCustomerId(customerId);
        orders.forEach(order -> {
            List<OrderItemReadModel> items = itemRepository.findByOrderId(order.getOrderId());
            order.setItems(items);
        });
        return orders;
    }
    
    public List<OrderReadModel> findOrdersByStatus(String status) {
        List<OrderReadModel> orders = orderRepository.findByStatus(status);
        orders.forEach(order -> {
            List<OrderItemReadModel> items = itemRepository.findByOrderId(order.getOrderId());
            order.setItems(items);
        });
        return orders;
    }
}