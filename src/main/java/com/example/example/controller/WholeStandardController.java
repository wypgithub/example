package com.example.example.controller;


import com.example.example.service.WholeStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class WholeStandardController {

    @Autowired
    private WholeStandardService wholeStandardService;


    @RequestMapping("/WholeStandard")
    public ModelAndView showModelAndView(){
        ModelAndView modelAndView = new ModelAndView("wholeStandard");
        return  modelAndView;
    }

    @RequestMapping("/showData")
    public Object showData(Date startDate, Date endDate){

        return wholeStandardService.getWholeStandardData(startDate, endDate);
    }

    @RequestMapping("/getFactory")
    public Object getFactory(){

        return wholeStandardService.getFactory();
    }

}
