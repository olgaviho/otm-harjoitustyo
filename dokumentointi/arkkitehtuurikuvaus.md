
# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria:

![alt text](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/Kuvat/uusipakkauskaavio.JPG)

Pakkaus _mystudies.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän _mystudies.domain_ sovelluslogiikan ja _mystudies.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää kuusi erilaista näkymää: kirjautuminen, uuden käyttäjän luominen, kurssilistaus, uuden kurssin luominen, uuden kurssisuorituksen luominen ja kurssisuorituksen poistaminen.

Jokainen niistä on toteutettu omana Scene-oliona. Käyttöliittymä on rakennettu ohjelmallisesti luokassa _mystudies.ui.MyStudies.Ui_.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat _User_ ja _Course_, jotka kuvaavat käyttäjiä ja kursseja.

Toiminnallisesta kokonaisuudesta vastaa luokan _myStudiesService_ ainoa olio. 

Luokka- ja pakkauskaavio:

![alt text](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/Kuvat/rakenne.JPG)

## Tietojen pysyväistallennus

Pakkauksen _myStudies.dao_ luokat _DatabaseCourseDao_, _DatabaseUserDao_ ja _DatabaseCourseUserDao_ huolehtivat tietojen pysyväistallennuksesta tietokantaan. Tietokannan nimi on _mycourses.db_ ja sen sisällä on kolme taulua: users, courses ja usersandcourses. 

Luokat on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos sovelluksen datan talletustapaa päätetään vaihtaa. Luokat on eristetty rajapintojen _UsersAndCoursesDao_ ja _Dao_ taakse ja sovelluslogiikka ei käytä itse luokkia suoraan.

## Päätoiminnallisuudet

Sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaaviona.

### Käyttäjän kirjautuminen

Kun kirjautumisnäkymässä on syötekenttään kirjoitettu käyttäjätunnus ja klikataan painiketta _loginButton_, tapahtuu seuraava:

![alt text](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/Kuvat/loginSekvenssi.JPG)

Tapahtumankäsittelijä kutsuu sovelluslogiikan metodia _login_ antaen parametriksi käyttäjätunnuksen. Sovelluslogiikka selvittää _userDao_:n avulla onko käyttäjätunnus olemassa. Jos on, eli kirjautuminen onnistuu, on seurauksena se, että käyttöliittymä vaihtaa näkymäksi _coursesScenen_ ja lisää näkymään kirjautuneen käyttäjän kurssit.

### Uuden käyttäjän luominen

Kun uuden käyttäjän luomisnäkymässä on syötekenttään kirjoitettu käyttäjätunnus ja käyttäjänimi ja klikataan painiketta _createUserButton_, tapahtuu seuraava:

![alt text](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/Kuvat/createUserSekvenssi.JPG)

Tapahtumankäsittelijä pyytää sovelluslogiikkaa luomaan metodin _createUser_ avulla uuden käyttäjän käyttäen _userDaon_ metodia _save_. Jos käyttäjätunnus on jo varattu, ei uuden käyttäjän luonti onnistu, josta lähtisi virheilmoitus sovelluslogiikkaan. Mikäli taas luonti onnistuu, käyttöliittymä vaihtaa näkymäksi _loginScenen_.

### Uuden kurssin luominen

Kun uuden kurssin luomisnäkymässä on syötekenttään kirjoitettu id, nimi, kuvaus ja opintopisteet ja klikataan painiketta _createCourseButton_ tapahtuu seuraava:

![alt text](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/Kuvat/createCourseSekvenssi.JPG)

Tapahtumankäsittelijä pyytää sovelluslogiikkaa luomaan metodin _createCourse_ avulla uuden kurssin käyttäen _courseDaon_ metodia _save_. Jos id on jo varattu, ei uuden kurssin luonti onnistu, mistä lähtisi virheilmoitus sovelluslogiikkaan. Mikäli taas luonti onnistuu, käyttöliittymä vaihtaa näkymäksi coursesScenen.

### Uuden kurssinsuorituksen luominen

Kun uuden kurssisuorituksen luomisnäkymässä on syötekentästä valittu kurssi ja kirjoitettu arvosana ja klikataan painiketta _createNewCompletedCourseButton_, tapahtuu seuraava:

![alt text](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/Kuvat/createNewCompletedCourseSekvenssikaavio.JPG)

Tapahtumakäsittelijä pyytää sovelluslogiikkaa tarkistamaan, onko käyttäjällä varmasti tämä kurssi metodin _userHasCourse_ avulla.  Metodi kutsuu _coursesAndUserDaota_ tarkistamaan onko käyttäjällä kurssi olemassa käyttäen metoda _findOne_. Mikäli _coursesAndUsersDao_ palauttaa false ja _MyStudiesService_ true, pyytää käyttöliittymä sovelluslogiikkaa tallentamaan kurssisuorituksen käyttäen metodia _createRelation_. Metodi pyytää _CoursesAnsUsersDaota_ tallentamaan kurssisuorituksen metodilla _save_. Mikäli taas luonti onnistuu, käyttöliittymä vaihtaa näkymäksi _coursesScenen_.

### Kurssinsuorituksen poistaminen

Kun kurssin poistonäkymässä on valittu kurssi ja klikataan painiketta deleteCourseButton tapahtuu seuraava:

![alt text](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/Kuvat/deletesekvenssi.JPG)

Tapahtumankäsittelijä pyytää sovelluslogiikkaa poistamaan metodin _deleteCourse_ avulla uuden kurssin käyttäen _coursesAndUsersDaon_ metodia _deleteCourse_. Mikäli poisto onnistuu, käyttöliittymä vaihtaa näkymäksi _coursesScenen_.

## Rakenteeseen jääneet heikkoudet

### Käyttöliittymä

Graafinen käyttöliittymä on toteutettu määrittelemällä lähes koko käyttöliittymä luokan _MyStudiesUi_ metodissa _start_. Vähintään kuuden päänäkymän rakentava koodi olisi syytä erottaa omiksi metodeikseen. Muuttujien nimentä ei aina ole loogisin mahdollinen.


### DAO-luokat

DatabaseDao-toteutuksiin on jäänyt toisteista koodia, jonka voisi erottaa omaan luokkaansa.
