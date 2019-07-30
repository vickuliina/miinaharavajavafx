## JavaFX Hello world

### Eclipse

* Asenna Java (mielellään versio 11, mutta versiot 10 tai 8 voivat myös toimia)
  * Versio 8 on vielä tuettu
  * Versioden 1-7 ja 9-10 tuki on virallisesti loppunut

* Asenna Eclipse
  * versio: Eclipse IDE for Java Developers ( https://www.eclipse.org/downloads/packages/release/2018-12/r/eclipse-ide-java-developers )
  * testattu versiolla 4.10 / 2018-12

* Varmista että Eclipseen on asennettu Eclipse Egit (git-tuki) ja Eclipse m2e (Maven-tuki).    
  * Jos jompi kumpi puuttuu, etsi netistä ohjeet näiden asentamiseksi
  * pluginien pitäisi olla 2018-12 Java-versiossa 

* Käynnistä Eclipse
  * Valitse ylävalikosta File
  * -> Import
  * -> Git -> Projects from Git -> Next -> Clone URI -> Next
  * kohtaan URI syötä: https://gitlab.utu.fi/tech/education/gui/fxhello
  * voit tallentaa utu-tunnukset user/password -kohtiin alle, jos haluat myöhemmin säästää kirjoitusvaivaa, mutta jos epäilet Eclipsen/koneen tietoturvaa, jätä tallentamatta
  * valitse Next -> Next -> Next (tämä kolmas Next ei toimi, jos olet jo hakenut projektin - poista ensin vanha, vinkki: hakemisto lukee Directory-kohdassa)
  * valitse Next -> Finnish

* Projektin nimi on nyt 'hellofx'.

* Tarkista että Windows -> Preferences -> Java -> Installed JREs sisältää halutun Java-version (jos ei, asenna JAva ja lisää puuttuva JRE Eclipselle tänne)

* Tarkista että Run -> Run configurations -> Maven build -> hellofx -> JRE-tabin alla valittu sama, oikea JRE (projekti ei välttämättä käänny muuten)

* Valitse ylävalikosta Run -> Run -> Maven build -> OK

* Myöhemmillä käyttökerroilla riittää valita vain Run -> Run -> ...

### Komentoriviltä

* Asenna Java (mielellään versio 11, mutta versiot 10 tai 8 voivat myös toimia)
  * Versio 8 on vielä tuettu
  * Versioden 1-7 ja 9-10 tuki on virallisesti loppunut
  * Tarkasta että käskyt java ja javac toimivat (PATH-ympäristömuuttuja)

* Asenna Maven. Tarkasta että käsky mvn toimii (PATH-ympäristömuuttuja)

* Asenna Git. Tarkasta että käsky git toimii (PATH-ympäristömuuttuja

* git clone https://gitlab.utu.fi/tech/education/gui/fxhello
* mvn compile exec:java

### Problems

* Jos koneelle on asennettu useita Java-versioita samaan aikaan, Eclipse voi
  epäonnistua käynnistämään projektia, jos Eclipse on itse käynnistynyt eri
  Java-versiota käyttäen (oletus Java 8) kuin projekti
  (voi olla säädetty esim. 11). Korjaus: vaihda projektin Java-versio tai
  poista ylimääräiset Java-asennukset.