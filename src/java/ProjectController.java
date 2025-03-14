
import com.mycompany.edu.ulatina.hth_db_connetion.ActivityService;
import com.mycompany.edu.ulatina.hth_db_connetion.ActivityTO;
import com.mycompany.edu.ulatina.hth_db_connetion.CreateActivityService;
import com.mycompany.edu.ulatina.hth_db_connetion.CreateActivityTO;
import com.mycompany.edu.ulatina.hth_db_connetion.EmployeeService;
import com.mycompany.edu.ulatina.hth_db_connetion.EmployeeTO;
import com.mycompany.edu.ulatina.hth_db_connetion.FeedbackService;
import com.mycompany.edu.ulatina.hth_db_connetion.FeedbackTO;
import com.mycompany.edu.ulatina.hth_db_connetion.PermitTO;
import com.mycompany.edu.ulatina.hth_db_connetion.ProjectService;
import com.mycompany.edu.ulatina.hth_db_connetion.ProjectTO;
import com.mycompany.edu.ulatina.hth_db_connetion.ProjectXEmployeeService;
import com.mycompany.edu.ulatina.hth_db_connetion.ProjectXEmployeeTO;
import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "projectController")
@SessionScoped
public class ProjectController implements Serializable {

    private ProjectTO selectedProject = new ProjectTO();
    private ProjectXEmployeeTO selectedProjectXEmployee = new ProjectXEmployeeTO();
    private final ProjectService proService = new ProjectService();
    private final ProjectXEmployeeService pXEService = new ProjectXEmployeeService();
    private boolean esNuevo;
    private final EmployeeService empService = new EmployeeService();
    private CreateActivityTO selectedCreateActivity = new CreateActivityTO();
    private final CreateActivityService cAService = new CreateActivityService();
    private final ActivityService actService = new ActivityService();  
    private EmployeeTO selectedEmployee = new EmployeeTO();
    private FeedbackTO selectedFeedback = new FeedbackTO();
    private final FeedbackService fService = new FeedbackService();
    private ActivityTO selectedActivity = new ActivityTO();
    
    private double numOfHoursToAdd;
    
    private int CAId;

    public ProjectController() {
    }
    
    public FeedbackTO getSelectedFeedback() {
        return selectedFeedback;
    }

    public void setSelectedFeedback(FeedbackTO selectedFeedback) {
        this.selectedFeedback = selectedFeedback;
    }

    public double getNumOfHoursToAdd() {
        return numOfHoursToAdd;
    }

    public void setNumOfHoursToAdd(double numOfHoursToAdd) {
        this.numOfHoursToAdd = numOfHoursToAdd;
    }

    public ActivityTO getSelectedActivity() {
        return selectedActivity;
    }

    public void setSelectedActivity(ActivityTO selectedActivity) {
        this.selectedActivity = selectedActivity;
    }
    
    
    
    public int getCAId() {
        return CAId;
    }

    public void setCAId(int CAId) {
        this.CAId = CAId;
    }

    public CreateActivityTO getSelectedCreateActivity() {
        return selectedCreateActivity;
    }

    public EmployeeTO getSelectedEmployee() {
        return selectedEmployee;
    }
    
    public void setSelectedEmployee(EmployeeTO emp){
        this.selectedEmployee = emp;
    }

    public void setSelectedCreateActivity(CreateActivityTO selectedCreateActivity) {
        this.selectedCreateActivity = selectedCreateActivity;
    }

    public ProjectXEmployeeTO getSelectedProjectXEmployee() {
        return selectedProjectXEmployee;
    }

    public void setSelectedProjectXEmployee(ProjectXEmployeeTO selectedProjectXEmployee) {
        this.selectedProjectXEmployee = selectedProjectXEmployee;
    }

    public ProjectTO getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(ProjectTO selectedProject) {
        this.selectedProject = selectedProject;
    }

    public boolean isProyectActive() {
        return selectedProject.getStatus() == 10;
    }

//    public List<EmployeeTO> getEmployeesOfProyect() {
//        try {
//            return empService.getEmployeesFromProyect(selectedProject.getId());
//        } catch (Exception e) {
//            e.printStackTrace();
//            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in retriving the list of employees of the project"));
//
//        }
//        List<EmployeeTO> list = new ArrayList<>();
//        return list;
//    }
//
//    public List<EmployeeTO> getEmployeesNotOnProyect() {
//        try {
//            return empService.getEmployeesNotOnProyect(selectedProject.getId());
//        } catch (Exception e) {
//            e.printStackTrace();
//            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in retriving the list of employees of the project"));
//
//        }
//        List<EmployeeTO> list = new ArrayList<>();
//        return list;
//    }

