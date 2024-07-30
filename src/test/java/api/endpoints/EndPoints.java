package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class EndPoints {
	//contains only the CRUD methods implementation
	
	/**
	 * This method would send a get request with parameters by Country, City and State
	 * @param country 
	 * @param state
	 * @param city
	 * @return response
	 */
	public static Response getRequestByCountryCityAndState(String country, String state, String city) {
		Response response = given().pathParam("country", country).pathParam("state", state).pathParam("city", city)
				.when().get(Routes.get_url_city)
				.then()//.log().all()
				.extract().response();
		System.out.println(country + state + city);
		System.out.println(Routes.get_url_city);
		return response;
	}
	
	/**
	 * This method would send a get request with parameters by Country and Postal Code
	 * @param country 
	 * @param postal code
	 * @return response
	 */
	public static Response getRequestByCountryAndPostalCode(String country, String postalCode) {
		Response response = given().pathParam("country", country).pathParam("postalcode", postalCode)
				.when().get(Routes.get_url_postalcode)
				.then()//.log().all()
				.extract().response();
		return response;
	}
}