package br.ada.ecommerce.test.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;

public class RestAssuredExample {

    private RequestSpecification request = RestAssured.given()
            .baseUri("http://localhost:8080")
            .contentType(ContentType.JSON);

    String name = RandomStringUtils.randomAlphabetic(20);
    String document = RandomStringUtils.randomNumeric(11);


    public void cadastrarCustomer() {
        Response response = request.body("{\"name\": \""+name+"\",\"document\": \""+document+"\"}").when().post("/customers");
        response.prettyPrint();
    }

    public void consultarCustomer() {
        Response response = request.when().get("/customers/"+name);
        response.prettyPrint();
        response.then().statusCode(200);//Assertions.assertEquals(200, response.statusCode)
    }

    public static void main(String[] args) {
        RestAssuredExample example = new RestAssuredExample();
        example.cadastrarCustomer();
        example.consultarCustomer();
    }

}
