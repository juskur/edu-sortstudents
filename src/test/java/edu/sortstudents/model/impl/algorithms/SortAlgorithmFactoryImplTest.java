package edu.sortstudents.model.impl.algorithms;

import edu.sortstudents.model.SortAlgorithm;
import edu.sortstudents.model.SortAlgorithmType;
import edu.sortstudents.model.validators.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SortAlgorithmFactoryImplTest {

    @Test
    public void testReturnsBubbles() throws ValidationException {
        SortAlgorithmFactoryImpl factory = new SortAlgorithmFactoryImpl();
        SortAlgorithm algorithm = factory.getSortAlgorithm(SortAlgorithmType.BUBBLE);
        assertNotNull(algorithm, "Null implementation returned");
    }
}
