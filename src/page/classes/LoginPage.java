package page.classes;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	@FindBy(id = "inputID")
	WebElement inputID;

	@FindBy(id = "inputPassword")
	WebElement inputPassword;

	@FindBy(id = "btnLogin")
	WebElement btnLogin;

	@FindBy(xpath = ".//*[@id='groupLogin']//p/a[text()='Wachtwoord vergeten?']")
	WebElement linkWachtwoordVergeten;

	@FindBy(linkText = "Registreren")
	WebElement linkRegistreer;

	/**
	 * Bestaat loginForm
	 */

	public boolean bestaatLoginForm() {
		try {
			driver.findElement(By.id("loginForm"));
			return true; // Success!
		} catch (NoSuchElementException ignored) {
			return false;
		}
	}

	/**
	 * Set the loginId Textbox
	 * 
	 * @param loginId
	 */

	public void setLoginIdTextBox(String loginId) {
		inputID.click();
		inputID.clear();
		inputID.sendKeys(loginId);
	}

	/**
	 * Set the password Textbox
	 * 
	 * @param password
	 */

	public void setPasswordTextBox(String password) {
		inputPassword.click();
		inputPassword.clear();
		inputPassword.sendKeys(password);
	}

	/**
	 * Click on Aanmeldenbutton
	 */

	public void clickOnAanmeldenButton() {

		btnLogin.click();
	}

	/**
	 * Click on Register Link
	 */
	public void clickOnRegisterLink() {
		linkRegistreer.click();
	}

	/**
	 * Click on Register Link
	 */
	public void clickOnWachtwoordVergetenLink() {
		linkWachtwoordVergeten.click();
	}

	// Check of foutmelding met tekst "Vul uw emailadres in" verschijnt.
	public boolean verschijntMeldingVoerEmailadresIn() {
		boolean result = false;
		WebElement webelement = driver
				.findElement(By.xpath(".//*[@id='emailMessage1']/small[@data-bv-result='INVALID']"));
		String text = webelement.getText();
		if (text.contains("Vul uw e-mailadres in svp")) {
			result = true;
		}
		return result;
	}

	// Check of foutmelding met tekst "Voer een geldige waarde in" verschijnt.
	public boolean verschijntMeldingVoerEenGeldigEmailadresIn() {
		boolean result = false;
		WebElement webelement = driver
				.findElement(By.xpath(".//*[@id='emailMessage1']/small[@data-bv-result='INVALID']"));
		String text = webelement.getText();
		if (text.contains("Voer een geldig e-mailadres in")) {
			result = true;
		}
		return result;
	}

	// Check of foutmelding met tekst "Voer een geldige waarde in" verschijnt.
	public boolean verschijntMeldingEmailEnOfPassword() {
		boolean result = false;
		WebElement webelement = driver
				.findElement(By.xpath(".//*[@id='emailMessage2']/small[@data-bv-result='INVALID']"));
		String text = webelement.getText();
		if (text.contains("Het opgegeven e-mailadres en/of wachtwoord is onjuist")) {
			result = true;
		}
		return result;
	}

	// Check of foutmelding met tekst "Vul uw wachtwoord in svp" verschijnt.
	public boolean verschijntMeldingVoerUwWachtwoordIn() {
		boolean result = false;
		WebElement webelement = driver
				.findElement(By.xpath(".//*[@id='emailMessage2']/small[@data-bv-result='INVALID']"));
		String text = webelement.getText();
		if (text.contains("Vul uw wachtwoord in svp")) {
			result = true;
		}
		return result;
	}

	/**
	 * 
	 * @param driver
	 * @param loginId
	 * @param password
	 */

	public void inloggenWerkWel(String loginId, String password) {

		setLoginIdTextBox(loginId);

		setPasswordTextBox(password);

		clickOnAanmeldenButton();

	}

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

}
