package Runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

	@RunWith(Cucumber.class)
	@CucumberOptions(
			features= {"..\\LMSApiTest\\src\\test\\java\\resources\\UsersAPI"},
			glue= {"stepDefinitions"},	
			//tags= "@PostNew",
			plugin= {"pretty","html:target/reports.html"}			
			)


	public class LMSRunner {

	}



