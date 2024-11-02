package org.muslim.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record Request(
        String city,
        String country,
        String method,
        String adjustment,
        @JsonFormat(pattern = "DD/MM/YYYY") LocalDate date) {

    public Request(String city, String country, LocalDate date) {
        this(city, country, null, null, date);
    }
}
