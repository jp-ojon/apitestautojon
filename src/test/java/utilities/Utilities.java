package utilities;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import io.restassured.response.Response;

public class Utilities {

	/**This method will verify List Of Places in the response body from get Request By Country, City And State.
	 *  
	 * @param response
	 * @param postalCode
	 * @param placeName
	 * @return true if found, false if not found
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static boolean VerifyListOfPlacesRequestByCountryCityAndState(Response response, String postalCode, String placeName) throws JsonMappingException, JsonProcessingException {
		// Extract the list of places from the response	
		String jsonResponse = response.getBody().asString();
		ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

		List<Map<String, ?>> places = objectMapper.convertValue(
				jsonNode.path("places"),
		    new TypeReference<List<Map<String, ?>>>() {}
		);

		// Iterate through the list of places
		for (Map<String, ?> place : places) {
			// Extract the post code and place name
			String getPostalCode = (String) place.get("post code");
			String getPlaceName = (String) place.get("place name");

			// Check if the get post code and get place name are equivalent the provided postalCode and placeName
			if (postalCode.equals(getPostalCode) && placeName.equals(getPlaceName)) {
				System.out.println("Place found "+ getPostalCode + " " + getPlaceName);
				return true; // Return true if a match is found
			}
		}
		return false; // Return false if no match is found
	}
	
	/** This method will verify List Of Places in the response body from get Request By Country and Postal Code
	 * 
	 * @param response
	 * @param countryAbbreviation
	 * @param postalCode
	 * @param placeName
	 * @return true if found, false if not found
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static boolean VerifyListOfPlacesRequestByCountryAndPostalCode(Response response, String countryAbbreviation, String postalCode, String placeName) throws JsonMappingException, JsonProcessingException {
		System.out.println("Input Data: "+countryAbbreviation + " " + postalCode + " " + placeName);
		
		/* Restassured library
		String getCountry = response.jsonPath().getString("country");
		String getPostalCode = response.jsonPath().getString("post code");
		*/
		/* gson library
		JsonObject jsonObject = JsonParser.parseString(response.getBody().asString()).getAsJsonObject();

		String getCountry = jsonObject.get("country abbreviation").getAsString();
		String getPostalCode = jsonObject.get("post code").getAsString();
		System.out.println(getCountry);
		System.out.println(getPostalCode);
		*/
		//using jackson library
		String jsonResponse = response.getBody().asString();
		ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        // Extract values
        String getCountry = jsonNode.get("country abbreviation").asText();
        String getPostalCode = jsonNode.get("post code").asText();

		List<Map<String, ?>> places = objectMapper.convertValue(
				jsonNode.path("places"),
		    new TypeReference<List<Map<String, ?>>>() {}
		);
		
		// Iterate through the list of places
		for (Map<String, ?> place : places) {
			// Extract the place name
			String getPlaceName = (String) place.get("place name");
			// Check if the get post code, get place name and get country abbreviation are equivalent the provided postalCode, placeName and countryAbbreviation
			if (postalCode.equals(getPostalCode) && placeName.equals(getPlaceName) && countryAbbreviation.equalsIgnoreCase(getCountry) ) {
				System.out.println("Place found "+ getCountry +" "+ getPostalCode + " " + getPlaceName);
				return true; // Return true if a match is found
			}
		}
		return false; // Return false if no match is found
	}
}
