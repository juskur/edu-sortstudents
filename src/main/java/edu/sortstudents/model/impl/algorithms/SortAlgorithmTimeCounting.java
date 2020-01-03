package edu.sortstudents.model.impl.algorithms;

import edu.sortstudents.model.SortAlgorithm;
import edu.sortstudents.model.data.SortResult;
import edu.sortstudents.model.data.Student;

import java.util.Date;
import java.util.List;

public class SortAlgorithmTimeCounting implements SortAlgorithm {

    private SortAlgorithm algorithm;

    public SortAlgorithmTimeCounting(SortAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public SortResult sort(List<Student> source) {
        long startTime = new Date().getTime();
        SortResult sortResult = algorithm.sort(source);
        sortResult.setSortingTime(new Date().getTime() - startTime);
        return sortResult;
    }
}
