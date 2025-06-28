package RestAssuredUtility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RestAssuredAuthExample {

    @Test
    public  void authExample() {
        performGetWithBasicAuth();
        performPostWithBearerToken();
    }

    // GET request with Basic Authentication
    public static void performGetWithBasicAuth() {
        System.out.println("===== GET with Basic Authentication =====");

        Response response = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("username", "password")  // Replace with your actual username & password
                .baseUri("https://httpbin.org")
                .when()
                .get("/basic-auth/username/password");

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.getBody().asPrettyString());
    }

    // POST request with Bearer Token Authentication
    public static void performPostWithBearerToken() {
        System.out.println("\n===== POST with Bearer Token Authentication =====");

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

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.getBody().asPrettyString());
    }
}
