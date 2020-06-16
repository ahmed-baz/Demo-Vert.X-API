package demo.vertx.app.bll;

import demo.vertx.app.beans.EmployeeBean;
import demo.vertx.app.bll.manager.EmployeeManager;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class ManagerGateWay {

    @Autowired
    private EmployeeManager employeeManager;

    public EmployeeBean findEmployeeById(int id) {
        return employeeManager.findEmployeeById(id);
    }

    public List<EmployeeBean> findEmployeeList() {
        return employeeManager.findEmployeeList();
    }

    public EmployeeBean addEmployee(EmployeeBean employeeBean) {
        return employeeManager.addEmployee(employeeBean);
    }

    public EmployeeBean updateEmployee(EmployeeBean employeeBean) {
        return employeeManager.updateEmployee(employeeBean);
    }

    public void removeEmployee(int id) {
        employeeManager.removeEmployee(id);
    }

    public void setEmployeeManager(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }
}
