package producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.jackson.datatype.VavrModule;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import javax.enterprise.inject.Produces;

import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.ALL;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.SerializationFeature.*;

@ApplicationScoped
public class ObjectMapperJacksonProducer {
    @Produces
    @Singleton
    public ObjectMapper createObjectMapper() {
        var objectMapper = new ObjectMapper()
                .registerModule(new VavrModule())
                .setVisibility(ALL, NONE)
                .setVisibility(FIELD, ANY)
                .setSerializationInclusion(NON_EMPTY)
                .enable(INDENT_OUTPUT)
                .enable(USE_EQUALITY_FOR_OBJECT_ID)
                .enable(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .disable(FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper;
    }

}