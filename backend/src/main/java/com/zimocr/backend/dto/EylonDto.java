package com.zimocr.backend.dto;

import com.zimocr.backend.model.BillOfLading;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EylonDto {
    private Long id;
    private String billOfLadingNumber;
    private String agentName;

    // Constructor ממיר Entity ל-DTO
    public EylonDto(BillOfLading bill) {
        this.id = bill.getId();
        this.billOfLadingNumber = bill.getBillOfLadingNumber();
        this.agentName = bill.getAgentName();
    }
}
