package com.zimocr.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDto {
    private String name;  // יכול להיות שם ה-Agency או BillOfLadingNumber
    private Double value; // הנתון המספרי (כמו משקל ממוצע, כמות הפלגות וכו')
    private Integer rank; // הדירוג
    private String textValue; // 🔹 שדה חדש לתמיכה בטקסטים (Collect, Prepaid וכו')
}
