package dao;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Inno Fang on 2018/1/17.
 */
public class Textbook {

    private final SimpleStringProperty no = new SimpleStringProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty version = new SimpleStringProperty();
    private final SimpleFloatProperty price = new SimpleFloatProperty();
    private final SimpleIntegerProperty num = new SimpleIntegerProperty();
    private final SimpleStringProperty pressName = new SimpleStringProperty();

    public Textbook() {
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

    public String getVersion() {
        return version.get();
    }

    public void setVersion(String version) {
        this.version.set(version);
    }

    public float getPrice() {
        return price.get();
    }

    public void setPrice(float price) {
        this.price.set(price);
    }

    public int getNum() {
        return num.get();
    }

    public void setNum(int num) {
        this.num.set(num);
    }

    public String getPressName() {
        return pressName.get();
    }

    public void setPressName(String pressName) {
        this.pressName.set(pressName);
    }

    public SimpleStringProperty noProperty() {
        return no;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty versionProperty() {
        return version;
    }

    public SimpleFloatProperty priceProperty() {
        return price;
    }

    public SimpleIntegerProperty numProperty() {
        return num;
    }

    public SimpleStringProperty pressNameProperty() {
        return pressName;
    }

    @Override
    public String toString() {
        return "Textbook{" +
                "no=" + no +
                ", name=" + name +
                ", version=" + version +
                ", price=" + price +
                ", num=" + num +
                ", pressName=" + pressName +
                '}';
    }
}
