package com.roni.genbe.controller;


import com.roni.genbe.model.dto.PendidikanDto;
import com.roni.genbe.model.dto.Response;
import com.roni.genbe.model.entity.Pendidikan;
import com.roni.genbe.model.entity.Person;
import com.roni.genbe.repository.PendidikanRepository;
import com.roni.genbe.repository.PersonRepository;
import com.roni.genbe.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/education")
@CrossOrigin
public class PendidikanController {
    private final PersonRepository personRepository;
    private final PendidikanRepository pendidikanRepository;

    @Autowired
    public PendidikanController(PersonRepository personRepository, PendidikanRepository pendidikanRepository){
        this.personRepository = personRepository;
        this.pendidikanRepository = pendidikanRepository;
    }

    @Autowired
    private PersonService personService;

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
        int count = 0;
        for (int i = 0;i<pendidikanDtoList.size();i++){
            count++;
        }

        personService.insertPendidikan(pendidikanDtoList,idPerson);
        if (count == pendidikanDtoList.size()){
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

    private PendidikanDto convertToDto(Pendidikan pendidikan){
        PendidikanDto pendidikanDto = new PendidikanDto();
        pendidikanDto.setIdEducation(pendidikan.getIdPendidikan());
        pendidikanDto.setJenjang(pendidikan.getJenjang());
        pendidikanDto.setInstitusi(pendidikan.getInstitusi());
        pendidikanDto.setTahunMasuk(pendidikan.getTahunMasuk());
        pendidikanDto.setTahunLulus(pendidikan.getTahunLulus());
        pendidikanDto.setIdPerson(pendidikan.getPerson().getIdPerson());

        return pendidikanDto;
    }

}
