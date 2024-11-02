package org.muslim.models;


import com.fasterxml.jackson.annotation.JsonProperty;

public record Response(int code, String status, Data data) {

    public Response(int code, String status) {
        this(code, status, null);
    }

    public record Data(Timings timings) {

        public record Timings(
                @JsonProperty("Fajr") String fajr,
                @JsonProperty("Dhuhr") String dhuhr,
                @JsonProperty("Asr") String asr,
                @JsonProperty("Maghrib") String maghrib,
                @JsonProperty("Isha") String isha) {
        }
    }
}
