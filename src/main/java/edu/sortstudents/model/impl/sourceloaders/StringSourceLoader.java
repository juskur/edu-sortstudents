package edu.sortstudents.model.impl.sourceloaders;

import edu.sortstudents.model.data.Student;
import edu.sortstudents.model.validators.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Semicolon separated string loader
 */
public class StringSourceLoader extends SourceLoaderBasic {

    public static String SEPARATOR = ";";

    private String source;
    private String saved;

    public StringSourceLoader(String source) {
        this.source = source;
    }

    @Override
    public List<Student> load() {
        List<Student> loaded = new ArrayList<>();
        StringTokenizer tokens = new StringTokenizer(source, SEPARATOR);
        while (tokens.hasMoreTokens()) {
            try {
                loaded.add(StringSourceLoaderHelper.load(tokens.nextToken()));
            } catch (ValidationException e) {
                addValidationException(e);
            }
        }
        return loaded;
    }
}
