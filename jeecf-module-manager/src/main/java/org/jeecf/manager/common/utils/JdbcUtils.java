package org.jeecf.manager.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * jdbc 工具类
 * 
 * @author jianyiming
 *
 */
public class JdbcUtils {

    public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    public static final String DBSOURCE_NAME = "com.alibaba.druid.pool.DruidDataSource";

    /**
     * 测试jdbc连接是否正常
     * 
     * @param url
     * @param username
     * @param password
     * @return
     */
    public static boolean test(String url, String username, String password) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            if (conn != null && !conn.isClosed()) {
                conn.close();
                return true;
            }
            conn.close();
            return false;
        } catch (Exception ee) {
            return false;
        }

    }

}
