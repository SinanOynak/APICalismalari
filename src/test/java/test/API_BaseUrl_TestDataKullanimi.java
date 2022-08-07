package test;

import baseURLDeposu.JsonPlaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDataDeposu.JsonPlaceHolderTestData;

import javax.accessibility.AccessibleStateSet;

import static io.restassured.RestAssured.given;

public class API_BaseUrl_TestDataKullanimi extends JsonPlaceholderBaseUrl {
    @Test
    public void test01() {
        // 1- Url ve request Body oluştur
        specJsonPlace.pathParam("pp1", 22);

        //2- Expected Data Oluşturulması
        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();

        JSONObject expBody = jsonPlaceHolderTestData.expectedDataOlustur();

        //3- Response kaydet
        Response response = given().spec(specJsonPlace).when().get("{pp1}");

        //4-Assertion
        JsonPath responseJsonPath = response.jsonPath();

        Assert.assertEquals(jsonPlaceHolderTestData.basariliStatusKod,response.getStatusCode());
        Assert.assertEquals(expBody.getInt("userId"),responseJsonPath.getInt("userId"));
        Assert.assertEquals(expBody.getInt("id"),responseJsonPath.getInt("id"));
        Assert.assertEquals(expBody.getString("title"),responseJsonPath.getString("title"));
        Assert.assertEquals(expBody.getString("body"),responseJsonPath.getString("body"));
    }
}
