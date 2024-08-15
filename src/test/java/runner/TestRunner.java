package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.*;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/API_tests"
        ,glue={"stepDefinations"},tags="@qa",plugin = { "pretty", "html:target/cucumber-reports.html" }
)
public class TestRunner {
	

}
