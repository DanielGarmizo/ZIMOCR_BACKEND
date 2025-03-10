package com.zimocr.backend.repository;

import com.zimocr.backend.dto.AverageWeightDto;
import com.zimocr.backend.model.BillOfLading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EylonRepository extends JpaRepository<BillOfLading, Long> {

    @Query(value = """
        SELECT agent_name AS agentName, 
               AVG(total_weight_per_voyage) AS avgWeightPerVoyage,
               RANK() OVER (ORDER BY AVG(total_weight_per_voyage) DESC) AS rank
        FROM (
            SELECT voyage_number, 
                   agent_name, 
                   SUM(weight_kg) AS total_weight_per_voyage
            FROM bill_of_lading
            GROUP BY voyage_number, agent_name
        ) voyage_weights
        GROUP BY agent_name
    """, nativeQuery = true)
    List<Object[]> getAverageWeightPerVoyage();
}
