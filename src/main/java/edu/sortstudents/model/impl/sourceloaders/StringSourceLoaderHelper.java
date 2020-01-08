package edu.sortstudents.model.impl.sourceloaders;

import edu.sortstudents.model.data.Student;
import edu.sortstudents.model.validators.ValidationException;

import java.util.StringTokenizer;
import java.util.stream.Stream;

public interface StringSourceLoaderHelper {

    String NAME_PERFORMANCE_SEPARATOR = ",";

    static Student load(String token) throws ValidationException {
        validateMandatoryToken(token);
        StringTokenizer tokens = new StringTokenizer(token, NAME_PERFORMANCE_SEPARATOR);
        validateTokensCount(tokens);
        return new Student(tokens.nextToken(), getPerformance(tokens.nextToken()));

    }

    static void validateMandatoryToken(String token) throws ValidationException {
        if (token == null || token.trim().length() == 0) {
            throw new ValidationException("String token for student performance value is null or empty");
        }
    }

    static void validateTokensCount(StringTokenizer tokens) throws ValidationException {
        if (tokens.countTokens() != 2) {
            throw new ValidationException("String token for student contains not two comma separated values");
        }
    }

    static Double getPerformance(String performanceValue) throws ValidationException {
        if (performanceValue.trim().length() == 0) {
            throw new ValidationException("Performance value is empty");
        }
        Double performance = null;
        try {
            performance = Double.parseDouble(performanceValue);
        } catch (NumberFormatException e) {
            throw new ValidationException("Performance value " + performanceValue + " is not a double:" + e.getMessage());
        }
        return performance;
    }

    static void readStudentsFromStream(Stream<String> lines, SourceLoaderBasic sourceLoaderBasic) {
        if (lines == null) {
            return;
        }
        lines.forEach(l -> {
            try {
                sourceLoaderBasic.addStudent(StringSourceLoaderHelper.load(l));
            } catch (ValidationException e) {
                sourceLoaderBasic.addValidationException(e);
            }
        });
    }

}
