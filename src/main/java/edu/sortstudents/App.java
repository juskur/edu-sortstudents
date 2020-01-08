package edu.sortstudents;

import edu.sortstudents.model.*;
import edu.sortstudents.model.data.SortResult;
import edu.sortstudents.model.data.Student;
import edu.sortstudents.model.impl.algorithms.SortAlgorithmFactoryImpl;
import edu.sortstudents.model.impl.sourceloaders.FileSourceLoader;
import edu.sortstudents.model.validators.ValidationException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Command line runner
 */
public class App  {

    /**
     * Entry point
     * @param args
     */
    public static void main(String[] args) {
        try {
            SourceLoader sourceLoader = new FileSourceLoader(getFileName(args));
            SortAlgorithm algorithm = new SortAlgorithmFactoryImpl().getSortAlgorithm(SortAlgorithmType.BUBBLE);
            SortResult sortResult = algorithm.sort(loadStudentList(sourceLoader));
            displayResult(sortResult);
            saveResult(args, sortResult);
        } catch (ValidationException e) {
            System.out.println("Students can not be sorted: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("System error occurred: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    private static List<Student> loadStudentList(SourceLoader sourceLoader) throws ValidationException {
        List<Student> studentList = sourceLoader.load();
        if (sourceLoader.getErrors().size() > 0) {
            throw sourceLoader.getErrors().get(0);
        }
        return studentList;
    }

    protected static void saveResult(String [] args, SortResult sortResult) throws ValidationException {
        String resultFileName = getResultFileName(args);
        if (resultFileName != null && sortResult.getSortedStudents() != null) {
            writeStudentsToFile(resultFileName, sortResult.getSortedStudents());
        }
    }

    private static void checkArguments(String[] args) throws ValidationException {
        if (args == null || args.length < 1) {
            throw new ValidationException("Run java edu.sortstudents.App -cp ./target/edu-sortstudents-1.0-SNAPSHOT.jar students.txt [results.txt]");
        }
    }

    private static String getFileName(String[] args) throws ValidationException {
        checkArguments(args);
        return args[0];
    }

    private static String getResultFileName(String[] args) {
        return args.length < 2 ? null : args[1];
    }

    private static void displayResult(SortResult sortResult) {
        if (sortResult == null || sortResult.getSortedStudents() == null) {
            System.out.println("No sort result");
        } else {
            printCountAndTime(sortResult);
            printOutSeparator();
            printOutResultTable(sortResult);
            printOutSeparator();
        }
    }

    private static void printCountAndTime(SortResult sortResult) {
        System.out.println("Number of records: " + sortResult.getNumberOfRecords()
                + ", Sorting time: " + sortResult.getSortingTime() + " msec");
    }

    private static void printOutResultTable(SortResult sortResult) {
        sortResult.getSortedStudents().stream().forEach(student -> {
            System.out.print(student.getName());
            System.out.print("\t");
            System.out.println(student.getPerformance());
        });
    }

    private static void printOutSeparator() {
        System.out.println("-----------------------------------------");
    }

    private static void writeStudentsToFile(String fileName, List<Student> studentList) throws ValidationException {
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            studentList.stream().forEach(s -> {
                try {
                    writer.write(s.toString());
                    writer.write(System.lineSeparator());
                } catch (IOException e) {
                   new ValidationException("Student writing to file error: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            throw new ValidationException("Writing to file failed " + e.getMessage());
        }
    }


}
