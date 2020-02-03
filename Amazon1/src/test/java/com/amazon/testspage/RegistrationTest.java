package com.amazon.testspage;

import org.testng.annotations.Test;

import com.amazon.pages.LoginPage;
import com.amazon.pages.RegistrationPage;
import com.amazon.pages.VerifyUserLoggedInPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class RegistrationTest extends LoginTest {
	ChromeDriver driver;
	//private static final Logger logger = LogManager.getLogger(RegistrationTest.class);
	LoginTest LT;
	RegistrationPage RP;
	VerifyUserLoggedInPage VP;

	VerifyUserLoggedInPage VPOne;
	
	//ExtentReports extent;
	//ExtentTest logger1;
	
	//ExtentReports extent = new ExtentReports();
	//ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/LoginPage_automation.html");
	//ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/RegistrationPage_automation.html");
	

	@Test(dataProvider = "getdata") //ScenarioOne : Valid Credentials
	public void ScenarioOne(String fullnm, String phno, String eml, String pwd, String expstring)throws IOException {

		//Report related code				
		extent.attachReporter(reporter);
		ExtentTest logger=extent.createTest("RegistrationTest-ScenarioONE");				
		
		logger.log(Status.INFO,"====Registration Test --> Scenario One =====");
		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S01I01- LoginPage.png");// screenshot code line
		RP.EnterUserDetails(fullnm, phno, eml, pwd);
		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S01I02- RegistrationDetailScreen.png"); // Screenshot code line
		//FileUtils.copyFile(source, new File("./Screenshots/EnteredDetails.png"));
		// screenshot code line

		RP.SubmitAction();
		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S01I03- DetailsSubmitedScreen.png");

		logger.log(Status.INFO,"All details entered and submitted");

		String captchaText = "Enter the characters as they are shown in the image.";

		try {

			while (captchaText.equals(RP.CaptchTxtMatch())) {

				logger.log(Status.INFO,"We are on captcha page");

				// prompt user to enter captcha
				String captchaVal1 = JOptionPane.showInputDialog("Please enter the captcha value : ");
				RP.EnterCaptcha(captchaVal1);
				RP.EnterPassOnly(pwd);
				RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S01I04- CaptchaAndPassword.png");
				RP.SubmitAction();

				System.out.println("Captcha appear..");
			}

		} catch (Exception e) {
			System.out.println("Captcha didn't Appear");
		}

		// OnVerify mobile number page
		// Entering OTP by taking input from user by using input pop up box
		String OTPcal = JOptionPane.showInputDialog("Please enter the OTP : ");
		RP.EnterOTP(OTPcal);
		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S01I05- VerifyMobNumberPage.png");
		RP.HitCreate();
		
		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S01I06- SuccessfullRegistration.png"); // Screenshot code line
		//Once Account is created we have to logout of it for future test
		//Logging out code 
		try {
			//Waiting for the sign out dropdown to appear
			Thread.sleep(3000);
			String result = VP.Verifylogin();

			if(result.equals(expstring)) {
				logger.log(Status.PASS,"Completed test execution");			
			}else
			{
				logger.log(Status.FAIL,"Test case FAILED");
			}
			
			Assert.assertEquals(result, expstring);
			

			Thread.sleep(4000);
			// Hover over 'Account and Lists'
			Actions hover = new Actions(driver);
			Action hoverOverSignin = hover.moveToElement(VP.HoveroverSign()).build();
			hoverOverSignin.perform();

			// Click on Sign out button
			VP.Signoutbutton();
		} catch (Exception e) {
			System.out.println();
		}

	}

	@Test(dataProvider = "getdata") //ScenarioTwo : Enter Valid mob no and then change mob no 
	public void ScenarioTwo(String fullnm, String phno, String eml, String pwd, String expstring) throws IOException {
		
		//Report related code				
				extent.attachReporter(reporter);
				ExtentTest logger=extent.createTest("RegistrationTest-ScenarioTWO");
		logger.log(Status.INFO,"====Registration Test --> Scenario Two =====");

		RP.EnterUserDetails(fullnm, phno, eml, pwd);
		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S02I01-ValidMobNoEnteredDetails.png"); // Screenshot code line
		RP.SubmitAction();
		
		logger.log(Status.INFO,"All details entered and submitted");
		
		String captchaText = "Enter the characters as they are shown in the image.";

		try {

			while (captchaText.equals(RP.CaptchTxtMatch())) {

				logger.log(Status.INFO,"We are on captcha page");

				// prompt user to enter captcha
				String captchaVal1 = JOptionPane.showInputDialog("Please enter the captcha value : ");
				RP.EnterCaptcha(captchaVal1);
				RP.EnterPassOnly(pwd);
				RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S02I02- CaptchaAndPassword.png");
				RP.SubmitAction();

				System.out.println("Captcha appear..");
			}

		} catch (Exception e) {
			System.out.println("Captcha didn't Appear");
		}
		
		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S02I03- RegistrationDetailScreen.png"); // Screenshot code line
		// click on change
		RP.ClickChange();
		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S02I04- AfterClickingChange.png"); // Screenshot code line
		// verify if name, mob no. and email are already filled
		RP.CheckPrefilledVals();
		
		
		//Extent report pass or fail
		if(RP.CheckPrefilledVals()[0].equals(fullnm)) {
			logger.log(Status.PASS,"Completed test execution");		
		}
		else if(RP.CheckPrefilledVals()[1].equals(phno)){
			logger.log(Status.PASS,"Completed test execution");
			
		}
		else if(RP.CheckPrefilledVals()[2].equals(eml)){
			logger.log(Status.PASS,"Completed test execution");
			
		}
		else
		{
			logger.log(Status.FAIL,"Test case FAILED");
		}
		
		
		Assert.assertEquals(RP.CheckPrefilledVals()[0], fullnm);
		Assert.assertEquals(RP.CheckPrefilledVals()[1], phno);
		Assert.assertEquals(RP.CheckPrefilledVals()[2], eml);
		
	}

	@Test(dataProvider = "getdata")//ScenarioThree : Check if Resend OTP button
	public void ScenarioThree(String fullnm, String phno, String eml, String pwd, String expstring) throws InterruptedException, IOException {
		
		//Report related code				
				extent.attachReporter(reporter);
				ExtentTest logger=extent.createTest("RegistrationTest-ScenarioTHREE");
		logger.log(Status.INFO,"====Registration Test --> Scenario Three =====");

		RP.EnterUserDetails(fullnm, phno, eml, pwd);
		
		RP.SubmitAction();

		logger.log(Status.INFO,"All details entered and submitted");

		String captchaText = "Enter the characters as they are shown in the image.";

		try {

			while (captchaText.equals(RP.CaptchTxtMatch())) {

				logger.log(Status.INFO,"We are on captcha page");

				// prompt user to enter captcha
				String captchaVal1 = JOptionPane.showInputDialog("Please enter the captcha value : ");
				RP.EnterCaptcha(captchaVal1);
				RP.EnterPassOnly(pwd);
				RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S03I01- CaptchaAndPassword.png");
				RP.SubmitAction();

				System.out.println("Captcha appear..");
			}

		} catch (Exception e) {
			System.out.println("Captcha didn't Appear");
		}
		
		RP.ClickResendOTP();
		Thread.sleep(4000);
		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S03I02- AfterHittingResendOTPButton.png"); // Screenshot code line
		String SuccessMsg = "We've sent a new OTP by SMS.";
		
		
		if(RP.ChekSuccessMsg().equals(SuccessMsg)) {
			logger.log(Status.PASS,"Completed test execution");			
		}else
		{
			logger.log(Status.FAIL,"Test case FAILED");
		}		
		Assert.assertEquals(RP.ChekSuccessMsg(), SuccessMsg);
	}

	
	@Test (dataProvider = "getdata")//ScenarioFour : Leave all fields blank
	public void ScenarioFour(String fullnm, String phno, String eml, String pwd, String expstring) throws IOException {
		//Report related code				
				extent.attachReporter(reporter);
				ExtentTest logger=extent.createTest("RegistrationTest-ScenarioFOUR");
		logger.log(Status.INFO,"====Registration Test --> Scenario Four =====");

		RP.EnterUserDetails("", "", "", "");
		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S04I01- EnteredBlankFields.png"); // Screenshot code line
		RP.SubmitAction();
		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S04I02- BlankFieldsAfterHittingSubmit.png"); // Screenshot code line
		String BlankEmailMsg = "Enter your name";
		
		
		if(RP.CheckBlankEmailMsg().equals(BlankEmailMsg)) {
			logger.log(Status.PASS,"Completed test execution");			
		}else
		{
			logger.log(Status.FAIL,"Test case FAILED");
		}				
		Assert.assertEquals(RP.CheckBlankEmailMsg(), BlankEmailMsg);

		
		
		String BlankMobMsg = "Enter your mobile number";		
		if(RP.CheckBlankMobMsg().equals(BlankMobMsg)) {
			logger.log(Status.PASS,"Completed test execution");			
		}else
		{
			logger.log(Status.FAIL,"Test case FAILED");
		}
		Assert.assertEquals(RP.CheckBlankMobMsg(), BlankMobMsg);
		
		
		
		String BlankPassMsg = "Enter your password";
		if(RP.CheckBlankPassMsg().equals(BlankPassMsg)) {
			logger.log(Status.PASS,"Completed test execution");			
		}else
		{
			logger.log(Status.FAIL,"Test case FAILED");
		}
		Assert.assertEquals(RP.CheckBlankPassMsg(), BlankPassMsg);
	}
	
	@Test (dataProvider = "getdata")//ScenarioFive : Already registered email
	public void ScenarioFive(String fullnm, String phno, String eml, String pwd, String expstring) throws IOException {
		//Report related code				
				extent.attachReporter(reporter);
				ExtentTest logger=extent.createTest("RegistrationTest-ScenarioFIVE");
		logger.log(Status.INFO,"====Registration Test --> Scenario Five =====");

		RP.EnterUserDetails(fullnm, phno, "g.gaurav2812@gmail.com", pwd);
		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S05I01- AlreadyRegisteredEmail.png"); // Screenshot code line
		RP.SubmitAction();
		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S05I02- AlreadyRegisteredEmailAfterSubmit.png"); // Screenshot code line
		String RegisEmailmsg = "Your provided Email g.gaurav2812@gmail.com has already been used. Please use another Email address.";
		
		
		if(RP.RegisEmail().equals(RegisEmailmsg)) {
			logger.log(Status.PASS,"Completed test execution");			
		}else
		{
			logger.log(Status.FAIL,"Test case FAILED");
		}
		
		Assert.assertEquals(RP.RegisEmail(), RegisEmailmsg);
		
	}
	
	@Test (dataProvider = "getdata")//ScenarioSix : Already registered mob
	public void ScenarioSix(String fullnm, String phno, String eml, String pwd, String expstring) throws IOException {
		//Report related code				
				extent.attachReporter(reporter);
				ExtentTest logger=extent.createTest("RegistrationTest-ScenarioSIX");
		logger.log(Status.INFO,"====Registration Test --> Scenario Six =====");

		RP.EnterUserDetails(fullnm, "7385502840", eml, pwd);
		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S06I01- AlreadyRegisteredMob.png"); // Screenshot code line
		RP.SubmitAction();
		
		String Regismobmsg = "You indicated you are a new customer, but an account already exists with the mobile number +91 7385502840";
		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S06I02- AlreadyRegisteredMobAfterSubmit.png"); // Screenshot code line
		
		
		if(RP.RegisMob().equals(Regismobmsg)) {
			logger.log(Status.PASS,"Completed test execution");			
		}else
		{
			logger.log(Status.FAIL,"Test case FAILED");
		}
				
		Assert.assertEquals(RP.RegisMob(), Regismobmsg);
		
	}
	
	@Test (dataProvider = "getdata")//ScenarioSeven : 5 digit mobile number
	public void ScenarioSeven(String fullnm, String phno, String eml, String pwd, String expstring) throws IOException {
		//Report related code				
				extent.attachReporter(reporter);
				ExtentTest logger=extent.createTest("RegistrationTest-ScenarioSeven");
		logger.log(Status.INFO,"====Registration Test --> Scenario Seven =====");

		RP.EnterUserDetails(fullnm, "90115", eml, pwd);
		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S07I01- InvalidPhoneNumberEntered.png"); // Screenshot code line

		RP.SubmitAction();
		
		String captchaText = "Enter the characters as they are shown in the image.";
		try {

			while (captchaText.equals(RP.CaptchTxtMatch())) {

				logger.log(Status.INFO,"We are on captcha page");

				// prompt user to enter captcha
				String captchaVal1 = JOptionPane.showInputDialog("Please enter the captcha value : ");
				RP.EnterCaptcha(captchaVal1);
				RP.EnterPassOnly(pwd);
				RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S07I02- CaptchaAndPassword.png");
				RP.SubmitAction();

				System.out.println("Captcha appear..");
			}

		} catch (Exception e) {
			System.out.println("Captcha didn't Appear");
		}

		RegistrationTest.screencapture(driver, "./Screenshots/RegistrationPage/S07I03- InvalidPhoneNumberWarnMessage.png"); // Screenshot code line
		String InvMob = "Please enter a valid mobile phone number with area code.";
		
		
		if(RP.RegisEmail().equals(InvMob)) {
			logger.log(Status.PASS,"Completed test execution");			
		}else
		{
			logger.log(Status.FAIL,"Test case FAILED");
		}
		
		Assert.assertEquals(RP.RegisEmail(), InvMob);
		
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2F%3Fref_%3Dnav_ya_signin%26_encoding%3DUTF8&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		RP = new RegistrationPage(driver);
		//RP.ClickGotoLoginP();
		// RegistrationTest.screencapture(driver,"./Screenshots/HomePage.png");
		RP.CreateNewAccount();
		VP = new VerifyUserLoggedInPage(driver);
	}

	@AfterMethod
	public void afterMethod() {
		
		String endOfATest = JOptionPane.showInputDialog("Please enter 'OK' to proceed with further test execution: ");
		System.out.println("CONFIRMATION to start execution of next scenario: "+endOfATest);
		
		//Report extent
		extent.flush();

	}

	@DataProvider
	public Object[][] getdata() throws IOException {

		String currentDirectory = System.getProperty("user.dir");
		// System.out.println(currentDirectory);
		
		String datafile = currentDirectory + "\\src\\test\\resources\\utils\\Amazone.xlsx";
		System.out.println("Current Directory of test data:" + datafile);
		String sheetname = "RegistrationData";
		Object[][] myTestData = ReadExcel.readTestData(datafile, sheetname);

		return myTestData;
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\P10404521.PDCDT01GY4ZVQ1\\Desktop\\Prashant\\chromedriver_win32 (1)\\chromedriver.exe");
		driver = new ChromeDriver();
		/*
		 * driver.get("https://www.amazon.in/"); driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 * 
		 * RP = new RegistrationPage(driver); RP.ClickGotoLoginP();
		 * RegistrationTest.screencapture(driver,"./Screenshots/HomePage.png");
		 * RP.CreateNewAccount();
		 */

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public static void screencapture(WebDriver driver, String fname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(fname));

		// FileUtils.copyFile(source, new File("./Screenshots/HomePage.png"));

	}

}
