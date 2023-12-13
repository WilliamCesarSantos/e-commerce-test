package br.ada.ecommerce.test.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class StepDefinitions {

    private boolean hungry = false;

    @Given("William is hungry")
    public void williamIsHungry() {
        hungry = true;
    }

    @When("William eats pizza")
    public void eatPizza() {
        System.out.println("Hummm delicia.");
        hungry = false;
    }

    @When("William will not have dinner")
    public void soSad() {
        hungry = true;
    }

    @Then("William will be hungry tonight")
    public void willBeSad() {
        Assertions.assertTrue(hungry);
    }


    @Then("William will be happy")
    public void willBeHappy() {
        Assertions.assertFalse(hungry);
    }
}
