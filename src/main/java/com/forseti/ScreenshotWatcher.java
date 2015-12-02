package com.forseti;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotWatcher extends TestWatcher {
    private WebDriver driver;

    public ScreenshotWatcher(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void failed(Throwable t, Description test) {
        try {
            takeScreenshot(test.getClassName() + "_" + test.getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void takeScreenshot(String screenshotName) throws IOException {
        if (driver instanceof TakesScreenshot) {
            File tempFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(tempFile, new File("screenshots/" + screenshotName + ".png"));
        }
    }
}
