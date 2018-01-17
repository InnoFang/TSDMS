package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Created by Inno Fang on 2017/11/15.
 */
public class JDBCUtils {

    private String driver;
    private String url;
    private String username;
    private String password;

    private final static String INIT_FILE = "F:\\IDEA Project\\TSDMS\\src\\main\\java\\mysql.ini";

    private static volatile JDBCUtils sInstance;

    private JDBCUtils() {
    }

    public static JDBCUtils get() {
        if (null == sInstance) {
            synchronized (JDBCUtils.class) {
                if (null == sInstance) {
                    sInstance = new JDBCUtils();
                }
            }
        }
        sInstance.initParam(INIT_FILE);
        return sInstance;
    }

    private void initParam(String paramFile) {
        try {
            // 使用 properties 类类加载属性文件
            Properties prop = new Properties();
            prop.load(new FileInputStream(paramFile));
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");

            // 加载驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //////deprecation///////////
    @Deprecated
    public void createTable(String sql) {

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {
            // 执行 DDL 语句， 创建数据表
            stmt.executeUpdate(sql);
            System.out.println("Create table successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Deprecated
    public void insertData(String name, String info) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO test VALUES(null, ?, ?);")) {
            pstmt.setString(1, name);
            pstmt.setString(2, info);
            int rows = pstmt.executeUpdate();
            if (rows != 0) {
                System.out.println("Insert data successfully.");
            } else {
                System.out.println("Failed to delete data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Deprecated
    public <T> List<T> selectData(String sql, OnSelectResultListener<T> listener) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return listener.select(rs);
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String info = rs.getString("info");
//                System.out.printf("ID: %d, name: %s, info: %s\n", id, name, info);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Deprecated
    public void deleteData(int id) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            int rows = stmt.executeUpdate("DELETE FROM test WHERE id = " + id);
            if (rows != 0) {
                System.out.println("Delete data successfully.");
            } else {
                System.out.println("Failed to delete data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Deprecated
    public interface OnSelectResultListener<T> {
        public List<T> select(ResultSet resultSet) throws SQLException;
    }
    @Deprecated
    public interface OnInsertValueListener<T> {
        public boolean insert();
    }
    ////////////////////////////

    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet resultSet;

    /**
     * 获取数据库连接
     * @return
     */
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * 增删改
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public boolean update(String sql, List<Object> params) throws SQLException {

        int result = -1;
        pstmt = getConnection().prepareStatement(sql);
        int index = 1;
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }

        result = pstmt.executeUpdate();
        return result > 0;
    }

    /**
     * 查询单条记录
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public Map<String, Object> getQueryResult(String sql, List<Object> params) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        int index = 1;
        pstmt = getConnection().prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }

        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columns = metaData.getColumnCount();
        while (resultSet.next()) {
            getResultSet(metaData, columns, map);
        }
        return map;
    }

    public List<Map<String, Object>> getQueryResults(String sql, List<Object> params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        int index = 1;
        pstmt = getConnection().prepareStatement(sql);
        if (null != params && !params.isEmpty()) {
            for (int i = 0; i < params.size() ; i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }

        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columns = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String , Object> map = new HashMap<>();
            getResultSet(metaData, columns, map);
            list.add(map);
        }
        return list;
    }

    private void getResultSet(ResultSetMetaData metaData, int columns, Map<String, Object> map) throws SQLException {
        for (int i = 1; i <= columns ; i++) {
            String name = metaData.getColumnName(i);
            Object value = resultSet.getObject(name);
            if (null == value) value = "";
            map.put(name, value);
        }
    }

}
