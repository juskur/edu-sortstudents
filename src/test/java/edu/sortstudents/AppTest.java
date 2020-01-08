package edu.sortstudents;

import edu.sortstudents.model.data.SortResult;
import edu.sortstudents.model.data.Student;
import edu.sortstudents.model.impl.sourceloaders.FileSourceLoader;
import edu.sortstudents.model.validators.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class AppTest {

    @Test
    void save() throws ValidationException {
        FileSourceLoader loader = new FileSourceLoader("src/test/resources/testStudents.txt");
        List<Student> studentList = Arrays.asList(new Student("Student3", 5.5),
                new Student("Student2", 6.6),
                new Student("Student1", 7.7));
        SortResult result = new SortResult(studentList);
        App.saveResult(new String[] {"src/test/resources/testStudents.txt", "src/test/resources/testStudentsResults.txt"},
                result);
    }
}
