# Testausdokumentti

Ohjelmaa on testattu yksikkö- ja integraatiotesteillä JUnitilla.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Testien ytimen muodostavat sovelluslogiikka eli pakkauksen _mystudies.domain_ luokkaa _MyStudiesService_ testaavat integraatiotestit [MyStudiesServiceTest](https://github.com/olgaviho/otm-harjoitustyo/blob/master/MyStudies/src/test/java/domain/MyStudiesServiceTest.java) joiden määrittelevät testitapaukset muistuttavat käyttöliittymän _MyStudiesService_-olion avulla suorittamaa toiminnallisuutta.

### DAO-luokat

Kaikkien DAO-luokkien toiminnallisuus on testattu luomalla testeissä testitietokanta _mystudiestest.db_, joka tyhjennetään aina testien lopuksi.

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on ja haarautumakattavuus ovat hyvällä tasolla.

![alt text](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/Kuvat/testikattavuus2.png)

Testaamatta jäivät tilanteet, joissa tietokantaa tai tarvittavia tauluja ei ole olemassa.

### Asennus

Sovellusta on testattu ainoastaan Linux- ympäristössä. Sovellus on ladattu koneelle [käyttöohjeen](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/kaytto-ohje.md) ilmoittamasta paikasta.

### Toiminnallisuudet

Kaikki [vaatimusmäärittelyn](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittelu.md) perusversion tarjoamaa toiminnallisuutta ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on syötekentät yritetty täyttää myös virheellisillä arvoilla kuten tyhjillä tai pelkillä kirjainyhdistelmillä.
