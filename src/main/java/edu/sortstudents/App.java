package edu.sortstudents;

import edu.sortstudents.model.*;
import edu.sortstudents.model.data.SortResult;
import edu.sortstudents.model.impl.algorithms.SortAlgorithmFactoryImpl;
import edu.sortstudents.model.impl.sourceloaders.FileSourceLoader;
import edu.sortstudents.model.impl.sourceloaders.SourceLoaderFactoryImpl;
import edu.sortstudents.model.validators.ValidationException;

import java.util.HashMap;
import java.util.Map;

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
            SourceLoader sourceLoader = new SourceLoaderFactoryImpl(getConfig(args)).getSourceLoader(SourceType.FILE);
            SortAlgorithm algorithm = new SortAlgorithmFactoryImpl().getSortAlgorithm(SortAlgorithmType.BUBBLE);
            SortResult sortResult = algorithm.sort(sourceLoader.load());
            displayResult(sortResult);
            saveResult(sourceLoader, args, sortResult);
        } catch (ValidationException e) {
            System.out.println("Students can not be sorted: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("System error occurred: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    private static void saveResult(SourceLoader sourceLoader, String [] args, SortResult sortResult) throws ValidationException {
        if (getResultFileName(args) != null && sortResult.getSortedStudents() != null) {
            sourceLoader.save(sortResult.getSortedStudents());
        }
    }

    private static Map<String, String> getConfig(String [] args) throws ValidationException {
        checkArguments(args);
        Map<String, String> config = new HashMap<>();
        config.put(FileSourceLoader.FILE_NAME, getFileName(args));
        configureResultFile(getResultFileName(args), config);
        return config;
    }

    private static void configureResultFile(String resultFileName, Map<String, String> config) {
        if (resultFileName != null) {
            config.put(FileSourceLoader.FILE_NAME_RESULTS, resultFileName);
        }
    }

    private static void checkArguments(String[] args) throws ValidationException {
        if (args == null || args.length < 1) {
            throw new ValidationException("Run java -jar target/edu-sortstudents-1.0-SNAPSHOT.jar students.txt [results.txt]");
        }
    }

    private static String getFileName(String[] args) {
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
}
