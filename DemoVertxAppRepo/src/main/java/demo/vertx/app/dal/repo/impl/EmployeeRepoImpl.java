package demo.vertx.app.dal.repo.impl;

import demo.vertx.app.dal.model.Employee;
import demo.vertx.app.dal.repo.AbstractEntityRepo;
import demo.vertx.app.dal.repo.CommonRepo;
import demo.vertx.app.dal.repo.EmployeeRepo;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepoImpl extends AbstractEntityRepo<Employee> implements EmployeeRepo {

    public EmployeeRepoImpl() {
        super(Employee.class);
    }

}
