package com.example.example.service;

import com.example.example.model.Location;
import com.example.example.model.Sample;
import com.example.example.model.Wind;

import java.util.List;

public interface SampleService {
    List<Sample> list();
    //List<Location> list();
}