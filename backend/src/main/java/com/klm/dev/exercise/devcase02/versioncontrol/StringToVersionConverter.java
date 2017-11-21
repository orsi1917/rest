package com.klm.dev.exercise.devcase02.versioncontrol;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToVersionConverter implements Converter<String, Versions> {
    @Override
    public Versions convert(String acceptHeader) {
        Versions version = Versions.V2;
        if (acceptHeader.contains("com.klm.dev.exercise.devcase02.v1")) version = Versions.V1;
        return version;

    }
}
