
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "parameterController")
@SessionScoped
public class ParameterController implements Serializable {
    
    private boolean suspend;
    private boolean allInfo;
    private boolean edit;
    private boolean vacationSDate;
    private boolean vacationEDate;
    private boolean documentDelete;
    
    public ParameterController(){
        suspend = true;
        allInfo = true;
        edit = true;
        vacationSDate = true;
        vacationEDate = true;
        documentDelete = true;
    }

    public boolean isSuspend() {
        return suspend;
    }

    public void setSuspend(boolean suspend) {
        this.suspend = suspend;
    }

    public boolean isAllInfo() {
        return allInfo;
    }

    public void setAllInfo(boolean allInfo) {
        this.allInfo = allInfo;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isVacationSDate() {
        return vacationSDate;
    }

    public void setVacationSDate(boolean vacationSDate) {
        this.vacationSDate = vacationSDate;
    }

    public boolean isVacationEDate() {
        return vacationEDate;
    }

    public void setVacationEDate(boolean vacationEDate) {
        this.vacationEDate = vacationEDate;
    }

    public boolean isDocumentDelete() {
        return documentDelete;
    }

    public void setDocumentDelete(boolean documentDelete) {
        this.documentDelete = documentDelete;
    }
}
