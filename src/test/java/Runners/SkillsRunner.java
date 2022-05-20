package Runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

	@RunWith(Cucumber.class)
	@CucumberOptions(
			features= {"C:\\Users\\Rakhi\\eclipse-workspace_new\\LMSApiTest\\src\\test\\java\\resources\\skills"},
			glue= {"stepDefinitions"},
			//tags= "@skillsget",
			plugin= {"pretty","html:target/reports.html"}
		    
			
			
			
			)


	public class SkillsRunner {

	}



