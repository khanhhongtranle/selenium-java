package AutomationTestLibraries;

import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AT {
    public static WebDriver openBrowser(String browser) {
        WebDriver driver;
        switch (browser) {
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", "browsers/chromedriver");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("start-maximized");
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
                break;
            }
            case "firefox": {
                System.setProperty("webdriver.gecko.driver", "browsers/geckodriver");
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--incognito");
                options.addArguments("start-maximized");
                driver = new FirefoxDriver(options);
                break;
            }
            default: {
                System.setProperty("webdriver.chrome.driver", "browsers/chromedriver");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("start-maximized");
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
                break;
            }
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void setValue(WebDriver driver, String selector, String value) {
        WebElement element = driver.findElement(By.cssSelector(selector));
        element.clear();
        element.sendKeys(value);
    }

    public static void waitPresense(WebDriver driver, String selector) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector)));
    }

    public static void waitVisible(WebDriver driver, String selector) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(selector))));
    }

    public static void waitClickable(WebDriver driver, String selector) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(selector))));
    }

    public static void waitNotVisible(WebDriver driver, String selector) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(selector)));
    }

    public static void waitAlertPresence(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void click(WebDriver driver, String selector) {
        driver.findElement(By.cssSelector(selector)).click();
    }

    public static void get(WebDriver driver, String url) {
        driver.get(url);
    }

    public static List<Map<String, String>> getDataExcel(String path) throws Exception {
        FileInputStream file = new FileInputStream(path);
        XSSFWorkbook book = new XSSFWorkbook(file);
        XSSFSheet sheet = book.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();
        List<Map<String, String>> data = new ArrayList<>();
        boolean firstRow = true;
        List<String> keyData = new ArrayList<>();
        Map<String, String> rowData;
        Row row;
        Cell cell;
        int i;
        while (rows.hasNext()) {
            row = rows.next();
            Iterator<Cell> cells = row.cellIterator();
            if (firstRow) {
                firstRow = false;
                while (cells.hasNext()) {
                    cell = cells.next();
                    keyData.add(cell.getStringCellValue());
                }
            } else {
                i = 0;
                rowData = new HashMap<>();
                while (cells.hasNext()) {
                    cell = cells.next();
                    rowData.put(keyData.get(i), cell.getStringCellValue());
                    i++;
                }
                data.add(rowData);
            }

        }
        return data;
    }
}
