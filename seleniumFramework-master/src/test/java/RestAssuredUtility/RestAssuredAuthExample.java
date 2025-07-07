package RestAssuredUtility;

import Pages.Add_Log;
import TestBase.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RestAssuredAuthExample extends TestBase {

    @Test
    public  void authExample() {
        performGetWithBasicAuth();
        performPostWithBearerToken();
    }

    // GET request with Basic Authentication
    public static void performGetWithBasicAuth() {
        Add_Log.info("===== GET with Basic Authentication =====");

        test = extent.createTest("API Test");
        Response response = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("username", "password")  // Replace with your actual username & password
                .baseUri("https://httpbin.org")
                .when()
                .get("/basic-auth/username/password");

        Add_Log.info("Status Code: " + response.getStatusCode());
        Add_Log.info("Response Body:\n" + response.getBody().asPrettyString());

        test.pass("Status COde " + response.getStatusCode());
    }

    // POST request with Bearer Token Authentication
    public static void performPostWithBearerToken() {
        Add_Log.info("\n===== POST with Bearer Token Authentication =====");

        String token = "your_bearer_token_here";  // Replace with your actual bearer token

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "foo");
        requestBody.put("body", "bar");
        requestBody.put("userId", 1);

        Response response = RestAssured
                .given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts");

        test.pass("Status COde " + response.getStatusCode());
        Add_Log.info("Status Code: " + response.getStatusCode());
        Add_Log.info("Response Body:\n" + response.getBody().asPrettyString());
    }
}
