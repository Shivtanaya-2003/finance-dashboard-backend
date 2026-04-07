package com.finance.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class FinancialRecordRequest {
    
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;
    
    @NotNull(message = "Type is required")
    private String type;
    
    @NotBlank(message = "Category is required")
    private String category;
    
    @NotNull(message = "Date is required")
    private LocalDate date;
    
    private String description;
    
    // Getters (These were missing!)
    public Double getAmount() {
        return amount;
    }
    
    public String getType() {
        return type;
    }
    
    public String getCategory() {
        return category;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public String getDescription() {
        return description;
    }
    
    // Setters
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}