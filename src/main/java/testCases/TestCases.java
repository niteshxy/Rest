package testCases;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import constants.LocatorConstants;
import pageObjects.CommonMethods;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import Utilities.BaseClass;
import Utilities.ExcelUtils;
import Utilities.GetDrivers;

public class TestCases extends BaseClass {
	WebDriver driver;
	LoginPage objloginpage;
	GetDrivers objgetdriver;
	CommonMethods objcommonmethod;
	HomePage objhomepage;
	ExtentReports report;
	ExtentTest logger;
	
	
	@BeforeSuite
	public void startreport()
	{
		report=new ExtentReports("src/test/resources/Reports/TestReport"+new Date().getTime()+".html");
	}
	
	
	@Parameters(value={"browser"})
	@BeforeTest
	public void setup(String browser) throws Exception
	{
		objgetdriver=new GetDrivers();
		driver=objgetdriver.getDriverForApplication(browser);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		objloginpage = new LoginPage(driver);
		objcommonmethod = new CommonMethods(driver);
		objhomepage = new HomePage(driver);
		
	}

	
	@Test(dataProvider="Authentication")
	public void testlogin(String Username, String Password) {
		
		logger=report.startTest("Verify Successfull Login");
		objcommonmethod.OpenURL();
		logger.log(LogStatus.INFO, "Application is up and running");
		objhomepage.clickLanguageOption();
		objhomepage.verifyLanguagePresent("english");
		
		objloginpage.login(Username, Password);
		Assert.assertTrue(objhomepage.isElementPresennt(LocatorConstants.profileimgCssSelector));
		objhomepage.clickAdvancedSearch();
		objhomepage.ClickFilterPeopleMenuByText("Keywords");
		objhomepage.typeFirstName(CommonMethods.randomtext());
		objhomepage.clickSearch();
		logger.log(LogStatus.PASS, "Title verified");
	}
	
	
	 @DataProvider(name="Authentication")
	 
	    public Object[][] Authentication() throws Exception{
	 
	         Object[][] testObjArray = ExcelUtils.getTableArray("src/test/resources/TestData/TestData.xlsx","Sheet1");
	 
	         return (testObjArray);
	 
			}
	
	 @AfterTest
	 public void endTest()
	 {
		 driver.quit();
	 }
	 
	 @AfterSuite
	public void endSetup()
	{
		report.endTest(logger);
		report.flush();
	}

}
