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

    public void setClassNo(String classNo) {
        this.classNo.set(classNo);
    }

    public String getClassNo(String classNo) {
        return this.classNo.get();
    }

    public void setCourse(String course) {
        this.course.set(course);
    }

    public String getCourse(String course) {
        return this.course.get();
    }

    public void setStuNumber(int number) {
        this.stuNumber.set(number);
    }

    public int getStuNumber() {
        return this.stuNumber.get();
    }

    public String getClassNo() {
        return classNo.get();
    }

    public void setMonitor(String monitor) {
        this.monitor.set(monitor);
    }

    public String getMonitor() {
        return this.monitor.get();
    }

    public SimpleStringProperty classNoProperty() {
        return classNo;
    }

    public String getCourse() {
        return course.get();
    }

    public SimpleStringProperty courseProperty() {
        return course;
    }

    public SimpleIntegerProperty stuNumberProperty() {
        return stuNumber;
    }

    public SimpleStringProperty monitorProperty() {
        return monitor;
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
