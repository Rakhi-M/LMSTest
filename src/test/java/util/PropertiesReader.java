package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class PropertiesReader {
	
	static String userName;
	static String password;
	static String baseUrl;
	static String skillsApiEndpoint;
	static String usersApiEndpoint;
	static String userSkillMapApiEndpoint;
	static String userSkillMapGetApiEndpoint;
	static String userSkillMapGetApi_skillIdEndpoint;
	
	public static void readProperties() throws IOException {
		Properties prop=new Properties();		
		FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\config\\config.properties");
		prop.load(ip);
		userName=prop.getProperty("userName");
		password=prop.getProperty("password");
		baseUrl=prop.getProperty("baseUrl");
		skillsApiEndpoint=prop.getProperty("skillsApi");
		usersApiEndpoint = prop.getProperty("usersApi");
		userSkillMapApiEndpoint = prop.getProperty("userSkillMapApi");
		userSkillMapGetApiEndpoint =prop.getProperty("userSkillMapGetApi");
		userSkillMapGetApi_skillIdEndpoint=prop.getProperty("userSkillMapGetApi_skillId");
		usersApiEndpoint=prop.getProperty("usersApi");
	}

	
	public static RequestSpecification getAuthorizedRequest() throws IOException {
		
		return RestAssured.given().auth().basic(userName,password);
	}


	
	public static String setEndPoint(String ApiName) {

		String endPoint=null;
		switch(ApiName) {
		
		case "skills":
			endPoint=baseUrl+skillsApiEndpoint;

			break;
		case "users":
			endPoint=baseUrl+usersApiEndpoint;
			break;
		case "userSkillMap":
			endPoint=baseUrl+userSkillMapApiEndpoint;
			break;
		case "userSkillMapGet":
			endPoint=baseUrl+userSkillMapGetApiEndpoint;
			break;
		case "userSkillMapGetSkillId":
			endPoint=baseUrl+userSkillMapGetApi_skillIdEndpoint;
			break;
		default: 
			System.out.println("Invalid API Name.");
			System.exit(0);

		}
		return endPoint;
	}
}
