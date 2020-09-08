package com.roni.genbe.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_person")

public class Person {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_person",updatable = false, nullable = false)
    private Integer idPerson;

    @Column(name = "nik", nullable = false, length = 16, unique = true)
    private String nik;

    @Column(name = "nama",length = 50)
    private String name;

    @Column(name = "alamat",length = 255)
    private String alamat;

//    private Biodata biodata;

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

//    public String getNama() {
//        return name;
//    }

//    public void setNama(String name) {
//        this.name = name;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

//    public Biodata getBiodata() {
//        return biodata;
//    }
//
//    public void setBiodata(Biodata biodata) {
//        this.biodata = biodata;
//    }
}
