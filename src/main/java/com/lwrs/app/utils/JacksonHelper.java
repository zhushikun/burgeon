package com.lwrs.app.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lwrs.app.exception.BurgeonException;
import com.lwrs.app.utils.date.DateModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@Slf4j
public class JacksonHelper {

    public static ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule())
            .registerModule(new DateModule())
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .writerWithDefaultPrettyPrinter();
        return objectMapper;
    }

    public static String obj2Str(Object obj, Class type){
        ObjectMapper objectMapper = getObjectMapper();
        String str;
        try {
            str = objectMapper.writeValueAsString(obj);
            return str;
        } catch (JsonProcessingException e) {
            log.error("exception in obj2Str", e);
            throw new BurgeonException("Exception in obj2Str, class=" + type.getSimpleName());
        }
    }

    public static Object str2Obj(String str, Class type){
        if(StringUtils.isEmpty(str) || null == type){
            return null;
        }
        ObjectMapper objectMapper = getObjectMapper();
        try {
           return objectMapper.readValue(str, type);
        } catch (IOException e) {
            log.error("exception in str2Obj", e);
            throw new BurgeonException(String.format("Exception in str2Obj, str=%s class=%s", str, type.getSimpleName()));
        }
    }


}
