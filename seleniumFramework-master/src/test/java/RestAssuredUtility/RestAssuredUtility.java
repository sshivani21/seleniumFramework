package RestAssuredUtility;

import Pages.Add_Log;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RestAssuredUtility {


       @Test
        public void apiMethod() {
            Response response = RestAssured
                    .given()
                    .baseUri("https://jsonplaceholder.typicode.com")
                    .when()
                    .get("/posts/1");

           Add_Log.info("Status Code: " + response.getStatusCode());
           Add_Log.info("Response Body:\n" + response.getBody().asPrettyString());


            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("title", "foo");
            requestBody.put("body", "bar");
            requestBody.put("userId", 1);

            Response responsePost = RestAssured
                    .given()
                    .baseUri("https://jsonplaceholder.typicode.com")
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("/posts");

           Add_Log.info("Status Code: " + responsePost.getStatusCode());
           Add_Log.info("Response Body:\n" + responsePost.getBody().asPrettyString());
        }
    }


