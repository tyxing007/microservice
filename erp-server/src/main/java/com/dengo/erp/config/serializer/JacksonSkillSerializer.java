package com.dengo.erp.config.serializer;

import com.dengo.erp.model.Skill;
import com.dengo.erp.service.UserService;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Sergey Petrovsky
 */
public class JacksonSkillSerializer extends JsonSerializer<Skill> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonSkillSerializer.class);

    @Override
    public void serialize(Skill value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {

        LOGGER.debug("Serializing Skill object to JSON");

        gen.writeStartObject();
            gen.writeStringField("name", value.getName());

            gen.writeFieldName("users");
            gen.writeStartArray();
                Optional.ofNullable(value.getUsers()).ifPresent(users -> users.forEach(user -> {
                    try {
                        gen.writeStartObject();
                            gen.writeNumberField("id", user.getId());
                            gen.writeStringField("name", user.getName());
                            gen.writeStringField("lastName", user.getLastName());
                        gen.writeEndObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
            gen.writeEndArray();

        gen.writeEndObject();
    }
}
