package com.roni.genbe.repository;

import com.roni.genbe.model.entity.Pendidikan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PendidikanRepository extends JpaRepository<Pendidikan,Integer> {
    List<Pendidikan> findAllByPersonIdPerson(Integer id);
}
