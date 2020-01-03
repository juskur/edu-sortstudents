package edu.sortstudents.model.impl.sourceloaders;

import com.sun.org.apache.xpath.internal.operations.String;
import edu.sortstudents.model.data.Student;
import edu.sortstudents.model.validators.ValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringSourceLoaderTest {

    @Test
    void loadFromString() throws ValidationException {
        StringSourceLoader loader = new StringSourceLoader("Student2,6.5;Student3,5.0;Student1,8.5");
        List<Student> studentList = loader.load();
        assertNotNull(studentList, "Student list is null");
        assertTrue(studentList.size() == 3, "Not three students");
        assertTrue(studentList.get(1).getName().equals("Student3"), "Name is not correct");
        assertTrue(studentList.get(1).getPerformance().equals(5.0), "Performance is not correct");
    }
}