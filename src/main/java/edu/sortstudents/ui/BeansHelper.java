package edu.sortstudents.ui;

import edu.sortstudents.model.validators.ValidationException;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

public interface BeansHelper {
    static StudentsBackingBean getStudentsBackingBean() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        return (StudentsBackingBean) elContext.getELResolver().getValue(elContext,
                null, "studentsBacking");
    }

    static void addErrorMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
    }

    static void addInfoMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(summary, detail));
    }

    static void addValidationErrors(List<ValidationException> errors) {
        errors.stream().forEach((e) -> {addErrorMessage("Student format error", e.getMessage());});
    }

    static void displayAllErrors(List<ValidationException> exceptions) {
        exceptions.stream().forEach((e) -> {
            BeansHelper.addErrorMessage("Error", e.getMessage());
        });
    }


}
