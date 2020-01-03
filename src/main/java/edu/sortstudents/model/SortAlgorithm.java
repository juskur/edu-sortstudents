package edu.sortstudents.model;

import edu.sortstudents.model.data.SortResult;
import edu.sortstudents.model.data.Student;

import java.util.List;

public interface SortAlgorithm {
    SortResult sort(List<Student> source);
}
