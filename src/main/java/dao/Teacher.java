package dao;

/**
 * Created by Inno Fang on 2018/1/12.
 */
public class Teacher {

    public enum Sex {
        MALE('男'),
        FEMALE('女');

        char sex;

        Sex(char sex) {
            this.sex = sex;
        }
    }

    private String no;
    private String name;
    private Sex sex = Sex.MALE;
    private String password;
    private String tel;
    private String cno;

    public Teacher() {
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

}
