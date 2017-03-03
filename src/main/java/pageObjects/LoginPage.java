package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver)
	{
			this.driver=driver;
	}
	
	public void login(String Username, String Password) {
		

		driver.get("http://www.linkedin.com");
		driver.findElement(By.id("login-email")).sendKeys(Username);
		driver.findElement(By.id("login-password")).sendKeys(Password);
		driver.findElement(By.id("login-submit")).click();
	}

}
