package Runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"C:\\Users\\Rakhi\\eclipse-workspace_new\\LMSApiTest\\src\\test\\java\\resources\\userSkillMapGet"},
		glue= {"stepDefinitions"},
		//tags= "@skillUserMapGetId",
		plugin= {"pretty","html:target/reports.html"}
	    
		
		
		
		)
public class UserSkillMapGetRunner {

}
