package edu.sortstudents.ui;

import edu.sortstudents.model.data.Student;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named("studentsBacking")
@ViewScoped
@Slf4j
@Data
public class StudentsBackingBean implements Serializable {

    private List<Student> students = new ArrayList<>();
    private String algorithmType;
    private String recordsCountText;
    private String sortTimeText;

    public StudentsBackingBean() {
        super();
        log.info("StudentsView created");
    }

    public void updateRecordsCountText() {
        if (students != null) {
            setRecordsCountText(String.format("Number of records: %d", students.size()));
        } else {
            setRecordsCountText(null);
        }
        setSortTimeText(null);
    }

    public void updateSortTimeText(long time) {
        setSortTimeText(String.format("Sorting time %d msecs.", time));
    }

}
