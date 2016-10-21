package com.dengo.erp.config.deserializer;

import com.dengo.erp.model.*;
import com.dengo.erp.model.enums.StatusCandidate;
import com.dengo.erp.model.enums.TypeUser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * JacksonUserDeserializer
 * Custom JSON deserializer for User json deserialization
 *
 * @author Sergey Petrovsky, Andrii Blyznuk
 */

public class JacksonUserDeserializer extends JsonDeserializer<User> {

	private static final Logger LOGGER = LoggerFactory.getLogger(JacksonUserDeserializer.class);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE  ;
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;


    @Override
    public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        LOGGER.debug("Deserializing User object to JSON");

        User user = new User();

        JsonNode jsonNode = p.getCodec().readTree(p);

        Optional.ofNullable(jsonNode.get("id")).ifPresent(id -> user.setId(id.asLong()));
        Optional.ofNullable(jsonNode.get("name")).ifPresent(name -> user.setName(name.asText()));
        Optional.ofNullable(jsonNode.get("lastName")).ifPresent(lastName -> user.setLastName(lastName.asText()));
        Optional.ofNullable(jsonNode.get("birthday")).ifPresent(birthday -> user.setBirthday(LocalDate.parse(birthday.asText(), dateTimeFormatter)));
        Optional.ofNullable(jsonNode.get("position")).ifPresent(position -> user.setPosition(position.asText()));

        //Email is business key, it set without checking for null
        user.setEmail(jsonNode.get("email").asText());

        Optional.ofNullable(jsonNode.get("phone")).ifPresent(phone -> user.setPhone(phone.asText()));
        Optional.ofNullable(jsonNode.get("photo")).ifPresent(photo -> user.setPhoto(photo.asText()));
        Optional.ofNullable(jsonNode.get("previewPhoto")).ifPresent(previewPhoto -> user.setPreviewPhoto(previewPhoto.asText()));
        Optional.ofNullable(jsonNode.get("city")).ifPresent(city -> user.setCity(city.asText()));
        Optional.ofNullable(jsonNode.get("messengerLogin")).ifPresent(messengerLogin -> user.setMessengerLogin(messengerLogin.asText()));
        Optional.ofNullable(jsonNode.get("type")).ifPresent(type -> user.setType(TypeUser.valueOf(type.asText())));
        Optional.ofNullable(jsonNode.get("statusToCandidate")).ifPresent(statusToCandidate -> user.setStatusToCandidate(StatusCandidate.valueOf(statusToCandidate.asText())));

        //Section for skills
        Optional.ofNullable(jsonNode.get("skills")).ifPresent(skillJsonNodes -> {
            Set<Skill> skills = new HashSet<>();
            skillJsonNodes.forEach(skillJsonNode -> {
                Skill skillDTO = new Skill();
                skillDTO.setName(skillJsonNode.get("name").asText());
                skills.add(skillDTO);
            });
            user.setSkills(skills);
        });

        //Section for hobbies
        Optional.ofNullable(jsonNode.get("hobbies")).ifPresent(hobbyJsonNodes -> {
            Set<String> hobbies = new HashSet<>();
            hobbyJsonNodes.forEach(hobbyJsonNode -> {
                hobbies.add(hobbyJsonNode.asText());
            });
            user.setHobbies(hobbies);
        });

