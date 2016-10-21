package com.dengo.erp.config.deserializer;

import com.dengo.erp.model.Department;
import com.dengo.erp.model.User;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * JacksonDepartmentDeserializer
 * Custom JSON deserializer for Department json deserialization
 *
 * @author Sergey Petrovsky
 */

public class JacksonDepartmentDeserializer extends JsonDeserializer<Department> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonUserDeserializer.class);

    @Override
    public Department deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        LOGGER.debug("Deserializing Department object to JSON");

        Department department = new Department();

        JsonNode jsonNode = p.getCodec().readTree(p);

        Optional.ofNullable(jsonNode.get("id")).ifPresent(id -> department.setId(id.asLong()));
        Optional.ofNullable(jsonNode.get("name")).ifPresent(name -> department.setName(name.asText()));
        Optional.ofNullable(jsonNode.get("parent")).ifPresent(id -> department.setParent(id.asLong()));

        /**
         * Optional functionality for deserialization Department with filled fields director and employees
         * See specific method for binding in Department controller
         */
      /*Optional.ofNullable(jsonNode.get("director")).ifPresent(directorJsonNode -> {
            User user = new User();
            user.setId(directorJsonNode.get("id").asLong());
            user.setEmail(directorJsonNode.get("email").asText());
            user.setName(directorJsonNode.get("name").asText());
            department.setDirector(user);
        });

        Optional.ofNullable(jsonNode.get("employees")).ifPresent(employeesJsonNode -> {
            Set<User> employees = new HashSet<>();
            employeesJsonNode.forEach(employeeJsonNode -> {
                User userDTO = new User();
                userDTO.setId(employeeJsonNode.get("id").asLong());
                userDTO.setEmail(employeeJsonNode.get("email").asText());
                userDTO.setName(employeeJsonNode.get("name").asText());
                employees.add(userDTO);
            });
            department.setEmployees(employees);
        });*/


        return department;
    }
}
