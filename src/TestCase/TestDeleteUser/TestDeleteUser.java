package TestCase.TestDeleteUser;

import AutomationTestLibraries.AT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class TestDeleteUser {

    private WebDriver drive;

    @Parameters({"browser"}) //lấy value trong thẻ parameter name là browser từ file run.xml

    @BeforeClass
    public void beforeClass(String browser) {
        //khởi tạo drive với parameter brower thông qua class hỗ trợ AT
        drive = AT.openBrowser(browser);
    }

    @AfterClass
    public void afterClass() {
        //tắt trình duyệt, đóng driver
        drive.quit();
    }

    @Test(description = "Delete 10 user test")
    public void case01() throws Exception {
        String MSSV = "1712465";
        String loginUrl = "http://localhost/khanhhongtranle/KCPM/mantisbt-2.24.2/login_page.php";
        WebDriverWait wait = new WebDriverWait(drive, 30);
        boolean pass = true;

        //đăng nhập bằng tài khoản admin
        //mở trình duyệt tới đường dẫn
        drive.get(loginUrl);
        //set value cho input username form login
        drive.findElement(By.cssSelector("#username")).sendKeys("Administrator");
        drive.findElement(By.cssSelector("input[value='Login']")).click();
        drive.findElement(By.cssSelector("#password")).sendKeys("123456");
        drive.findElement(By.cssSelector("input[value='Login']")).click();

        //login thành công
        //mở tab manager user
        drive.findElement(By.xpath("//*[contains(text(), 'Manage Users')]")).click();
        for(int i = 1; i <= 10; i++) {
            //chọn user cần update
            drive.findElement(By.xpath("//*[contains(text(), '"+MSSV+"_"+i+"')]")).click();
            //click delete user
            drive.findElement(By.cssSelector("input[value='Delete User']")).click();
            //click comfirm delete user
            drive.findElement(By.cssSelector("input[value='Delete Account']")).click();

            //drive.findElement(By.cssSelector("input[value='Proceed']")).click();

            wait.until(ExpectedConditions.visibilityOf(drive.findElement(By.cssSelector("#search"))));
        }
    }


}
