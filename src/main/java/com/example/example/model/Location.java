package com.example.example.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    @JsonSetter("area_id")
    private Integer areaId;
    private Integer id;
    @JsonAlias("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
}