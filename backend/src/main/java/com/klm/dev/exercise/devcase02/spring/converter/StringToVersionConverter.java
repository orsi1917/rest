package com.klm.dev.exercise.devcase02.spring.converter;

import com.klm.dev.exercise.devcase02.version.ApiVersion;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToVersionConverter implements Converter<String, ApiVersion> {
    @Override
    public ApiVersion convert(String acceptHeader) {
//        MediaType.parseMediaType()
        return ApiVersion.determineApiVersion(acceptHeader);
    }
}
