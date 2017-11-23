package com.klm.dev.exercise.devcase02.Error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorObject {
    private int statusCode;
    private String errorMessage;
}
