package com.klm.dev.exercises.devcase02.randomquote;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String quote;

    public Value() {
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
