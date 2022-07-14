package Viesure.backend.runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt"
        },

        features = "src/test/resources/features",
        glue = "Viesure/backend/Step_Definations",
        dryRun = false,
        tags = " "


)
public class CukesRunner {
}
