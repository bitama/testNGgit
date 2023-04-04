package testngapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyTestNG {
	// Element Locator;
	WebDriver driver;
	String path = "src\\main\\java\\testngapp\\config.properties";
	String browser =null;
	String url=null;
	By userName = By.id("username");
	By passWord = By.id("password");
	By Login = By.name("login");
    @BeforeClass
	public void readProp() {
		try {
			InputStream fi = new FileInputStream(path);
			Properties prop = new Properties();
			prop.load(fi);
			
		 browser=prop.getProperty("browser");
		 url=prop.getProperty("url");
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	@BeforeMethod()
	public void launchBrowser() {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Patrick  Perereza\\Desktop\\INTERVIEWWORKSPACE\\TesTNGProj\\dirivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Patrick  Perereza\\Desktop\\INTERVIEWWORKSPACE\\TesTNGProj\\dirivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test()
	public void loginTest() {
		driver.findElement(userName).sendKeys("demo@techfios.com");
		driver.findElement(passWord).sendKeys("abc123");
		driver.findElement(Login).click();

	}

	@Test()
	public void negLoginTest() {
		driver.findElement(By.id("username")).sendKeys("demo@techfios.com");
		driver.findElement(By.id("password")).sendKeys("abc123");
		driver.findElement(By.name("login")).click();
		String actual = "Dashboard- iBilling";
		String expected = driver.getTitle();
		Assert.assertEquals(actual, expected);

	}

//		@AfterMethod()
	public void tearsDoewn() {
		driver.close();
		driver.quit();
	}

}
