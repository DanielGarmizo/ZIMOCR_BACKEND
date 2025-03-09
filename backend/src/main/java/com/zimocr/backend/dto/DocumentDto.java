package com.zimocr.backend.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentDto {
    private String billOfLadingNumber;
    private String containerNumber;
    private String vessel;
    private String voyageNumber;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private String originPort;
    private String destinationPort;
}
