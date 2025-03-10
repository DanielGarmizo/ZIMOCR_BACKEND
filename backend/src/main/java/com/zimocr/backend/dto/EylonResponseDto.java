package com.zimocr.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EylonResponseDto {
    private List<AverageWeightDto> averageWeightPerVoyage;
    private List<TotalVoyagesDto> totalVoyagesPerAgent;
    private List<HazardousVoyagesDto> hazardousVoyagesPerAgent;
}
