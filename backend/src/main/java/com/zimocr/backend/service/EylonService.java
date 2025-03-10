package com.zimocr.backend.service;

import com.zimocr.backend.dto.AverageWeightDto;
import com.zimocr.backend.repository.EylonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EylonService {

    private final EylonRepository eylonRepository;

    public List<AverageWeightDto> getAverageWeightPerVoyage() {
        return eylonRepository.getAverageWeightPerVoyage()
                .stream()
                .map(AverageWeightDto::new)
                .collect(Collectors.toList());
    }
}
