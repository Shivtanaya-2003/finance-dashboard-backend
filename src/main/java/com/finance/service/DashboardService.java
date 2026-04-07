package com.finance.service;

import com.finance.dto.response.DashboardSummary;
import com.finance.entity.FinancialRecord;
import com.finance.entity.User;
import com.finance.repository.FinancialRecordRepository;
import com.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    
    @Autowired
    private FinancialRecordRepository recordRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public DashboardSummary getDashboardSummaryByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return getDashboardSummary(user.getId());
    }
    
    public DashboardSummary getDashboardSummary(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        DashboardSummary summary = new DashboardSummary();
        
        // Calculate totals
        Double totalIncome = recordRepository.sumIncomeByUser(user);
        Double totalExpenses = recordRepository.sumExpenseByUser(user);
        
        summary.setTotalIncome(totalIncome != null ? totalIncome : 0.0);
        summary.setTotalExpenses(totalExpenses != null ? totalExpenses : 0.0);
        summary.setNetBalance(summary.getTotalIncome() - summary.getTotalExpenses());
        
        // Get category totals
        List<Object[]> categoryResults = recordRepository.getCategoryTotals(user);
        Map<String, Double> categoryTotals = new HashMap<>();
        for (Object[] result : categoryResults) {
            categoryTotals.put((String) result[0], (Double) result[1]);
        }
        summary.setCategoryTotals(categoryTotals);
        
        // Get recent transactions
        List<FinancialRecord> recentRecords = recordRepository.findTop10ByUserOrderByDateDesc(user);
        List<DashboardSummary.FinancialRecordDto> recentTransactions = recentRecords.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        summary.setRecentTransactions(recentTransactions);
        
        // Calculate monthly trends
        List<DashboardSummary.MonthlyTrend> monthlyTrends = calculateMonthlyTrends(user);
        summary.setMonthlyTrends(monthlyTrends);
        
        return summary;
    }
    
    private DashboardSummary.FinancialRecordDto convertToDto(FinancialRecord record) {
        DashboardSummary.FinancialRecordDto dto = new DashboardSummary.FinancialRecordDto();
        dto.setId(record.getId());
        dto.setAmount(record.getAmount());
        dto.setType(record.getType().toString());
        dto.setCategory(record.getCategory());
        dto.setDate(record.getDate().toString());
        dto.setDescription(record.getDescription());
        return dto;
    }
    
    private List<DashboardSummary.MonthlyTrend> calculateMonthlyTrends(User user) {
        List<FinancialRecord> records = recordRepository.findByUserOrderByDateDesc(user);
        
        Map<String, DashboardSummary.MonthlyTrend> trendsMap = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        
        for (FinancialRecord record : records) {
            String monthKey = record.getDate().format(formatter);
            DashboardSummary.MonthlyTrend trend = trendsMap.getOrDefault(monthKey, 
                    new DashboardSummary.MonthlyTrend());
            
            if (trend.getMonth() == null) {
                trend.setMonth(monthKey);
                trend.setIncome(0.0);
                trend.setExpenses(0.0);
            }
            
            if (record.getType() == FinancialRecord.TransactionType.INCOME) {
                trend.setIncome(trend.getIncome() + record.getAmount());
            } else {
                trend.setExpenses(trend.getExpenses() + record.getAmount());
            }
            
            trendsMap.put(monthKey, trend);
        }
        
        return new ArrayList<>(trendsMap.values());
    }
}