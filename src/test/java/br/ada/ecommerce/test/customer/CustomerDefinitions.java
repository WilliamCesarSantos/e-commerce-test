package br.ada.ecommerce.test.customer;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;

import java.util.List;

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

    @Given("customer without document")
    public void customerWithoutDocument() {
        customer = new Customer();
        customer.setName(RandomStringUtils.randomAlphabetic(20));
        customer.setDocument(null);
    }


    @When("customer is registered with success")
    public void customerIsRegistered() {
        response = request.body(customer).when().post("/customers");
        response.then().statusCode(201);
    }

    @When("customer failed to register")
    public void customerFailedToRegister() {
        response = request.body(customer).when().post("/customers");
        response.then().statusCode(400);
    }

    @Then("customer is known")
    public void customerIsKnown() {
        response = request.when().get("/customers?name="+customer.getName());
        response.then().statusCode(200);
        String name = response.jsonPath().get("[0].name");
        Assertions.assertEquals(customer.getName(), name);
    }

    @Then("notify document must be not null")
    public void notifyDocumentMustBeNotNull() {
        String failReason = response.jsonPath().get("errors[0].document");
        Assertions.assertEquals("must not be null", failReason);
    }

    @And("customer is still unknown")
    public void customerIsStillUnknown() {
        response = request.when().get("/customers?name="+customer.getName());
        response.then().statusCode(200);
        List<Customer> found = response.jsonPath().getList("$");
        Assertions.assertTrue(found.isEmpty());
    }

}
