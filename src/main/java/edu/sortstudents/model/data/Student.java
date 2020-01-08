package edu.sortstudents.model.data;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {
    private String name;
    private Double performance;

    public Student(String name, Double performance) {
        this.name = name;
        this.performance = performance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPerformance() {
        return performance;
    }

    public void setPerformance(Double performance) {
        this.performance = performance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return name.equals(student.name) &&
                performance.equals(student.performance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, performance);
    }

    @Override
    public String toString() {
        return name + "," + performance;
    }
}
