package com.example.example.repository;

import com.example.example.model.Sample;
import com.example.example.util.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SampleRepository /*extends BaseRepository<Sample> */ {
    @Autowired
    private RestUtils restUtils;

    public List<Sample> list() {
        String sql = "select * from location";
        return restUtils.list(sql, Sample.class);
    }

//    // 测试在一个Repository返回其他数据类型。
//    public Location returnOtherValue(String sql) {
//        return getRestUtils().get(sql, Location.class);
//    }
//
//    public List<Wind> getWindByDate(String date) {
//        String sql = "select * from wind where date_time>='" + date + "'";
//        return getRestUtils().list(sql, Wind.class);
//    }
//    public List<Location> getlocationByptype(Integer ptype){
//        String sql = "select * from location where ptype=" + ptype + "";
//        return getRestUtils().list(sql, Location.class);
//    }
}