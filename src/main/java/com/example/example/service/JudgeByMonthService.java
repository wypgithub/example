package com.example.example.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

public interface JudgeByMonthService {
    List<Map<String, Object>> getMonthData(Date startDate, Date endDate);
    List<Map<String, Object>> getFactory();
    List<Map<String, Object>> getTVOC(Date startDate, Date endDate);
}
