package stepdefs;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/html/"},
        glue = {"stepdefs"},
        features = {"src/test/resources"}
)
public class RunTest {
}
