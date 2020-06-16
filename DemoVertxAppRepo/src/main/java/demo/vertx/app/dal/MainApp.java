package demo.vertx.app.dal;

import demo.vertx.app.dal.model.Employee;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext appCon = new ClassPathXmlApplicationContext("dal-spring-context.xml");
        EmployeeManager manager = appCon.getBean("employeeManager", EmployeeManager.class);
        Employee employee = manager.findEmployeeById(appCon, 2);
        employee.setFirstName("Fox");
        manager.updateEmployee(appCon, employee);

    }

}
