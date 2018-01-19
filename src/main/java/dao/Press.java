package main.java.dao;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Inno Fang on 2018/1/17.
 */
public class Press {

    private final SimpleStringProperty no = new SimpleStringProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty tel = new SimpleStringProperty();

    public Press() {
    }

    public Press(String no, String name, String tel) {
        this.no.set(no);
        this.name.set(name);
        this.tel.set(tel);
    }

    public String getNo() {
        return no.get();
    }

    public void setNo(String no) {
        this.no.set(no);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    public String getTel() {
        return tel.get();
    }

    public void setTel(String tel) {
        this.tel.set(tel);
    }

    public SimpleStringProperty noProperty() {
        return no;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty telProperty() {
        return tel;
    }

    @Override
    public String toString() {
        return "Press{" +
                "no=" + no +
                ", name=" + name +
                ", tel=" + tel +
                '}';
    }
}
