package com.finance.controller;

import com.finance.dto.response.DashboardSummary;
import com.finance.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    
    @Autowired
    private DashboardService dashboardService;
    
    @GetMapping("/summary")
    public ResponseEntity<DashboardSummary> getDashboardSummary() {
        String email = "admin@gmail.com";
        DashboardSummary summary = dashboardService.getDashboardSummaryByEmail(email);
        return ResponseEntity.ok(summary);
    }
}