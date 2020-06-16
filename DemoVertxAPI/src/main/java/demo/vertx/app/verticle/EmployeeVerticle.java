package demo.vertx.app.verticle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import demo.vertx.app.beans.EmployeeBean;
import demo.vertx.app.bll.ManagerGateWay;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

// vertical is the smallest unit of productivity in vert.X
public class EmployeeVerticle extends AbstractVerticle {

    @Autowired
    private ManagerGateWay service;

    public EmployeeVerticle(final ApplicationContext context) {
        service = (ManagerGateWay) context.getBean("managerGateWay");
    }

    @Override
    public void start(Future<Void> future) {
        //You should perform any necessary initialization work inside the start() method

        Router router = Router.router(vertx);
        GsonBuilder builder = new GsonBuilder();
        // ---------------- Standard Forms -------------- 
        // 1.0 "yyyy-MM-dd'T'HH:mm:ss.SSS" 
        // 2.0 "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        // 3.0 "EEE, dd MMM yyyy HH:mm:ss zzz"
        // 4.0 "yyyy-MM-dd"
        builder.setDateFormat("yyyy-MM-dd");
        builder.serializeNulls();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        router.get("/employee/findEmployeeList").handler(
                ctx -> {
                    List<EmployeeBean> list = service.findEmployeeList();
                    String employeeList = gson.toJson(list);
                    ctx.response()
                            .putHeader("content-type", "application/json")
                            .end(employeeList);
                }
        );

        router.get("/employee/findEmployeeById/:id").handler(
                ctx -> {
                    int id = Integer.parseInt(ctx.pathParam("id"));
                    EmployeeBean employeeBean = service.findEmployeeById(id);
                    String employee = gson.toJson(employeeBean);
                    ctx.response()
                            .putHeader("content-type", "application/json")
                            .end(employee);
                }
        );
        router.delete("/employee/removeEmployee/:id").handler(
                ctx -> {
                    int id = Integer.parseInt(ctx.pathParam("id"));
                    service.removeEmployee(id);
                    ctx.response()
                            .putHeader("content-type", "application/json")
                            .end("Employee Removed Successfully");
                }
        );

        router.post("/employee/addEmployee").handler(BodyHandler.create()).handler(
                ctx -> {
                    EmployeeBean employee = Json.decodeValue(ctx.getBodyAsString(), EmployeeBean.class);
                    EmployeeBean addedEmployee = service.addEmployee(employee);
                    String toJson = gson.toJson(addedEmployee);
                    System.out.println(toJson);

                    ctx.response()
                            .putHeader("content-type", "application/json")
                            .end(toJson);
                }
        );

        router.put("/employee/updateEmployee").handler(BodyHandler.create()).handler(
                ctx -> {

                    EmployeeBean employee = Json.decodeValue(ctx.getBodyAsString(), EmployeeBean.class);
                    EmployeeBean updatedEmployee = service.updateEmployee(employee);
                    ctx.response()
                            .putHeader("content-type", "application/json")
                            .end(gson.toJson(updatedEmployee));
                }
        );

        router.get("/api/v1/:name").handler(
                ctx -> {
                    String name = ctx.pathParam("name");
                    ctx.response().
                            putHeader("content-type", "text/plain")
                            .end("Welcome to Vert.x : " + name);
                    System.out.println("Welcome to Vert.x using port 8888 ");
                }
        );

        vertx.createHttpServer().requestHandler(router).listen(8888);

    }

}
