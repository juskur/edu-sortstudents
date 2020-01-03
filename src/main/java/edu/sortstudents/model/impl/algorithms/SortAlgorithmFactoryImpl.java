package edu.sortstudents.model.impl.algorithms;

import edu.sortstudents.model.SortAlgorithm;
import edu.sortstudents.model.SortAlgorithmFactory;
import edu.sortstudents.model.SortAlgorithmType;
import edu.sortstudents.model.validators.ValidationException;

public class SortAlgorithmFactoryImpl implements SortAlgorithmFactory {

    @Override
    public SortAlgorithm getSortAlgorithm(SortAlgorithmType sortAlgorithmType) throws ValidationException {
        switch (sortAlgorithmType) {
            case BUBBLE:
                return new SortAlgorithmTimeCounting(new BubbleSortAlgorithm());
            default:
                throw new ValidationException("Sort algorithm not implemented yet:" + sortAlgorithmType);
        }
    }
}
