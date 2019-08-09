package com.example.example.repository;

import com.example.example.util.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author osly
 * @Email
 * @Date 2019/8/2 9:37
 */
@Repository
public class FactoryReportRepository {
    @Autowired
    private RestUtils restUtils;

    public Object getFactoryReportData(Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "SELECT factoryId AS factoryId, SUM(IF(overInDays=0,1,0)) AS day100,SUM(IF(overInDays=1,1,0)) AS day95,\n"
                + "                SUM(IF(overInDays=2,1,0)) AS day90,\n"
                + "                SUM(IF(overInDays>2,1,0)) AS day89, SUM(overinDays) AS hourInMonth\n"
                + "                FROM\n"
                + "                (SELECT factoryId,LEFT(hours,8) AS days, SUM(IF(overCount>0 ,1,0)) AS overInDays\n"
                + "                  FROM(SELECT l.factoryId,  DATE_FORMAT(d_dateTime,'%Y%m%d%H') hours,\n"
                + "                    SUM(CASE\n" + "                      WHEN l.dtype=1 AND r.d_values>1000 THEN 1\n"
                + "                      WHEN l.dtype=1 AND r.d_values<=1000 THEN 0\n"
                + "                      WHEN l.dtype=2 AND r.d_values>100 THEN 1\n"
                + "                      WHEN l.dtype=2 AND r.d_values<=100 THEN 0\n"
                + "                      WHEN l.dtype=3 AND r.d_values>100 THEN 1\n"
                + "                      WHEN l.dtype=3 AND r.d_values<=100 THEN 0\n"
                + "                      WHEN l.dtype=4 AND r.d_values>20 THEN 1\n"
                + "                      WHEN l.dtype=4 AND r.d_values<=20 THEN 0\n" + "                ELSE 0\n"
                + "                     END) AS\n" + "                    overCount\n"
                + "                  FROM real_density r,location l\n"
                + "                  WHERE l.no = r.d_no AND d_dateTime>='" + sdf.format(startDate)
                + "' AND d_dateTime<'" + sdf.format(endDate) + "' AND l.dtype<4\n"
                + "                  GROUP BY l.factoryId,hours ORDER BY l.factoryId,hours ) t1\n"
                + "                 GROUP BY factoryId, LEFT(hours,8)) t2\n" + "                GROUP BY factoryId";
        Object a = restUtils.listAsMap(sql);
        return a;
    }

    public Object getFactory() {
        String sql = "SELECT id,name FROM location WHERE ptype=2";
        return restUtils.listAsMap(sql);
    }
}
