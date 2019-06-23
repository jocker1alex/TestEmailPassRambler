import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage {
    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@name='login' and @id='login']")
    private WebElement loginEmailField;
    @FindBy(xpath = "//input[@name='login' and contains(@class, 'rui-Select-field')]")
    private WebElement domainEmailListbox;
    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPasswordField;
    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPasswordField;
    @FindBy(xpath="//div[@class='c0216'][1]//section[1]//div[@class='rui-InputStatus-message']")
    private WebElement errorLoginText;
    @FindBy(xpath="//div[@class='c0216'][2]//section[1]//div[@class='rui-InputStatus-message']/div")
    private WebElement errorPasswordText;
    @FindBy(xpath="//div[@class='c0216'][2]//section[2]//div[@class='rui-InputStatus-message']/div")
    private WebElement errorConfirmPasswordText;

    private String getErrorText(WebElement webElement) {
        try {
            return webElement.getText();
        }
        catch(org.openqa.selenium.NoSuchElementException e) {
            return "";
        }    }
    public String getErrorLoginText() {
        return getErrorText(errorLoginText);
    }
    public String getErrorPasswordText() {
        return getErrorText(errorPasswordText);
    }
    public String getErrorConfirmPasswordText() {
        return getErrorText(errorConfirmPasswordText);
    }

    public RegistrationPage typeLoginEmail(String login) {
        loginEmailField.sendKeys(login);
        newPasswordField.click();
        return this;
    }
    public RegistrationPage typeNewPassword(String password) {
        newPasswordField.sendKeys(password);
        confirmPasswordField.click();
        return this;
    }
    public RegistrationPage typeConfirmPassword(String password) {
        confirmPasswordField.sendKeys(password);
        newPasswordField.click();
        return this;
    }
    public RegistrationPage selectEmailDomain(String emailDomainName) {
        domainEmailListbox.click();
        String emailDomainXpath = String.format("//div[contains(@data-cerber-id, '%s')]", emailDomainName);
        driver.findElement(By.xpath(emailDomainXpath)).click();
        return this;
    }

}
