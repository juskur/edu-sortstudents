package edu.sortstudents.model;

import edu.sortstudents.model.validators.ValidationException;

public interface SourceLoaderFactory {
    SourceLoader getSourceLoader(SourceType sourceType) throws ValidationException;
}
