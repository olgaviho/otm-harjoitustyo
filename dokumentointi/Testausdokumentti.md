# Testausdokumentti

Ohjelmaa on testattu yksikkö- ja integraatiotesteillä JUnitilla.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Testien ytimen muodostavat sovelluslogiikka eli pakkauksen mystudies.domain luokkaa _MyStudiesService_ testaavat integraatiotestit [MyStudiesServiceTest](https://github.com/olgaviho/otm-harjoitustyo/blob/master/MyStudies/src/test/java/domain/MyStudiesServiceTest.java) joiden määrittelevät testitapaukset muistuttavat käyttöliittymän _MyStudiesService_-olion avulla suorittamaa toiminnallisuutta.

### DAO-luokat

Kaikkien DAO-luokkien toiminnallisuus on testattu luomalla testeissä testitietokanta _mystudiestest.db_, joka tyhjennetään aina testien lopuksi.

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 100% ja haarautumakattavuus 96%

Testaamatta jäivät tilanteet, joissa tietokantaa tai tarvittavia tauluja ei ole olemassa.

### Asennus

Sovellusta on testattu ainoastaan Linux- ympäristössä. Sovellus on ladattu koneelle [käyttöohjeen](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/kaytto-ohje.md) ilmoittamasta paikasta.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/maarittelydokumentti.md) perusversion tarjoamaa toiminnallisuutta ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on syötekentät yritetty täyttää myös virheellisillä arvoilla kuten tyhjillä tai pelkillä kirjainyhdistelmillä.
