package runner;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;

import helpers.contextData;
import io.cucumber.junit.*;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/API_tests"
        ,glue={"stepDefinations"},plugin = { "pretty", "html:target/cucumber-reports.html" }
)
public class Testrunner {
	

}
