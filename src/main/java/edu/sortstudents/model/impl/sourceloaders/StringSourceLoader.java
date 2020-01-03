package edu.sortstudents.model.impl.sourceloaders;

import edu.sortstudents.model.SourceLoader;
import edu.sortstudents.model.data.Student;
import edu.sortstudents.model.validators.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Semicolon separated string loader
 */
public class StringSourceLoader implements SourceLoader {

    public static String SEPARATOR = ";";

    private String source;
    private String saved;

    public StringSourceLoader(String source) {
        this.source = source;
    }

    @Override
    public List<Student> load() throws ValidationException {
        List<Student> loaded = new ArrayList<>();
        StringTokenizer tokens = new StringTokenizer(source, SEPARATOR);
        while (tokens.hasMoreTokens()) {
            loaded.add(StringSourceLoaderHelper.load(tokens.nextToken()));
        }
        return loaded;
    }

    @Override
    public void save(List<Student> studentList) throws ValidationException {
        StringBuilder concatenated = new StringBuilder();
        studentList.forEach(concatenated::append);
        saved = concatenated.toString();
    }
}
