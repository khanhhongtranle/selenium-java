package TestCase.SapXepSPTheoGia;

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

public class TestSapXepSPTheoGia {

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

    @Test(description = "Sắp xếp sản phẩm theo giá tăng dần")
    public void case01() throws Exception {
        String loginUrl = "http://localhost:3000/shoes-stall.html";

        //mở trình duyệt tới đường dẫn
        drive.get(loginUrl);
        //click vào sắp xếp
        drive.findElement(By.xpath("//*[contains(text(), 'Sắp xếp')]")).click();
         //click vào lọc theo giá tăng dần
        drive.findElement(By.xpath("//*[contains(text(), 'Giá tăng dần')]")).click();

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
        for (int p = 1; p < priceList.size(); p++){
            String current = priceList.get(p).substring(1);
            String before = priceList.get(p-1).substring(1);
              if (Integer.parseInt(current) < Integer.parseInt(before)){
                  pass= false;
                  break;
              }
        }
        Assert.assertTrue(pass, "Test failed");
    }
}
