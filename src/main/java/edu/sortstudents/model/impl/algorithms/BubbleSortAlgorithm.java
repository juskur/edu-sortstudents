package edu.sortstudents.model.impl.algorithms;

import edu.sortstudents.model.SortAlgorithm;
import edu.sortstudents.model.data.SortResult;
import edu.sortstudents.model.data.Student;

import java.util.ArrayList;
import java.util.List;

class BubbleSortAlgorithm implements SortAlgorithm {

    @Override
    public SortResult sort(List<Student> source) {
        List<Student> copy = new ArrayList<>(source);
        bubbleSort(copy);
        return new SortResult(copy);
    }

    private static void bubbleSort(List<Student> data) {
        for (int j = 0; j < data.size() - 1; j++) {
            bubbleBiggestUp(data, j);
        }
    }

    private static void bubbleBiggestUp(List<Student> data, int bubbleToPlace) {
        for (int i = data.size() - 1; i >= bubbleToPlace + 1; i--) {
            compareAndSwitchPlaces(data, i);
        }
    }

    private static void compareAndSwitchPlaces(List<Student> data, int baseIdx) {
        if (data.get(baseIdx - 1).getPerformance().compareTo(data.get(baseIdx).getPerformance()) < 0) {
            switchPlaces(data, baseIdx - 1, baseIdx);
        }
    }

    private static void switchPlaces(List<Student> data, int from, int to) {
        Student betterPerformanceStudent = data.get(from);
        data.set(from, data.get(to));
        data.set(to, betterPerformanceStudent);
    }
}
