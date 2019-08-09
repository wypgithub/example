package com.example.example.controller;


import com.example.example.service.JudgeByMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/JudgeByMonth")
@CrossOrigin
public class JudgeByMonth {
    @Autowired
    private JudgeByMonthService judgeByMonthService;

    @RequestMapping({"/", "", "index"})
    public ModelAndView showModelAndView(){
        ModelAndView modelAndView = new ModelAndView("JudgeByMonth");
        return  modelAndView;
    }

    @RequestMapping("/showData")
    public Object showData(Date startDate, Date endDate){
        List<Map<String, Object>> StinkData = judgeByMonthService.getMonthData(startDate, endDate);
        List<Map<String, Object>> TVOCData = judgeByMonthService.getTVOC(startDate, endDate);
        List<Map<String, Object>> MonthData = new ArrayList<>();
        MonthData.addAll(StinkData);
        MonthData.addAll(TVOCData);
        System.out.println(MonthData);
        return MonthData;
    }

    @RequestMapping("/getFactory")
    public Object getFactory(){
        return judgeByMonthService.getFactory();
    }

}
