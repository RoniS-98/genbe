package com.roni.genbe.service;


import com.roni.genbe.model.dto.PendidikanDto;
import com.roni.genbe.model.entity.Biodata;
import com.roni.genbe.model.entity.Pendidikan;
import com.roni.genbe.model.entity.Person;
import com.roni.genbe.repository.BiodataRepository;
import com.roni.genbe.repository.PendidikanRepository;
import com.roni.genbe.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BiodataRepository biodataRepository;
    @Autowired
    private PendidikanRepository pendidikanRepository;

    @Override
    public Person insertPerson(Person person){
        personRepository.save(person);
            return personRepository.save(person);
    }

    @Override
    public Biodata insertBiodata(Biodata biodata) {
        biodataRepository.save(biodata);
        return biodataRepository.save(biodata);
    }

    @Override
    public void insertPendidikan(List<PendidikanDto> pendidikanDtoList, Integer idPerson) {
        for (int i = 0;i<pendidikanDtoList.size();i++){
            Pendidikan pendidikan = convertToEntity(pendidikanDtoList.get(i),idPerson);
            if (pendidikan.getJenjang()==null || pendidikan.getInstitusi()==null || pendidikan.getTahunMasuk()==null){

            }
            pendidikan.setPerson(personRepository.findById(idPerson).get());
            pendidikanRepository.save(pendidikan);
        }
    }


    private Pendidikan convertToEntity(PendidikanDto dto, Integer idPerson){
        Pendidikan pendidikan = new Pendidikan();
        pendidikan.setIdPendidikan(dto.getIdEducation());
        pendidikan.setJenjang(dto.getJenjang());
        pendidikan.setInstitusi(dto.getInstitusi());
        pendidikan.setTahunMasuk(dto.getTahunMasuk());
        pendidikan.setTahunLulus(dto.getTahunLulus());

        if (personRepository.findById(idPerson).isPresent()){
            Person person = personRepository.findById(idPerson).get();
            pendidikan.setPerson(person);
        }

        return pendidikan;
    }
}
