package edu.sortstudents.model.impl.sourceloaders;

import edu.sortstudents.model.data.Student;
import edu.sortstudents.model.validators.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileSourceLoaderTest {

    @Test
    void load() throws ValidationException {
        FileSourceLoader loader = new FileSourceLoader("src/test/resources/testStudents.txt");
        List<Student> studentList = loader.load();
        assertFalse(loader.hasErrors(), "Errors while loading valid file encountered");
        assertNotNull(studentList, "Student list is null");
        assertTrue(studentList.size() == 3, "Not three students");
        assertTrue(studentList.get(1).getName().equals("Student1"), "Name is not correct");
        assertTrue(studentList.get(1).getPerformance().equals(8.5), "Performance is not correct");
    }
}