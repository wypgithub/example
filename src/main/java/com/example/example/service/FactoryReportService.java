package com.example.example.service;

import java.util.Date;

/**
 * @author osly
 * @Email
 * @Date 2019/8/2 9:37
 */
public interface FactoryReportService {
   Object getFactoryReportData(Date startDate,Date endDate);
   Object getFactory();
}
