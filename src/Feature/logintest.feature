# language: nl
Functionaliteit: Registreren nieuwe WerkWel gebruiker

  Scenario: Niet succesvolle registratie, emailadres niet gevuld
    Gegeven Gebruiker is op de registratiepagina
    Als Gebruiker geen emailadres invult
    En Klikt op de button nu registreren
    Dan wordt de melding "Voer een waarde in" getoond

  Scenario: Al geregistreerd
    Gegeven Gebruiker is op de registratiepagina
    Als Gebruiker op link algeregistreerd klikt
    Dan gaat gebruiker verder naar het inlogscherm

  Scenario: Annuleren
    Gegeven Gebruiker is op de registratiepagina
    Als Gebruiker op link annuleren klikt
    Dan gaat gebruiker verder naar het inlogscherm

  Scenario: Succesvolle registratie met geldig emailadres
    Gegeven Gebruiker is op de registratiepagina
    Als Gebruiker het geldig en nog niet geregistreerd emailadres "ikwerkweleens2@gmail.com" invult
    En Klikt op de button nu registreren
    Dan Verschijnt er een popup melding
    En Is naar het mailaccount "ikwerkweleens2@gmail.com" met password "WerkWel@3" een email gestuurd
  
  Scenario: Registreren gegevens van nieuwe gebruiker
    Gegeven: Nieuw geregistreerde gebruiker heeft email met activeringslink ontvangen

    Als gebruiker met mailaccount "ikwerkweleens2@gmail.com" met password "WerkWel@3" op activeringslink in de mail klikt
    Dan gaat gebruiker met mailaccount "ikwerkweleens2@gmail.com" naar het userdata scherm
    Als gebruiker alle gegevens correct invult en akkoord gaat met de voorwaarden
      | naam          | wachtwoord     | herhaalWachtwoord | straat      | postcode | plaats    | telefoon   |
      | Frank Peeters | WerkWel@8 		 | WerkWel@8    		 | Dijkweg 12  | 1783AB   | Amsterdam | 0630564950 |
    En gebruiker klikt op button gegevens opslaan
    Dan gaat gebruiker verder naar het inlogscherm
