package db;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Inno Fang on 2018/1/4.
 */
public class UserManager {

    private static final Map<String, String> MAP = new HashMap<>();

    static {
        MAP.put("demo", "demo");
    }

    public static boolean validate(String user, String password){
        String validUserPassword = MAP.get(user);
        return validUserPassword != null && validUserPassword.equals(password);
    }
}
