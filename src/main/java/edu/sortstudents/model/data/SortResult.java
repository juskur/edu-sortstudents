package edu.sortstudents.model.data;

import java.util.List;

public class SortResult {
    private List<Student> sortedStudents;
    private long sortingTime;

    public SortResult(List<Student> sortedStudents) {
        this.sortedStudents = sortedStudents;
    }

    public List<Student> getSortedStudents() {
        return sortedStudents;
    }

    public long getSortingTime() {
        return sortingTime;
    }

    public void setSortingTime(long sortingTime) {
        this.sortingTime = sortingTime;
    }

    public int getNumberOfRecords() {
        return sortedStudents == null ? 0 : sortedStudents.size();
    }
}
