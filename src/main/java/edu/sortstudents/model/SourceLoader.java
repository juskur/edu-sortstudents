package edu.sortstudents.model;

import edu.sortstudents.model.data.Student;
import edu.sortstudents.model.validators.ValidationException;

import java.util.List;

public interface SourceLoader {
    List<Student> load();
    List<ValidationException> getErrors();
    boolean hasErrors();
}
