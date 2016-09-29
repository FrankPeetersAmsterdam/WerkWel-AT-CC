package nl.werkwel.cucumber.loginsteps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.nl.Als;
import cucumber.api.java.nl.Dan;
import cucumber.api.java.nl.En;
import cucumber.api.java.nl.Gegeven;
import nl.werkwel.cucumber.domein.Userdata;
import nl.werkwel.cucumber.pages.LoginPage;
import nl.werkwel.cucumber.pages.RegisterPage;
import nl.werkwel.cucumber.pages.UserdataPage;
import nl.werkwel.cucumber.utilities.Constants;
import nl.werkwel.cucumber.utilities.GmailUtility;

public class loginTestStepDefinitions {
	/*@Gegeven("^Gebruiker is op de registratiepagina$")
	public void gebruiker_is_op_de_registratiepagina() throws Throwable {
		System.out.println("gebruikert");
	}

	@Als("^Gebruiker geen emailadres invult$")
	public void gebruiker_geen_emailadres_invult() throws Throwable {
		System.out.println("vvult mail");
	}

	@Als("^Klikt op de button nu registreren$")
	public void klikt_op_de_button_nu_registreren() throws Throwable {
		System.out.println(" om te regis");
	}

	@Dan("^wordt de melding \"([^\"]*)\" getoond$")
	public void wordt_de_melding_getoond(String arg1) throws Throwable {
		System.out.println("zoals je ziet");
		Assert.assertTrue(false);
	}*/
	
	static WebDriver driver;
	
	LoginPage loginPage;
	RegisterPage registerPage;
	UserdataPage userdataPage;
	String activeringsURL;
	FirefoxProfile fxProfile;
	
	public void initDriver (String url){
		ProfilesIni profile = new ProfilesIni();
		fxProfile = profile.getProfile("SeleniumTestProfile");
		fxProfile.setPreference("browser.startup.homepage_override.mstone", "ignore");
		fxProfile.setPreference("startup.homepage_welcome_url.additional",  "about:blank");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        capabilities.setCapability(FirefoxDriver.PROFILE, fxProfile);
		driver= new FirefoxDriver(capabilities);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	@Gegeven("^Gebruiker is op de registratiepagina$")
	public void user_is_on_Register_Page() {
		initDriver(Constants.LoginURL);
		loginPage = new LoginPage(driver);
		loginPage.clickOnRegisterLink();
		registerPage = new RegisterPage(driver);
		Assert.assertTrue(registerPage.bestaatRegisterForm());
	}

	
	@Als("^Gebruiker het geldig en nog niet geregistreerd emailadres \"([^\"]*)\" invult$")
	public void vulGeldigEmailadresIn(String emailadres) throws Exception{
		registerPage.setLoginIdTextBox(emailadres);	
	}
	
	@En ("^Klikt op de button nu registreren$")
	public void klikt_op_de_button_nu_registreren(){
		registerPage.clickOnConfirmRegistrationButton();
	}
	
	@Dan ("^Verschijnt er een popup melding$") 
	public void verschijnt_er_een_popup_melding () {
		Assert.assertTrue(registerPage.checkAlertPresent());
	}
	
	@En ("^Is naar het mailaccount \"([^\"]*)\" met password \"([^\"]*)\" een email gestuurd$")
	public void isEmailVerstuurd (String emailadres, String passwordGmail) throws Exception{
		Assert.assertTrue(GmailUtility.existsUnReadMail(emailadres, passwordGmail, "Activeren van uw WerkWel account"));
	}
	
	@Als("^Gebruiker geen emailadres invult$")
	public void gebruiker_geen_emailadres_invult() throws Throwable {
		registerPage = new RegisterPage(driver);
		registerPage.setLoginIdTextBox("");
	}

	@Dan("^wordt de melding \"([^\"]*)\" getoond$")
	public void wordt_de_melding_getoond(String arg1) throws Throwable {
		System.out.println("in wordt_de_melding_getoond");
		Assert.assertTrue(registerPage.verschijntMeldingVoerWaardeIn(arg1));
	}

	@Als("^Gebruiker op link algeregistreerd klikt$")
	public void gebruiker_op_link_algeregistreerd_klikt() throws Throwable {
	    registerPage.clickOnAlGeregistreerd();
	}

	@Als("^Gebruiker op link annuleren klikt$")
	public void gebruiker_op_link_annuleren_klikt() throws Throwable {
	    registerPage.clickOnAnnuleren();;
	}
	
	@Dan("^gaat gebruiker verder naar het inlogscherm$")
	public void gaat_gebruiker_verder_naar_het_inlogscherm() throws Throwable {
		loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.bestaatLoginForm());
	}
	@Als("^gebruiker met mailaccount \"([^\"]*)\" met password \"([^\"]*)\" op activeringslink in de mail klikt$")
	public void gebruiker_op_activerings_link_klikt(String emailadres, String password) throws Throwable {
		activeringsURL = 
		GmailUtility.getURLFromMail(emailadres, password,"Activeren van uw WerkWel account",  "'>Klik hier</a> om uw", "gebruikt.<p><a href='");
	}

	@Dan("^gaat gebruiker met mailaccount \"([^\"]*)\" naar het userdata scherm$")
	public void gaat_gebruiker_naar_het_userdata_scherm(String emailadres) throws Throwable {
		initDriver(activeringsURL);
		userdataPage = new UserdataPage(driver);
		Assert.assertTrue(userdataPage.correctNaarUserdataPage(emailadres));	
	}

	@Als("^gebruiker alle gegevens correct invult en akkoord gaat met de voorwaarden$")
	public void gebruiker_alle_gegevens_correct_invult(List<Userdata> users) throws Throwable {
	    userdataPage.setUserData(users.get(0));
	    userdataPage.clickInputApprove();
	}

	@En("^gebruiker klikt op button gegevens opslaan$")
	public void gebruiker_klikt_op_button_gegevens_opslaan() throws Throwable {
	    userdataPage.clickSaveButton();
	}

	
}
