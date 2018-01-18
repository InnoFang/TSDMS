package dao;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Inno Fang on 2018/1/17.
 */
public class ClassInfo {

    private final SimpleStringProperty classNo = new SimpleStringProperty();
    private final SimpleStringProperty course = new SimpleStringProperty();
    private final SimpleIntegerProperty stuNumber = new SimpleIntegerProperty();
    private final SimpleStringProperty monitor = new SimpleStringProperty();

    public ClassInfo() {
    }

    public ClassInfo(String classNo, String course, int stuNumber, String monitor) {
        this.classNo.set(classNo);
        this.course.set(course);
        this.stuNumber.set(stuNumber);
        this.monitor.set(monitor);
    }

    public String getClassNo() {
        return classNo.get();
    }

    public SimpleStringProperty classNoProperty() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo.set(classNo);
    }

    public String getCourse() {
        return course.get();
    }

    public SimpleStringProperty courseProperty() {
        return course;
    }

    public void setCourse(String course) {
        this.course.set(course);
    }

    public int getStuNumber() {
        return stuNumber.get();
    }

    public SimpleIntegerProperty stuNumberProperty() {
        return stuNumber;
    }

    public void setStuNumber(int stuNumber) {
        this.stuNumber.set(stuNumber);
    }

    public String getMonitor() {
        return monitor.get();
    }

    public SimpleStringProperty monitorProperty() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor.set(monitor);
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "classNo=" + classNo +
                ", course=" + course +
                ", stuNumber=" + stuNumber +
                '}';
    }
}
