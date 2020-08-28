package com.roni.genbe.model.dto;

public class PendidikanDto {
    private Integer idEducation;
    private String levelEducation;
    private String institution;
    private String inYear;
    private String endYear;
    private Integer idPerson;

    public Integer getIdEducation() {
        return idEducation;
    }

    public void setIdEducation(Integer idEducation) {
        this.idEducation = idEducation;
    }

    public String getLevelEducation() {
        return levelEducation;
    }

    public void setLevelEducation(String levelEducation) {
        this.levelEducation = levelEducation;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getInYear() {
        return inYear;
    }

    public void setInYear(String inYear) {
        this.inYear = inYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }
}
