import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java",
        tags = "@this",
        plugin = "html:target/Output.html"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
