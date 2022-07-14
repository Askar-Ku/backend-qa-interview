package Viesure.backend.Step_Definations.Api;

import Viesure.backend.utilities.ConfigurationReader;
import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class Weather_Api_Step_Def {

	private static Response response;
	private static RequestSpecification request;
	private static String expectedDate;
	private static String expectedTime;
	private static String expectedMessage;
	private JsonPath jsonPath;
	private final Gson gson = new Gson();

	@Given("I connected to Base URI")
	public void i_connected_to_base_uri() {
		RestAssured.baseURI = ConfigurationReader.get("base_URI");
	}


	@Given("Content-type is {string}")
	public void content_type_is(String contentType) {
		request = RestAssured.given();
		request.header("Content-Type", contentType);
	}

	@Given("Accept is {string}")
	public void accept_is(String string) {
		request.header("Accept", "application/json");
	}

	@When("I send a Put request with the temperature {int} to the endpoint {string}")
	public void i_send_a_put_request_with_the_temperature_to_the_endpoint(Integer temperature, String endpoint) {
		Map<String, Object> putRequestMap = new HashMap<>();
		putRequestMap.put("tempInFahrenheit", temperature);

		response = request.pathParam("endpoint", endpoint)
				.body(putRequestMap)
				.when().put("{endpoint}");
	}


	@And("i send a get request")
	public void iSendAGetRequest() {
		request = RestAssured.given();
		response = request
				.when().get();
	}


	@When("i send a get request with the path parameter {string}")
	public void i_send_a_get_request_with_the_path_parameter(String param) {
		response = request.pathParam("param", param)
				.when().get("{param}");
	}

	@Then("the status code is {int}")
	public void the_status_code_is(int statusCode) {
		Assert.assertEquals(statusCode, response.statusCode());
	}



	@And("The weather is {string}")
	public void theWeatherIs(String weather) {

		jsonPath = response.jsonPath();
		String expectedWeather = "";

		int celsius = jsonPath.getInt("weather.tempInCelsius");
		String message = jsonPath.getString("description");  //"The weather is mild"

		if (celsius <= 0 ) {
			expectedWeather = "freezing";
		}else if (celsius < 10 ) {
			expectedWeather = "cold";
		}else if (celsius < 20 ) {
			expectedWeather = "mild";
		}else if (celsius < 25 ) {
			expectedWeather = "warm";
		}else if (celsius >= 25 ) {
			expectedWeather = "hot";
		}

		Assert.assertTrue(message.contains(expectedWeather));
	}

}
