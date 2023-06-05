package apiTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateProjectTest {

    /*
    * given() --->Requerimientos/configuracion
    * when() ---> method+url
    * then() ----> response ->verifications
     */

    @Test
    public void verifyCreateProject(){
        given()
                .auth()
                .preemptive()
                .basic("reva14u_r955m@xeoty.com", "gogeta321")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"Content\":\"Eynar\",\n" +
                        "  \"Icon\":4\n" +
                        "}")
                .log().all()
                .when()
                        .post("https://todo.ly/api/projects.json")
                .then()
                        .log().all()
                        .statusCode(200)
                        .body("Content",equalTo("Eynar"))
                        .body("Icon",equalTo(4));
    }
}