    public List<CreateActivityTO> getCActivity() {
        try {
            return cAService.getActivity();
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in retriving the list of employees of the project"));

        }
        List<CreateActivityTO> list = new ArrayList<>();
        return list;
    }

    public List<CreateActivityTO> getActivity() {
        try {
            /*Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
            flash.setKeepMessages(true);
            pullFlash(null);*/
            return cAService.getActividad(CAId);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in retriving the list of employees of the project"));

        }
        List<CreateActivityTO> list = new ArrayList<>();
        return list;
    }

    public boolean isEsNuevo() {
        return esNuevo;
    }
    
    public boolean isNoEsNuevo() {
        return !esNuevo;
    }

    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }

    public void openNew() {
        this.esNuevo = true;
        this.selectedProject = new ProjectTO();
    }

    public void openNewPXE() {
        this.esNuevo = true;
        this.selectedProjectXEmployee = new ProjectXEmployeeTO();
    }

    public List<ProjectTO> getProjects () {
        try {
            CAId = 0;
            return proService.getProjects();
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in retriving th list of employees"));

        }
        List<ProjectTO> list = new ArrayList<>();
        return list;
    }
    
    public List<ProjectTO> getMyProjects(int pk) {
        try {
            CAId = 0;
            return proService.getMYProjects(pk);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in retriving th list of employees"));

        }
        List<ProjectTO> list = new ArrayList<>();
        return list;
    }

    public List<ProjectXEmployeeTO> getPXEInfoFromEmployee(int pk) {
        try {
            return pXEService.getProjectEmployeeById(pk);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in retriving the project information"));

        }
        List<ProjectXEmployeeTO> list = new ArrayList<>();
        return list;
    }

    public String statusToStrStatus(int status) {
        String result = "";
        switch (status) {
            case 10:
                result = "Inprogress";
                break;
            case 11:
                result = "Completed";
                break;

        }
        return result;
    }

    public void saveProject() throws Exception {

        boolean flag = true;

        if (this.selectedProject.getName() == null || this.selectedProject.getName().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Name is empty"));
            flag = false;
        }
        if (this.selectedProject.getStatus() != 10 && this.selectedProject.getStatus() != 11) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "status is incorrect"));
            flag = false;
        }
        if (this.selectedProject.getStartingDate() == null) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Starting Date is empty"));
            flag = false;
        }

        if (flag) {
            System.out.println("Saving permit");
            //Date day = (java.sql.Date) (java.sql.Date) selectedPermit.getDate();
            ProjectTO i = new ProjectTO(0, this.selectedProject.getName(), this.selectedProject.getStatus(), this.selectedProject.getStartingDate(), this.selectedProject.getEndingDate());
            this.proService.insert(i);
            //---this.servicioUsuario.listarUsuarios();
            //this.listaUsuarios.add(selectedEmployee);//para simular       
            this.esNuevo = false;
            this.selectedProject = new ProjectTO();
            PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        }

    }

    public void updateProject() throws Exception {

        boolean flag = true;

        if (flag) {

            this.proService.update(selectedProject, this.selectedProject.getName(), this.selectedProject.getStatus(), this.selectedProject.getStartingDate(), this.selectedProject.getEndingDate());
            //---this.servicioUsuario.listarUsuarios();
            //this.listaUsuarios.add(selectedEmployee);//para simular       
            this.esNuevo = false;
            this.selectedProject = new ProjectTO();
            PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        }

    }

    public java.util.Date getStartingDate() {
        return (java.util.Date) this.selectedProject.getStartingDate();
    }

    public void setStartingDate(java.util.Date startingDate) {
        if (startingDate != null) {
            this.selectedProject.setStartingDate(new java.sql.Date(startingDate.getTime()));
        }
    }

    public java.util.Date getEndingDate() {
        return (java.util.Date) this.selectedProject.getEndingDate();
    }

    public void setEndingDate(java.util.Date endingdate) {
        if (endingdate != null) {
            this.selectedProject.setEndingDate(new java.sql.Date(endingdate.getTime()));
        }
    }

    public ProjectTO getProject(int PK) {
        ProjectTO foundPro = null;
        try {

            foundPro = proService.searchByPK(PK);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in searching the user"));
        }
        return foundPro;
    }

    public void deleteProject(int PK) throws Exception {

        try {
            ProjectTO searched = this.getProject(PK);
            if (searched != null) {
                proService.delete(searched);
            }

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in suspending the user"));

        }

    }

    public void reviewProjectFeedBack() throws Exception {

        this.redirect("/faces/reviewProjectFeedBack.xhtml");

    }

    public void redirect(String rute) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + rute);
        } catch (Exception e) {

        }

    }

    public ProjectXEmployeeTO getProyectFeedBack(int PK) {
        ProjectXEmployeeTO foundFeedBack = null;
        try {

            foundFeedBack = pXEService.searchPXEByPk(PK);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in searching the user"));
        }
        return foundFeedBack;
    }

    public void deleteProjectFeedBack(int PK) throws Exception {

        try {
            ProjectXEmployeeTO searchedFeedBack = this.getProyectFeedBack(PK);
            if (searchedFeedBack != null) {
                pXEService.delete(searchedFeedBack);
            }

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in deleting the Schedule Vacation"));

        }

    }

    public void returnToProjectPage() throws Exception {

        this.redirect("/faces/projects.xhtml");

    }

    

    public void saveActivity() throws Exception {

        boolean flag = true;
        
        if (this.selectedCreateActivity.getName() == null || this.selectedCreateActivity.getName().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "FeedBack is Empty"));
            flag = false;
        }

        if (flag) {

            System.out.println("Saving permit");
            //Date day = (java.sql.Date) (java.sql.Date) selectedPermit.getDate();
            CreateActivityTO i = new CreateActivityTO(0, CAId, this.selectedCreateActivity.getName(), this.selectedCreateActivity.getDescription());
            this.cAService.insert(i);
            //---this.servicioUsuario.listarUsuarios();
            //this.listaUsuarios.add(selectedEmployee);//para simular       
            this.esNuevo = false;
            this.selectedCreateActivity = new CreateActivityTO();
            PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        }

    }

    public void updateCreateActivity() throws Exception {

        boolean flag = true;
        
        if (this.selectedCreateActivity.getName() == null || this.selectedCreateActivity.getName().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "FeedBack is Empty"));
            flag = false;
        }

        if (flag) {

            this.cAService.update(selectedCreateActivity, CAId, this.selectedCreateActivity.getName(), this.selectedCreateActivity.getDescription());
            //---this.servicioUsuario.listarUsuarios();
            //this.listaUsuarios.add(selectedEmployee);//para simular       
            this.esNuevo = false;
            this.selectedCreateActivity = new CreateActivityTO();
            PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        }

    }

    public void deleteCActivity(int pk) throws Exception {

        try {

            cAService.delete(pk);

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in suspending the user"));

        }

    }

    public void pullFlash(ComponentSystemEvent e) {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        CAId = (int) flash.get("id");
        System.out.println(CAId);
    }

    public void addCollaboratorToProject(int employeePK) {
        try {
            proService.addCollaborator(CAId, employeePK);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in addingPersonToProject"));
        }
    }

    public String getHeaderForProject() {
        return "Members of Project: ";
    }

    public void viewProject() throws Exception {

        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("name", selectedProject.getName());
        flash.put("id", selectedProject.getId());

        CAId = 0;

        pullFlash(null);
        System.out.println(CAId);

        this.redirect("/faces/viewProject.xhtml");

    }

    public List<ActivityTO> getActivitiesFrom() throws Exception{
         try {
            return actService.getSearchActivity(selectedEmployee.getId() , CAId);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in retriving the list of employees of the project"));

        }
        List<ActivityTO> list = new ArrayList<>();
        return list;
    }
    
    public List<ActivityTO> getActivitiesFromPK(int pk) throws Exception{
         try {
            return actService.getSearchActivity(pk , selectedProject.getId());
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in retriving the list of employees of the project"));

        }
        List<ActivityTO> list = new ArrayList<>();
        return list;
    }
    
    public List<CreateActivityTO> getActivitiesNotAssignedTo() throws Exception{
         try {
            return cAService.getActivitiesNotAssignedTo(selectedEmployee.getId(), selectedProject.getId());
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in retriving the list of employees of the project"));

        }
        List<CreateActivityTO> list = new ArrayList<>();
        return list;
    }
    
    public String getActivityName(int pk) throws Exception{
        return actService.getActivityName(pk);
    }
    
