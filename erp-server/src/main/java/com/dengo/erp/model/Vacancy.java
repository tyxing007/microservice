package com.dengo.erp.model;


import com.dengo.erp.model.enums.TypeVacancy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Vacancy entity
 * Entity containing vacancies
 *
 * @author Andrii Blyznuk
 */

@Entity
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private LocalDate date;

    //In the responsible will store user responsible for job
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private User responsible;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<User> candidates = new HashSet<>();

    @Column(columnDefinition="TEXT")
    private String dutiesAndResponsible;

    @Column(columnDefinition="TEXT")
    private String requiredSkills;

    @Column(columnDefinition="TEXT")
    private String willByAPlus;

    @Column(columnDefinition="TEXT")
    private String offer;

    @Enumerated(EnumType.ORDINAL)
    private TypeVacancy type;

    private Boolean hotVacancy;

    private String[] nameStep;


    public Vacancy() {
    }

    public Vacancy(String name, TypeVacancy type) {
        this.name = name;
        this.type = type;
    }

    public Vacancy(String name, TypeVacancy type,  String[] nameStep) {
        this.name = name;
        this.type = type;
        this.nameStep = nameStep;
    }

    public Vacancy(String name, TypeVacancy type,  LocalDate date) {
        this.name = name;
        this.date = date;
        this.type = type;
    }

    public Vacancy(String name, TypeVacancy type,  LocalDate date, String[] nameStep) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.nameStep = nameStep;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public String getDutiesAndResponsible() {
        return dutiesAndResponsible;
    }

    public void setDutiesAndResponsible(String dutiesAndResponsible) {
        this.dutiesAndResponsible = dutiesAndResponsible;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getWillByAPlus() {
        return willByAPlus;
    }

    public void setWillByAPlus(String willByAPlus) {
        this.willByAPlus = willByAPlus;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public TypeVacancy getType() {
        return type;
    }

    public void setType(TypeVacancy type) {
        this.type = type;
    }

    public Set<User> getCandidates() {
        return candidates;
    }

    public void setCandidates(Set<User> candidates) {
        this.candidates = candidates;
    }

    public void addCandidate (User candidate){
        this.candidates.add(candidate);
    }

    public String[] getNameStep() {
        return nameStep;
    }

    public Boolean getHotVacancy() {
        return hotVacancy;
    }

    public void setHotVacancy(Boolean hotVacancy) {
        this.hotVacancy = hotVacancy;
    }

    public void setNameStep(String[] nameStep) {
        this.nameStep = nameStep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacancy vacancy = (Vacancy) o;

        return name.equals(vacancy.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", responsible=" + responsible +
                ", candidate=" + candidates +
                ", dutiesAndResponsible='" + dutiesAndResponsible + '\'' +
                ", requiredSkills='" + requiredSkills + '\'' +
                ", willByAPlus='" + willByAPlus + '\'' +
                ", offer='" + offer + '\'' +
                ", type=" + type +
                '}';
    }


}
