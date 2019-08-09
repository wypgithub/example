package com.example.example.controller;

import com.example.example.service.FactoryReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author osly
 * @Email
 * @Date 2019/8/2 9:36
 */
@RestController
@RequestMapping(value = "/JudgeByFacory")
@CrossOrigin
public class FactoryReportController {
    @Autowired
    private FactoryReportService factoryReportService;

    //显示UI界面
    @RequestMapping({"/", "", "index"})
    public ModelAndView showModelAndView(){
        ModelAndView modelAndView = new ModelAndView("factoryReport");
        return  modelAndView;
    }

    @RequestMapping("/showData")
    public Object showData(Date startDate,Date endDate){

        return factoryReportService.getFactoryReportData(startDate, endDate);
    }

    @RequestMapping("/getFactory")
    public Object getFactory(){

        return factoryReportService.getFactory();
    }
}
