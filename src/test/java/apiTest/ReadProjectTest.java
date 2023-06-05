package apiTest;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReadProjectTest {

    private int idProject;
    /*
     * given() --->Requerimientos/configuracion
     * when() ---> method+url
     * then() ----> response ->verifications
     */

    JSONObject body = new JSONObject();

    @BeforeEach
    public void requirementProject(){
        body.put("Content","Creacion");
        body.put("Icon",6);
        Response response=given()
                .auth()
                .preemptive()
                .basic("reva14u_r955m@xeoty.com", "gogeta321")
                .header("Content-Type", "application/json")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/projects.json");

        response.then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo(body.get("Content")))
                .body("Icon", equalTo(body.get("Icon")));

        idProject = response.then().extract().path("Id");
    }

    @Test
    public void verifyReadProject() {
        Response response= given()
                .auth()
                .preemptive()
                .basic("nikita27111@hotmail.com", "estoesesparta")
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .get("https://todo.ly/api/projects/"+idProject+".json");

        response.then()
                .log().all()
                .statusCode(200)
                .body("Icon",equalTo(body.get("Icon")))
                .body("Content",equalTo(body.get("Content")));
    }

    //Extraer datos de la respuesta

}
