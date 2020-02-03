package com.amazon.pages;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

	private WebDriver driver;
	
	@FindBy(id="createAccountSubmit") 
	private WebElement CreateNewAccountButton;
	
	@FindBy(id="ap_customer_name") 
	private WebElement NameTextBox;
	
	@FindBy(id="ap_phone_number") 
	private WebElement PhoneNoTextBox;
	
	@FindBy(id="ap_email") 
	private WebElement EmailTextBox;
	
	@FindBy(id="ap_password") 
	private WebElement PwdTextBox;
		
	@FindBy(id="continue") 
	private WebElement ContinueButton;
	
	@FindBy(id="auth-captcha-guess")
	private WebElement CaptchInpBox;
	
	@FindBy(id="auth-verify-button")
	private WebElement AfterOtpCreateBtn;
	
	@FindBy(xpath="//span[@class='nav-line-1' and contains(text(),'Hello. Sign in')]")
	private WebElement HomePageNavClick;
	
	@FindBy(id="auth-pv-enter-code")
	private WebElement OtpInpBox;
	
	@FindBy (xpath ="//div[@id='auth-error-message-box']//span")
	private WebElement CaptchaMsg;
	
	public RegistrationPage(WebDriver driver) { 
	// initializing instance variable with local variable
	this.driver = driver;
	// This initElements method will create all WebElemnts
	PageFactory.initElements(driver, this);
	}

	public void EnterUserDetails(String fullNm, String phno, String eml, String pwd) {
		NameTextBox.sendKeys(fullNm);
		PhoneNoTextBox.sendKeys(phno);
		EmailTextBox.sendKeys(eml);
		PwdTextBox.sendKeys(pwd);		
	}
	
	public void EnterPassOnly(String pwd) {
		PwdTextBox.sendKeys(pwd);
	}
	
	public void CreateNewAccount() {
		CreateNewAccountButton.click();
	}
	public void SubmitAction() {
		ContinueButton.click();
	}
	public String CaptchTxtMatch() {
		String CaptchaFinalMsg = CaptchaMsg.getText();
		return CaptchaFinalMsg;
	}

	public void EnterCaptcha(String CaptchaInp) {
		CaptchInpBox.sendKeys(CaptchaInp);
	}
	
	public void EnterOTP(String OTPcal) {
		OtpInpBox.sendKeys(OTPcal);
		
	}
	
	public void HitCreate() {
		AfterOtpCreateBtn.click();
		
	}
	
	/*
	@FindBy(id = "ap_email")
	private WebElement IfUsername;
	
	public void ClickGotoLoginP() {
		HomePageNavClick.click();
		
		if(IfUsername.isDisplayed()) {
			 
		  }else {
		 try {
		 Thread.sleep(2000);
		 HomePageNavClick.click();
		 HomePageNavClick.click();
		} catch (Exception e) {
		
		}
		
	}
	}
	*/
	
	@FindBy (xpath = "//a[text() = ' Change ']")
	private WebElement ChangeLink;
	
	public void ClickChange() {
		ChangeLink.click();
	}

	public String[] CheckPrefilledVals() {		
		String[] Alldetails = {NameTextBox.getAttribute("value"),PhoneNoTextBox.getAttribute("value"),EmailTextBox.getAttribute("value")};
		return Alldetails;
	}

	@FindBy (xpath = "//a[@id = 'auth-resend-code-link']")
	private WebElement ResendOTPLink;
	
	public void ClickResendOTP() {
		ResendOTPLink.click();
	}

	
	@FindBy (xpath = "//li[@id = 'auth-resend-code-succeeded']/span")
	//@FindBy (id="auth-resend-code-succeeded")
	private WebElement ResendSuccessMsg;
	
	public String ChekSuccessMsg() {
		String SuccessMessage = ResendSuccessMsg.getText();
		return SuccessMessage;
	}

	
	@FindBy (xpath = "//div[@id='auth-customerName-missing-alert']/div/div")
	private WebElement BlankEmailmsg;
	
	public String CheckBlankEmailMsg() {
		String BlankEmail = BlankEmailmsg.getText();
		return BlankEmail;
	}
	
	@FindBy (xpath = "//div[@id='auth-phoneNumber-missing-alert']/div/div")
	private WebElement BlankMobmsg;
	
	public String CheckBlankMobMsg() {
		String BlankMob = BlankMobmsg.getText();
		return BlankMob;
	}

	@FindBy (xpath = "//div[@id='auth-password-missing-alert']/div/div")
	private WebElement BlankPassmsg;
	
	public String CheckBlankPassMsg() {
		String BlankPass = BlankPassmsg.getText();
		return BlankPass;
	}

	
	@FindBy (xpath = "//div[@id='auth-error-message-box']//span")
	private WebElement RegisEmailmsg;
	
	public String RegisEmail() {
		String RegisEmailMessage = RegisEmailmsg.getText();
		return RegisEmailMessage;
		
	}

	@FindBy (xpath = "//div[@id='authportal-main-section']//p[contains(text(),'already exists')]")
	private WebElement RegisMobmsg;
	
	public String RegisMob() {
		String RegisMobMessage = RegisMobmsg.getText();
		return RegisMobMessage;
	}

	
	

	
	
	
	
}
		


