package stepdifinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class DeletePurchaseOrder {
    int id;
    String url;
    Response response;

    @Given("Request data for delete purchase order")
    public void requestDataForDeletePurchaseOrder(){
        url ="https://petstore.swagger.io/v2/store/order/7";

    }

    @When("I send request for delete purchase order")
    public void iSendRequestForDeletePurchaseOrder() {
        response = RestAssured
                .given()
                .header("accept"," application/json")
                .delete(url);
        response.then().log().all();
    }

    @Then("I should get successful response for delete purchase order")
    public void iShouldGetSuccessfulResponseForDeletePurchaseOrder() {
        response.then().statusCode(200);



    }
}
