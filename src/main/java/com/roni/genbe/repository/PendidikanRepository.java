package com.roni.genbe.repository;

import com.roni.genbe.model.entity.Pendidikan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PendidikanRepository extends JpaRepository<Pendidikan,Integer> {
}
