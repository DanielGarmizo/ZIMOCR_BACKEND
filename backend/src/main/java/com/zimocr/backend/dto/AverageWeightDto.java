package com.zimocr.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AverageWeightDto {
    private String agentName;
    private Double avgWeightPerVoyage;
    private Integer rank;

    public AverageWeightDto(Object[] obj) {
        this.agentName = (String) obj[0];
        this.avgWeightPerVoyage = ((Number) obj[1]).doubleValue();
        this.rank = ((Number) obj[2]).intValue();
    }
}
