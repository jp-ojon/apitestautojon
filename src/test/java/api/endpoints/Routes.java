package api.endpoints;

public class Routes {
	// contains only the URLs
	public static String base_url = "http://api.zippopotam.us/";
	
	public static String get_url_city = base_url + "{country}/{state}/{city}";
	public static String get_url_postalcode = base_url + "{country}/{postalcode}";
}
