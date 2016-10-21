package com.dengo.erp.model;

import javax.persistence.*;

/**
 * Comment entity
 * The house which stands for total project as the comet to be shared
 *
 * @author Andrii Blyznuk
 */

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition="TEXT")
    private String comment;

    //In owner variable will indicate the user who left comment
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private User owner;

    public Comment() {
    }

    public Comment(String comment) {
        this.comment = comment;
    }

    public Comment(String comment, User owner) {
        this.comment = comment;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
