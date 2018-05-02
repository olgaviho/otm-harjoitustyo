# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjien on mahdollista seurata omien opintojensa edistymistä yliopistossa. Sovellukseen voi luoda useita eri käyttäjiä.

## Käyttäjät

Sovelluksella on ainoastaan yksi käyttäjärooli eli _normaali käyttäjä_.

## Käyttöliittymäluonnos

Sovellus koostuu neljästä eri näkymästä

![alt text](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/Kuvat/n%C3%A4kym%C3%A4t.JPG)


Sovellus aukeaa kirjautumisnäkymään, josta on mahdollista siirtyä uuden käyttäjän luomisnäkymään tai onnistuneen kirjautumisen yhteydessä kirjaantuneen käyttäjän kurssilistaukseen.

## Toiminnallisuus

### Ennen kirjautumista

- Käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - Käyttäjän id:n täytyy olla uniikki ja pituudeltaan vähintään 1 merkki. ID:n tulee sisältää ainoastaan numeroita.
  - Nimi taas voi sisältää myös kirjaimia

- Käyttäjä voi kirjautua sovellukseen
  - Kirjautuminen kun syöttää id:n näkymään
  - Jos käyttäjää ei olemassa, pitäisi siitä tulla jokin ilmoitus

### Kirjautumisen jälkeen

- Käyttäjä näkee omat kurssit, jotka hän on suorittanut

- Käyttäjä voi luoda uuden kurssin
  - Luodulla kurssilla tulee olla uniikki id ja useammalla käyttjällä voi olla sama kurssi

- Käyttäjä voi kirjautua ulos järjestelmästä

## Jatkokehitysideoita

Perusversion jälkeen harjoitustyötä voin täydentää seuraavilla asioilla:

- Kurssien poistaminen
- Käyttäjätunnuksen (ja siihen liittyvien kurssien) poisto
