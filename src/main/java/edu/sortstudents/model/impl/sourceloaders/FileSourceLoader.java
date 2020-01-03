package edu.sortstudents.model.impl.sourceloaders;

import edu.sortstudents.model.data.Student;
import edu.sortstudents.model.validators.ValidationException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class FileSourceLoader extends SourceLoaderConfigurable {

    public static String FILE_NAME = "file";
    public static String FILE_NAME_RESULTS = "results";
    private List<Student> studentList = null;
    private List<ValidationException> validationExceptions = null;

    public FileSourceLoader(Map config) {
        super(config);
    }

    @Override
    public List<Student> load() throws ValidationException {
        clear();
        readStudentsFromFile(getFileName(FILE_NAME));
        throwExceptions();
        return studentList;
    }

    @Override
    public void save(List<Student> studentList) throws ValidationException {
        clear();
        writeStudentsToFile(getFileName(FILE_NAME_RESULTS), studentList);
        throwExceptions();
    }

    private void writeStudentsToFile(String fileName, List<Student> studentList) throws ValidationException {
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            studentList.stream().forEach(s -> {
                try {
                    writer.write(s.toString());
                    writer.write(System.lineSeparator());
                } catch (IOException e) {
                    addValidationException(new ValidationException("Student writing to file error: " + e.getMessage()));
                }
            });
        } catch (Exception e) {
            throw new ValidationException("Writing to file failed " + e.getMessage());
        }
    }

    private void clear() {
        studentList = new ArrayList<>();
        validationExceptions = new ArrayList<>();
    }

    private String getFileName(String configKey) throws ValidationException {
        String fileName = getConfig().get(configKey);
        if (fileName == null || "".equals(fileName.trim())) {
            throw new ValidationException(configKey + " config entry missing ");
        }
        return fileName;
    }

    private void readStudentsFromFile(String fileName) throws ValidationException {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(l -> {
                try {
                    addStudent(l);
                } catch (ValidationException e) {
                    addValidationException(e);
                }
            });
        } catch (Exception e) {
            throw new ValidationException("Error while reading file");
        }
    }

    private void throwExceptions() throws ValidationException {
        if (validationExceptions.size() > 0) {
            throw validationExceptions.get(0);
        }
    }

    private void addStudent(String line) throws ValidationException {
        studentList.add(StringSourceLoaderHelper.load(line));
    }

    private void addValidationException(ValidationException e) {
        validationExceptions.add(e);
    }
}
