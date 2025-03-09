package com.zimocr.backend.service;

import com.zimocr.backend.repository.DashboardRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {
    private final DashboardRepository dashboardRepository;

    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public Map<String, List<Object[]>> getAllRankings() {
        Map<String, List<Object[]>> rankings = new HashMap<>();
        rankings.put("rankedAgenciesByVoyageCount", dashboardRepository.rankAgenciesByVoyageCount());
        rankings.put("rankedAgenciesByAvgWeightPerVoyage", dashboardRepository.rankAgenciesByAvgWeightPerVoyage());
        rankings.put("rankedAgenciesByHazardousVoyages", dashboardRepository.rankAgenciesByHazardousVoyages());
        rankings.put("rankedAgenciesByTotalFreightCharges", dashboardRepository.rankAgenciesByTotalFreightCharges());
        rankings.put("rankedAgenciesByAvgFreightCharges", dashboardRepository.rankAgenciesByAvgFreightCharges());
        rankings.put("rankedVoyagesByFreightCharges", dashboardRepository.rankVoyagesByFreightCharges());
        rankings.put("rankedAgenciesByAvgVoyageDuration", dashboardRepository.rankAgenciesByAvgVoyageDuration());
        rankings.put("rankedAgenciesByShortestVoyageDuration", dashboardRepository.rankAgenciesByShortestVoyageDuration());
        rankings.put("rankedBillOfLadingsByWeight", dashboardRepository.rankBillOfLadingsByWeight());
        rankings.put("rankedBillOfLadingsByVolume", dashboardRepository.rankBillOfLadingsByVolume());
        rankings.put("rankedBillsByQuantity", dashboardRepository.rankBillsByQuantity());
        rankings.put("rankedBillsByFreightCharges", dashboardRepository.rankBillsByFreightCharges());
        return rankings;
    }
}