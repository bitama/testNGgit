import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class TesTclass {
	//Element Locator;
	WebDriver driver;
	String browser="firefox";
	By userName=By.id("username");
	By passWord=By.id("password");
	By Login=By.name("login");
    @BeforeMethod()
	public void launchBrowser() {
    	if(browser.equalsIgnoreCase("chrome")) {
    		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Patrick  Perereza\\Desktop\\INTERVIEWWORKSPACE\\TesTNGProj\\dirivers\\chromedriver.exe");
    		driver = new ChromeDriver();
    		}else {
    			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Patrick  Perereza\\Desktop\\INTERVIEWWORKSPACE\\TesTNGProj\\dirivers\\geckodriver.exe");
        		driver = new FirefoxDriver();
    		}
		driver.manage().deleteAllCookies();
		driver.get("https://www.techfios.com/billing/?ng=admin/");
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
		
			
	}

//	@AfterMethod()
	public void tearsDoewn() {
		driver.close();
		driver.quit();
	}


}
