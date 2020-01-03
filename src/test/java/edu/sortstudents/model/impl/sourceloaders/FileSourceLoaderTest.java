package edu.sortstudents.model.impl.sourceloaders;

import edu.sortstudents.model.data.Student;
import edu.sortstudents.model.validators.ValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileSourceLoaderTest {

    private Map<String, String> config = new HashMap<>();

    @BeforeEach
    void setUp() {
        config.put("file", "src/test/resources/testStudents.txt");
        config.put("results", "src/test/resources/testStudentsResults.txt");
    }

    @Test
    void load() throws ValidationException {
        FileSourceLoader loader = new FileSourceLoader(config);
        List<Student> studentList = loader.load();
        assertNotNull(studentList, "Student list is null");
        assertTrue(studentList.size() == 3, "Not three students");
        assertTrue(studentList.get(1).getName().equals("Student1"), "Name is not correct");
        assertTrue(studentList.get(1).getPerformance().equals(8.5), "Performance is not correct");
    }

    @Test
    void save() throws ValidationException {
        FileSourceLoader loader = new FileSourceLoader(config);
        List<Student> studentList = loader.load();
        loader.save(studentList);
    }
}