import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MainClass {
/*
    static WebDriver driver;
    static WebDriverWait wait;
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\User\\IdeaProjects\\TestEmailPassRambler\\webdrivers\\geckodriver.exe");
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\IdeaProjects\\TestEmailPassRambler\\webdrivers\\chromedriver.exe");

        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://id.rambler.ru/login-20/mail-registration");
        wait  = (new WebDriverWait(driver, 5));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@role='presentation']")));

        //RegistrationPage page = new RegistrationPage(driver);
        RegistrationPage page = PageFactory.initElements(driver, RegistrationPage.class);

        page.selectEmailDomain("lenta.ru");
        page.typeNewPassword("qwerty");
        page.typeConfirmPassword("qwerty");
    }*/
}
