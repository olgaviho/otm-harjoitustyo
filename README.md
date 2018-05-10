# MyCourses<h1>

Sovelluksen avulla voi seurata omien opintojensa edistymistä yliopistossa.

## Dokumentaatio<h2>

[Käyttöohje](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/kaytto-ohje.md)

[Vaatimusmäärittely](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittelu.md)

[Työaikakirjanpito](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/arkkitehtuurikuvaus.md)


## Releaset<h3>
[Releaset](https://github.com/olgaviho/otm-harjoitustyo/releases)


## Komentorivitoiminnot<h4>

### Testaus<h5>
Testit suoritetaan komennolla
```
mvn test
```
Testikattavuusraportti luodaan komennolla
```
mvn jacoco:report
```
Kattavuusraporttia voi tarkastella avaamalla tiedoston target/site/jacoco/index.html

### Suoritettavan jarin generointi<h6>

Komento
```
mvn package
```
generoi jar-tiedoston hakemistoon target mystudies-1.0-SNAPSHOT.jar

### Checkstyle<h7>

Tarkistukset suoritetaan komennolla
```
 mvn jxr:jxr checkstyle:checkstyle
 ```
Virheilmoitukset selviävät avaamalla tiedoston target/site/checkstyle.html

### JavaDoc<h8>

JavaDoc generoidaan komennolla
```
mvn javadoc:javadoc
```
JavaDocia voi tarkastella avaamalla selaimella tiedoston target/site/apidocs/index.html


