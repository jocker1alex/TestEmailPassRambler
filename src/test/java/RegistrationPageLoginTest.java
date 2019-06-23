import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class RegistrationPageLoginTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private RegistrationPage registrationPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\User\\IdeaProjects\\TestEmailPassRambler\\webdrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\IdeaProjects\\TestEmailPassRambler\\webdrivers\\chromedriver.exe");
        //driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://id.rambler.ru/login-20/mail-registration");
        wait  = (new WebDriverWait(driver, 5));
        //you must wait until the captcha is loaded
        // otherwise the page will turn white
        // due to an error while running javascript
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@role='presentation']"))); //expect loading captcha
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
    }
    @Test
    public void emptyLoginTest() {
        registrationPage.typeLoginEmail("");
        String error = registrationPage.getErrorLoginText();
        Assert.assertEquals("Messages do not match!","Логин должен быть от 3 до 32 символов", error);
    }
    @Test
    public void oneSymbolLoginTest() {
        registrationPage.typeLoginEmail("1");
        String error = registrationPage.getErrorLoginText();
        Assert.assertEquals("Messages do not match!","Логин должен быть от 3 до 32 символов", error);
    }
    @Test
    public void twoSymbolsLoginTest() {
        registrationPage.typeLoginEmail("qw");
        String error = registrationPage.getErrorLoginText();
        Assert.assertEquals("Messages do not match!","Логин должен быть от 3 до 32 символов", error);
    }
    @Test
    public void thirtyThreeSymbolsLoginTest() {
        registrationPage.typeLoginEmail("lijf75nd.ld89-vkd90r_kdue691jfYTn");
        String error = registrationPage.getErrorLoginText();
        Assert.assertEquals("Messages do not match!","Логин должен быть от 3 до 32 символов", error);
    }
    @Test
    public void startSpecSymbolLoginTest() {
        registrationPage.typeLoginEmail("-p0d");
        String error = registrationPage.getErrorLoginText();
        Assert.assertEquals("Messages do not match!","Недопустимый логин", error);
    }
    @Test
    public void finishSpecSymbolLoginTest() {
        registrationPage.typeLoginEmail("p0d_");
        String error = registrationPage.getErrorLoginText();
        Assert.assertEquals("Messages do not match!","Недопустимый логин", error);
    }
    @Test
    public void togetherEqualSpecSymbolsLoginTest() {
        registrationPage.typeLoginEmail("p0--d");
        String error = registrationPage.getErrorLoginText();
        Assert.assertEquals("Messages do not match!","Недопустимый логин", error);
    }
    @Test
    public void notLatinSymbolsLoginTest() {
        registrationPage.typeLoginEmail("йошкаtest123");
        String error = registrationPage.getErrorLoginText();
        Assert.assertEquals("Messages do not match!","Недопустимый логин", error);
    }
    @Test
    public void invalidSymbolLoginTest() {
        registrationPage.typeLoginEmail("test@");
        String error = registrationPage.getErrorLoginText();
        Assert.assertEquals("Messages do not match!","Недопустимый логин", error);
    }
    @Test
    public void validSequenceOfSymbolsLoginTest() {
        registrationPage.typeLoginEmail("09-Karaku_m.123");
        String error = registrationPage.getErrorLoginText();
        Assert.assertEquals("Messages do not match!","", error);
    }
    @Test
    public void invalidEmailTest() {
        registrationPage.typeLoginEmail("alex");
        //Domain: rambler.ru lenta.ru autorambler.ru myrambler.ru ro.ru rambler.ua
        registrationPage.selectEmailDomain("lenta.ru");
        String error = registrationPage.getErrorLoginText();
        Assert.assertEquals("Messages do not match!","Почтовый ящик недоступен для регистрации. Попробуйте другой", error);
    }
    @Test
    public void validEmailTest() {
        registrationPage.typeLoginEmail("alex33312");
        //Domain: rambler.ru lenta.ru autorambler.ru myrambler.ru ro.ru rambler.ua
        registrationPage.selectEmailDomain("ro.ru");
        String error = registrationPage.getErrorLoginText();
        Assert.assertEquals("Messages do not match!","", error);
    }
    @After
    public void shutDown() {
        driver.quit();
    }
}
