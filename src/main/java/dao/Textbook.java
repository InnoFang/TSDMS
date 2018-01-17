package dao;

/**
 * Created by Inno Fang on 2018/1/17.
 */
public class Textbook {

    private String no;
    private String name;
    private String version;
    private float price;
    private int num;
    private String pressNo;

    public Textbook() {
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPressNo() {
        return pressNo;
    }

    public void setPressNo(String pressNo) {
        this.pressNo = pressNo;
    }

    @Override
    public String toString() {
        return "Textbook{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", pressNo='" + pressNo + '\'' +
                '}';
    }
}
