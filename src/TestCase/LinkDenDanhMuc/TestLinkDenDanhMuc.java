package TestCase.LinkDenDanhMuc;

import AutomationTestLibraries.AT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TestLinkDenDanhMuc {

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

    @Test(description = "Kiểm tra các menu danh mục đều có đường dẫn đến trang danh mục đó hay chưa")
    public void case01() throws Exception {
        String loginUrl = "http://localhost:3000";
        WebDriverWait wait = new WebDriverWait(drive, 30);

        //đăng nhập bằng tài khoản admin
        //mở trình duyệt tới đường dẫn
        drive.get(loginUrl);
        //dò tìm element là thẻ a lọc giá và click vào
        ArrayList<WebElement> li = new ArrayList<WebElement>(drive.findElements(By.cssSelector("li.nav-item.submenu.dropdown ul li a")));
        boolean pass = true;
        for(int i = 0; i < li.size(); i++){
            String href = li.get(i).getAttribute("href");
            if (href.equals("http://localhost:3000/#") || href.equals("")){
                pass = false;
                break;
            }
        }
        Assert.assertTrue(pass, "test failed");
    }
}
