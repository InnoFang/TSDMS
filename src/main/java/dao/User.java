package main.java.dao;

/**
 * Created by Inno Fang on 2018/1/4.
 */
public class User {

    public static final String ADMIN = "admin";
    public static final String TEACHER = "teacher";

    private String userId;
    private String password;
    private String contactInfo;
    private String userType = ADMIN;

    public User() {
    }

    public User(String userId, String password, String contactInfo, String userType) {
        this.userId = userId;
        this.password = password;
        this.contactInfo = contactInfo;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getUserId() != null ? !getUserId().equals(user.getUserId()) : user.getUserId() != null) return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getUserType() != null ? !getUserType().equals(user.getUserType()) : user.getUserType() != null)
            return false;
        return getContactInfo() != null ? getContactInfo().equals(user.getContactInfo()) : user.getContactInfo() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getUserType() != null ? getUserType().hashCode() : 0);
        result = 31 * result + (getContactInfo() != null ? getContactInfo().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
