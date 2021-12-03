package stepdifinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;


public class Pet {
    WebDriver driver;
    String url;
    String requestBody;
    Response response;



    @Given("Adding pet with valid data")
    public void addingPetWithValidData() {
         url ="https://petstore.swagger.io/v2/pet";
         requestBody = "{\n" +
                "  \"id\": 4455,\n" +
                "  \"category\": {\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"bull\"\n" +
                "  },\n" +
                "  \"name\": \"tom\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 10,\n" +
                "      \"name\": \"abcd\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
    }

    @When("Send request for add pet")
    public void sendRequestForAddPet() {
        response = RestAssured
                .given()
                .header("accept","application/json")
                .header("Content-Type","application/json")
                .body(requestBody).log().all()
                .post(url);
        response.then().log().all();

    }

    @Then("Get successful response for add pet")
    public void getSuccessfulResponseForAddPet() {
        String responseBody = response.body().asString();
        response.then().statusCode(200);
        response.then().body("id",Matchers.equalTo(4455));
        Assert.assertEquals(requestBody.trim().replaceAll("\n","").replaceAll(" ",""),responseBody);

    }


}
