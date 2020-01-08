package edu.sortstudents.ui;

import edu.sortstudents.model.SourceLoader;
import edu.sortstudents.model.data.Student;
import edu.sortstudents.model.impl.sourceloaders.StreamSourceLoader;
import edu.sortstudents.model.validators.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.UploadedFile;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
@Slf4j
public class FileUploadController implements Serializable {

    private UploadedFile file;
    private List<ValidationException> validationExceptions = new ArrayList<>();
    private StudentsBackingBean studentsView;

    public StudentsBackingBean getStudentsBacking() {
        if (studentsView == null) {
            studentsView = BeansHelper.getStudentsBackingBean();
        }
        return studentsView;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if (file != null && file.getFileName() != null) {
            loadStudents();
        } else {
            BeansHelper.addErrorMessage("Error", "File is not provided");
        }
    }

    private void loadStudents() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputstream(),
                StandardCharsets.UTF_8))) {
            readLines(reader);
            updateDisplayResults();
        } catch (IOException e) {
            BeansHelper.addErrorMessage("Error while reading uploaded file", e.getMessage());
            log.error("Error while reading uploaded file", e);
        }
    }

    private void readLines(BufferedReader reader) {
        SourceLoader loader = new StreamSourceLoader(reader.lines());
        List<Student> studentList = loader.load();
        if (loader.hasErrors()) {
            displayAllErrors(loader.getErrors());
        } else {
            getStudentsBacking().getStudents().addAll(studentList);
        }
    }

    private static void displayAllErrors(List<ValidationException> exceptions) {
        exceptions.stream().forEach((e) -> {
            BeansHelper.addErrorMessage("Error", e.getMessage());
        });
    }

    private void updateDisplayResults() {
        if (validationExceptions.size() > 0) {
            BeansHelper.addValidationErrors(validationExceptions);
            validationExceptions.clear();
        } else {
            getStudentsBacking().updateRecordsCountText();
            BeansHelper.addInfoMessage("Successful", file.getFileName() + " is uploaded.");
        }
    }
}