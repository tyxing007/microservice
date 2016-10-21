package com.dengo.erp.model;

import javax.persistence.*;

/**
 * Mark entity
 * Entity containing estimates for the steps
 *
 * @author Andrii Blyznuk
 */

@Entity
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double mark;

    //In variable interviewer will indicate the user who will be
    // responsible for assessing that down,
    // it is used to determine who put this assessment
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private User interviewer ;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Comment comment;


    public Mark() {
    }

    public Mark(Double mark) {
        this.mark = mark;
    }

    public Mark(Double mark, User interviewer, Comment comment) {
        this.mark = mark;
        this.interviewer = interviewer;
        this.comment = comment;
    }

    public Mark(Double mark, User interviewer) {
        this.mark = mark;
        this.interviewer = interviewer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public User getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(User interviewer) {
        this.interviewer = interviewer;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", mark=" + mark +
                ", interviewer=" + interviewer +
                '}';
    }
}
