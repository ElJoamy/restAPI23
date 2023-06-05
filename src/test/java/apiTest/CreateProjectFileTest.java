package apiTest;

import groovy.json.JsonParser;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateProjectFileTest {

    /*
     * given() --->Requerimientos/configuracion
     * when() ---> method+url
     * then() ----> response ->verifications
     */

    @Test
    public void verifyCreateProject() {
        String filePath=new File("").getAbsolutePath();
        String resourcePath="/src/test/resources/createProjectBody.json";
        JSONObject test= new JSONObject(new File(filePath+resourcePath));

        given()
                .auth()
                .preemptive()
                .basic("reva14u_r955m@xeoty.com", "gogeta321")
                .header("Content-Type", "application/json")
                .body(test.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/projects.json")
                .then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo(test.get("Content")))
                .body("Icon", equalTo(test.get("Icon")));
    }
}
