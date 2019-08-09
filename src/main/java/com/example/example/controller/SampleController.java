package com.example.example.controller;

import java.util.Date;
import java.util.List;

import com.example.example.model.Location;
import com.example.example.model.Sample;
import com.example.example.model.Wind;
import com.example.example.service.SampleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @RequestMapping("other")
    public List<Sample> other(Date date){
        return  sampleService.list();
    }
//
//    @RequestMapping("sample")
//    public Sample sample(@RequestBody Sample sample){
//        return sample;
//    }
//
//    private SampleService service(){
//        sampleService.getById(1,Sample.class);
//        return  (SampleService) getBaseService();
//    }
//
//    @RequestMapping("wind")
//    public List<Wind> wind(String startDate)
//    {
//       return service().getWindByDate(startDate);
//    }
//
//    @RequestMapping("locationview")
//    public ModelAndView locationview()
//    {
//        return  new ModelAndView("location");
//    }
//
//    @RequestMapping("location")
//    public List<Location> location(Integer ptype)
//    {
//        return service().getlocationByptype(ptype);
//    }
//
//    /**
//     * JAVA中的方法都是virtual method,所以可以直接重载基类的方法以提供自定义的实现。
//     * @return
//     */
//    @Override
//    public String getSql() {
//        return getSql;
//    }
//
//    @Override
//    public String listSql() {
//        return listSql;
//    }
}