package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private WebDriver driver;

	@FindBy(id = "ap_email")
	private WebElement Username;

	public void EnterUsername(String usr) {
		Username.sendKeys(usr);
	}

	@FindBy(id = "continue")
	private WebElement Continuebutton;

	public void ContinueAction() {
		Continuebutton.click();
	}

	@FindBy(id = "ap_password")
	private WebElement Password;

	public void EnterPassword(String pwd) {
		Password.sendKeys(pwd);
	}

	@FindBy(id = "signInSubmit")
	private WebElement Loginbutton;

	public void SubmitAction() {
		Loginbutton.click();
	}

	@FindBy(id = "auth-captcha-guess")
	private WebElement CaptchaTextbox;

	public void CaptchaEnter(String captchaVal1) {
		CaptchaTextbox.sendKeys(captchaVal1);
	}

	@FindBy(xpath = "//div[@id='auth-error-message-box']//span")
	private WebElement InvalidEmailMessage;

	public String InvalidEmlMesage() {

		String message = InvalidEmailMessage.getText();
		return message;
	}

	@FindBy(xpath = "//input[@id=\"continue\" and @tabindex=\"6\"]")
	private WebElement ContinueWithOTPbutton;

	@FindBy(xpath = "//div[@id='auth-email-missing-alert']//div[@class='a-alert-content']")
	private WebElement BlankEmail;

	public String BlankEmail() {
		String TextMsg = BlankEmail.getText();
		return TextMsg;
	}

	@FindBy(xpath = "//a[@id='auth-fpp-link-bottom']")
	private WebElement ForgotPass;

	public void Forgotpass() {
		ForgotPass.click();
	}

	@FindBy(xpath = "//form[@name ='forgotPassword']//h1")
	private WebElement PassAssist;

	public String PassAssist() {
		String PassAssistText = PassAssist.getText();
		return PassAssistText;
	}

	@FindBy(xpath = "//span[@class= 'a-list-item']")
	private WebElement CaptchaHeadTxt;

	public String CaptchTxtMatch() {
		String CaptchaPgTxt = CaptchaHeadTxt.getText();
		return CaptchaPgTxt;
	}

	public LoginPage(WebDriver driver) {
		// initializing instance variable with local variable
		this.driver = driver;
		// This initElements method will create all WebElemnts
		PageFactory.initElements(driver, this);
	}

	/*
	 * public void EnterUsername(String usr){ Username.sendKeys(usr); }
	 * 
	 * public void EnterPassword(String pwd) { Password.sendKeys(pwd); }
	 * 
	 * public void SubmitAction() { Loginbutton.click(); }
	 */

	/*
	 * public void ContinueAction() { Continuebutton.click(); }
	 * 
	 * public void CaptchaEnter(String captchaVal1) {
	 * CaptchaTextbox.sendKeys(captchaVal1); }
	 * 
	 * public String InvalidEmlMesage(){
	 * 
	 * String message = InvalidEmailMessage.getText(); return message; }
	 * 
	 * 
	 * public String BlankEmail() { String TextMsg = BlankEmail.getText(); return
	 * TextMsg;
	 * 
	 * }
	 * 
	 * 
	 * 
	 * public void Forgotpass() { ForgotPass.click(); }
	 * 
	 * 
	 * 
	 * public String PassAssist() { String PassAssistText = PassAssist.getText();
	 * return PassAssistText; }
	 */
}
