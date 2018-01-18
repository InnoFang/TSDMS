package db;

import dao.User;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Inno Fang on 2018/1/4.
 */
public class UserManager {

    public static Map<String, Object> CURRENT_USR = new HashMap<>();

    public static boolean validate(String user, String password, String type) {

        try {
            Map<String, Object> map = null;
            if (type.equals(User.ADMIN)) {
                map = JDBCUtils.get()
                        .getQueryResult("select * from admin where Ano=? and Apassword=?", Arrays.asList(user, password));

            } else {
                map = JDBCUtils.get()
                        .getQueryResult("select * from teacher where Tno=? and Tpassword=?", Arrays.asList(user, password));
            }

            if (map != null && !map.isEmpty()) {
                CURRENT_USR.clear();
                CURRENT_USR.putAll(map);
                CURRENT_USR.put("type", type);
                return true;
            }
            return false;
        } catch (SQLException e) {
//            e.printStackTrace();
            return false;
        }
    }


    public static boolean register(User user) {
        try {
            if (user.getUserType().equals(User.ADMIN)) {
                return JDBCUtils.get()
                        .update("insert into admin values(?, ?, ?, NULL)",
                                Arrays.asList(user.getUserId(), user.getPassword(), user.getContactInfo()));
            } else {
                return JDBCUtils.get()
                        .update("insert into teacher values(?, ?, ?, NULL, NULL, NULL)",
                                Arrays.asList(user.getUserId(), user.getPassword(), user.getContactInfo()));
            }
        } catch (SQLException e) {
//            e.printStackTrace();
            return false;
        }
    }
}
