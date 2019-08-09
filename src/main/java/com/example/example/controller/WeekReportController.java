package com.example.example.controller;

import com.example.example.service.WeekReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@RestController
@RequestMapping(value = "/JudgeByWeek")
@CrossOrigin
public class WeekReportController {

    @Autowired
    private WeekReportService weekReportService;

    @RequestMapping({"/", "", "index"})
    public ModelAndView showModelAndView(){
        ModelAndView modelAndView = new ModelAndView("weekReport");
        return modelAndView;
    }
}
