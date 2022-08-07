package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class API_PostRequest {
    @Test
    public void post01(){
        // 1-Request URL ve body oluştur
        String url = "https://jsonplaceholder.typicode.com/posts";

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "API");
        requestBody.put("body", "API ogrenmek ne guzel");
        requestBody.put("userId", 10);

        // 2-Expected Data Oluşturulması
        JSONObject expBody = new JSONObject();
        expBody.put("title", "API");
        expBody.put("body", "API ogrenmek ne guzel");
        expBody.put("userId", 10);

        // 3-Response kaydetmek
        Response response = given().
                contentType(ContentType.JSON).
                when().
                body(requestBody.toString()).
                post(url);

        JsonPath actBody = response.jsonPath();

        response.
                then().
                assertThat().
                contentType(ContentType.JSON).
                statusCode(201);

        //Assertion
        Assert.assertEquals(expBody.get("title"),actBody.get("title"));
        Assert.assertEquals(expBody.get("body"),actBody.get("body"));
        Assert.assertEquals(expBody.get("userId"),actBody.get("userId"));

    }
}
