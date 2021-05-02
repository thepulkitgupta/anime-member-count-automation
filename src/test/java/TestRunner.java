import DTO.Members;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java",
        glue = {"DTO","Features","Steps"},
        tags = "@this",
        plugin = "html:target/Output.html"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
