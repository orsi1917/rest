package com.klm.dev.exercise.devcase02.version;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public enum ApiVersion {
    V1,
    V2;
    private static Map<String, ApiVersion> versionMap;

    static {
        versionMap = new HashMap<>();
        versionMap.put("com.klm.dev.exercise.devcase02.v1", ApiVersion.V1);
        versionMap.put("com.klm.dev.exercise.devcase02.v2", ApiVersion.V2);
    }

    public static ApiVersion determineApiVersion(String acceptHeader) {
        String[] acceptHeaderArray = acceptHeader.split(",");
        return Arrays.stream(acceptHeaderArray)
                .map(header -> MediaType.parseMediaType(header).getParameter("version"))
                .filter(value -> Objects.nonNull(value))
                .map(header -> versionMap.get(header))
                .filter(value -> Objects.nonNull(value))
                .findFirst()
                .orElse(ApiVersion.V2);
    }
}

