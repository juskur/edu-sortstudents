package edu.sortstudents.model.impl.sourceloaders;

import edu.sortstudents.model.SourceLoader;
import edu.sortstudents.model.SourceLoaderFactory;
import edu.sortstudents.model.SourceType;
import edu.sortstudents.model.validators.ValidationException;

import java.util.HashMap;
import java.util.Map;

public class SourceLoaderFactoryImpl implements SourceLoaderFactory {

    private Map<String, String> config = null;

    public SourceLoaderFactoryImpl(Map config) {
        this.config = config;
    }

    @Override
    public SourceLoader getSourceLoader(SourceType sourceType) throws ValidationException {
       switch (sourceType) {
           case STRING:
               return new StringSourceLoader("Student3,5.0;Student1,8.5;Student2,6.5");
           case FILE:
               return new FileSourceLoader(new HashMap<>(config));
           default:
               throw new ValidationException("Source loader " + sourceType + " is not implemented");
       }
    }
}
