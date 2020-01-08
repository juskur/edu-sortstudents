package edu.sortstudents.model.impl.sourceloaders;

import edu.sortstudents.model.SourceLoader;
import edu.sortstudents.model.data.Student;
import edu.sortstudents.model.validators.ValidationException;

import java.util.ArrayList;
import java.util.List;

public abstract class SourceLoaderBasic implements SourceLoader {

    private List<ValidationException> validationExceptions = null;
    private List<Student> studentList = null;

    public SourceLoaderBasic() {
        clear();
    }

    protected void clear() {
        studentList = new ArrayList<>();
        validationExceptions = new ArrayList<>();
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public List<ValidationException> getErrors() {
        return validationExceptions;
    }

    protected void addValidationException(ValidationException e) {
        validationExceptions.add(e);
    }

    @Override
    public boolean hasErrors() {
        return validationExceptions == null || validationExceptions.size() > 0;
    }
}
