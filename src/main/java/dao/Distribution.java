package dao;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Inno Fang on 2018/1/18.
 */
public class Distribution {

    private final SimpleStringProperty adminNo = new SimpleStringProperty();
    private final SimpleStringProperty textbookNo = new SimpleStringProperty();
    private final SimpleStringProperty textbookName = new SimpleStringProperty();
    private final SimpleIntegerProperty number = new SimpleIntegerProperty();
    private final SimpleStringProperty press = new SimpleStringProperty();
    private final SimpleStringProperty distributionDate = new SimpleStringProperty();
    private final SimpleStringProperty receiver = new SimpleStringProperty();


    public Distribution() {
    }

    public String getAdminNo() {
        return adminNo.get();
    }

    public SimpleStringProperty adminNoProperty() {
        return adminNo;
    }

    public void setAdminNo(String adminNo) {
        this.adminNo.set(adminNo);
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

    public String getPress() {
        return press.get();
    }

    public SimpleStringProperty pressProperty() {
        return press;
    }

    public void setPress(String press) {
        this.press.set(press);
    }

    public String getDistributionDate() {
        return distributionDate.get();
    }

    public SimpleStringProperty distributionDateProperty() {
        return distributionDate;
    }

    public void setDistributionDate(String distributionDate) {
        this.distributionDate.set(distributionDate);
    }

    public String getReceiver() {
        return receiver.get();
    }

    public SimpleStringProperty receiverProperty() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver.set(receiver);
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

    @Override
    public String toString() {
        return "Distribution{" +
                "adminNo=" + adminNo +
                ", textbookNo=" + textbookNo +
                ", textbookName=" + textbookName +
                ", number=" + number +
                ", press=" + press +
                ", distributionDate=" + distributionDate +
                ", receiver=" + receiver +
                '}';
    }
}
