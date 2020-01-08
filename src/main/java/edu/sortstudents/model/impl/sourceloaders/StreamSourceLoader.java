package edu.sortstudents.model.impl.sourceloaders;

import edu.sortstudents.model.data.Student;
import edu.sortstudents.model.validators.ValidationException;

import java.util.List;
import java.util.stream.Stream;

public class StreamSourceLoader extends SourceLoaderBasic {

    private Stream<String> stringStream = null;

    public StreamSourceLoader(Stream<String> stringStream) {
        super();
        setStringStream(stringStream);
    }

    private void setStringStream(Stream<String> stringStream) {
        this.stringStream = stringStream;
    }

    @Override
    public List<Student> load() {
        clear();
        readStudentsFromStream(getStream());
        return getStudentList();
    }

    private Stream<String> getStream() {
        if (stringStream == null) {
            addValidationException(new ValidationException("String stream missing."));
        }
        return stringStream;
    }

    protected void readStudentsFromStream(Stream<String> lines) {
        StringSourceLoaderHelper.readStudentsFromStream(lines, this);
    }
}
