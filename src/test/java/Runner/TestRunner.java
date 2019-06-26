package Runner;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/*import org.junit.runner.RunWith;

@RunWith(Cucumber.class)*/
@CucumberOptions(features= {"D:\\Git\\PO\\src\\test\\java\\Feature"}, format = {"json:target/cucumber.json","html:target/site/cucumber-pretty"}, glue = "Steps")

public class TestRunner extends AbstractTestNGCucumberTests {
}