package TestCase.LocSPTheoGia;

import AutomationTestLibraries.AT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestLocSPTheoGia {

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

    @Test(description = "Lọc sản phẩm giày theo giá cả từ $50 trở xuống")
    public void case01() throws Exception {
        String loginUrl = "http://localhost:3000/shoes-stall.html";
        WebDriverWait wait = new WebDriverWait(drive, 30);

        //đăng nhập bằng tài khoản admin
        //mở trình duyệt tới đường dẫn
        drive.get(loginUrl);
        //dò tìm element là thẻ a lọc giá và click vào
        drive.findElement(By.cssSelector("label[for='$50 trở xuống']")).click();

        ArrayList<String> priceList = new ArrayList<>();

        while (true) {
            ArrayList<WebElement> elementsList = new ArrayList<WebElement>(drive.findElements(By.cssSelector(".single-product .product-details .price h6:first-child")));
            if (elementsList.size() == 0){
                break;
            }
            for (int i = 0; i < elementsList.size(); i++){
                priceList.add(elementsList.get(i).getText());
            }

            //click next sang trang
            drive.findElement(By.cssSelector(".next-arrow")).click();
        }
        boolean pass = true;
        for (int p = 0; p < priceList.size(); p++){
               String subPrice = priceList.get(p).substring(1);
               if (Integer.parseInt(subPrice) > 50){
                   pass = false;
                   System.out.println(subPrice);
                   break;
               }
        }
        Assert.assertTrue(pass, "Test failed");
    }

    @Test(description = "Lọc sản phẩm giày theo giá cả tứ $50 - $100")
    public void case02() throws Exception {
        String loginUrl = "http://localhost:3000/shoes-stall.html";

        //mở trình duyệt tới đường dẫn
        drive.get(loginUrl);
        //dò tìm element là thẻ a lọc giá và click vào
        drive.findElement(By.cssSelector("label[for='$50 - $100']")).click();

        ArrayList<String> priceList = new ArrayList<>();

        while (true) {
            ArrayList<WebElement> elementsList = new ArrayList<WebElement>(drive.findElements(By.cssSelector(".single-product .product-details .price h6:first-child")));
            if (elementsList.size() == 0){
                break;
            }
            for (int i = 0; i < elementsList.size(); i++){
                priceList.add(elementsList.get(i).getText());
            }

            //click next sang trang
            drive.findElement(By.cssSelector(".next-arrow")).click();
        }
        boolean pass = true;
        for (int p = 0; p < priceList.size(); p++){
            String subPrice = priceList.get(p).substring(1);
            if (Integer.parseInt(subPrice) < 50 || Integer.parseInt(subPrice) > 100){
                pass = false;
                System.out.println(subPrice);
                break;
            }
        }
        Assert.assertTrue(pass, "Test failed");
    }
}
