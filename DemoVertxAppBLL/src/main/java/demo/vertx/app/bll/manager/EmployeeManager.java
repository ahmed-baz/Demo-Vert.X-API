/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.vertx.app.bll.manager;

import demo.vertx.app.beans.EmployeeBean;
import java.util.List;

public interface EmployeeManager {

    public EmployeeBean findEmployeeById(int id);

    public List<EmployeeBean> findEmployeeList();

    public EmployeeBean addEmployee(EmployeeBean employeeBean);

    public EmployeeBean updateEmployee(EmployeeBean employeeBean);

    public void removeEmployee(int id);
}
