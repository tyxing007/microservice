package com.dengo.erp.config.serializer;

import com.dengo.erp.model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * JacksonUserSerializer
 * Custom JSON serializer fo User json serialization
 *
 * @author Sergey Petrovsky, Andrii Blyznuk
 */

public class JacksonUserSerializer extends JsonSerializer<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonUserSerializer.class);

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;

    @Override
    public void serialize(User value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {

        LOGGER.debug("Serializing User object to JSON");

        gen.writeStartObject();
            gen.writeNumberField("id", value.getId());
            gen.writeStringField("name", value.getName());
            gen.writeStringField("lastName", value.getLastName());
            Optional.ofNullable(value.getBirthday()).ifPresent(birthday -> {
                try {
                    gen.writeStringField("birthday", birthday.format(dateTimeFormatter));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            gen.writeStringField("position", value.getPosition());
            gen.writeStringField("email", value.getEmail());
            gen.writeStringField("password", value.getPassword());
            gen.writeStringField("phone", value.getPhone());
            gen.writeStringField("photo", value.getPhoto());
            gen.writeStringField("previewPhoto", value.getPreviewPhoto());
            gen.writeStringField("city", value.getCity());
            gen.writeStringField("messengerLogin", value.getMessengerLogin());

            //Section for skills
            gen.writeFieldName("skills");
                gen.writeStartArray();
                    Optional.ofNullable(value.getSkills()).ifPresent(skills -> skills.forEach(skill -> {
                        try {
                            gen.writeStartObject();
                               gen.writeStringField("name", skill.getName());
                            gen.writeEndObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }));
            gen.writeEndArray();

            //Section for stepTorCandidate
            //Section serialization steps that are used while the
        // user is a candidate for Employment opportunities
            gen.writeFieldName("stepToCandidate");
                gen.writeStartArray();
                    Optional.ofNullable(value.getStepToCandidate()).ifPresent(steps -> steps.forEach(step -> {
                        try {
                            gen.writeStartObject();
                                gen.writeNumberField("id", step.getId());
                                gen.writeStringField("file", step.getFile());
                                gen.writeStringField("date", step.getDate().format(dateTimeFormatter));
                                gen.writeStringField("time", step.getTime().format(timeFormatter));
                                Optional.ofNullable(step.getResponsible()).ifPresent(responsible -> {
                                    try {
                                        gen.writeFieldName("responsible");
                                        gen.writeStartObject();
                                        gen.writeNumberField("id", responsible.getId());
                                        gen.writeStringField("name", responsible.getName());
                                        gen.writeStringField("lastName", responsible.getLastName());
                                        gen.writeEndObject();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                                Optional.ofNullable(step.getVacancy()).ifPresent(vacancy -> {
                                    try {
                                        gen.writeFieldName("vacancy");
                                        gen.writeStartObject();
                                        gen.writeNumberField("id", vacancy.getId());
                                        gen.writeStringField("name", vacancy.getName());
                                        gen.writeEndObject();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                                gen.writeFieldName("interviewer");
                                    gen.writeStartArray();
                                         Optional.ofNullable(step.getInterviewer()).ifPresent(interviewers -> interviewers.forEach(interviewer -> {
                                                try {
                                                    gen.writeStartObject();
                                                        gen.writeNumberField("id", interviewer.getId());
                                                        gen.writeStringField("name", interviewer.getName());
                                                        gen.writeStringField("lastName", interviewer.getLastName());
                                                        gen.writeStringField("email", interviewer.getEmail());
                                                    gen.writeEndObject();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }));
                                    gen.writeEndArray();
                                gen.writeFieldName("marks");
                                    gen.writeStartArray();
                                        Optional.ofNullable(step.getMarks()).ifPresent(marks -> marks.forEach(mark -> {
                                                try {
                                                    gen.writeStartObject();
                                                        gen.writeNumberField("id", mark.getId());
                                                        gen.writeNumberField("mark", mark.getMark());
                                                            Optional.ofNullable(mark.getComment()).ifPresent(comment -> {
                                                                try {
                                                                    gen.writeFieldName("comment");
                                                                        gen.writeStartObject();
                                                                        gen.writeNumberField("id", comment.getId());
                                                                        gen.writeStringField("comment", comment.getComment());
                                                                        gen.writeEndObject();
                                                                } catch (IOException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            });
                                                            Optional.ofNullable(mark.getInterviewer()).ifPresent(interviewer -> {
                                                                try {
                                                                    gen.writeFieldName("interviewer");
                                                                    gen.writeStartObject();
                                                                    gen.writeNumberField("id", interviewer.getId());
                                                                    gen.writeStringField("name", interviewer.getName());
                                                                    gen.writeStringField("lastName", interviewer.getLastName());
                                                                    gen.writeEndObject();
                                                                } catch (IOException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            });
                                                    gen.writeEndObject();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                        }));
                                    gen.writeEndArray();
                            gen.writeEndObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }));
                gen.writeEndArray();

            //Section for step to Interviewer
            gen.writeFieldName("stepToInterviewer");
            gen.writeStartArray();
            Optional.ofNullable(value.getStepToInterviewer()).ifPresent(stepToInterviewers -> stepToInterviewers.forEach(stepToInterviewer -> {
                try {
                    gen.writeStartObject();
                    gen.writeNumberField("id", stepToInterviewer.getId());
                    gen.writeStringField("file", stepToInterviewer.getFile());
                    gen.writeStringField("date", stepToInterviewer.getDate().format(dateTimeFormatter));
                    gen.writeStringField("time", stepToInterviewer.getTime().format(timeFormatter));
                    gen.writeEndObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
            gen.writeEndArray();

            //Section for hobbies
            gen.writeFieldName("hobbies");
                gen.writeStartArray();
                    Optional.ofNullable(value.getHobbies()).ifPresent(hobbies -> hobbies.forEach(hobby -> {
                        try {
                            gen.writeString(hobby);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }));
            gen.writeEndArray();

            //Section for type of User
            Optional.ofNullable(value.getType()).ifPresent(type -> {
                try {
                    gen.writeStringField("type", type.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            //Section for status to candidate
            Optional.ofNullable(value.getStatusToCandidate()).ifPresent(type -> {
                try {
                    gen.writeStringField("statusToCandidate", type.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                 }
            });

            //Section for departments
            gen.writeFieldName("departments");
                gen.writeStartArray();
                    Optional.ofNullable(value.getDepartments()).ifPresent(departments -> departments.forEach(department -> {
                        try {
                            gen.writeStartObject();
                                gen.writeNumberField("id", department.getId());
                                gen.writeStringField("name", department.getName());
                                Optional.ofNullable(department.getDirector()).ifPresent(director -> {
                                    try {
                                        gen.writeFieldName("director");
                                            gen.writeStartObject();
                                                gen.writeNumberField("id", director.getId());
                                                gen.writeStringField("name", director.getName());
                                                gen.writeStringField("lastName", director.getLastName());
                                            gen.writeEndObject();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                            gen.writeEndObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }));
            gen.writeEndArray();

            //Section for directors
            gen.writeFieldName("directors");
                gen.writeStartArray();
                    Optional.ofNullable(value.getDirectors()).ifPresent(directors -> directors.forEach(director -> {
                        try {
                            gen.writeStartObject();
                                gen.writeNumberField("id", director.getId());
                                gen.writeStringField("name", director.getName());
                                gen.writeStringField("lastName", director.getLastName());
                            gen.writeEndObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }));
                gen.writeEndArray();

            //Section for managed departments
            gen.writeFieldName("managedDepartments");
                gen.writeStartArray();
                    Optional.ofNullable(value.getManagedDepartments()).ifPresent(departments -> departments.forEach(department -> {
                        try {
                            gen.writeStartObject();
                                gen.writeNumberField("id", department.getId());
                                gen.writeStringField("name", department.getName());
                            gen.writeEndObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }));
                gen.writeEndArray();

            //Section for subjects
            gen.writeFieldName("subjects");
                gen.writeStartArray();
                    Optional.ofNullable(value.getDirectors()).ifPresent(directors -> directors.forEach(director -> {
                        try {
                            gen.writeStartObject();
                                gen.writeNumberField("id", director.getId());
                                gen.writeStringField("name", director.getName());
                                gen.writeStringField("lastName", director.getLastName());
                            gen.writeEndObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }));
                gen.writeEndArray();

        gen.writeEndObject();
    }
}