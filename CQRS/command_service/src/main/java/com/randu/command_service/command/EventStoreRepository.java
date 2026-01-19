package com.randu.command_service.command;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStoreRepository extends JpaRepository<EventStore, String> {
    List<EventStore> findByOrderIdOrderByTimestamp(String orderId);
    List<EventStore> findByEventType(String eventType);
}