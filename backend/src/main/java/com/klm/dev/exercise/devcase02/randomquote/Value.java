package com.klm.dev.exercise.devcase02.randomquote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class Value {
    private Long id;
    private String quote;

}
