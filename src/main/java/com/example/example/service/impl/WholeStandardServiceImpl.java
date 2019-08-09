package com.example.example.service.impl;

import com.example.example.repository.WholeStandardRepository;
import com.example.example.service.WholeStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.MessageHandler;
import java.util.Date;

@Service
public class WholeStandardServiceImpl implements WholeStandardService {
    @Autowired
    private WholeStandardRepository wholeStandardRepository;

    @Override
    public Object getWholeStandardData(Date startDate, Date endDate) {
        return wholeStandardRepository.getWholeStandardData(startDate, endDate);
    }

    @Override
    public Object getFactory() {
        return wholeStandardRepository.getFactory();
    }
}
