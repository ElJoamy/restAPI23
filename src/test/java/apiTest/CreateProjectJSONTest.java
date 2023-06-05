package apiTest;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateProjectJSONTest {

    /*
     * given() --->Requerimientos/configuracion
     * when() ---> method+url
     * then() ----> response ->verifications
     */

    @Test
    public void verifyCreateProject() {
        JSONObject body = new JSONObject();
        body.put("Content","TestSubjectItemHolder");
        body.put("Icon",8);
        given()
                .auth()
                .preemptive()
                .basic("reva14u_r955m@xeoty.com", "gogeta321")
                .header("Content-Type", "application/json")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/projects.json")
                .then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo(body.get("Content")))
                .body("Icon", equalTo(body.get("Icon")));
    }
}
