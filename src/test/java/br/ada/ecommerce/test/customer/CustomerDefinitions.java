package br.ada.ecommerce.test.customer;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;

public class CustomerDefinitions {

    private RequestSpecification request = RestAssured.given()
            .baseUri("http://localhost:8080")
            .contentType(ContentType.JSON);
    private Response response = null;
    private Customer customer = new Customer();

    @Given("customer is unknown")
    public void customerIsUnknown() {
        customer = new Customer();
        customer.setName(RandomStringUtils.randomAlphabetic(20));
        customer.setDocument(RandomStringUtils.randomNumeric(11));
    }

    @When("customer is registered with success")
    public void customerIsRegistered() {
        response = request.body(customer).when().post("/customers");
        response.then().statusCode(201);
    }

    @Then("customer is known")
    public void customerIsKnown() {
        response = request.when().get("/customers?name="+customer.getName());
        response.then().statusCode(200);
        String name = response.jsonPath().get("[0].name");
        Assertions.assertEquals(customer.getName(), name);
    }

}
