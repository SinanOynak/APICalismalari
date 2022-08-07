package test;

import baseURLDeposu.JsonPlaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class API_Deserialization extends JsonPlaceholderBaseUrl {
    @Test
    public void test01() {
        // 1-Url ve request body kullanma
        specJsonPlace.pathParam("pp1", 70);

        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();
        Map<String, Object> reqBodyMap = jsonPlaceHolderTestData.requestBodyOlusturMap();

        // 2-Expected Data Olustur
        Map<String, Object> expDataMap = jsonPlaceHolderTestData.requestBodyOlusturMap();

        //3-Response Kaydetmek

        Response response = given().
                contentType(ContentType.JSON).
                spec(specJsonPlace).
                when().
                body(reqBodyMap).
                put("{pp1}");

        //4-Assertion
        Assert.assertEquals(jsonPlaceHolderTestData.basariliStatusKod,response.getStatusCode());

        Map <String,Object> respBodyMap = response.as(HashMap.class);
        Assert.assertEquals(expDataMap.get("title"),respBodyMap.get("title"));
        Assert.assertEquals(expDataMap.get("body"),respBodyMap.get("body"));
        Assert.assertEquals(expDataMap.get("userId"),respBodyMap.get("userId"));
        Assert.assertEquals(expDataMap.get("id"),respBodyMap.get("id"));


    }
}
