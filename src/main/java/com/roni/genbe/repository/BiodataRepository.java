package com.roni.genbe.repository;

import com.roni.genbe.model.entity.Biodata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiodataRepository extends JpaRepository<Biodata, Integer> {
}
