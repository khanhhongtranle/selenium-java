package TestCase.SoLanSPDuocXem;

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

import java.util.ArrayList;

public class TestSoLanSPDuocXem {

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

    @Test(description = "Reload trang sản phẩm và kiểm tra xem số lượt xem sản phẩm có tăng 1 hay không")
    public void case01() throws Exception {
        //chọn sẵn 1 sản phẩm và lấy đường link của nó
        String loginUrl = "http://localhost:3000/single-product.html?_id=5e1553b9cb0e2e056ec0e0d7";

        //mở trình duyệt tới đường dẫn
        drive.get(loginUrl);
        //dò tìm element là thẻ li
        ArrayList<WebElement> liTagElements = new ArrayList<>(drive.findElements(By.cssSelector(".s_product_text ul li")));
        int luotxem_1 = Integer.parseInt(liTagElements.get(2).getText().substring(11));

        //reload trang
        drive.get(drive.getCurrentUrl());

        //dò tìm element là thẻ li
        liTagElements = new ArrayList<>(drive.findElements(By.cssSelector(".s_product_text ul li")));
        int luotxem_2 = Integer.parseInt(liTagElements.get(2).getText().substring(11));

        Assert.assertEquals(luotxem_2, luotxem_1 + 1);
    }
}
