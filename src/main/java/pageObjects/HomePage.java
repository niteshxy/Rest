package pageObjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	WebDriver driver;

	public HomePage(WebDriver driver)
	{
			this.driver=driver;
	}
	
	public void clickLanguageOption() {
		
		driver.findElement(By.cssSelector(".lang-selector-state-label")).click();
	}
    public int findNumberOfLanguages() {
		
    	List<WebElement> allLanguages = driver.findElements(By.cssSelector(".lang-selector>li"));
    	int i= allLanguages.size();
    	return i;
	}
    
    public void verifyLanguagePresent(String language)
    {
    	List<WebElement> allLanguages = driver.findElements(By.cssSelector(".lang-selector>li"));
    	for (WebElement e : allLanguages)
    	{
    	if (e.getText().equalsIgnoreCase(language))
    	{
    		System.out.println("passssssssssssssssssssssssssssssssssssss");
    	}
    }
    
    	
    
    }
public void clickAdvancedSearch()
{
	driver.findElement(By.cssSelector(".nav-search-button")).click();
}

public void ClickFilterPeopleMenuByText(String FilterText)
{
	List<WebElement> Filters = driver.findElements(By.cssSelector(".search-facet__legend"));
	for (WebElement e : Filters)
	{
		System.out.println(e.getText());
	if (e.getText().contains(FilterText))
	{
		e.click();
		break;
	}
}
	
	}
public boolean isElementPresennt(String locator)
{
	boolean ispresent = false;
	WebElement e = driver.findElement(By.cssSelector(locator));
	ispresent = e.isDisplayed();
			return ispresent;
}

public void typeFirstName(String FirstName)
{
	driver.findElement(By.id("advanced-search-first-name")).sendKeys(FirstName);
}

public void clickSearch()
{
	driver.findElement(By.cssSelector(".submit-button")).click();
}
}



