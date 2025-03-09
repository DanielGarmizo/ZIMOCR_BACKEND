package com.zimocr.backend.service;

import com.zimocr.backend.dto.DocumentDto;
import com.zimocr.backend.model.BillOfLading;
import com.zimocr.backend.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public List<DocumentDto> getAllDocuments() {
        List<BillOfLading> documents = documentRepository.findAll();
        return documents.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private DocumentDto convertToDto(BillOfLading billOfLading) {
        return DocumentDto.builder()
                .billOfLadingNumber(billOfLading.getBillOfLadingNumber())
                .containerNumber(billOfLading.getContainerNumber())
                .vessel(billOfLading.getVessel())
                .voyageNumber(billOfLading.getVoyageNumber())
                .departureDate(billOfLading.getDepartureDate())
                .arrivalDate(billOfLading.getArrivalDate())
                .originPort(billOfLading.getOriginPort())
                .destinationPort(billOfLading.getDestinationPort())
                .build();
    }
}
