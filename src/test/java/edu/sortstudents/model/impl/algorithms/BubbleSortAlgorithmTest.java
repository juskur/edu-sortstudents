package edu.sortstudents.model.impl.algorithms;

import edu.sortstudents.model.data.SortResult;
import edu.sortstudents.model.data.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BubbleSortAlgorithmTest {

    @Test
    public void testAtLeastThreeStudentsSort() {
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("Student3", 5.0));
        students.add(new Student("Student1", 8.0));
        students.add(new Student("Student2", 6.5));
        BubbleSortAlgorithm algorithm = new BubbleSortAlgorithm();
        SortResult result = algorithm.sort(students);
        assertNotNull(result, "Sorted result is null");
        assertNotNull(result.getSortedStudents(), "Sorted list of students is null");
        assertTrue(result.getNumberOfRecords() == 3, "Sorted list size is not 3");
        assertTrue(result.getSortedStudents().get(0).getPerformance() == 8.0, "Best performance student is not with performance of 8");
    }
}
