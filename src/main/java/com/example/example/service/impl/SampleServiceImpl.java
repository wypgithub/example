package com.example.example.service.impl;

import com.example.example.model.Location;
import com.example.example.model.Sample;
import com.example.example.model.Wind;
import com.example.example.repository.SampleRepository;

import com.example.example.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleServiceImpl /*extends BaseServiceImpl<SampleRepository,Sample>*/ implements SampleService {
    @Autowired
    private SampleRepository repository;

    @Override
    public List<Sample> list() {
        return repository.list();
    }

//    @Override
//    public List<Wind> getWindByDate(String startDate) {
//        return getRepository().getWindByDate(startDate);
//    }
//
//    @Override
//    public Location returnOtherValue(String sql){
//        return getRepository().returnOtherValue(sql);
//    }
//
//    @Override
//    public List<Location> getlocationByptype(Integer ptype) {
//        return getRepository().getlocationByptype(ptype);
//    }
}