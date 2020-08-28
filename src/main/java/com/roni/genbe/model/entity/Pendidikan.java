package com.roni.genbe.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_pendidikan")

public class Pendidikan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pendidikan",updatable = false, nullable = false)
    private Integer idPendidikan;

    @Column(name = "jenjang",length = 10, nullable = false)
    private String jenjang;

    @Column(name = "institusi",length = 50, nullable = false)
    private String institusi;

    @Column(name = "tahunmasuk",length = 10,nullable = false)
    private String tahunMasuk;

    @Column(name = "tahunlulus",length = 10,nullable = false)
    private String tahunLulus;

    @ManyToOne
    @JoinColumn(name = "id_person",nullable = false)
    private Person person;


    public Integer getIdPendidikan() {
        return idPendidikan;
    }

    public void setIdPendidikan(Integer idPendidikan) {
        this.idPendidikan = idPendidikan;
    }

    public String getJenjang() {
        return jenjang;
    }

    public void setJenjang(String jenjang) {
        this.jenjang = jenjang;
    }

    public String getInstitusi() {
        return institusi;
    }

    public void setInstitusi(String institusi) {
        this.institusi = institusi;
    }

    public String getTahunMasuk() {
        return tahunMasuk;
    }

    public void setTahunMasuk(String tahunMasuk) {
        this.tahunMasuk = tahunMasuk;
    }

    public String getTahunLulus() {
        return tahunLulus;
    }

    public void setTahunLulus(String tahunLulus) {
        this.tahunLulus = tahunLulus;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
