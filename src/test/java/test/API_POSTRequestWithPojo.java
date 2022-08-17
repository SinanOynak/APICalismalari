package test;

import baseURLDeposu.HerokuappBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Booking;
import pojos.Bookingdates;
import pojos.Bookingid;

import static io.restassured.RestAssured.given;

public class API_POSTRequestWithPojo extends HerokuappBaseURL {
    @Test
    public void test01() {
        specHerokuapp.pathParam("pp1", "booking");
        Bookingdates bookingdates = new Bookingdates("2022-09-09", "2022-09-21");
        Booking booking = new Booking("Sinan", "Oynak", 1500, true, bookingdates);

        Response response = given()
                .contentType(ContentType.JSON)
                .spec(specHerokuapp)
                .body(booking)
                .when()
                .post("{pp1}");

        response.prettyPrint();

        //Dönen reponse u Bookingid pojos formatına döndürecek
        Bookingid responseBody = response.as(Bookingid.class);

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(booking.getFirstname(),responseBody.getBooking().getFirstname());
        Assert.assertEquals(booking.getLastname(),responseBody.getBooking().getLastname());
        Assert.assertEquals(booking.getBookingdates().getCheckin(),responseBody.getBooking().getBookingdates().getCheckin());
    }
}
