package com.lwrs.app.utils.date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateDeserializer extends JsonDeserializer<Date> {

    public static JsonDateDeserializer INSTANCE = new JsonDateDeserializer();

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.TIME_FORMAT);

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String str = p.getValueAsString();
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            throw new IOException("exception in parse " + str);
        }

    }
}
