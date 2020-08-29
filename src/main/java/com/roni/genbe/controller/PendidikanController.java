package com.roni.genbe.controller;


import com.roni.genbe.model.dto.PendidikanDto;
import com.roni.genbe.model.dto.PersonDto;
import com.roni.genbe.model.dto.Response;
import com.roni.genbe.model.entity.Biodata;
import com.roni.genbe.model.entity.Pendidikan;
import com.roni.genbe.model.entity.Person;
import com.roni.genbe.repository.PendidikanRepository;
import com.roni.genbe.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/education")
public class PendidikanController {
    private final PersonRepository personRepository;
    private final PendidikanRepository pendidikanRepository;

    @Autowired
    public PendidikanController(PersonRepository personRepository, PendidikanRepository pendidikanRepository){
        this.personRepository = personRepository;
        this.pendidikanRepository = pendidikanRepository;
    }

    @GetMapping
    public List<PendidikanDto> pendidikan(){
        List<Pendidikan> pendidikanList = pendidikanRepository.findAll();
        List<PendidikanDto> pendidikanDtoList = pendidikanList.stream().map(this::convertToDto)
                .collect(Collectors.toList());

        return pendidikanDtoList;
    }

    @PostMapping("/person/{idPerson}")
    public Response insert(@PathVariable Integer idPerson, @RequestBody List<PendidikanDto> pendidikanDtoList){
        if (personRepository.findById(idPerson).isPresent()){
            List<Pendidikan> pendidikanList = pendidikanDtoList.stream().map(x-> convertToEntity(x,idPerson))
                    .collect(Collectors.toList());
            pendidikanList.stream().forEach(y -> pendidikanRepository.save(y));
            if (pendidikanRepository.findAllByPersonIdPerson(idPerson).containsAll(pendidikanList)){
                return response("true","Data berhasil masuk ");
            }else {
                return response("false","Data gagal masuk");
            }
        }
        return null;
    }

    private Response response (String status, String message){
        Response response = new Response();
        if (status=="true"){
            response.setStatus("True");
            response.setMessage(message);
        }else {
            response.setStatus("False");
            response.setMessage(message);
        }
        return response;
    }

    private Pendidikan convertToEntity(PendidikanDto dto, Integer idPerson){
        Pendidikan pendidikan = new Pendidikan();
        pendidikan.setIdPendidikan(dto.getIdEducation());
        pendidikan.setJenjang(dto.getLevelEducation());
        pendidikan.setInstitusi(dto.getInstitution());
        pendidikan.setTahunMasuk(dto.getInYear());
        pendidikan.setTahunLulus(dto.getEndYear());

        if (personRepository.findById(idPerson).isPresent()){
            Person person = personRepository.findById(idPerson).get();
            pendidikan.setPerson(person);
        }

        return pendidikan;
    }

    private PendidikanDto convertToDto(Pendidikan pendidikan){
        PendidikanDto pendidikanDto = new PendidikanDto();
        pendidikanDto.setIdEducation(pendidikan.getIdPendidikan());
        pendidikanDto.setLevelEducation(pendidikan.getJenjang());
        pendidikanDto.setInstitution(pendidikan.getInstitusi());
        pendidikanDto.setInYear(pendidikan.getTahunMasuk());
        pendidikanDto.setEndYear(pendidikan.getTahunLulus());
        pendidikanDto.setIdPerson(pendidikan.getPerson().getIdPerson());

        return pendidikanDto;
    }

}