        //Section for step to candidate
        //Section deserializer steps that are used while
        //the user is a candidate for Employment opportunities
        Optional.ofNullable(jsonNode.get("stepToCandidate")).ifPresent(stepToCandidateJsonNodes -> {
            Set<Step> stepForVacancies = new HashSet<>();
            stepToCandidateJsonNodes.forEach(stepToCandidateJsonNode -> {
                Step step = new Step();
                step.setId(stepToCandidateJsonNode.get("id").asLong());
                if (!stepToCandidateJsonNode.get("file").asText().equals("null")){
                    step.setFile(stepToCandidateJsonNode.get("file").asText());
                }
                Optional.ofNullable(stepToCandidateJsonNode.get("vacancy")).ifPresent(vacancyJsonNode -> {
                    Vacancy vacancy = new Vacancy();
                    if (vacancyJsonNode.get("id") != null){
                        vacancy.setId(vacancyJsonNode.get("id").asLong());
                    }
                    step.setVacancy(vacancy);
                });
                Optional.ofNullable(stepToCandidateJsonNode.get("responsible")).ifPresent(responsibleJsonNode -> {
                    User responsible = new User();
                    if (responsibleJsonNode.get("id") != null){
                        responsible.setId(responsibleJsonNode.get("id").asLong());
                    }
                    step.setResponsible(responsible);
                });
                if (!stepToCandidateJsonNode.get("time").asText().equals("null")){
                    step.setTime(LocalTime.parse(stepToCandidateJsonNode.get("time").asText(), timeFormatter));
                } else {
                    step.setTime(LocalTime.of(0,0,0));
                }
                if (!stepToCandidateJsonNode.get("date").asText().equals("null")){
                    step.setDate(LocalDate.parse(stepToCandidateJsonNode.get("date").asText(), dateTimeFormatter));
                } else {
                    step.setDate(LocalDate.of(0,1,1));
                }
                Optional.ofNullable(stepToCandidateJsonNode.get("interviewer")).ifPresent(interviewerJsonNodes -> {
                        Set<User> interviewers = new HashSet<>();
                        interviewerJsonNodes.forEach(interviewerJsonNode -> {
                            User interviewer = new User();
                            interviewer.setId(interviewerJsonNode.get("id").asLong());
                            interviewer.setName(interviewerJsonNode.get("name").asText());
                            interviewer.setLastName(interviewerJsonNode.get("lastName").asText());
                            interviewer.setEmail(interviewerJsonNode.get("email").asText());
                            interviewers.add(interviewer);
                        });
                        step.setInterviewer(interviewers);
                    });
                Optional.ofNullable(stepToCandidateJsonNode.get("marks")).ifPresent(markJsonNodes -> {
                    Set<Mark> marks = new HashSet<>();
                    markJsonNodes.forEach(markJsonNode -> {
                        Mark mark = new Mark();
                        mark.setId(markJsonNode.get("id").asLong());
                        mark.setMark(markJsonNode.get("mark").asDouble());
                        Optional.ofNullable(markJsonNode.get("comment")).ifPresent(commentJsonNode -> {
                                Comment comment = new Comment();
                                comment.setId(commentJsonNode.get("id").asLong());
                                comment.setComment(commentJsonNode.get("comment").asText());
                            mark.setComment(comment);
                        });
                        Optional.ofNullable(markJsonNode.get("interviewer")).ifPresent(interviewerJsonNode -> {
                            User interviewer = new User();
                            interviewer.setId(interviewerJsonNode.get("id").asLong());
                            interviewer.setName(interviewerJsonNode.get("name").asText());
                            interviewer.setLastName(interviewerJsonNode.get("lastName").asText());
                            mark.setInterviewer(interviewer);
                        });
                        marks.add(mark);
                    });
                    step.setMarks(marks);
                });
                stepForVacancies.add(step);
            });
            user.setStepToCandidate(stepForVacancies);
        });

        /**
         * Optional functionality for deserialization User with filled fields departments and managedDepartments
         * See specific method for binding in User controller
         */
        /*Optional.ofNullable(jsonNode.get("departments")).ifPresent(departmentJsonNodes -> {
            Set<Department> departments = new HashSet<>();
            departmentJsonNodes.forEach(departmentJsonNode -> {
                Department departmentDTO = new Department();
                departmentDTO.setId(departmentJsonNode.get("id").asLong());
                departmentDTO.setName(departmentJsonNode.get("name").asText());
                departments.add(departmentDTO);
            });
            user.setDepartments(departments);
        });

        Optional.ofNullable(jsonNode.get("managedDepartments")).ifPresent(managedDepartmentJsonNodes -> {
            Set<Department> departments = new HashSet<>();
            managedDepartmentJsonNodes.forEach(managedDepartmentJsonNode -> {
                Department departmentDTO = new Department();
                departmentDTO.setId(managedDepartmentJsonNode.get("id").asLong());
                departmentDTO.setName(managedDepartmentJsonNode.get("name").asText());
                departments.add(departmentDTO);
            });
            user.setManagedDepartments(departments);
        });*/

        return user;
    }
}
