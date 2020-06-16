/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.vertx.app.bll.manager.impl;

import demo.vertx.app.beans.EmployeeBean;
import demo.vertx.app.bll.manager.EmployeeManager;
import demo.vertx.app.bll.transformer.EmployeeTransformer;
import demo.vertx.app.dal.model.Employee;
import demo.vertx.app.dal.repo.EmployeeRepo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeManagerImpl implements EmployeeManager {

    public void setEmployeeTransformer(EmployeeTransformer employeeTransformer) {
        this.employeeTransformer = employeeTransformer;
    }

    public void setEmployeeRepo(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Autowired
    private EmployeeTransformer employeeTransformer;
    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public EmployeeBean findEmployeeById(int id) {
        Employee employee = employeeRepo.findById(id);
        return employeeTransformer.fromEntityToBean(employee);
    }

    @Override
    public List<EmployeeBean> findEmployeeList() {
        List<Employee> employeeList = employeeRepo.findList();
        List<EmployeeBean> employeeBeanList = new ArrayList();
        employeeList.forEach(entity -> employeeBeanList.add(employeeTransformer.fromEntityToBean(entity)));
        return employeeBeanList;
    }

    @Override
    public EmployeeBean addEmployee(EmployeeBean bean) {
        Employee employeeEntity = employeeTransformer.fromBeanToEntity(bean);
        Employee addedEmployee = employeeRepo.add(employeeEntity);
        EmployeeBean employeeBean = employeeTransformer.fromEntityToBean(addedEmployee);
        return employeeBean;
    }

    @Override
    public EmployeeBean updateEmployee(EmployeeBean bean) {
        Employee employeeEntity = employeeTransformer.fromBeanToEntity(bean);
        Employee newEmployeeEntity = employeeRepo.update(employeeEntity);
        EmployeeBean employeeBean = employeeTransformer.fromEntityToBean(newEmployeeEntity);
        return employeeBean;
    }

    @Override
    public void removeEmployee(int id) {
        employeeRepo.remove(id);
    }

}
