package com.example.example.model;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Date;

public class Density {
    private Integer id;
    @JsonSetter("location_id")
    private Integer locationid;

    private Date edate;

    private Double density;

    private Double acrylo;

    private Double tetrahyd;

    private Double toluene;

    private Double chlorob;

    private Double dichloro;

    private Double lamine;

    private Double lformam;

    private Double air;

}
