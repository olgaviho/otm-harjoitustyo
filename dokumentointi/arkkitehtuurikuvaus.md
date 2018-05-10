
# Arkkitehtuurikuvaus <h1>

## Käyttöliittymä <h3>

Käyttöliittymä sisältää kuusi erilaista näkymää: kirjautuminen, uuden käyttäjän luominen, kurssilistaus, uuden kurssin luominen uuden kurssisuorituksen luominen ja kurssisuorituksen poistaminen.

Jokainen niistä on toteutettu omana Scene-oliona. Käyttöliittymä on rakennettu ohjelmallisesti luokassa _mystudies.ui.MyStudies.Ui_

## Sovelluslogiikka <h3>

Sovelluksen loogisen datamallin muodostavat luokat User ja Course, jotka kuvaavat käyttäjiä ja kursseja.

Toiminnallisesta kokonaisuudesta vastaa luokan myStudiesService ainoa olio. 

Luokka- ja pakkauskaavio:

![alt text](https://raw.githubusercontent.com/olgaviho/otm-harjoitustyo/master/dokumentointi/Kuvat/Arkkitehtuurikuvaus.JPG "Logo Title Text 1")

## Tietojen pysyväistallennus <h3>

Pakkauksen _myStudies.dao_ luokat _DatabaseCourseDao_, _DatabaseUserDao_ ja _DatabaseCourseUserDao_ huolehtivat tietojen pysyväistallennuksesta tietokantaan. Tietokannan nimi on _mycourses.db_ ja sen sisällä on kolme taulua: users, courses ja usersandcourses. 

## Päätoiminnallisuudet <h3>

Sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaaviona.

![alt text](https://raw.githubusercontent.com/olgaviho/otm-harjoitustyo/master/dokumentointi/Kuvat/sekvenssikaavio.JPG "Logo Title Text 1")
