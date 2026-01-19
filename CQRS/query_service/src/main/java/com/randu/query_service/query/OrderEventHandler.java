package com.randu.query_service.query;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderEventHandler {
    
    private final OrderReadModelRepository orderRepository;
    private final OrderItemReadModelRepository itemRepository;
    private final ObjectMapper objectMapper;
    
    public OrderEventHandler(OrderReadModelRepository orderRepository,
                           OrderItemReadModelRepository itemRepository,
                           ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.objectMapper = objectMapper;
    }
    
    @RabbitListener(queues = "order-events-queue")
    public void handleOrderEvent(String eventMessage) {
        try {
            JsonNode jsonNode = objectMapper.readTree(eventMessage);
            String eventType = jsonNode.get("eventType").asText();
            
            System.out.println("Received event type: " + eventType);
            
            switch (eventType) {
                case "ORDER_CREATED":
                    OrderCreatedEvent createdEvent = objectMapper.readValue(eventMessage, OrderCreatedEvent.class);
                    handleOrderCreated(createdEvent);
                    break;
                case "ORDER_STATUS_UPDATED":
                    OrderStatusUpdatedEvent updatedEvent = objectMapper.readValue(eventMessage, OrderStatusUpdatedEvent.class);
                    handleOrderStatusUpdated(updatedEvent);
                    break;
                default:
                    System.out.println("Unknown event type: " + eventType);
            }
        } catch (Exception e) {
            System.err.println("Error processing event: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void handleOrderCreated(OrderCreatedEvent event) {
        // Implementasi handle order created
        OrderReadModel order = new OrderReadModel(
            event.getOrderId(),
            event.getCustomerId(),
            "CREATED",
            event.getTotalAmount(),
            event.getTimestamp(),
            event.getTimestamp(), null
        );
        orderRepository.save(order);
        
        if (event.getItems() != null) {
            List<OrderItemReadModel> items = event.getItems().stream()
                .map(item -> new OrderItemReadModel(
                    null, event.getOrderId(),
                    item.getProductId(),
                    item.getProductName(),
                    item.getQuantity(),
                    item.getPrice()
                ))
                .collect(Collectors.toList());
            
            itemRepository.saveAll(items);
        }
        
        System.out.println("Order created in read model: " + event.getOrderId());
    }
    
    private void handleOrderStatusUpdated(OrderStatusUpdatedEvent event) {
        // Implementasi handle status update
        Optional<OrderReadModel> orderOpt = orderRepository.findById(event.getOrderId());
        if (orderOpt.isPresent()) {
            OrderReadModel order = orderOpt.get();
            order.setStatus(event.getStatus());
            order.setUpdatedAt(event.getTimestamp());
            orderRepository.save(order);
            System.out.println("Order status updated: " + event.getOrderId() + " -> " + event.getStatus());
        }
    }
}