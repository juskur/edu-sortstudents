package edu.sortstudents.model;

import edu.sortstudents.model.data.Student;
import edu.sortstudents.model.validators.ValidationException;

import java.util.List;

/**
 * This interface should be renamed to e.g. SourceManager as at this point it represents not only loading but saving functionality also
 */
public interface SourceLoader {
    List<Student> load() throws ValidationException;
    void save(List<Student> studentList) throws ValidationException;
}
