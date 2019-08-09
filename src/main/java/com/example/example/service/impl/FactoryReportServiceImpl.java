package com.example.example.service.impl;

import com.example.example.repository.FactoryReportRepository;
import com.example.example.service.FactoryReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author osly
 * @Email
 * @Date 2019/8/2 9:37
 */
@Service
public class FactoryReportServiceImpl implements FactoryReportService {
    @Autowired
    private FactoryReportRepository factoryReportRepository;

    @Override
    public Object getFactoryReportData(Date startDate, Date endDate) {
        return factoryReportRepository.getFactoryReportData(startDate, endDate);
    }

    @Override
    public Object getFactory() {
        return factoryReportRepository.getFactory();
    }
}
