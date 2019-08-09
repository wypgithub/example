//package com.example.example.service.impl;
//
//import com.example.example.repository.BaseRepository;
//import com.example.example.service.BaseService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.Map;
//
//public abstract class BaseServiceImpl<R extends BaseRepository<T>, T> implements BaseService<T> {
//
//    @Autowired
//    private R repository;
//
//    @Override
//    public T get(String sql, Class<T> clazz) {
//        return repository.get(sql, clazz);
//    }
//
//    @Override
//    public Map<String, Object> getAsMap(String sql) {
//        return repository.getAsMap(sql);
//    }
//
//    @Override
//    public List<T> list(String sql, Class<T> clazz) {
//        return repository.list(sql, clazz);
//    }
//
//    @Override
//    public List<Map<String, Object>> listAsMap(String sql) {
//        return repository.listAsMap(sql);
//    }
//
//    @Override
//    public void update(String sql) {
//        repository.update(sql);
//    }
//
//    @Override
//    public void insert(String sql) {
//        repository.insert(sql);
//    }
//
//    @Override
//    public void delete(String sql) {
//        repository.delete(sql);
//    }
//
//    public R getRepository() {
//        return repository;
//    }
//}