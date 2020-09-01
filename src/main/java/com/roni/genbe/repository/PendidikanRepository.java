package com.roni.genbe.repository;

import com.roni.genbe.model.entity.Pendidikan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PendidikanRepository extends JpaRepository<Pendidikan,Integer> {
    List<Pendidikan> findAllByPersonIdPerson(Integer id);
    @Query(value = "select jenjang from t_pendidikan inner join t_person on t_pendidikan.id_person = t_person.id_person where nik =? order by tahunlulus desc limit 1", nativeQuery = true)
    String findJenjangByNik(String nik);

}
