//package com.roni.genbe.controller;
//
//import com.roni.genbe.model.dto.BiodataDto;
//import com.roni.genbe.model.dto.PersonDto;
//import com.roni.genbe.model.entity.Biodata;
//import com.roni.genbe.model.entity.Person;
//import com.roni.genbe.repository.BiodataRepository;
//import com.roni.genbe.repository.PersonRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@RestController
//@RequestMapping("/biodata")
//public class BiodataController {
//    private final PersonRepository personRepository;
//    private final BiodataRepository biodataRepository;
//
//    @Autowired
//    public BiodataController(BiodataRepository biodataRepository, PersonRepository personRepository){
//        this.personRepository = personRepository;
//        this.biodataRepository = biodataRepository;
//    }
//
//    @GetMapping
//    public List<BiodataDto> biodata(){
//        List<Biodata> biodataList = biodataRepository.findAll();
//        List<BiodataDto> biodataDtoList = biodataList.stream().map(this::convertToDto)
//                .collect(Collectors.toList());
//        return biodataDtoList;
//    }
//
//    @PostMapping
//    public BiodataDto insert (@RequestBody BiodataDto biodataDto){
//        Biodata biodata = convertToEntity(biodataDto);
//        biodataRepository.save(biodata);
//        return convertToDto(biodata);
//    }
//
//    private Biodata convertToEntity(BiodataDto dto){
//        Biodata biodata = new Biodata();
//        biodata.setIdBiodata(dto.getIdBiodata());
//        biodata.setNoHp(dto.getNoHp());
//        biodata.setTgl(dto.getDate());
//        biodata.setTempatLahir(dto.getBornPlace());
//
//        if (personRepository.findById(dto.getIdPerson()).isPresent()){
//            Person person = personRepository.findById(dto.getIdPerson()).get();
//            biodata.setPerson(person);
//        }
//
//        return biodata;
//    }
//
//    private BiodataDto convertToDto(Biodata biodata){
//        BiodataDto biodataDto = new BiodataDto();
//        biodataDto.setIdBiodata(biodata.getIdBiodata());
//        biodataDto.setNoHp(biodata.getNoHp());
//        biodataDto.setDate(biodata.getTgl());
//        biodataDto.setBornPlace(biodata.getTempatLahir());
//        biodataDto.setIdPerson(biodata.getPerson().getIdPerson());
//
//        return  biodataDto;
//    }
//}
