package com.dengo.erp.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Step for vacancy entity
 * The Entity contains the steps which will be assessed candidate
 *
 * @author Andrii Blyznuk
 */

@Entity()
@Table(name = "step")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;

    private LocalTime time;

    private String file;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    private Vacancy vacancy;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    private User responsible;

    //In variable interviewer will indicate the user
    // who participates in a step that has a candidate for the vacancy
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Set<User> interviewer = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Mark> marks = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Set<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }

    public void addMark (Mark mark){
        this.marks.add(mark);
    }

    public Set<User> getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(Set<User> interviewer) {
        this.interviewer = interviewer;
    }

    public void addInterviewer(User interviewer) {
        this.interviewer.add(interviewer);
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    @Override
    public String toString() {
        return "StepForVacancy{" +
                "id=" + id +
                ", date=" + date +
                ", file='" + file + '\'' +
                '}';
    }


}
