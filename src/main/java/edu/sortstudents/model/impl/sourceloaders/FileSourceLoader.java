package edu.sortstudents.model.impl.sourceloaders;

import edu.sortstudents.model.data.Student;
import edu.sortstudents.model.validators.ValidationException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileSourceLoader extends SourceLoaderBasic {

    private String fileName;

    public FileSourceLoader(String fileName) {
        super();
        setFileName(fileName);
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Student> load() {
        clear();
        readStudentsFromFile(getFileName());
        return getStudentList();
    }

    private String getFileName() {
        if (fileName == null || "".equals(fileName.trim())) {
            addValidationException(new ValidationException("fileName is missing "));
        }
        return fileName;
    }

    private void readStudentsFromFile(String fileName) {
        readStudentsFromPath(Paths.get(fileName));
    }

    protected void readStudentsFromPath(Path path) {
        try (Stream<String> stream = Files.lines(path)) {
            StringSourceLoaderHelper.readStudentsFromStream(stream, this);
        } catch (IOException e) {
            addValidationException(new ValidationException(String.format("Reading from file failed: %s", e.getMessage())));
        }
    }
}
