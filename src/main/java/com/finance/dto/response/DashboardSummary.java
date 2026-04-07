package com.finance.dto.response;

import java.util.Map;
import java.util.List;

public class DashboardSummary {
    private Double totalIncome;
    private Double totalExpenses;
    private Double netBalance;
    private Map<String, Double> categoryTotals;
    private List<FinancialRecordDto> recentTransactions;
    private List<MonthlyTrend> monthlyTrends;
    
    // Getters and Setters
    public Double getTotalIncome() {
        return totalIncome;
    }
    
    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }
    
    public Double getTotalExpenses() {
        return totalExpenses;
    }
    
    public void setTotalExpenses(Double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
    
    public Double getNetBalance() {
        return netBalance;
    }
    
    public void setNetBalance(Double netBalance) {
        this.netBalance = netBalance;
    }
    
    public Map<String, Double> getCategoryTotals() {
        return categoryTotals;
    }
    
    public void setCategoryTotals(Map<String, Double> categoryTotals) {
        this.categoryTotals = categoryTotals;
    }
    
    public List<FinancialRecordDto> getRecentTransactions() {
        return recentTransactions;
    }
    
    public void setRecentTransactions(List<FinancialRecordDto> recentTransactions) {
        this.recentTransactions = recentTransactions;
    }
    
    public List<MonthlyTrend> getMonthlyTrends() {
        return monthlyTrends;
    }
    
    public void setMonthlyTrends(List<MonthlyTrend> monthlyTrends) {
        this.monthlyTrends = monthlyTrends;
    }
    
    // Inner Class FinancialRecordDto
    public static class FinancialRecordDto {
        private Long id;
        private Double amount;
        private String type;
        private String category;
        private String date;
        private String description;
        
        // Getters
        public Long getId() {
            return id;
        }
        
        public Double getAmount() {
            return amount;
        }
        
        public String getType() {
            return type;
        }
        
        public String getCategory() {
            return category;
        }
        
        public String getDate() {
            return date;
        }
        
        public String getDescription() {
            return description;
        }
        
        // Setters
        public void setId(Long id) {
            this.id = id;
        }
        
        public void setAmount(Double amount) {
            this.amount = amount;
        }
        
        public void setType(String type) {
            this.type = type;
        }
        
        public void setCategory(String category) {
            this.category = category;
        }
        
        public void setDate(String date) {
            this.date = date;
        }
        
        public void setDescription(String description) {
            this.description = description;
        }
    }
    
    // Inner Class MonthlyTrend
    public static class MonthlyTrend {
        private String month;
        private Double income;
        private Double expenses;
        
        // Constructors
        public MonthlyTrend() {
            this.income = 0.0;
            this.expenses = 0.0;
        }
        
        public MonthlyTrend(String month, Double income, Double expenses) {
            this.month = month;
            this.income = income;
            this.expenses = expenses;
        }
        
        // Getters
        public String getMonth() {
            return month;
        }
        
        public Double getIncome() {
            return income;
        }
        
        public Double getExpenses() {
            return expenses;
        }
        
        // Setters
        public void setMonth(String month) {
            this.month = month;
        }
        
        public void setIncome(Double income) {
            this.income = income;
        }
        
        public void setExpenses(Double expenses) {
            this.expenses = expenses;
        }
    }
}