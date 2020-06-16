package demo.vertx.app.bll.transformer;

import demo.vertx.app.beans.EmployeeBean;
import demo.vertx.app.dal.model.Employee;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeTransformer implements CommonTransformer<Employee, EmployeeBean> {

    @Override
    public EmployeeBean fromEntityToBean(Employee entity) {
        return new DozerBeanMapper().map(entity, EmployeeBean.class);
    }

    @Override
    public Employee fromBeanToEntity(EmployeeBean bean) {
        return new DozerBeanMapper().map(bean, Employee.class);
    }

}
