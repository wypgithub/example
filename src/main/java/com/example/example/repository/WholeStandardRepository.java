package com.example.example.repository;

import com.example.example.util.JDBCUtils;
import com.example.example.util.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class WholeStandardRepository {
    @Autowired
    private RestUtils restUtils;

    public Object getWholeStandardData(Date startDate, Date endDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "select DATE_FORMAT(cdate,'%Y-%m-%d') days,ceiling(avg(blocklevel)) avg " +
                "from qualityrating " +
                "group by days where cdate>='" +sdf.format(startDate)+"' " +
                "and cdate<='"+ sdf.format(endDate)+"'";
        return JDBCUtils.query(sql); // restUtils.listAsMap(sql);
    }

    public Object getFactory() {
        String sql = "SELECT id,name FROM location WHERE ptype=2";
        return restUtils.listAsMap(sql);
    }
}
