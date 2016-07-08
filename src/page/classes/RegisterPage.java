package page.classes;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
	WebDriver driver;

	@FindBy(name = "inputID")
	WebElement inputID;

	@FindBy(id = "usertype1")
	WebElement radioBtnOrganisatie;

	@FindBy(id = "btnConfirmRegistration")
	WebElement btnConfirmRegistration;

	@FindBy(id = "registerForm")
	WebElement registerForm;

	@FindBy(xpath = ".//div[@id='groupContactSettings']//p/a[text()='Al geregistreerd?']")
	WebElement linkAlGeregistreerd;

	@FindBy(xpath = ".//div[@id='groupContactSettings']//p/a[text()='Annuleren']")
	WebElement linkAnnuleren;

	/**
	 * Set the email Textbox
	 * 
	 * @param inputID
	 */

	public void setLoginIdTextBox(String emailAdress) {
		inputID.clear();
		inputID.sendKeys(emailAdress);
	}

	/**
	 * Click on ConfirmRegistrationButton
	 * 
	 * @param driver
	 */

	public void clickOnRadioBtnOrganisatie() {
		radioBtnOrganisatie.click();
	}

	public void clickOnConfirmRegistrationButton() {

		btnConfirmRegistration.click();
	}

	public void clickOnAlGeregistreerd() {
		linkAlGeregistreerd.click();
	}

	public void clickOnAnnuleren() {
		linkAnnuleren.click();
	}

	/**
	 * Bestaat registerForm
	 */

	public boolean bestaatRegisterForm() {
		try {
			driver.findElement(By.id("registerForm"));
			return true; // Success!
		} catch (NoSuchElementException ignored) {
			return false;
		}
	}

	// Check of foutmelding met tekst "Voer een waarde in" verschijnt.
	public boolean verschijntMeldingVoerWaardeIn() {
		boolean result = false;
		WebElement webelement = driver
				.findElement(By.xpath(".//*[@id='emailMessage']/small[@data-bv-result='INVALID']"));
		String text = webelement.getText();
		if (text.contains("Voer een waarde in")) {
			result = true;
		}
		return result;
	}

	// Check of foutmelding met tekst "Voer een geldige waarde in" verschijnt.
	public boolean verschijntMeldingVoerEenGeldigEmailadresIn() {
		boolean result = false;
		WebElement webelement = driver
				.findElement(By.xpath(".//*[@id='emailMessage']/small[@data-bv-result='INVALID']"));
		String text = webelement.getText();
		if (text.contains("Voer een geldig e-mailadres in")) {
			result = true;
		}
		return result;
	}

	// Check of foutmelding met tekst "Dit E-mail adres is al geregistreerd,
	// kies een ander." verschijnt.
	public boolean verschijntMeldingAlGeregistreerd() {
		boolean result = false;
		WebElement webelement = driver
				.findElement(By.xpath(".//*[@id='emailMessage']/small[@data-bv-result='INVALID']"));
		String text = webelement.getText();
		if (text.contains("Dit E-mail adres is al geregistreerd, kies een ander.")) {
			result = true;
		}
		return result;
	}

	public boolean checkAlertPresent(){
		boolean result = false;
		new WebDriverWait(driver, 50).ignoring(NoAlertPresentException.class)
				.until(ExpectedConditions.alertIsPresent());

		Alert alert = driver.switchTo().alert();

		if (alert.getText()
				.contains("U ontvangt binnen enkele ogenblikken een e-mail met verdere instructies voor het inloggen"))
			;
		{
			result = true;
			
		}
		return result;
	}

	/**
	 * Constructor
	 * 
	 * @param driver
	 */
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

}
