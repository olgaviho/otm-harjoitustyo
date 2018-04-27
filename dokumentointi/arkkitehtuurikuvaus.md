
# Arkkitehtuurikuvaus <h1>

# Käyttöliittymä <h3>

Käyttöliittymä sisältää neljä erilaista näkymää: kirjautuminen, uuden käyttäjän luominen, kurssilistaus ja uuden kurssin luominen.

Jokainen niistä on toteutettu omana Scene-oliona. Käyttöliittymä on rakennettu ohjelmallisesti luokassa mystudies.ui.MyStudies.Ui

# Sovelluslogiikka <h3>

Sovelluksen loogisen datamallin muodostavat luokat User ja Course, jotka kuvaavat käyttäjiä ja kursseja.

Toiminnallisesta kokonaisuudesta vastaa luokan myStudiesService ainoa olio. 

Luokka- ja pakkauskaavio:

![alt text](https://raw.githubusercontent.com/olgaviho/otm-harjoitustyo/master/dokumentointi/Kuvat/Arkkitehtuurikuvaus.JPG "Logo Title Text 1")

# Tietojen pysyväistallennus <h3>

Pakkauksen myStudies.dao luokat DatabaseCourseDao, DatabaseUserDao ja DatabaseCourseUserDao huolehtivat tietojen pysyväistallennuksesta tietokantaan. Tietokannan nimi on mycourses.db ja sen sisällä on kolme taulua: users, courses ja usersandcourses. 

# Päätoiminnallisuudet <h3>

Sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaaviona.

![alt text](https://raw.githubusercontent.com/olgaviho/otm-harjoitustyo/master/dokumentointi/Kuvat/sekvenssikaavio.JPG "Logo Title Text 1")
