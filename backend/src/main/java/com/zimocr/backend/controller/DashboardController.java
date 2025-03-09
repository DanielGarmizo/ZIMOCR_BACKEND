package com.zimocr.backend.controller;

import com.zimocr.backend.dto.DashboardDto;
import com.zimocr.backend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public Map<String, List<DashboardDto>> getDashboardData() {
        return dashboardService.getDashboardData();
    }
}
