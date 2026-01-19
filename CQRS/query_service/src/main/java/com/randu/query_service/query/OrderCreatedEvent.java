package com.randu.query_service.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderCreatedEvent extends OrderEvent {
    private String customerId;
    private BigDecimal totalAmount;
    private List<OrderItem> items;
    
    // Default constructor for Jackson
    public OrderCreatedEvent() {
        super();
    }
    
    public OrderCreatedEvent(String orderId, String customerId, 
                           BigDecimal totalAmount, List<OrderItem> items) {
        this.eventId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.items = items;
        this.timestamp = LocalDateTime.now();
        this.eventType = "ORDER_CREATED";
    }
    
    // getters and setters
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
}