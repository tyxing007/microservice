package com.dengo.erp.model;

import com.dengo.erp.config.serializer.JacksonSkillSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Skill entity
 * Entity that holds user's skill
 *
 * @author Andrii Blyznuk
 */


@Entity
@JsonSerialize(using = JacksonSkillSerializer.class)
public class Skill {

    @Id
    private String name;

    @ManyToMany(mappedBy = "skills", fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

    public Skill() {}

    public Skill(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUsers(User user) {
        this.users.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        return name.equals(skill.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
