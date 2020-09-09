package com.roni.genbe.service;

import com.roni.genbe.model.dto.PendidikanDto;
import com.roni.genbe.model.entity.Biodata;
import com.roni.genbe.model.entity.Pendidikan;
import com.roni.genbe.model.entity.Person;

import java.util.List;

public interface PersonService {
    Person insertPerson(Person person);
    Biodata insertBiodata(Biodata biodata);
    Biodata idbio(Biodata biodata);
    public void insertPendidikan(List<PendidikanDto> pendidikanDtoList,Integer idPerson);

}