//    public List<FeedbackTO> getFeedbackFrom() throws Exception{
//         try {
//              
//            // System.out.println(selectedEmployee.getId());
//            return fService.getFeedback(selectedEmployee.getId(), CAId);
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in retriving the list of employees of the project"));
//
//        }
//        List<FeedbackTO> list = new ArrayList<>();
//        return list;
//    }
    
    public void updateFeedback( int pk) throws Exception {

        boolean flag = true;
        
        if (this.selectedFeedback.getName() == null || this.selectedFeedback.getName().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "FeedBack name is Empty"));
            flag = false;
        }
        if (this.selectedFeedback.getDescription() == null || this.selectedFeedback.getDescription().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "FeedBack description is Empty"));
            flag = false;
        }
        

        if (flag) {

            this.fService.update(selectedFeedback, this.selectedFeedback.getName(), this.selectedFeedback.getDescription(), this.selectedFeedback.getDateOfFeedback(), this.selectedFeedback.getIdStatus(), 23, pk);
            //---this.servicioUsuario.listarUsuarios();
            //this.listaUsuarios.add(selectedEmployee);//para simular       
            this.esNuevo = false;
            this.selectedFeedback= new FeedbackTO();
            PrimeFaces.current().executeScript("PF('updatePEFeedback').hide()");
        }

    }
    
    
