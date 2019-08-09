package com.example.example.util;

import com.example.example.model.Result;
import com.example.example.model.ResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Repository
public final class RestUtils {
    @Value("${app.rest_url}")
    private String uri;

    @Autowired
    private RestTemplate restTemplate;

    public <T> List<T> list(String sql, Class<T> clazz) {
        ResponseEntity<ResultList<T>> entity = restTemplate.exchange(uri, HttpMethod.POST, getEntity(sql),
                ParameterizedTypeReference.forType(
                        ResolvableType.forClassWithGenerics(ResultList.class, clazz).getType())
        );

        ResultList<T> result = entity.getBody();

        return result.getResult();
    }

    public <T> T get(String sql, Class<T> clazz) {
        ResponseEntity<Result<T>> entity = restTemplate.exchange(uri, HttpMethod.POST, getEntity(sql),
                ParameterizedTypeReference.forType(
                        ResolvableType.forClassWithGenerics(Result.class, clazz).getType())
        );

        Result<T> result = entity.getBody();
        return result.getResult();
    }

    public List<Map<String, Object>> listAsMap(String sql) {
        Result<List<Map<String, Object>>> result = restTemplate.exchange(uri, HttpMethod.POST, getEntity(sql),
                new ParameterizedTypeReference<Result<List<Map<String, Object>>>>() {
                }
        ).getBody();
        System.out.println();
        return result.getResult();
        // TODO: verify code here.
    }

    public Map<String, Object> getAsMap(String sql) {
        Result<Map<String, Object>> result = restTemplate.exchange(uri, HttpMethod.POST, getEntity(sql),
                new ParameterizedTypeReference<Result<Map<String, Object>>>() {
                }
        ).getBody();
        // TODO: verify code here.
        return null /*result.getResult()*/;
    }

    public void insert(String sql) {
        restTemplate.exchange(uri, HttpMethod.POST, getEntity(sql), String.class);
    }

    public void update(String sql) {
        restTemplate.exchange(uri, HttpMethod.POST, getEntity(sql), String.class);
    }

    public void delete(String sql) {
        restTemplate.exchange(uri, HttpMethod.POST, getEntity(sql), String.class);
    }


    private static HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private static String toJSONString(String sql) {
        return "{\"sql\":\"" + sql + "\"}";
    }

    private static HttpEntity<String> getEntity(String sql) {
        return new HttpEntity<String>(toJSONString(sql), getDefaultHeaders());
    }
}