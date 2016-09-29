# language: nl
Functionaliteit: Registreren nieuwe WerkWel gebruiker



Scenario: Registreren gegevens van nieuwe gebruiker
    Gegeven: Nieuw geregistreerde gebruiker heeft email met activeringslink ontvangen
    Als gebruiker met mailaccount "fjpeeters@werkwel.nl" met password "WerkWel@4" op activeringslink in de mail klikt
    Dan gaat gebruiker met mailaccount "fjpeeters@werkwel.nl" naar het userdata scherm
    Als gebruiker alle gegevens correct invult en akkoord gaat met de voorwaarden
      | naam          | wachtwoord     | herhaalWachtwoord | straat      | postcode | plaats    | telefoon   |
      | Frank Peeters | WerkWel@8 		 | WerkWel@8    		 | Dijkweg 12  | 1783AB   | Amsterdam | 0630564950 |
    En gebruiker klikt op button gegevens opslaan
    Dan gaat gebruiker verder naar het inlogscherm