package stepdifinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

public class FindPetOrder {
    int id;
    String url;
    Response response;


    @Given("Request data  for finding purchase order")
    public void requestDataForFindingPurchaseOrder(){
      url ="https://petstore.swagger.io/v2/store/order/10";

    }

    @When("I am sending request for purchase order")
    public void iAmSendingRequestForPurchaseOrder() {
        response = RestAssured
                .given()
                .header("accept"," application/json")
                .get(url);
        response.then().log().all();
    }

    @Then("I should get successful response for purchase order")
    public void iShouldGetSuccessfulResponseForPurchaseOrder() {
        response.then().statusCode(200);
        response.then().body("id", Matchers.equalTo(10));

    }
}

