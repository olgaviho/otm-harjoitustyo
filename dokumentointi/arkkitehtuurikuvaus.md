
# Arkkitehtuurikuvaus <h1>

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria:

[kuva]

Pakkaus _mystudies.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän _mystudies.domain_ sovelluslogiikan ja _mystudies.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää kuusi erilaista näkymää: kirjautuminen, uuden käyttäjän luominen, kurssilistaus, uuden kurssin luominen uuden kurssisuorituksen luominen ja kurssisuorituksen poistaminen.

Jokainen niistä on toteutettu omana Scene-oliona. Käyttöliittymä on rakennettu ohjelmallisesti luokassa _mystudies.ui.MyStudies.Ui_.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat User ja Course, jotka kuvaavat käyttäjiä ja kursseja.

Toiminnallisesta kokonaisuudesta vastaa luokan myStudiesService ainoa olio. 

Luokka- ja pakkauskaavio:

(kuva)

## Tietojen pysyväistallennus

Pakkauksen _myStudies.dao_ luokat _DatabaseCourseDao_, _DatabaseUserDao_ ja _DatabaseCourseUserDao_ huolehtivat tietojen pysyväistallennuksesta tietokantaan. Tietokannan nimi on _mycourses.db_ ja sen sisällä on kolme taulua: users, courses ja usersandcourses. 

Luokkani eivät noudata täysin Data Access Object -suunnittelumallia, joten mikäli tallennustapaa halutaan jossain vaiheessa muokata, pitää myös sovelluslogiikkaan tehdä pieniä muutoksia. Dao-luokat pitäisi eristetää rajapintojen _UsersAndCoursesDao_, _CourseDao_ ja _UserDao_ taakse, jotta sovelluslogiikka ei käyttäisi niitä suoraan. 

Luokat on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos sovelluksen datan talletustapaa päätetään vaihtaa. Luokat on eristetty rajapintojen _CourseDao_, _UsersAndCoursesDao_ ja _UserDao_ taakse ja sovelluslogiikka ei käytä itse luokkia suoraan.

## Päätoiminnallisuudet

Sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaaviona.

(kuva)

(kuva)

(kuva)

(kuva)

(kuva)
