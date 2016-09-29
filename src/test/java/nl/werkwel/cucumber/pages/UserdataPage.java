package nl.werkwel.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import nl.werkwel.cucumber.domein.Userdata;

public class UserdataPage {

	WebDriver driver;

	@FindBy(id = "registrerenAls")
	WebElement registrerenAls;

	@FindBy(name = "inputName")
	WebElement inputName;

	@FindBy(name = "inputPassword")
	WebElement inputPassword;

	@FindBy(name = "inputPasswordRepeat")
	WebElement inputPasswordRepeat;

	@FindBy(name = "inputCountry")
	WebElement inputCountry;

	@FindBy(name = "inputStreet ")
	WebElement inputStreet;

	@FindBy(name = "inputZipCode")
	WebElement inputZipCode;

	@FindBy(name = "inputCity")
	WebElement inputCity;

	@FindBy(name = "inputPhone")
	WebElement inputPhone;

	@FindBy(id = "inputApprove")
	WebElement inputApprove;

	@FindBy(id = "btnSave")
	WebElement btnSave;

	public boolean correctNaarUserdataPage(String loginId) throws InterruptedException {
		boolean result = false;

		WebElement registrerenAlsElement = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("registrerenAls")));
		Thread.sleep(3000);
		if (registrerenAlsElement != null) {
			if (registrerenAlsElement.getText().contains(loginId)) {
				result = true;
			}
		}
		return result;
	}
	
	public void setUserData (Userdata user){
		setInputTextBox(inputName , user.naam);
		setInputTextBox(inputPassword , user.wachtwoord);
		setInputTextBox(inputPasswordRepeat , user.herhaalWachtwoord);
		setInputCountryTextBox(user.land);
		setInputTextBox(inputStreet , user.straat);
		setInputTextBox(inputZipCode , user.postcode);
		setInputTextBox(inputCity, user.plaats);
		setInputTextBox(inputPhone, user.telefoon);
	}

	public void setInputTextBox(WebElement inputTextBox , String waardeInputfield) {
		inputTextBox.click();
		inputTextBox.clear();
		inputTextBox.sendKeys(waardeInputfield);
	}
	
	public void setInputCountryTextBox(String land) {

		inputCountry.sendKeys(land);
	}

	public void clickInputApprove() {
		inputApprove.click();
	}

	public void clickSaveButton() {
		btnSave.click();
	}

	public boolean verschijntJuisteFoutmeldingNaam(String foutmelding) {
		boolean result = false;
		WebElement webelement = driver
				.findElement(By.xpath(".//*[@id='naamMessage']/small[@data-bv-result='INVALID']"));
		String text = webelement.getText();
		if (text.contains(foutmelding)) {
			result = true;
		}
		return result;
	}

	public boolean verschijntJuisteFoutmeldingPassword(String foutmelding) {
		boolean result = false;
		WebElement webelement = driver
				.findElement(By.xpath(".//*[@id='wachtwoordMessage']/small[@data-bv-result='INVALID']"));
		String text = webelement.getText();
		if (text.contains(foutmelding)) {
			result = true;
		}
		return result;
	}

	public boolean verschijntJuisteFoutmeldingPasswordRepeat(String foutmelding) {
		boolean result = false;
		WebElement webelement = driver
				.findElement(By.xpath(".//*[@id='bevestigWachtwoordMessage']/small[@data-bv-result='INVALID']"));
		String text = webelement.getText();
		if (text.contains(foutmelding)) {
			result = true;
		}
		return result;
	}

	public boolean verschijntTelefoonnummerInvalid() {
		boolean result = false;
		WebElement webelement = driver
				.findElement(By.xpath(".//*[@id='telefoonMessage']/small[@data-bv-result='INVALID']"));
		String text = webelement.getText();
		if (text.contains("Voer een geldig telefoonnummer in (10 cijfers)")) {
			result = true;
		}
		return result;
	}

	public boolean verschijntJuisteFoutmeldingAkkoordMetVoorwaarden(String foutmelding) {
		boolean result = false;
		WebElement webelement = driver.findElement(By.xpath(".//small[@data-bv-result='INVALID']"));
		String text = webelement.getText();
		if (text.contains(foutmelding)) {
			result = true;
		}
		return result;
	}
	public UserdataPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
}
