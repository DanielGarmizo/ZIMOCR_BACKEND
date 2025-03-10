package com.zimocr.backend.controller;

import com.zimocr.backend.dto.AverageWeightDto;
import com.zimocr.backend.service.EylonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eylon")
@RequiredArgsConstructor
public class EylonController {

    private final EylonService eylonService;

    @GetMapping
    public List<AverageWeightDto> getAverageWeightPerVoyage() {
        return eylonService.getAverageWeightPerVoyage();
    }
}
