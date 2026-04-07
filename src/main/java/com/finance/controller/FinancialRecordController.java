package com.finance.controller;

import com.finance.dto.request.FinancialRecordRequest;
import com.finance.dto.response.ApiResponse;
import com.finance.entity.FinancialRecord;
import com.finance.service.FinancialRecordService;
import com.finance.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/records")
public class FinancialRecordController {
    
    @Autowired
    private FinancialRecordService recordService;
    
    @Autowired
    private UserService userService;
    
    // Hardcoded user ID for testing (use admin@gmail.com user)
    private Long getCurrentUserId() {
        // Get admin user ID from database
        try {
            return userService.getUserByEmail("admin@gmail.com").getId();
        } catch (Exception e) {
            return 1L; // Fallback to ID 1
        }
    }
    
    @PostMapping
    public ResponseEntity<?> createRecord(@Valid @RequestBody FinancialRecordRequest request) {
        try {
            Long userId = getCurrentUserId();
            FinancialRecord record = recordService.createRecord(userId, request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(true, "Record created successfully", record));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }
    
    @GetMapping
    public ResponseEntity<List<FinancialRecord>> getAllRecords(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        
        Long userId = getCurrentUserId();
        List<FinancialRecord> records = recordService.getAllRecords(userId, category, type, startDate, endDate);
        return ResponseEntity.ok(records);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<FinancialRecord> getRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(recordService.getRecordById(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecord(@PathVariable Long id, 
                                          @Valid @RequestBody FinancialRecordRequest request) {
        try {
            FinancialRecord record = recordService.updateRecord(id, request);
            return ResponseEntity.ok(new ApiResponse(true, "Record updated successfully", record));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecord(@PathVariable Long id) {
        try {
            recordService.deleteRecord(id);
            return ResponseEntity.ok(new ApiResponse(true, "Record deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }
}