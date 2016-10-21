package com.dengo.erp.model;

import com.dengo.erp.config.deserializer.JacksonUserDeserializer;
import com.dengo.erp.config.serializer.JacksonUserSerializer;
import com.dengo.erp.model.enums.StatusCandidate;
import com.dengo.erp.model.enums.TypeUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * User entity
 * Entity that holds users's representation
 *
 * @author Sergey Petrovsky, Andrii Blyznuk, Dmitry Sheremet
 */
@Entity
@NamedEntityGraph(
        name = "user.departments",
        attributeNodes = {
                @NamedAttributeNode(value = "departments"),
                @NamedAttributeNode(value = "managedDepartments"),
                @NamedAttributeNode(value = "skills"),
                @NamedAttributeNode(value = "hobbies")
        }
)
@JsonSerialize(using = JacksonUserSerializer.class)
@JsonDeserialize(using = JacksonUserDeserializer.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String position;
    @NotNull
    private String email;
    private String password;
    private String phone;
    private String photo;
    private String previewPhoto;
    private String city;
    private String messengerLogin;

    @ManyToMany(mappedBy = "employees", cascade = CascadeType.MERGE)
    private Set<Department> departments = new HashSet<>();

    @OneToMany(mappedBy = "director", cascade = CascadeType.MERGE)
    private Set<Department> managedDepartments = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    private Set<Skill> skills = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name="hobbiesList",
            joinColumns=@JoinColumn(name="user_id")
    )
    private Set<String> hobbies = new HashSet<>();

    @Transient
    @JsonProperty
    private Set<User> directors;
    @Transient
    @JsonProperty
    private Set<User> subjects;

    private String token;

    @Enumerated(EnumType.ORDINAL)
    private TypeUser type;

    //When someone is as candidate to be used in variable statusToCandidate
    // it will indicate the status of candidate.
    @Enumerated(EnumType.ORDINAL)
    private StatusCandidate statusToCandidate;

    //When the user will participate in an interview with the candidate
    // for the position will be used stepToInterviewer variable in order
    // to have a list of steps which will engage the user (Interviewer)
    @ManyToMany(mappedBy = "interviewer", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Set<Step> stepToInterviewer = new HashSet<>();

    //If a user is a candidate for the position will be used
    // to store variable vacancies job listings which candidate will participate
    @ManyToMany(mappedBy = "candidates", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Set<Vacancy> vacancies = new HashSet<>();

    //If a user is a candidate for the position it will
    // store a list of steps he has to go for assessment of his knowledge
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Step> stepToCandidate = new HashSet<>();


    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public User withDepartment(Department department) {
        department.getEmployees().add(this);
        this.departments.add(department);
        return this;
    }

    public User withDepartment(Department... departments) {
        fillByDepartments(Stream.of(departments));
        return this;
    }

    public User withDepartment(Collection<Department> collection) {
        fillByDepartments(collection.stream());
        return this;
    }

    private void fillByDepartments(Stream<Department> stream) {
        List<Department> list = stream
                .map(department -> {
                    department.getEmployees().add(this);
                    return department;
                })
                .collect(Collectors.toList());

        this.departments.addAll(list);
    }

    public User withSkills(Skill skill) {
        skill.getUsers().add(this);
        this.addSkill(skill);
        return this;
    }

    public User withSkills(Skill... skill) {
        fillBySkills(Stream.of(skill));
        return this;
    }

    public User withSkills(Collection<Skill> collection) {
        fillBySkills(collection.stream());
        return this;
    }

    private void fillBySkills(Stream<Skill> stream) {
        Set<Skill> set = stream
                .map(user -> {
                    user.getUsers().add(this);
                    return user;
                })
                .collect(Collectors.toSet());

        this.setSkills(set);
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public void setManagedDepartments(Set<Department> managedDepartments) {
        this.managedDepartments = managedDepartments;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public Set<Department> getManagedDepartments() {
        return managedDepartments;
    }

    public Set<User> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<User> directors) {
        this.directors = directors;
    }

    public Set<User> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<User> subjects) {
        this.subjects = subjects;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPreviewPhoto() {
        return previewPhoto;
    }

    public void setPreviewPhoto(String previewPhoto) {
        this.previewPhoto = previewPhoto;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessengerLogin() {
        return messengerLogin;
    }

    public void setMessengerLogin(String setMessengerLogin) {
        this.messengerLogin = setMessengerLogin;
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<String> hobbies) {
        this.hobbies = hobbies;
    }

    public TypeUser getType() {
        return type;
    }

    public void setType(TypeUser typeUser) {
        this.type = typeUser;
    }

    public Set<Step> getStepToInterviewer() {
        return stepToInterviewer;
    }

    public void setStepToInterviewer(Set<Step> step) {
        this.stepToInterviewer = step;
    }

    public void addStepToInterviewer (Step step){
        this.stepToInterviewer.add(step);
    }

    public Set<Step> getStepToCandidate() {
        return stepToCandidate;
    }

    public void setStepToCandidate(Set<Step> step) {
        this.stepToCandidate = step;
    }

    public void addStepToCandidate (Step step){
        this.stepToCandidate.add(step);
    }

    public StatusCandidate getStatusToCandidate() {
        return statusToCandidate;
    }

    public void setStatusToCandidate(StatusCandidate statusToCandidate) {
        this.statusToCandidate = statusToCandidate;
    }

    public void setTypeByString(String type){
        for (TypeUser typeUser: TypeUser.values()) {
            if (typeUser.name().equals(type)) this.type = typeUser;
        }
    }

    public Set<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(Set<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public void addVacancy(Vacancy vacancy){
        this.vacancies.add(vacancy);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email.equals(user.email);

    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", previewPhoto='" + previewPhoto + '\'' +
                ", city='" + city + '\'' +
                ", messengerLogin='" + messengerLogin + '\'' +
                ", departments=" + departments +
                ", managedDepartments=" + managedDepartments +
                ", skills=" + skills +
                ", hobbies=" + hobbies +
                ", directors=" + directors +
                ", subjects=" + subjects +
                ", token='" + token + '\'' +
                ", type=" + type +
                ", step=" + stepToInterviewer +
                '}';
    }
}
