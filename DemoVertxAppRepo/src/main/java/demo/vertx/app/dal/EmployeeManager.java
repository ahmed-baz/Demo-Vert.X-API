/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.vertx.app.dal;

import demo.vertx.app.dal.model.Employee;
import demo.vertx.app.dal.repo.EmployeeRepo;
import demo.vertx.app.dal.repo.impl.EmployeeRepoImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Qualifier("employeeManager")
public class EmployeeManager {

    @Transactional
    public List<Employee> findEmployeeList(ApplicationContext appCon) {
        EmployeeRepo bean = appCon.getBean("employeeRepoImpl", EmployeeRepo.class);
        List<Employee> list = bean.findList();
        return list;
    }

    @Transactional
    public Employee findEmployeeById(ApplicationContext appCon, Integer id) {
        EmployeeRepo bean = appCon.getBean("employeeRepoImpl", EmployeeRepo.class);
        return bean.findById(id);
    }

    @Transactional
    public Employee updateEmployee(ApplicationContext appCon, Employee employee) {
        EmployeeRepo bean = appCon.getBean("employeeRepoImpl", EmployeeRepo.class);
        return bean.update(employee);
    }
}
