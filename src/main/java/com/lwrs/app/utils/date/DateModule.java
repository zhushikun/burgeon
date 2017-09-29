package com.lwrs.app.utils.date;

import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Date;

public class DateModule extends SimpleModule{

    public DateModule() {
        addSerializer(Date.class, JsonDateSerializer.INSTANCE);
        addDeserializer(Date.class, JsonDateDeserializer.INSTANCE);
    }
}
