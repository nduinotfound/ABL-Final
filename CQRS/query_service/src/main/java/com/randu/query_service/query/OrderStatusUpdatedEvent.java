package com.randu.query_service.query;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderStatusUpdatedEvent extends OrderEvent {
    private String status;
    private String reason;
    
    // Default constructor for Jackson
    public OrderStatusUpdatedEvent() {
        super();
    }
    
    public OrderStatusUpdatedEvent(String orderId, String status, String reason) {
        this.eventId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.status = status;
        this.reason = reason;
        this.timestamp = LocalDateTime.now();
        this.eventType = "ORDER_STATUS_UPDATED";
    }
    
    // getters and setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}