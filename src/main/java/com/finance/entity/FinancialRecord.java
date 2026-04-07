package com.finance.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "financial_records")
public class FinancialRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Double amount;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;
    
    @Column(nullable = false)
    private String category;
    
    @Column(nullable = false)
    private LocalDate date;
    
    private String description;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
    
    // Constructors
    public FinancialRecord() {}
    
    public FinancialRecord(Double amount, TransactionType type, String category, 
                          LocalDate date, String description, User user) {
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
        this.description = description;
        this.user = user;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public TransactionType getType() {
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
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public User getUser() {
        return user;
    }
    
    // Setters
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public void setType(TransactionType type) {
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
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Enum
    public enum TransactionType {
        INCOME, EXPENSE
    }
}