package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class API_GetRequest {
    /*
    https://jsonplaceholder.typicode.com/posts/44 urline bir
    GET request yolladığımızda donen Response un

    status code unun 200,
    ve content type inin JSON,
    ve Response body sinde bulunan userId nin 5,
    ve response bodysinde bulunan title'in "optio dolar molestias sit"
    olduğunu test et
    */

    @Test
    public void get01(){

        // 1-Request url ve Body olustur
        String url = "https://jsonplaceholder.typicode.com/posts/44 ";

        // 2-Expected Data oluştur
        JSONObject expBody = new JSONObject();
        expBody.put("userId",5);
        expBody.put("title","optio dolor molestias sit");

        // 3-Response Kaydet
        Response response = given().when().get(url);

       // response.prettyPrint(); Response yazdırmaya yarıyor

        // 4-Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        JsonPath actBody = response.jsonPath();

        Assert.assertEquals(expBody.get("userId"),actBody.get("userId"));
        Assert.assertEquals(expBody.get("title"),actBody.get("title"));
    }

}

