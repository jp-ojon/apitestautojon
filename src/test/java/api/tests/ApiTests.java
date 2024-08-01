package api.tests;

import org.testng.*;
import org.testng.annotations.*;
import api.endpoints.EndPoints;
import io.restassured.response.Response;
import utilities.CSVReader;
import utilities.Utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.util.List;

public class ApiTests {
	
	//Declare variables with common usage
	Response response;
	int statusCode;
	String contentType;
	String country;
	String state;
	long responseTime;
	boolean placeFound;

	@DataProvider(name = "csvData")
	public Object[][] csvDataProvider() {
		// Specify the path to your CSV file
		String filePath = "testdata.csv"; // Because of the way CSVReader was programmed, default folder path would be
											// src/test/resources
		List<String[]> data = CSVReader.readCSV(filePath);

		// Skip header row
		data.remove(0);

		// Convert List<String[]> to Object[][]
		Object[][] dataArray = new Object[data.size()][];
		for (int i = 0; i < data.size(); i++) {
			dataArray[i] = data.get(i);
		}
		return dataArray;
	}

	@Test(priority = 1, description = "Write API Test for http://api.zippopotam.us/de/bw/stuttgart")
	public void testGetRequestByCityAndState() throws JsonMappingException, JsonProcessingException {
		
		// Perform get request and store the response
		response = EndPoints.getRequestByCountryCityAndState("de", "bw", "stuttgart");

		// Verify the response status code. Status code is 200
		statusCode = response.getStatusCode();
		System.out.println("Status code: "+statusCode);
		Assert.assertEquals(statusCode, 200, "Status code should be 200");

		// Verify the response content type. Content type is JSON
		contentType = response.getContentType();
		System.out.println("Content type: "+contentType);
		Assert.assertTrue(contentType.contains("application/json"), "Content type should be JSON");

		// Get the response time and print the response time for debugging purposes
		responseTime = response.getTime();
		System.out.println("Response Time: " + responseTime + " milliseconds");

		// Assert that the response time is below 1 second (1000 milliseconds)
		Assert.assertTrue(responseTime < 1000, "Response time should be below 1000 milliseconds");

		// Extract values from the response
		country = response.jsonPath().getString("country");
		state = response.jsonPath().getString("state");
		System.out.println("Country: "+country);
		System.out.println("State: "+state);

		// Assert that the country is correct as expected. "country" is "Germany"
		Assert.assertEquals(country, "Germany", "Country value not as expected");

		// Assert that the state is correct as expected. "state" is "Baden-Württemberg".
		Assert.assertEquals(state, "Baden-Württemberg", "State value not as expected");

		// Call method to check if place name is found in the list of places. "70597" is "Stuttgart Degerloch"
		placeFound = Utilities.VerifyListOfPlacesRequestByCountryCityAndState(response, "70597",
				"Stuttgart Degerloch");

		// Assert that the place name is found
		Assert.assertTrue(placeFound, "Place is not found under the provided postal code");
	}

	@Test(priority = 2, description = "Write API Data Driven Test for http://api.zippopotam.us/{country}/{postal-code}", dataProvider = "csvData" )
	public void testGetRequestByCountryAndPostalCode(String countryAbbreviation, String postalCode, String placeName)
			throws JsonMappingException, JsonProcessingException {
		// Perform get request and store the response
		response = EndPoints.getRequestByCountryAndPostalCode(countryAbbreviation, postalCode);
		
		// Verify the response status code. Status code is 200
		statusCode = response.getStatusCode();
		System.out.println("Status code: "+statusCode);
		Assert.assertEquals(statusCode, 200, "Status code should be 200");

		// Verify the response content type. Content type is JSON
		contentType = response.getContentType();
		System.out.println("Content type: "+contentType);
		Assert.assertTrue(contentType.contains("application/json"), "Content type should be JSON");

		// Get the response time and print the response time for debugging purposes
		responseTime = response.getTime();
		System.out.println("Response Time: " + responseTime + " milliseconds");
		
		// Call method to check if place name is found in the list of places.
		placeFound = Utilities.VerifyListOfPlacesRequestByCountryAndPostalCode(response, countryAbbreviation,
				postalCode, placeName);
		
		// Assert that the place name is found
		Assert.assertTrue(placeFound, "Place is not found under the provided postal code and country");

	}

	@AfterMethod
	public void tearDown() {
		// Cleanup code
	}

}
