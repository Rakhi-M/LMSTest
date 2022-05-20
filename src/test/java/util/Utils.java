package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification reqspec;
	ValidatableResponse response;

	public RequestSpecification requestSpecification() throws IOException {

		if (reqspec == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logDetails.txt"));
			reqspec = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(getGlobalValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();

			return reqspec;
		}
		return reqspec;
	}

	public static String getGlobalValue(String key) throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir")+"\\src\\test\\java\\config\\config.properties");		
		prop.load(fis);
		return prop.getProperty(key);

	}

	public String getJsonValue(String string, String key) {
		String resp = string.toString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();

	}

	public String getJsonValueForField(String keyField) {
		String resp = response.toString();		
		JsonPath js = new JsonPath(resp);
		return js.get(keyField).toString();

	}

	
}
