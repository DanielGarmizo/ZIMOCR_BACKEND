package com.zimocr.backend.repository;

import com.zimocr.backend.model.BillOfLading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<BillOfLading, Long> {
}
