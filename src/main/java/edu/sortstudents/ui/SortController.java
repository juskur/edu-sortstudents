package edu.sortstudents.ui;

import edu.sortstudents.model.SortAlgorithm;
import edu.sortstudents.model.SortAlgorithmType;
import edu.sortstudents.model.data.SortResult;
import edu.sortstudents.model.data.Student;
import edu.sortstudents.model.impl.algorithms.SortAlgorithmFactoryImpl;
import edu.sortstudents.model.validators.ValidationException;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Slf4j
public class SortController implements Serializable {

    public void sort() {
        log.info("sort()");
        StudentsBackingBean studentsBackingBean = BeansHelper.getStudentsBackingBean();
        if (validateStudentsPresent(studentsBackingBean.getStudents()) & validateAlgorithmSelected(studentsBackingBean.getAlgorithmType())) {
            try {
                sortStudents(studentsBackingBean);
            } catch (ValidationException e) {
                BeansHelper.addErrorMessage("Error",
                        String.format("Sorting failed: %s", e.getMessage()));
            }
        }
    }

    private static void sortStudents(StudentsBackingBean studentsBackingBean) throws ValidationException {
        SortAlgorithm algorithm = new SortAlgorithmFactoryImpl().getSortAlgorithm(
                SortAlgorithmType.valueOf(studentsBackingBean.getAlgorithmType()));
        SortResult sortResult = algorithm.sort(studentsBackingBean.getStudents());
        studentsBackingBean.getStudents().clear();
        studentsBackingBean.getStudents().addAll(sortResult.getSortedStudents());
        studentsBackingBean.updateRecordsCountText();
        studentsBackingBean.updateSortTimeText(sortResult.getSortingTime());
    }

    private static boolean validateStudentsPresent(List<Student> studentList) {
        if (studentList == null || (studentList.size() == 0)) {
            BeansHelper.addErrorMessage("Error", "Upload students file first");
            return false;
        }
        return true;
    }

    private static boolean validateAlgorithmSelected(String algorithmType) {
        if (algorithmType == null || "".equals(algorithmType.trim())) {
            BeansHelper.addErrorMessage("Error", "Select algorithm");
            return false;
        }
        return true;
    }
}
