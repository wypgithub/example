//package com.example.example.repository;
//
//import com.example.example.util.RestUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.Map;
//
//public abstract class BaseRepository<T> {
//    @Autowired
//    protected RestUtils restUtils;
//
//    public T get(String sql, Class<T> clazz) {
//        return restUtils.get(sql, clazz);
//    }
//
//    public List<T> list(String sql, Class<T> clazz) {
//        return restUtils.list(sql, clazz);
//    }
//
//    public Map<String, Object> getAsMap(String sql) {
//        return restUtils.getAsMap(sql);
//    }
//
//    public List<Map<String, Object>> listAsMap(String sql) {
//        return restUtils.listAsMap(sql);
//    }
//
//    public void update(String sql) {
//        restUtils.update(sql);
//    }
//
//    public void insert(String sql) {
//        restUtils.insert(sql);
//    }
//
//    public void delete(String sql) {
//        restUtils.delete(sql);
//    }
//
//    public RestUtils getRestUtils() {
//        return restUtils;
//    }
//}
