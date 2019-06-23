import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.awt.Symbol;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class RegistrationPagePasswordTest {
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
    public void emptyPasswordTest() {
        registrationPage.typeNewPassword("");
        String error = registrationPage.getErrorPasswordText();
        Assert.assertEquals("Messages do not match!","Пароль должен содержать от 8 до 32 символов, включать хотя бы одну заглавную латинскую букву, одну строчную и одну цифру", error);
    }
    @Test
    public void sevenSymbolsPasswordTest() {
        registrationPage.typeNewPassword("htrd53e");
        String error = registrationPage.getErrorPasswordText();
        Assert.assertEquals("Messages do not match!","Пароль должен содержать от 8 до 32 символов, включать хотя бы одну заглавную латинскую букву, одну строчную и одну цифру", error);
    }
    @Test
    public void thirtyThreeSymbolsPasswordTest() {
        registrationPage.typeNewPassword("lijf75nd!ld89-vkd90r_kdue691jfYTn");
        String error = registrationPage.getErrorPasswordText();
        Assert.assertEquals("Messages do not match!","Пароль должен содержать от 8 до 32 символов, включать хотя бы одну заглавную латинскую букву, одну строчную и одну цифру", error);
    }
    @Test
    public void withoutNumeralSymbolPasswordTest() {
        registrationPage.typeNewPassword("pOlOnEzzz");
        String error = registrationPage.getErrorPasswordText();
        Assert.assertEquals("Messages do not match!","Пароль должен содержать от 8 до 32 символов, включать хотя бы одну заглавную латинскую букву, одну строчную и одну цифру", error);
    }
    @Test
    public void withoutUppercaseLetterPasswordTest() {
        registrationPage.typeNewPassword("polonez22");
        String error = registrationPage.getErrorPasswordText();
        Assert.assertEquals("Messages do not match!","Пароль должен содержать от 8 до 32 символов, включать хотя бы одну заглавную латинскую букву, одну строчную и одну цифру", error);
    }
    @Test
    public void withoutLowercaseLetterPasswordTest() {
        registrationPage.typeNewPassword("POLONEZ22");
        String error = registrationPage.getErrorPasswordText();
        Assert.assertEquals("Messages do not match!","Пароль должен содержать от 8 до 32 символов, включать хотя бы одну заглавную латинскую букву, одну строчную и одну цифру", error);
    }
    @Test
    public void invalidSymbolPasswordTest() {
        char invalidSymbol = '?';
        registrationPage.typeNewPassword("lijf75nd" + invalidSymbol + "d89");
        String error = registrationPage.getErrorPasswordText();
        Assert.assertEquals("Messages do not match!","Символ \"" + invalidSymbol + "\" не поддерживается. Можно использовать символы ! @ $ % ^ & * ( ) _ - +", error);
    }
    @Test
    public void cyrillicSymbolsPasswordTest() {
        registrationPage.typeNewPassword("Йошка1111");
        String error = registrationPage.getErrorPasswordText();
        Assert.assertEquals("Messages do not match!","Вы вводите русские буквы", error);
    }
    @Test
    public void fromEightToFifteenSymbolsPasswordTest() {
        registrationPage.typeNewPassword("2tTpolon");
        String error = registrationPage.getErrorPasswordText();
        Assert.assertEquals("Messages do not match!","Пароль средней сложности", error);
    }
    @Test
    public void fromSixteenSymbolsPasswordTest() {
        registrationPage.typeNewPassword("2tTppppppppppppp");
        String error = registrationPage.getErrorPasswordText();
        Assert.assertEquals("Messages do not match!","Сложный пароль", error);
    }
    @Test
    public void fromEightNumeralsPasswordTest() {
        registrationPage.typeNewPassword("tT22222222");
        String error = registrationPage.getErrorPasswordText();
        Assert.assertEquals("Messages do not match!","Сложный пароль", error);
    }
    @Test
    public void fromFiveSpecSymbolsPasswordTest() {
        registrationPage.typeNewPassword("tT2!!!!!");
        String error = registrationPage.getErrorPasswordText();
        Assert.assertEquals("Messages do not match!","Сложный пароль", error);
    }
    @Test
    public void confirmPasswordTest() {
        registrationPage.typeNewPassword("tT2!!!!!");
        registrationPage.typeConfirmPassword("tT2!!!!2");
        String error = registrationPage.getErrorConfirmPasswordText();
        Assert.assertEquals("Messages do not match!","Пароли не совпадают", error);
    }
    @Test
    public void emptyСonfirmPasswordTest() {
        registrationPage.typeNewPassword("tT2!!!!!");
        registrationPage.typeConfirmPassword("");
        String error = registrationPage.getErrorConfirmPasswordText();
        Assert.assertEquals("Messages do not match!","Пароли не совпадают", error);
    }
    @After
    public void shutDown() {
        driver.quit();
    }
}
