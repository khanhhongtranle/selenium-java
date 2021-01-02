package TestCase.TestSetAccessLevelForUser;

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

public class TestSetAccessLevelForUser {

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

//    @Test(description = "Set Access level cho 10 users đã tạo")
//    public void case01() throws Exception {
//        String MSSV = "1712465";
//        String loginUrl = "http://localhost/khanhhongtranle/KCPM/mantisbt-2.24.2/login_page.php";
//        WebDriverWait wait = new WebDriverWait(drive, 30);
//        boolean pass = true;
//
//        //đăng nhập bằng tài khoản admin
//        //mở trình duyệt tới đường dẫn
//        drive.get(loginUrl);
//        //set value cho input username form login
//        drive.findElement(By.cssSelector("#username")).sendKeys("Administrator");
//        drive.findElement(By.cssSelector("input[value='Login']")).click();
//        drive.findElement(By.cssSelector("#password")).sendKeys("123456");
//        drive.findElement(By.cssSelector("input[value='Login']")).click();
//
//        //login thành công
//
//        for(int i = 1; i <= 10; i++) {
//            //mở tab manager user
//            drive.findElement(By.xpath("//*[contains(text(), 'Manage Users')]")).click();
//            //chọn user cần update
//            drive.findElement(By.xpath("//*[contains(text(), '"+MSSV+"_"+i+"')]")).click();
//            //select level
//            String newLevel, newIdLevel;
//            // option "developer" có value = 55
//            // option "manager" có value = 70
//            if (i % 2 == 0){
//                newLevel = "manager";
//                newIdLevel = "70";
//            }else{
//                newLevel= "developer";
//                newIdLevel = "55";
//            }
//            drive.findElement(By.cssSelector("#edit-access-level")).sendKeys(newLevel);
//            //click update user
//            drive.findElement(By.cssSelector("input[value='Update User']")).click();
//
//            //đợi user updated xong, reload lại trang chi tiết user
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#edit-access-level")));
//            //kiểm tra access level đã được update đúng hay chưa
//            if (!drive.findElement(By.cssSelector("#edit-access-level")).getAttribute("value").equals(newIdLevel)){
//                pass = false;  //nếu có 1 trường hợp set sai thì báo ngay test case fail
//                break;
//            }
//        }
//        if (!pass){ //nếu có 1 trường hợp set sai thì báo ngay test case fail
//            Assert.fail("Fail");
//        }
//    }


    @Test(description = "Set Access level cho 10 users đã tạo từ level từ excel file")
    public void case02() throws Exception{
        String loginUrl = "http://localhost/khanhhongtranle/KCPM/mantisbt-2.24.2/login_page.php";
        List<Map<String, String>> data=AT.getDataExcel("src/TestData/data_access_level.xlsx");
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

        for(Map<String, String> item: data) {
            //mở tab manager user
            drive.findElement(By.xpath("//*[contains(text(), 'Manage Users')]")).click();
            //chọn user cần update
            drive.findElement(By.xpath("//*[contains(text(), '"+item.get("username")+"')]")).click();
            //select level
            drive.findElement(By.cssSelector("#edit-access-level")).sendKeys(item.get("level"));
            //click update user
            drive.findElement(By.cssSelector("input[value='Update User']")).click();

            //đợi user updated xong, reload lại trang chi tiết user
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#edit-access-level")));
            //kiểm tra access level đã được update đúng hay chưa
            if (!drive.findElement(By.cssSelector("#edit-access-level")).getAttribute("value").equals(item.get("value"))){
                pass = false;  //nếu có 1 trường hợp set sai thì báo ngay test case fail
                break;
            }
        }
        if (!pass){ //nếu có 1 trường hợp set sai thì báo ngay test case fail
            Assert.fail("Fail");
        }
    }
}
