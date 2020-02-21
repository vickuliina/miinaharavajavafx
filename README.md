# Miinaharava


Pelissä on käytetty JavaFX:aa graaffisen käyttöliittymän luomisessa ja ohjelma käyttää Mavenia.
Koodin pohjana on yliopiston Käyttöliittymät-kurssilla esiintynyt esimerkki-projekti JavaFX:n toimimisesta.
Peli on työnalla ja siksi vielä kesken.

Projektin suunnitelukansio: tehtavalista.txt

## Ohjelman suoritus
1. Importtaa projekti IntelJ IDEA:lla tai cloonaa repositio.
2. Suorita ohjelman Miinaharava.java-kansion main-metodi.
HUOM, Projekti on luotu käyttäen Mavenia.

## Ohjelman runko

Miinaharava.java: Manuaalisesti voidaan valita kentän koko(ei vielä toimi) ja kutsua Ruudukkoa-luomaan halutun kokoisen pelin asetetuilla pommi määrillä.
Ohjelma käynnistetään Miinaharava.javan main-metodissa.

Ruudukko.java: Pelin ruudukko, joka sisältää Ruutu-olioita. Täällä myös alustetaan peli kentän koon ja pommien määrän mukaan.

Ruutu.java: Kuvastaa yhtä Ruutua pelissä. Aluksi pelin kaikki Ruutu-oliot esittävät nappuloita.

Yläpalkki.java: Ohjelman yläpalkki ja sen toiminnalisuudet. Yläpalkki sisältää pommimerkkien määrän, uuden pelin aloittavan nappulan, sekä ajan.

tyyli.css: Ohjelman tyyli-tiedosto. Sisältää vielä vain nappuloiden hehkun poiston.


Versionhallinta: Turun yliopiston GitLab -> GitHub 3.2.2020
