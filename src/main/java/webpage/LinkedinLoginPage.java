package webpage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.Properties;

public class LinkedinLoginPage{

    private WebDriver webDriver;
    private Properties prop;

    @FindBy(className = "authentication-iframe")
    private WebElement iframe;

    @FindBy(id="session_key-login")
    private WebElement username;

    @FindBy(id="session_password-login")
    private WebElement password;

    @FindBy(xpath="//div[@class='app-body']//img[contains(@class,'profile-image')]")
    private WebElement profilePic;

    public LinkedinLoginPage(Properties prop) {
        initDriver();
        this.prop = prop;
        PageFactory.initElements(webDriver, this);
    }

    private void initDriver() {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-type","--start-maximized");
        this.webDriver = new ChromeDriver(options);
	}

    public CoursePage login() throws IOException {
        webDriver.get("https://www.linkedin.com/learning/login");
        webDriver.switchTo().frame(iframe);

        username.sendKeys(prop.getProperty("Username"));
        password.sendKeys(prop.getProperty("Password"));
        password.submit();
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.visibilityOf(profilePic));
        return new CoursePage(webDriver);
    }

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

}
