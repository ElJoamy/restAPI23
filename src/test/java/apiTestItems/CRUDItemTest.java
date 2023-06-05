package apiTestItems;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUDItemTest {

    private static final String USERNAME = "reva14u_r955m@xeoty.com";
    private static final String PASSWORD = "gogeta321";
    private static final String BASE_URL = "https://todo.ly/api/items.json";

    @Test
    public void verifyCRUDItem() {
        int projectId = 4114245;
        int itemId;
        JSONObject body = new JSONObject();
        body.put("Content", "Item Para Meneses");
        body.put("ProjectId", projectId);

        // CREATE
        System.out.println("--------------------CREATE--------------------");
        Response response = given()
                .auth()
                .preemptive()
                .basic(USERNAME, PASSWORD)
                .header("Content-Type", "application/json")
                .body(body.toString())
                .log().all()
                .when()
                .post(BASE_URL);

        response.then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo(body.get("Content")))
                .body("ProjectId", equalTo(body.get("ProjectId")));

        itemId = response.then().extract().path("Id");

        // UPDATE
        System.out.println("--------------------UPDATE--------------------");
        body.remove("Content");
        body.put("Content", "New Meneses Item");
        body.put("Checked", true);

        Response response2 = given()
                .auth()
                .preemptive()
                .basic(USERNAME, PASSWORD)
                .header("Content-Type", "application/json")
                .body(body.toString())
                .log().all()
                .when()
                .put("https://todo.ly/api/items/" + itemId + ".json");

        response2.then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo(body.get("Content")))
                .body("ProjectId", equalTo(body.get("ProjectId")))
                .body("Checked", equalTo(body.get("Checked")));

        // GET
        System.out.println("--------------------GET--------------------");
        Response response3 = given()
                .auth()
                .preemptive()
                .basic(USERNAME, PASSWORD)
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .get("https://todo.ly/api/items/" + itemId + ".json");

        response3.then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo(body.get("Content")))
                .body("ProjectId", equalTo(body.get("ProjectId")))
                .body("Checked", equalTo(body.get("Checked")));

        // DELETE
        System.out.println("--------------------DELETE--------------------");
        Response response4 = given()
                .auth()
                .preemptive()
                .basic(USERNAME, PASSWORD)
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .delete("https://todo.ly/api/items/" + itemId + ".json");

        response4.then()
                .log().all()
                .statusCode(200)
                .body("Content", equalTo(body.get("Content")))
                .body("ProjectId", equalTo(body.get("ProjectId")))
                .body("Checked", equalTo(body.get("Checked")))
                .body("Deleted", equalTo(true));
    }
}
