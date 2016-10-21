package com.dengo.erp.config.serializer;

import com.dengo.erp.config.deserializer.JacksonUserDeserializer;
import com.dengo.erp.model.Department;
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
 * JacksonDepartmentSerializer
 * Custom JSON serializer fo Department json serialization
 *
 * @author Sergey Petrovsky
 */
public class JacksonDepartmentSerializer extends JsonSerializer<Department> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonDepartmentSerializer.class);

    @Override
    public void serialize(Department value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {

        LOGGER.debug("Serializing Department object to JSON");

        gen.writeStartObject();
            gen.writeNumberField("id", value.getId());
            gen.writeStringField("name", value.getName());
            gen.writeFieldName("parent");
                gen.writeStartObject();
                    Optional.ofNullable(value.getParent()).ifPresent(id -> {
                        try {
                            gen.writeNumberField("id", id);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                });
                gen.writeEndObject();

            gen.writeFieldName("director");
                gen.writeStartObject();
                    Optional.ofNullable(value.getDirector()).ifPresent(director -> {
                        try {
                            gen.writeNumberField("id", director.getId());
                            gen.writeStringField("name", director.getName());
                            gen.writeStringField("lastName", director.getLastName());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                gen.writeEndObject();

            gen.writeFieldName("employees");
                gen.writeStartArray();
                    Optional.ofNullable(value.getEmployees()).ifPresent(employees -> employees.forEach(employee -> {
                        try {
                            gen.writeStartObject();
                                gen.writeNumberField("id", employee.getId());
                                gen.writeStringField("name", employee.getName());
                                gen.writeStringField("lastName", employee.getLastName());
                            gen.writeEndObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }));
                gen.writeEndArray();

        gen.writeEndObject();
    }
}