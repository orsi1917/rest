package com.klm.dev.exercise.devcase02.version;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public enum ApiVersion {
    V1("com.klm.dev.exercise.devcase02.v1"),
    V2("com.klm.dev.exercise.devcase02.v2");

    private static Map<String, ApiVersion> versionMap;

    static {
        versionMap = new HashMap<>();
        versionMap.put("application/json;version=com.klm.dev.exercise.devcase02.v1", ApiVersion.V1);
        versionMap.put("com.klm.dev.exercise.devcase02.v2", ApiVersion.V2);
    }

    @Getter
    private String value;

    ApiVersion(String version) {
        this.value = version;
    }

    public static ApiVersion determineApiVersion(String acceptHeader) {
        String[] acceptHeaderArray = acceptHeader.split(",");
        return Arrays.stream(acceptHeaderArray)
//                .map(header -> {
//
//                })
                .filter(value -> Objects.nonNull(value))
                .map(header -> versionMap.get(header))
                .filter(value -> Objects.nonNull(value))
                .findFirst()
                .orElse(ApiVersion.V2);
    }

}
