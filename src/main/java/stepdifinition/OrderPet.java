package stepdifinition;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import order.PetOrderClass;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.util.Random;

public class OrderPet {
    public String url;
    public String requestBody;
    Response response;
    PetOrderClass petOrderClass = new PetOrderClass();

    @Given ("I am sending request data for order pet")
    public void iAmSendingRequestDataForOrderPet(){
        url ="https://petstore.swagger.io/v2/store/order";
        petOrderClass.setId(Math.abs(new Random().nextInt()));
        petOrderClass.setPetId(Math.abs(new Random().nextInt()));
        petOrderClass.setQuantity(Math.abs(new Random().nextInt()));
        petOrderClass.setShipDate("2021-12-01T02:28:24.776Z");
        petOrderClass.setStatus("placed");
        petOrderClass.setComplete(true);

    }

    @When("I am sending order request")
    public void iAmSendingOrderRequest() {
        Gson gson = new GsonBuilder().create();
        requestBody = gson.toJson(petOrderClass);
        response = RestAssured
                .given()
                .header("accept","application/json").header("Content-Type","application/json")
                .body(requestBody).log().all()
                .post(url);
        response.then().log().all();

    }

    @Then("I should get successful response for order pet")
    public void iShouldGetSuccessfulResponseForOrderPet() {
        String responseBody = response.body().asString();
        response.then().statusCode(200);
        response.then().body("id", Matchers.equalTo(petOrderClass.getId()));
        Assert.assertEquals(requestBody.trim().replaceAll("\n","").replaceFirst("776Z", "776+0000").replaceAll(" ",""),responseBody);
    }
}
