package com.finance.repository;

import com.finance.entity.FinancialRecord;
import com.finance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
    List<FinancialRecord> findByUserOrderByDateDesc(User user);
    
    @Query("SELECT f FROM FinancialRecord f WHERE f.user = :user " +
           "AND (:category IS NULL OR f.category = :category) " +
           "AND (:type IS NULL OR f.type = :type) " +
           "AND (:startDate IS NULL OR f.date >= :startDate) " +
           "AND (:endDate IS NULL OR f.date <= :endDate) " +
           "ORDER BY f.date DESC")
    List<FinancialRecord> findWithFilters(@Param("user") User user,
                                          @Param("category") String category,
                                          @Param("type") String type,
                                          @Param("startDate") LocalDate startDate,
                                          @Param("endDate") LocalDate endDate);
    
    @Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.user = :user AND f.type = 'INCOME'")
    Double sumIncomeByUser(@Param("user") User user);
    
    @Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.user = :user AND f.type = 'EXPENSE'")
    Double sumExpenseByUser(@Param("user") User user);
    
    @Query("SELECT f.category, SUM(f.amount) FROM FinancialRecord f " +
           "WHERE f.user = :user GROUP BY f.category")
    List<Object[]> getCategoryTotals(@Param("user") User user);
    
    List<FinancialRecord> findTop10ByUserOrderByDateDesc(User user);
}