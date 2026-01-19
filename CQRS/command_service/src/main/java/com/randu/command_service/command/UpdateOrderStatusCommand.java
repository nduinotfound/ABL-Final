package com.randu.command_service.command;

public class UpdateOrderStatusCommand {
    private String status;
    private String reason;
    public UpdateOrderStatusCommand() {
    }
    public UpdateOrderStatusCommand(String status, String reason) {
        this.status = status;
        this.reason = reason;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    // getters and setters
    
}