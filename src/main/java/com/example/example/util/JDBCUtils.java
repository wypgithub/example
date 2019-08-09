package com.example.example.util;



import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class JDBCUtils
{
    /**
     * url格式 -> jdbc:子协议:子名称//主机名:端口号/数据库的名字?属性名=属性值&属性名=属性值
     * configString变量中有多个参数，需要深入地去研究它们的具体含义
     */
    private static String configString = "?useUnicode=true&characterEncoding=utf8&useSSL=true";
    private static String url = "jdbc:mysql://localhost:3306/bjx" + configString;
    // 本地的mysql数据库(无子名称) 端口号3306 数据库jdbcforjava

    private static String user = "root";
    private static String password = "root";

    private JDBCUtils() {}
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void free(ResultSet rs, PreparedStatement ps, Connection con) throws SQLException {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    throw e;
                }
            }
        }
    }


    public static List<Map<String,Object>> query(String sql){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        try {
            // 注册驱动并建立连接
            con = JDBCUtils.getConnection();

            // 创建语句 并 对sql语句进行一些预处理
            ps = con.prepareStatement(sql);

            // 执行语句
            rs = ps.executeQuery();

            // 得到列的个数
            rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            // 因为要存储列的名字，所以要与列的个数相同
            String[] colNames = new String[colCount];

            /* 装上列的名字
             * getColumnLabel 别名
             * getColumnLabel()方法的索引是从1开始的。给最苦 写成从0开始，就抛了异常。
             */
            for (int i = 1; i <= colCount; i++) {
                colNames[i-1] = rsmd.getColumnLabel(i);
            }

            System.out.println("存储列的别名的数组内容是:");
            System.out.println(Arrays.toString(colNames));


            // 这里的泛型声明也是有技巧的。
            HashMap<String,Object> data = null;
            List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
            // 把查询到的结果装入datas中
            while(rs.next()){
                data = new HashMap<String, Object>();
                for (int i = 0; i < colCount; i++) {
                    //        key             value
                    data.put(colNames[i], rs.getObject(colNames[i]));
                }
                datas.add(data);
            }

            // 返回结果
            return datas;

        } catch (SQLException e) {
           e.printStackTrace();

        } finally {
            // 释放资源
            try {
                JDBCUtils.free(rs, ps, con);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
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
                "    WHERE l.no = r.d_no AND d_dateTime>='2018-12-11' AND d_dateTime<'2019-08-09' \n" +
                "    GROUP BY l.id,hours,d_no ORDER BY d_no,hours) t\n" +
                "GROUP BY t.id\n";
        List<Map<String,Object>> result = query(sql);

        System.out.println(11111);
    }
}
