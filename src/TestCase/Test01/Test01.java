package TestCase.Test01;

import AutomationTestLibraries.AT;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test01 {

    private WebDriver drive;

    @Parameters({"browser"})

    @BeforeClass
    public void beforeClass(String browser) {
        drive = AT.openBrowser(browser);
    }

    @AfterClass
    public void afterClass() {
        drive.quit();
    }

    @Test
    public void case01() throws Exception {
        AT.get(drive, "https://google.com");
    }
}