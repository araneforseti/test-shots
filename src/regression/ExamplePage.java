import org.openqa.selenium.WebDriver;
import static org.junit.Assert.fail;

public class ExamplePage {
    final WebDriver driver;

    public ExamplePage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://google.com");
    }
}
