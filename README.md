# test-shots
Library containing a TestWatcher for taking screenshots when running webdriver tests through junit

Requires:
- jUnit (uses TestWatcher)
- Webdriver

To use, add to the test class you want capable of taking screenshots:
 @Rule
 public TestRule testWatcher = new ScreenshotWatcher(driver);