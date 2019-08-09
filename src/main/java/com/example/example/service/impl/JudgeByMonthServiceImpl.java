package com.example.example.service.impl;

import com.example.example.repository.JudgeByMonthRepository;
import com.example.example.service.JudgeByMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JudgeByMonthServiceImpl implements  JudgeByMonthService{
    @Autowired
    private JudgeByMonthRepository judgeByMonthRepository;

    @Override
    public List<Map<String, Object>> getMonthData(Date startDate, Date endDate){
        return judgeByMonthRepository.getMonthData(startDate, endDate);
    }

    @Override
    public List<Map<String, Object>> getFactory() {
        return judgeByMonthRepository.getFactory();
    }
    @Override
    public List<Map<String, Object>> getTVOC(Date startDate, Date endDate){
        return judgeByMonthRepository.getTVOC(startDate, endDate);
    }
}
