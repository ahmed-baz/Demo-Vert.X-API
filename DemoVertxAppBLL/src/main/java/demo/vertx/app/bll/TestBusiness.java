package demo.vertx.app.bll;

import demo.vertx.app.beans.EmployeeBean;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBusiness {

    public static void main(String[] args) {

        ApplicationContext appCon = new ClassPathXmlApplicationContext("bll-spring-context.xml");
        ManagerGateWay service = appCon.getBean("managerGateWay", ManagerGateWay.class);
        EmployeeBean employeeBean = new EmployeeBean("A", "B", "absss", "0164646464", new Date(), BigDecimal.valueOf(9800));
        EmployeeBean addEmployee = service.addEmployee(employeeBean);
        System.out.println(addEmployee.getemployeeId());

    }
}
