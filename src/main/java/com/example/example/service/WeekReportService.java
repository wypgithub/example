package com.example.example.service;

import java.util.Date;

public interface WeekReportService {
    Object getWeekReportData(Date startDate, Date endDate);
    Object getFactory();
}
