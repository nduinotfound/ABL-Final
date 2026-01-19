package com.randu.command_service.command;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "event_store")
public class EventStore {
    @Id
    private String eventId;
    
    private String orderId;
    private String eventType;
    private LocalDateTime timestamp;
    
    @Column(columnDefinition = "CLOB")
    private String eventData;
    
    private String aggregateType = "ORDER";

    public EventStore() {
    }

    public EventStore(String eventId, String orderId, String eventType, LocalDateTime timestamp, String eventData,
            String aggregateType) {
        this.eventId = eventId;
        this.orderId = orderId;
        this.eventType = eventType;
        this.timestamp = timestamp;
        this.eventData = eventData;
        this.aggregateType = aggregateType;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public void setAggregateType(String aggregateType) {
        this.aggregateType = aggregateType;
    }
    
    // constructor, getters, setters
    
}