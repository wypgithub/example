package com.example.example.repository;

import com.example.example.util.JDBCUtils;
import com.example.example.util.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class JudgeByMonthRepository {
    @Autowired
    private  RestUtils restUtils;

    public List<Map<String, Object>> getMonthData(Date startDate, Date endDate){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String sql = "SELECT id, MAX(maxDensity) AS maxDensity, MIN(minDensity) AS minDensity, \n" +
                "SUM(recordCount) AS recordCount, SUM(overCount) AS densityCount,\n" +
                "SUM(CASE\n" +
                "  WHEN overCount>0 THEN 1\n" +
                "  ELSE 0\n" +
                "  END\n" +
                ") AS densityHours FROM (SELECT l.id, DATE_FORMAT(d_dateTime,'%Y%m%d%H') hours, MAX(d_values) AS maxDensity,\n" +
                "    MIN(d_values) AS minDensity, COUNT(*) AS recordCount, -- 同一小时组中记录个数\n" +
                "    SUM(CASE\n" +
                "      WHEN l.dtype=1 AND r.d_values>1000 THEN 1\n" +
                "      WHEN l.dtype=1 AND r.d_values<=1000 THEN 0\n" +
                "      WHEN l.dtype=2 AND r.d_values>100 THEN 1\n" +
                "      WHEN l.dtype=2 AND r.d_values<=100 THEN 0\n" +
                "      WHEN l.dtype=3 AND r.d_values>100 THEN 1\n" +
                "      WHEN l.dtype=3 AND r.d_values<=100 THEN 0\n" +
                "      WHEN l.dtype=4 AND r.d_values>20 THEN 1\n" +
                "      WHEN l.dtype=4 AND r.d_values<=20 THEN 0\n" +
                "      ELSE 0\n" +
                "      END) AS\n" +
                "    overCount \n" +
                "    FROM real_density r,location l\n" +
                "    WHERE l.no = r.d_no AND d_dateTime>='"+ sdf.format(startDate) +"' AND d_dateTime<'"+ sdf.format(endDate)+"' \n" +
                "    GROUP BY l.id,hours,d_no ORDER BY d_no,hours) t\n" +
                "GROUP BY t.id\n";

        return JDBCUtils.query(sql); //restUtils.listAsMap(sql);
    }

    public List<Map<String, Object>> getTVOC(Date startDate, Date endDate){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "SELECT id, MAX(maxDensity) AS maxTvocDensity, MIN(minDensity) AS minTvocDensity, \n" +
                "SUM(recordCount) AS recordTvocCount, SUM(overCount) AS densityTvocCount,\n" +
                "SUM(CASE\n" +
                "  WHEN overCount>0 THEN 1\n" +
                "  ELSE 0\n" +
                "  END\n" +
                ") AS densityTvocHours FROM (SELECT l.id, DATE_FORMAT(d_dateTime,'%Y%m%d%H') hours, MAX(d_values) AS maxDensity,\n" +
                "    MIN(d_values) AS minDensity, COUNT(*) AS recordCount, -- 同一小时组中记录个数\n" +
                "    SUM(CASE\n" +
                "      WHEN l.dtype=1 AND r.d_values>1500 THEN 1\n" +
                "      WHEN l.dtype=1 AND r.d_values<=1500 THEN 0\n" +
                "      WHEN l.dtype=2 AND r.d_values>500 THEN 1\n" +
                "      WHEN l.dtype=2 AND r.d_values<=500 THEN 0\n" +
                "      WHEN l.dtype=3 AND r.d_values>300 THEN 1\n" +
                "      WHEN l.dtype=3 AND r.d_values<=300 THEN 0\n" +
                "      WHEN l.dtype=4 AND r.d_values>200 THEN 1\n" +
                "      WHEN l.dtype=4 AND r.d_values<=200 THEN 0\n" +
                "      ELSE 0\n" +
                "      END) AS\n" +
                "    overCount \n" +
                "    FROM real_tvoc r,location l\n" +
                "    WHERE l.no = r.d_no AND d_dateTime>='"+ sdf.format(startDate) +"' AND d_dateTime<'"+ sdf.format(endDate)+"' \n" +
                "    GROUP BY l.id,hours,d_no ORDER BY d_no,hours) t\n" +
                "GROUP BY t.id\n";
        return  JDBCUtils.query(sql); // restUtils.listAsMap(sql);
    }

    public List<Map<String, Object>> getFactory() {
        String sql = "SELECT id,name FROM location WHERE ptype=4";
        return JDBCUtils.query(sql); //restUtils.listAsMap(sql);
    }
}
