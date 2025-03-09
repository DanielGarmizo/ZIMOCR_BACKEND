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
            agent_name, 
            COUNT(DISTINCT voyage_number) AS hazardous_voyage_count,
            RANK() OVER (ORDER BY COUNT(DISTINCT voyage_number) DESC) AS rank
        FROM bill_of_lading
        WHERE hazardous = TRUE
        GROUP BY agent_name
    """, nativeQuery = true)
    List<Object[]> rankAgenciesByHazardousVoyages();

    @Query(value = """ 
        SELECT 
            agent_name, 
            freight_charges, 
            COUNT(*) AS occurrences
        FROM bill_of_lading
        GROUP BY agent_name, freight_charges
        ORDER BY agent_name
    """, nativeQuery = true)
    List<Object[]> countFreightChargeTypes();

    @Query(value = """
        SELECT 
            agent_name, 
            SUM(NULLIF(freight_charges, 'Collect')::numeric) AS total_freight_charges,
            RANK() OVER (ORDER BY SUM(NULLIF(freight_charges, 'Collect')::numeric) DESC) AS rank
        FROM bill_of_lading
        WHERE freight_charges ~ '^[0-9]+(\\.[0-9]+)?$'
        GROUP BY agent_name
    """, nativeQuery = true)
    List<Object[]> rankAgenciesByAvgFreightCharges();

    @Query(value = """
        WITH VoyageFreight AS (
            SELECT
                agent_name,
                voyage_number,
                SUM(NULLIF(freight_charges, 'Collect')::numeric) AS total_freight_charges
            FROM
                bill_of_lading
            WHERE freight_charges ~ '^[0-9]+(\\.[0-9]+)?$'
            GROUP BY
                agent_name, voyage_number
        )
        SELECT
            agent_name,
            voyage_number,
            total_freight_charges,
            RANK() OVER (ORDER BY total_freight_charges DESC) AS rank
        FROM
            VoyageFreight
        ORDER BY
            rank
    """, nativeQuery = true)
    List<Object[]> rankVoyagesByFreightCharges();

    @Query(value = """
        SELECT
            bill_of_lading_number,
            agent_name,
            COUNT(freight_charges) AS freight_charge_count,
            RANK() OVER (ORDER BY COUNT(freight_charges) DESC) AS rank
        FROM
            bill_of_lading
        GROUP BY
            bill_of_lading_number, agent_name
        ORDER BY
            rank
    """, nativeQuery = true)
    List<Object[]> rankBillsByFreightCharges();
}
