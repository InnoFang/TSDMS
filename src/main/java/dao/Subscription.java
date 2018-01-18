package dao;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Inno Fang on 2018/1/18.
 */
public class Subscription {

    private final SimpleStringProperty teacherNo = new SimpleStringProperty();
    private final SimpleStringProperty textbookNo = new SimpleStringProperty();
    private final SimpleStringProperty textbookName = new SimpleStringProperty();
    private final SimpleStringProperty version = new SimpleStringProperty();
    private final SimpleIntegerProperty number = new SimpleIntegerProperty();
    private final SimpleStringProperty press = new SimpleStringProperty();
    private final SimpleStringProperty subscribeDate = new SimpleStringProperty();


    public Subscription() {
    }

    public String getTeacherNo() {
        return teacherNo.get();
    }

    public SimpleStringProperty teacherNoProperty() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo.set(teacherNo);
    }

    public String getTextbookNo() {
        return textbookNo.get();
    }

    public SimpleStringProperty textbookNoProperty() {
        return textbookNo;
    }

    public void setTextbookNo(String textbookNo) {
        this.textbookNo.set(textbookNo);
    }

    public String getTextbookName() {
        return textbookName.get();
    }

    public SimpleStringProperty textbookNameProperty() {
        return textbookName;
    }

    public void setTextbookName(String textbookName) {
        this.textbookName.set(textbookName);
    }

    public String getVersion() {
        return version.get();
    }

    public SimpleStringProperty versionProperty() {
        return version;
    }

    public void setVersion(String version) {
        this.version.set(version);
    }

    public int getNumber() {
        return number.get();
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public String getPress() {
        return press.get();
    }

    public SimpleStringProperty pressProperty() {
        return press;
    }

    public void setPress(String press) {
        this.press.set(press);
    }

    public String getSubscribeDate() {
        return subscribeDate.get();
    }

    public SimpleStringProperty subscribeDateProperty() {
        return subscribeDate;
    }

    public void setSubscribeDate(String subscribeDate) {
        this.subscribeDate.set(subscribeDate);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "teacherNo=" + teacherNo +
                ", textbookNo=" + textbookNo +
                ", textbookName=" + textbookName +
                ", version=" + version +
                ", number=" + number +
                ", press=" + press +
                ", subscribeDate=" + subscribeDate +
                '}';
    }
}
