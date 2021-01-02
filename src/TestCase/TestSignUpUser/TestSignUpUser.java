package TestCase.TestSignUpUser;

import AutomationTestLibraries.AT;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class TestSignUpUser {

    private WebDriver drive;

    @Parameters({"browser"}) //lấy value trong thẻ parameter name là browser từ file run.xml

    @BeforeClass
    public void beforeClass(String browser) {
        //khởi tạo drive với parameter brower thông qua class hỗ trợ AT
        drive = AT.openBrowser(browser);
    }

    @AfterClass
    public void afterClass() {
        //đóng drive
        drive.quit();
    }

//    @Test(description = "Đăng ký 10 tài khoản theo quy ước username là MSSV_1 ... MSSV_10")
//    public void case01() throws Exception {
//        String MSSV = "1712465";
//        String url = "http://localhost/khanhhongtranle/KCPM/mantisbt-2.24.2/";
//        for (int i = 0; i < 10 ; i++) {
//            //mở trình duyệt tới đường dẫn
//            drive.get(url);
//            //click vào sign up
//            drive.findElement(By.xpath("//*[contains(text(), 'Signup for a new account')]")).click();
//            //tìm element theo CSS seletor và truyền value vào
//            drive.findElement(By.cssSelector("#username")).sendKeys(MSSV + "_" + (i+1));
//            drive.findElement(By.cssSelector("#email-field")).sendKeys(MSSV + "_" + (i+1) + "@gmail.com");
//            //tìm element button sign up và gọi event click
//            drive.findElement(By.cssSelector("input[type='submit']")).click();
//        }
//    }

    @Test(description = "Đăng ký 10 tài khoản với dữ liệu lấy từ file excel")
    public void case02() throws Exception {
        List<Map<String, String>> data=AT.getDataExcel("src/TestData/data_signup.xlsx");

        String url = "http://localhost/khanhhongtranle/KCPM/mantisbt-2.24.2/";
        for (Map<String, String> item: data) {
            //mở trình duyệt tới đường dẫn
            drive.get(url);
            //click vào sign up
            drive.findElement(By.xpath("//*[contains(text(), 'Signup for a new account')]")).click();
            //tìm element theo CSS seletor và truyền value vào
            drive.findElement(By.cssSelector("#username")).sendKeys(item.get("username"));
            drive.findElement(By.cssSelector("#email-field")).sendKeys(item.get("email"));
            //tìm element button sign up và gọi event click
            drive.findElement(By.cssSelector("input[type='submit']")).click();
            Thread.sleep(1000);
        }
    }
}
