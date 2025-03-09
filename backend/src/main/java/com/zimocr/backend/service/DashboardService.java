package com.zimocr.backend.service;

import com.zimocr.backend.dto.DashboardDto;
import com.zimocr.backend.repository.DashboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    public Map<String, List<DashboardDto>> getDashboardData() {
        return Map.ofEntries(
                Map.entry("rankAgenciesByVoyageCount", convertToDto(dashboardRepository.rankAgenciesByVoyageCount(), "totalVoyages")),
                Map.entry("rankAgenciesByAvgWeightPerVoyage", convertToDto(dashboardRepository.rankAgenciesByAvgWeightPerVoyage(), "avgWeightPerVoyage")),
                Map.entry("rankAgenciesByHazardousVoyages", convertToDto(dashboardRepository.rankAgenciesByHazardousVoyages(), "hazardousVoyageCount")),
                Map.entry("countFreightChargeTypes", convertToDto(dashboardRepository.countFreightChargeTypes(), "freightChargeOccurrences")),
                Map.entry("rankAgenciesByAvgFreightCharges", convertToDto(dashboardRepository.rankAgenciesByAvgFreightCharges(), "avgFreightCharges")),
                Map.entry("rankVoyagesByFreightCharges", convertToDto(dashboardRepository.rankVoyagesByFreightCharges(), "totalFreightCharges")),
                Map.entry("rankBillsByFreightCharges", convertToDto(dashboardRepository.rankBillsByFreightCharges(), "freightCharges"))
        );
    }

    private List<DashboardDto> convertToDto(List<Object[]> results, String metricName) {
        return results.stream().map(row -> {
            System.out.println("Processing row: " + Arrays.toString(row));

            // Convert first column (agent_name or bill_of_lading_number) to String safely
            String name = (row[0] != null) ? row[0].toString() : "Unknown";

            // Convert second column to Double if it's numeric
            Double metricValue = (row.length > 1 && row[1] instanceof Number) ? ((Number) row[1]).doubleValue() : null;

            // Handle text values like "Collect" or "Prepaid" from freight_charges
            String textValue = (row.length > 1 && row[1] instanceof String) ? (String) row[1] : null;

            // Convert last column to Integer (Rank)
            Integer rank = (row.length > 2 && row[row.length - 1] instanceof Number) ? ((Number) row[row.length - 1]).intValue() : 0;

            return DashboardDto.builder()
                    .name(name)
                    .textValue(textValue) // שדה חדש לתמיכה בטקסט
                    .value(metricValue) // תמיכה בערכים מספריים
                    .rank(rank)
                    .build();
        }).collect(Collectors.toList());
    }
}
