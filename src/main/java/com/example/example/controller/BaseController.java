//package com.example.example.controller;
//
//import com.example.example.service.BaseService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
///**
// *
// * @param <T>
// */
//public abstract class BaseController<T> {
//    @Autowired
//    private BaseService<T> baseService;
//
////    @RequestMapping("get")
////    public T get(){
////        String getSql=getSql();
////        return baseService.get(getSql);
////    }
//
////    @RequestMapping("list")
////    public List<T> list(){
////        String listSql=listSql();
////        return baseService.list(listSql);
////    }
//
//    @RequestMapping("export")
//    public ModelAndView export() {
//        return null;
//    }
//
//    @RequestMapping("insert")
//    public ResponseEntity<String> insert(@RequestBody T t) {
//        String insertSql = "";
//        baseService.insert(insertSql);
//        return null;
//    }
//
//    @RequestMapping("update")
//    public ResponseEntity<String> update(@RequestBody T t) {
//        String insertSql = "";
//        baseService.insert(insertSql);
//        return null;
//    }
//
//    @RequestMapping("delete")
//    public ResponseEntity<String> delete(@RequestBody T t) {
//        String insertSql = "";
//        baseService.insert(insertSql);
//        return null;
//    }
//
//    public String getSql() {
//        return null;
//    }
//
//    public String listSql() {
//        return null;
//    }
//
//    public BaseService<T> getBaseService() {
//        return baseService;
//    }
//}