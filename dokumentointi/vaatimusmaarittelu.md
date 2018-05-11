# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjien on mahdollista seurata omien opintojensa edistymistä yliopistossa. Sovellukseen voi luoda useita eri käyttäjiä.

## Käyttäjät

Sovelluksella on ainoastaan yksi käyttäjärooli eli _normaali käyttäjä_.

## Käyttöliittymäluonnos

Sovellus koostuu viidestä eri näkymästä

![alt text](https://github.com/olgaviho/otm-harjoitustyo/blob/master/dokumentointi/Kuvat/uudeN%C3%A4kym%C3%A4t.JPG)

Sovellus aukeaa kirjautumisnäkymään, josta on mahdollista siirtyä uuden käyttäjän luomisnäkymään tai onnistuneen kirjautumisen yhteydessä kirjaantuneen käyttäjän kurssilistaukseen.

## Toiminnallisuus

### Ennen kirjautumista

- Käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - Käyttäjän id:n täytyy olla uniikki ja pituudeltaan vähintään 1 merkki. ID:n tulee sisältää ainoastaan numeroita
  - Nimi taas voi sisältää myös kirjaimia ja sen pitää olla vähintään kahden merkin mittainen

- Käyttäjä voi kirjautua sovellukseen
  - Kirjautuminen onnistuu, kun syöttää id:n näkymään
  - Jos käyttäjää ei olemassa järjestelmässä, pitäisi siitä tulla ilmoitus

### Kirjautumisen jälkeen

- Käyttäjä näkee omat kurssit, jotka hän on suorittanut

- Käyttäjä voi luoda uuden kurssin
  - Luodulla kurssilla tulee olla uniikki id ja useammalla käyttäjällä voi olla sama kurssi
  
- Käyttäjä voi luoda uuden kurssisuorituksen
  - Kurssisuorituksen voi luoda vain sellaiseen kurssiin, jota käyttäjällä ei vielä ole. Kurssisuoritukseen liittyy arvosana.
  - Ohjelma laskee automaattisesti keskiarvon käyttäjän suorittamista kursseista
  
- Käyttäjä voi poistaa kurssisuorituksen

- Käyttäjä voi kirjautua ulos järjestelmästä

## Jatkokehitysideoita

Perusversion jälkeen harjoitustyötä voin täydentää seuraavilla asioilla:

- Käyttäjätunnuksen (ja siihen liittyvien kurssisuorituksien) poisto
- Lisää statistiikkaa käyttäjän suorittamista kursseista 
