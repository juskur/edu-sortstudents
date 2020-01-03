package edu.sortstudents.model.validators;

public interface Validator<T> {
    void validate(T value) throws ValidationException;
}
