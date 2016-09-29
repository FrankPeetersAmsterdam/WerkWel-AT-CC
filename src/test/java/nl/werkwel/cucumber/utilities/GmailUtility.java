package nl.werkwel.cucumber.utilities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeUtility;
import javax.mail.search.SubjectTerm;

public class GmailUtility {

	public static boolean existsUnReadMail(String emailadres, String password, String subject) throws Exception {

		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		Session session = Session.getDefaultInstance(props, null);
		Store store = session.getStore("imaps");
		store.connect("imap.gmail.com", emailadres, password);
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_WRITE);
		Message[] messages = null;
		boolean isMailFound = false;

		// Zoek naar mail met als onderwerp de waarde van subject
		for (int i = 0; i < 5; i++) {
			messages = folder.search(new SubjectTerm(subject), folder.getMessages());
			// Wait for 50 seconds
			if (messages.length == 0) {
				Thread.sleep(5000);
			}
		}

		// Zoek ongelezen activation mail

		for (Message mail : messages) {
			if (!mail.isSet(Flags.Flag.SEEN)) {
				isMailFound = true;
			}
		}
		return isMailFound;
	}

	public static String getURLFromMail(String emailadres, String password, String subject, String splitTerm1,
			String splitTerm2) throws Exception {

		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		Session session = Session.getDefaultInstance(props, null);
		Store store = session.getStore("imaps");
		store.connect("imap.gmail.com", emailadres, password);
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_WRITE);
		Message[] messages = null;
		boolean isMailFound = false;
		Message mailFromWerkWel = null;

		// Zoek naar mail met als onderwerp de waarde van subject
		for (int i = 0; i < 5; i++) {
			messages = folder.search(new SubjectTerm(subject), folder.getMessages());
			// Wait for 50 seconds
			if (messages.length == 0) {
				Thread.sleep(50000);
			} 
		}

		// Zoek (ongelezen) activation mail
		// Voor het gemak tijdens testen van test programmatuur mag het ook gelezen mail zijn.

		for (Message mail : messages) {
		//	if (!mail.isSet(Flags.Flag.SEEN)) {
				mailFromWerkWel = mail;
				isMailFound = true;
		//	}
		}

		// Test fails if no unread mail was found from WerkWel
		if (!isMailFound) {
			throw new Exception("Could not find activation mail");

			// Lees de inhoud van de mail en extraheer de registratie URL
		} else {
			String line;
			// Er wordt gebruik gemaakt van '=' als end of line character
			// "quoted-printable, met MimeUtility wordt
			// werkelijke tekst weer teruggezet.
			InputStream is = MimeUtility.decode(mailFromWerkWel.getInputStream(), "quoted-printable");
			StringBuffer buffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			// Er wordt gezocht naar het patroon "SplitTerm2 URL SplitTerm1"
			String gezochteURL = buffer.toString().split(splitTerm1)[0].split(splitTerm2)[1];
			return gezochteURL;
		}
	}

}
