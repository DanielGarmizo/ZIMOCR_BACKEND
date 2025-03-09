package com.ZIMOCR.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bill_of_lading")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillOfLading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bill_of_lading_number", unique = true, nullable = false)
    private String billOfLadingNumber;

    @Column(name = "container_number")
    private String containerNumber;

    private String vessel;

    @Column(name = "voyage_number")
    private String voyageNumber;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "origin_port")
    private String originPort;

    @Column(name = "destination_port")
    private String destinationPort;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "weight_kg")
    private Integer weightKg;

    private Integer quantity;

    @Column(name = "volume_m3")
    private Integer volumeM3;

    private Boolean hazardous;

    @Column(name = "shipper_name")
    private String shipperName;

    @Column(name = "shipper_address")
    private String shipperAddress;

    @Column(name = "shipper_contact")
    private String shipperContact;

    @Column(name = "consignee_name")
    private String consigneeName;

    @Column(name = "consignee_address")
    private String consigneeAddress;

    @Column(name = "consignee_contact")
    private String consigneeContact;

    @Column(name = "agent_name")
    private String agentName;

    @Column(name = "agent_address")
    private String agentAddress;

    @Column(name = "agent_contact")
    private String agentContact;

    private String incoterms;

    @Column(name = "freight_charges")
    private String freightCharges;

    @Column(name = "payment_terms")
    private String paymentTerms;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
