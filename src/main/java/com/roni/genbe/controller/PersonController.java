package com.roni.genbe.controller;

import com.roni.genbe.model.dto.BiodataDto;
import com.roni.genbe.model.dto.PersonDto;
import com.roni.genbe.model.dto.Response;
import com.roni.genbe.model.entity.Biodata;
import com.roni.genbe.model.entity.Person;
import com.roni.genbe.repository.BiodataRepository;
import com.roni.genbe.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonRepository personRepository;
    private final BiodataRepository biodataRepository;

    @Autowired
    public PersonController(PersonRepository personRepository, BiodataRepository biodataRepository){
        this.personRepository = personRepository;
        this.biodataRepository = biodataRepository;
    }

    @GetMapping
    public List<PersonDto> person(){
        List<Person> personList = personRepository.findAll();
        List<PersonDto> personDtoList = personList.stream().map(this::convertToDto)
                .collect(Collectors.toList());

        return personDtoList;
    }

//    @GetMapping("/{nik}")
//    public BiodataDto get(@PathVariable String nik){
//        PersonDto personDto = convertToDto(personRepository.findById(nik));
//        BiodataDto biodataDto = convertToEntityPerson(personRepository.findById(nik),biodataRepository
//                .findAllById((personDto.getIdPerson())));
//        return biodataDto;
//    }
//    @GetMapping("/{nik}")
//    public PersonDto get(@PathVariable Integer nik) {
//        if(personRepository.findById(nik).isPresent()){
//            PersonDto personDto = convertToDto(personRepository.findById(nik).get());
//            return personDto;
//        }
//        if (biodataRepository.findById(Integer.parseInt(nik)).isPresent()){
//            Biodata biodata = convertToDto(biodataRepository.findById(Integer.parseInt(nik)).get());
//            return biodata;
//        }
//
//    }
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

    @PostMapping
    public Response insert (@RequestBody PersonDto personDto){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(personDto.getTgl());
        if (personDto.getNik().length()==16 && Year.now().getValue()-calendar.get(Calendar.YEAR)>=30){
            Person person = convertToEntityPerson(personDto);
            personRepository.save(person);

            Biodata biodata = convertToEntityBio(personDto, person.getIdPerson());
            biodataRepository.save(biodata);
            return response("true","Data berhasil masuk");
        }
        return response("false","Data gagal masuk, digit NIK tidak sama dengan 16 atau umur kurang dari 30 tahun");

    }
    private Biodata convertToEntityBio(PersonDto dto, Integer idPerson){
        Biodata biodata = new Biodata();
        biodata.setNoHp(dto.getHp());
        biodata.setTgl(dto.getTgl());
        biodata.setTempatLahir(dto.getTempatLahir());

        if (personRepository.findById(idPerson).isPresent()){
            Person person = personRepository.findById(idPerson).get();
            biodata.setPerson(person);
        }

        return biodata;
    }

    private Person convertToEntityPerson(PersonDto dto){
        Person person = new Person();
        person.setIdPerson(dto.getIdPerson());
        person.setNik(dto.getNik());
        person.setNama(dto.getName());
        person.setAlamat(dto.getAdress());

        return person;
    }



    private PersonDto convertToDto(Person person){
        PersonDto personDto = new PersonDto();
        personDto.setIdPerson(person.getIdPerson());
        personDto.setNik(person.getNik());
        personDto.setName(person.getNama());
        personDto.setAdress(person.getAlamat());

        return personDto;
    }



}