//    
//    public void saveFeedBack(int pk) throws Exception {
//
//        boolean flag = true;
//        
//        
//        if (this.selectedFeedback.getName() == null || this.selectedFeedback.getName().equals("")) {
//            //ERROR
//            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "FeedBack name is Empty"));
//            flag = false;
//        }
//        if (this.selectedFeedback.getDescription() == null || this.selectedFeedback.getDescription().equals("")) {
//            //ERROR
//            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "FeedBack description is Empty"));
//            flag = false;
//        }
//        
//
//        if (flag) {
//            
//            
//           
//            this.fService.insert(this.selectedFeedback.getName(), this.selectedFeedback.getDescription(), this.selectedFeedback.getDateOfFeedback(), this.selectedFeedback.getIdStatus(), 23, pk);
//            
//            
//            
//            
//            this.esNuevo = false;
//            this.selectedFeedback = new FeedbackTO();
//            
//            
//            this.fService.insertAct(selectedEmployee.getId(), CAId);
//
//            Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
//            flash.put("name", selectedProject.getName());
//            flash.put("id", selectedProject.getId());
//            flash.put("idf", this.selectedFeedback.getId());
//
//
//            
//            System.out.println("Salvando feedback");
//            PrimeFaces.current().executeScript("PF('addFeedback').hide()");
//
//        }
//        
//
//    }
//    
    public String statusToStr(int status) {
        String result = "";
        switch (status) {
            case 18:
                result = "Pending";
                break;
            case 19:
                result = "Completed";
                break;
            case 20:
                result = "Failed";
                break;
        }
        return result;
    }
    
    public List<FeedbackTO> getEFeedbackFrom() throws Exception{
         try {
         
            return fService.getEFeedback(selectedEmployee.getId());
            
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in retriving the list of employees of the project"));

        }
        List<FeedbackTO> list = new ArrayList<>();
        return list;
    }
    
    public void updateEFeedback( int pk) throws Exception {

        boolean flag = true;
        
        if (this.selectedFeedback.getName() == null || this.selectedFeedback.getName().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "FeedBack name is Empty"));
            flag = false;
        }
        if (this.selectedFeedback.getDescription() == null || this.selectedFeedback.getDescription().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "FeedBack description is Empty"));
            flag = false;
        }
        

        if (flag) {

            this.fService.update(selectedFeedback, this.selectedFeedback.getName(), this.selectedFeedback.getDescription(), this.selectedFeedback.getDateOfFeedback(), this.selectedFeedback.getIdStatus(), 22, pk);
            //---this.servicioUsuario.listarUsuarios();
            //this.listaUsuarios.add(selectedEmployee);//para simular       
            this.esNuevo = false;
            this.selectedFeedback= new FeedbackTO();
            PrimeFaces.current().executeScript("PF('updateEFeedback').hide()");
        }

    }
    
    public void deleteEmpFeedback(int pk) throws Exception {

        try {

            fService.deleteEmp(pk);

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in suspending the user"));

        }

    }
