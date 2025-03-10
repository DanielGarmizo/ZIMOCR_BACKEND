package com.zimocr.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalVoyagesDto {
    private Long totalVoyages;
    private String agentName;
    private Integer rank;
}
