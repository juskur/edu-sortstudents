package edu.sortstudents.model;

import edu.sortstudents.model.validators.ValidationException;

public interface SortAlgorithmFactory {
    SortAlgorithm getSortAlgorithm(SortAlgorithmType sortAlgorithmType) throws ValidationException;
}
