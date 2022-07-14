package Viesure.backend.Step_Definations.Api;

import com.google.gson.Gson;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;


public class weather_condition_step_Def {
    private static Response response;
    private static RequestSpecification request;
    private JsonPath jsonPath;
    private final Gson gson = new Gson();




    @When("I send a Put request with the condition {int} to the endpoint {string}")
    public void i_send_a_put_request_with_the_condition_to_the_endpoint(Integer condition, String endpoint) {
        Map<String, Object> putRequestMap1 = new HashMap<>();
        putRequestMap1.put("condition", condition);

        response = request.pathParam("endpoint",endpoint)
        .body(putRequestMap1).when().put("{endpoint}");
    }


    @Then("The condition is {string} if we send {int}")
    public void the_condition_is_if_we_send(String string, Integer condition) {
        jsonPath = response.jsonPath();
        String expectecondition = "";

        //int celsius = jsonPath.getInt("weather.tempInCelsius");
        String message = jsonPath.getString("condition");  //"The weather is mild"

        if (condition == 1) {
            expectecondition = "mild";
        } else if (condition == 2) {
            expectecondition = "windy";
        } else if (condition == 3) {
            expectecondition = "mist";
        } else if (condition == 4) {
            expectecondition = "drizzle";
        } else if (condition == 5) {
            expectecondition = "dust";
        }

        Assert.assertEquals(expectecondition, message);
    }


}


















