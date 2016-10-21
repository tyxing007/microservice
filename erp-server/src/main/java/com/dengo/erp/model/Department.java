package com.dengo.erp.model;

import com.dengo.erp.config.deserializer.JacksonDepartmentDeserializer;
import com.dengo.erp.config.serializer.JacksonDepartmentSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Department entity Entity that holds departments representation
 *
 * @author Sergey Petrovsky, Dmitry Sheremet
 */
@Entity
@JsonSerialize(using = JacksonDepartmentSerializer.class)
@JsonDeserialize(using = JacksonDepartmentDeserializer.class)
@NamedEntityGraph(
        name = "department.employeesAndParentS",
        attributeNodes = {
                @NamedAttributeNode(value = "employees"),
                @NamedAttributeNode(value = "director")
        }
)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    private Long parent;

    @ManyToOne(fetch = FetchType.EAGER)
    private User director;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> employees = new HashSet<>();

    public Department() {
    }

    public Department(String name) {
        this(name, null);
    }

    public Department(String name, Long parent) {
        this.name = name;
        this.parent = parent;
    }

    public Department withEmployee(User user) {
        user.getDepartments().add(this);
        this.employees.add(user);
        return this;
    }

    public Department withEmployee(User... user) {
        fillByEmployees(Stream.of(user));
        return this;
    }

    public Department withEmployee(Collection<User> users) {
        fillByEmployees(users.stream());
        return this;
    }

    public Department withDirector(User user) {
        this.director = user;
        return this;
    }

    private void fillByEmployees(Stream<User> stream) {
        List<User> list = stream
                .map(user -> {
                    user.getDepartments().add(this);
                    return user;
                })
                .collect(Collectors.toList());

        this.employees.addAll(list);
    }

    public void deleteUser(User user) {
        this.employees.remove(user);
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

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public User getDirector() {
        return director;
    }

    public void setDirector(User director) {
        this.director = director;
    }

    public void setEmployees(Set<User> employees) {
        this.employees = employees;
    }

    public Set<User> getEmployees() {
        return employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
