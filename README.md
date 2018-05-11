# MyStudies

Sovelluksen avulla voi seurata omien opintojensa edistymistä yliopistossa.

## Dokumentaatio

[Käyttöohje](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/kaytto-ohje.md)

[Vaatimusmäärittely](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittelu.md)

[Työaikakirjanpito](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/arkkitehtuurikuvaus.md)

[Testausdokumentti](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/Testausdokumentti.md)


## Loppupalautus
[Releaset](https://github.com/olgaviho/otm-harjoitustyo/releases)


## Komentorivitoiminnot

### Testaus
Testit suoritetaan komennolla
```
mvn test
```
Testikattavuusraportti luodaan komennolla
```
mvn jacoco:report
```
Kattavuusraporttia voi tarkastella avaamalla tiedoston target/site/jacoco/index.html

### Suoritettavan jarin generointi

Komento
```
mvn package
```
generoi jar-tiedoston hakemistoon target mystudies-1.0-SNAPSHOT.jar

### Checkstyle

Tarkistukset suoritetaan komennolla
```
 mvn jxr:jxr checkstyle:checkstyle
 ```
Virheilmoitukset selviävät avaamalla tiedoston target/site/checkstyle.html

### JavaDoc

JavaDoc generoidaan komennolla
```
mvn javadoc:javadoc
```
JavaDocia voi tarkastella avaamalla selaimella tiedoston target/site/apidocs/index.html


