package com.example.example.repository;

import com.example.example.util.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class WeekReportRepository {
    @Autowired
    private RestUtils restUtils;

    public Object getWeekReportData(Date startDate, Date endDate) {
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
                "    WHERE l.no = r.d_no AND d_dateTime>='2019-07-01' AND d_dateTime<'2019-07-31' \n" +
                "    GROUP BY l.id,hours,d_no ORDER BY d_no,hours) t\n" +
                "GROUP BY t.id\n";
        return restUtils.listAsMap(sql);
        }

    public Object getFactory() {
        String sql = "SELECT id, name FROM location WHERE ptype=4";
        return restUtils.listAsMap(sql);
    }
}