//    public void deletePjFeedback(int pk) throws Exception {
//
//        try {
//
//            fService.deletePj(pk);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in suspending the user"));
//
//        }
//
//    }
    public void deleteActFeedback(int pk) throws Exception {

        try {

            fService.deleteAct(pk);

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in suspending the user"));

        }

    }
    
    public void saveEFeedBack(int pk) throws Exception {

        boolean flag = true;
        
        
        if (this.selectedFeedback.getName() == null || this.selectedFeedback.getName().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "FeedBack name is Empty"));
            flag = false;
        }
        if (this.selectedFeedback.getDescription() == null || this.selectedFeedback.getDescription().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "FeedBack description is Empty"));
            flag = false;
        }
        

        if (flag) {
            
            
           
            this.fService.insert(this.selectedFeedback.getName(), this.selectedFeedback.getDescription(), this.selectedFeedback.getDateOfFeedback(), this.selectedFeedback.getIdStatus(), 22, pk);
            //pass = true;

            this.esNuevo = false;
            this.selectedFeedback = new FeedbackTO();
            //this.selectedProject = new ProjectTO();
            
            System.out.println(selectedEmployee.getId());
            
            this.fService.insertEmp(selectedEmployee.getId());
            
            //System.out.println(this.selectedEmployee.getId() );
            
            

            System.out.println("Salvando feedback");
            PrimeFaces.current().executeScript("PF('addFeedback').hide()");

        }
        

    }
    
    
    public List<FeedbackTO> getPFeedbackFrom() throws Exception{
         try {
         
            return fService.getPFeedback(CAId);
            
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in retriving the list of employees of the project"));

        }
        List<FeedbackTO> list = new ArrayList<>();
        return list;
    }
    
    public void updatePFeedback( int pk) throws Exception {

        boolean flag = true;
        
        if (this.selectedFeedback.getName() == null || this.selectedFeedback.getName().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "FeedBack name is Empty"));
            flag = false;
        }
        if (this.selectedFeedback.getDescription() == null || this.selectedFeedback.getDescription().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "FeedBack description is Empty"));
            flag = false;
        }
        

        if (flag) {

            this.fService.update(selectedFeedback, this.selectedFeedback.getName(), this.selectedFeedback.getDescription(), this.selectedFeedback.getDateOfFeedback(), this.selectedFeedback.getIdStatus(), 21, pk);
            //---this.servicioUsuario.listarUsuarios();
            //this.listaUsuarios.add(selectedEmployee);//para simular       
            this.esNuevo = false;
            this.selectedFeedback= new FeedbackTO();
            PrimeFaces.current().executeScript("PF('updateEFeedback').hide()");
        }

    }
    
    
//    
//    public void savePFeedBack(int pk) throws Exception {
//
//        boolean flag = true;
//        
//        
//        /*if (this.selectedFeedback.getName() == null || this.selectedFeedback.getName().equals("")) {
//            //ERROR
//            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "FeedBack name is Empty"));
//            flag = false;
//        }
//        if (this.selectedFeedback.getDescription() == null || this.selectedFeedback.getDescription().equals("")) {
//            //ERROR
//            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "FeedBack description is Empty"));
//            flag = false;
//        }*/
//        
//
//        if (flag) {
//            
//            
//           
//            this.fService.insert(this.selectedFeedback.getName(), this.selectedFeedback.getDescription(), this.selectedFeedback.getDateOfFeedback(), this.selectedFeedback.getIdStatus(), 21, pk);
//            //pass = true;
//
//            this.esNuevo = false;
//            this.selectedFeedback = new FeedbackTO();
//            //this.selectedProject = new ProjectTO();
//            
//            System.out.println(selectedEmployee.getId());
//            
//            this.fService.insertPj(CAId);
//            
//            //System.out.println(this.selectedEmployee.getId() );
//            
//            
//
//            System.out.println("Salvando feedback");
//            PrimeFaces.current().executeScript("PF('addFeedback').hide()");
//
//        }
//        
//
//    }

    
    public void assignActivity(int idCActivity) throws Exception{
        int idEmployee = selectedEmployee.getId();
        double hours = 0;
        actService.insert(idEmployee, idCActivity, hours); 
    }
    public void removeActivity(int idAct) throws Exception{
        System.out.println("\n\n\n\n\n\n\n\n\n hours to add: " + idAct + "\n\n\n");
        actService.delete(idAct);
    }
    
    public void addHours(int idActivity) throws Exception{
        double hoursToAdd = this.getNumOfHoursToAdd();
        ActivityTO act = actService.searchByPk(idActivity);
        double hoursAct = act.getHours();
        System.out.println("\n\n\n\n\n\n\n\n\n hours to add: " + hoursToAdd+ "\n\n\n");
        double totalhours = hoursAct + hoursToAdd;
        actService.update(act, act.getIdEmployee(), act.getIdActivity(), totalhours);
    }
    
    public void deleteEmployeeFromProject(int employeeID) throws Exception {

        try {
            pXEService.deleteEmployeeFromProject(selectedProject.getId(), employeeID);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Couldn't delete employee from project"));

        }

    }
}