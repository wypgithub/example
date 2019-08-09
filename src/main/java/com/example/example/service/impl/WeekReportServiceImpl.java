package com.example.example.service.impl;

import com.example.example.repository.WeekReportRepository;
import com.example.example.service.WeekReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WeekReportServiceImpl implements WeekReportService {

    @Autowired
    private WeekReportRepository weekReportRepository;

    @Override
    public Object getWeekReportData(Date startDate, Date endDate) {
        return weekReportRepository.getWeekReportData(startDate, endDate);
    }

    public Object getFactory(){
        return weekReportRepository.getFactory();
    }
}
