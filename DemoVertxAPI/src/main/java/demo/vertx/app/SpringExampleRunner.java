package demo.vertx.app;

import demo.vertx.app.verticle.EmployeeVerticle;
import io.vertx.core.Vertx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringExampleRunner {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new EmployeeVerticle(context));
        
        System.out.println("Deployment done");
    }

}
