package com.finance.service;

import com.finance.dto.request.FinancialRecordRequest;
import com.finance.entity.FinancialRecord;
import com.finance.entity.User;
import com.finance.repository.FinancialRecordRepository;
import com.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class FinancialRecordService {
    
    @Autowired
    private FinancialRecordRepository recordRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public FinancialRecord createRecord(Long userId, FinancialRecordRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        FinancialRecord record = new FinancialRecord();
        record.setAmount(request.getAmount());
        record.setType(FinancialRecord.TransactionType.valueOf(request.getType()));
        record.setCategory(request.getCategory());
        record.setDate(request.getDate());
        record.setDescription(request.getDescription());
        record.setUser(user);
        
        return recordRepository.save(record);
    }
    
    public List<FinancialRecord> getAllRecords(Long userId, String category, String type, 
                                                LocalDate startDate, LocalDate endDate) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return recordRepository.findWithFilters(user, category, type, startDate, endDate);
    }
    
    public FinancialRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }
    
    public FinancialRecord updateRecord(Long id, FinancialRecordRequest request) {
        FinancialRecord record = getRecordById(id);
        record.setAmount(request.getAmount());
        record.setType(FinancialRecord.TransactionType.valueOf(request.getType()));
        record.setCategory(request.getCategory());
        record.setDate(request.getDate());
        record.setDescription(request.getDescription());
        
        return recordRepository.save(record);
    }
    
    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }
}