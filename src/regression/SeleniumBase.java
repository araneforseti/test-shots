import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.jboss.arquillian.phantom.resolver.ResolvingPhantomJSDriverService;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.*;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class SeleniumBase {
    static WebDriver driver;

    @BeforeClass
    public static void setup() throws IOException {
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();

        capabilities.setCapability("phantomjs.binary.path", "./target/phantomjs");

        driver = new PhantomJSDriver(
                ResolvingPhantomJSDriverService.createDefaultService(capabilities),
                capabilities);
    }

    @Rule
    public TestRule testWatcher = new TestWatcher() {
        @Override
        public void failed(Throwable t, Description test) {
            takeScreenshot(test.getClassName() + "_" + test.getMethodName());
        }

        public void takeScreenshot(String screenshotName) {
            if (driver instanceof TakesScreenshot) {
                File tempFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                try {
                    FileUtils.copyFile(tempFile, new File("screenshots/" + screenshotName + ".png"));
                } catch (IOException e) {
                    // TODO handle exception
                }
            }
        }
    };
}
