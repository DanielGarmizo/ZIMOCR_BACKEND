package com.zimocr.backend.repository;

import com.zimocr.backend.model.BillOfLading;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DashboardRepository extends CrudRepository<BillOfLading, Long> {

    @Query(value = """
        SELECT 
            COUNT(voyage_number) AS total_voyages, 
            agent_name, 
            RANK() OVER (ORDER BY COUNT(voyage_number) DESC) AS rank
        FROM bill_of_lading
        GROUP BY agent_name
        """, nativeQuery = true)
    List<Object[]> rankAgenciesByVoyageCount();

    @Query(value = """
        SELECT 
            agent_name, 
            AVG(total_weight_per_voyage) AS avg_weight_per_voyage,
            RANK() OVER (ORDER BY AVG(total_weight_per_voyage) DESC) AS rank
        FROM (
            SELECT 
                voyage_number, 
                agent_name, 
                SUM(weight_kg) AS total_weight_per_voyage
            FROM bill_of_lading
            GROUP BY voyage_number, agent_name
        ) voyage_weights
        GROUP BY agent_name
        """, nativeQuery = true)
    List<Object[]> rankAgenciesByAvgWeightPerVoyage();

    @Query(value = """ 
        SELECT 
            agentName, 
            COUNT(DISTINCT voyageNumber) AS hazardous_voyage_count,
            RANK() OVER (ORDER BY COUNT(DISTINCT voyageNumber) DESC) AS rank
        FROM bill_of_lading
        WHERE hazardous = TRUE
        GROUP BY agentName
    """, nativeQuery = true)
    List<Object[]> rankAgenciesByHazardousVoyages();

    @Query(value = """ 
        SELECT 
            agentName, 
            SUM(freightCharges::numeric) AS total_freight_charges,
            RANK() OVER (ORDER BY SUM(freightCharges::numeric) DESC) AS rank
        FROM bill_of_lading
        GROUP BY agentName
    """, nativeQuery = true)
    List<Object[]> rankAgenciesByTotalFreightCharges();

    @Query(value = """
        SELECT agentName AS agency,
               AVG(voyage_freight.total_freight_charges) AS avg_freight_charges,
               RANK() OVER (ORDER BY AVG(voyage_freight.total_freight_charges) DESC) AS rank
        FROM (
            SELECT voyageNumber,
                   agentName,
                   SUM(freightCharges::numeric) AS total_freight_charges
            FROM bill_of_lading
            GROUP BY voyageNumber, agentName
        ) AS voyage_freight
        GROUP BY agentName
        ORDER BY avg_freight_charges DESC
    """, nativeQuery = true)
    List<Object[]> rankAgenciesByAvgFreightCharges();

    @Query(value = """
        WITH VoyageFreight AS (
            SELECT
                agentName,
                voyageNumber,
                SUM(freightCharges::numeric) AS total_freight_charges
            FROM
                bill_of_lading
            GROUP BY
                agentName, voyageNumber
        )
        SELECT
            agentName,
            voyageNumber,
            total_freight_charges,
            RANK() OVER (ORDER BY total_freight_charges DESC) AS rank
        FROM
            VoyageFreight
        ORDER BY
            rank
    """, nativeQuery = true)
    List<Object[]> rankVoyagesByFreightCharges();

    @Query(value = """
        WITH VoyageDuration AS (
            SELECT
                agentName,
                voyageNumber,
                EXTRACT(DAY FROM (arrivalDate - departureDate)) AS voyage_duration
            FROM
                bill_of_lading
            WHERE
                departureDate IS NOT NULL AND arrivalDate IS NOT NULL
            GROUP BY
                agentName, voyageNumber, arrivalDate, departureDate
        )
        SELECT
            agentName,
            AVG(voyage_duration) AS avg_voyage_duration,
            RANK() OVER (ORDER BY AVG(voyage_duration) DESC) AS rank
        FROM
            VoyageDuration
        GROUP BY
            agentName
        ORDER BY
            rank
    """, nativeQuery = true)
    List<Object[]> rankAgenciesByAvgVoyageDuration();

    @Query(value = """
        WITH VoyageDurations AS (
            SELECT
                agentName,
                voyageNumber,
                MIN(EXTRACT(DAY FROM (arrivalDate - departureDate))) AS shortest_duration
            FROM
                bill_of_lading
            WHERE
                departureDate IS NOT NULL AND arrivalDate IS NOT NULL
            GROUP BY
                agentName, voyageNumber
        )
        SELECT
            agentName,
            shortest_duration,
            RANK() OVER (ORDER BY shortest_duration ASC) AS rank
        FROM
            VoyageDurations
        ORDER BY
            rank
    """, nativeQuery = true)
    List<Object[]> rankAgenciesByShortestVoyageDuration();

    @Query(value = """
        SELECT
            billOfLadingNumber,
            weightKg,
            agentName,  
            RANK() OVER (ORDER BY weightKg DESC) AS rank,
            SUM(weightKg) OVER () AS total_weight
        FROM
            bill_of_lading
        ORDER BY
            rank
    """, nativeQuery = true)
    List<Object[]> rankBillOfLadingsByWeight();

    @Query(value = """
        SELECT
            billOfLadingNumber,
            volumeM3,
            agentName,
            RANK() OVER (ORDER BY volumeM3 DESC) AS rank
        FROM
            bill_of_lading
        ORDER BY
            rank
    """, nativeQuery = true)
    List<Object[]> rankBillOfLadingsByVolume();

    @Query(value = """
        SELECT
            voyageNumber,
            billOfLadingNumber,
            agentName,
            quantity,
            RANK() OVER (PARTITION BY voyageNumber ORDER BY quantity DESC) AS rank
        FROM
            bill_of_lading
        ORDER BY
            voyageNumber, rank
    """, nativeQuery = true)
    List<Object[]> rankBillsByQuantity();

    @Query(value = """
        SELECT
            billOfLadingNumber,
            agentName,
            freightCharges,
            RANK() OVER (ORDER BY freightCharges::numeric DESC) AS rank
        FROM
            bill_of_lading
        ORDER BY
            rank
    """, nativeQuery = true)
    List<Object[]> rankBillsByFreightCharges();

}