import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(tags = {"@now1"},
        monochrome = true)
public class RunTests {
}