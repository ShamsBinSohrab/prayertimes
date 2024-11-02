package org.muslim.models;

import java.time.LocalDate;

public record Request(String city, String country, String method, String adjustment, LocalDate date) {

    public Request(String city, String country, LocalDate date) {
        this(city, country, null, null, date );
    }
}
