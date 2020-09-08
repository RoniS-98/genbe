package com.roni.genbe.model.entity;


import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "t_biodata")
public class Biodata {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_bio",updatable = false, nullable = false)
    private Integer idBiodata;

    @Column(name = "nohp", length = 16)
    private String hp;

    @Column(name = "tanggal_lahir", nullable = false)
    private Date tgl;

    @Column(name = "tempat_lahir",length = 50)
    private String tempatLahir;

    @OneToOne
    @JoinColumn(name = "id_person",nullable = false)
    private Person person;

    public Integer getIdBiodata() {
        return idBiodata;
    }

    public void setIdBiodata(Integer idBiodata) {
        this.idBiodata = idBiodata;
    }

//    public String getNoHp() {
//        return noHp;
//    }

//    public void setNoHp(String noHp) {
//        this.noHp = noHp;
//    }

    public Date getTgl() {
        return tgl;
    }

    public void setTgl(Date tgl) {
        this.tgl = tgl;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }
}
