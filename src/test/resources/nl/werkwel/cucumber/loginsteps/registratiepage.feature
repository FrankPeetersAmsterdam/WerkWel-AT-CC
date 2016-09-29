# language: nl
Functionaliteit: Testen van de Registratiepagina

	Achtergrond: 
	  Gegeven Gebruiker is op de registratiepagina
  
  Scenario: Niet succesvolle registratie, emailadres niet gevuld
    Als Gebruiker geen emailadres invult
    En Klikt op de button nu registreren
    Dan wordt de melding "Voer een waarde in" getoond
    
	Scenario: Link Algeregistreerd
    Als Gebruiker op link algeregistreerd klikt
    Dan gaat gebruiker verder naar het inlogscherm

  Scenario: Link Annuleren
    Als Gebruiker op link annuleren klikt
    Dan gaat gebruiker verder naar het inlogscherm

  
  
  