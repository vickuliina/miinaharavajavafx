package org.openjfx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Miinaharava extends Application {
	Stage miinaharava;
	//Stage alkuvalikko;	//toteutetaan myöhemmin
	//Stage nykyinen;	//toteutetaan myöhemmin

	Scene pieniPelikentta;
	//Scene keskiKentta;	//toteutetaan myöhemmin
	//Scene isoKentta;	//toteutetaan myöhemmin
	BorderPane alustaPaneeli;

	Ruudukko ruudukko;
	Ylapalkki ylapalkki;

    @Override
	/*
	  Itse pelin aloitus, toimii alkuun vain pienellä kentalla, muut rakennetaan myohemmin
	 */
    public void start(Stage stage) throws Exception {
    	//toteutetaan myöhemmin
    	//pieniKentta = new Kentta("pieni", 8, 8,9, 400, 500);
    	//keskiKentta = new Kentta("keski", 16, 40, 640);
    	//isoKentta = new Kentta("iso", 24, 99, 700);

		miinaharava = new Stage();
		miinaharava.setTitle("Miinaharava");

		//luodaan alustapaneeli ja annetaan sille tyylikansio käytettäväksi
		alustaPaneeli = new BorderPane();
		pieniPelikentta = new Scene(alustaPaneeli);
		pieniPelikentta.getStylesheets().add("tyyli.css");


		//500 on pienen pelikentän ikkunakoko
		int ikkunanKoko = 550;	//pieni
		//int ikkunanKoko = 750;	//keskikoko
		//int ikkunanKoko = 800;	//iso

		//asettaa ikkunan hieman leveämmäksi, kuin mitä kenttä
		miinaharava.setWidth(ikkunanKoko);
		miinaharava.setHeight(ikkunanKoko);

		//luodaan alkuun pienenkentän muokaiset ruudukko ja yläpalkki
		luoRuudukko(8,8,9);	//pieni
		//luoRuudukko(16,16,40);	//keskikoko
		//luoRuudukko(24,24,99);	//iso

    	ylapalkki = new Ylapalkki(this,400, 60);

		ylapalkki.setAlignment(Pos.CENTER);
		alustaPaneeli.setTop(ylapalkki);

		miinaharava.setScene(pieniPelikentta);
		miinaharava.show();
    }


	/**
	 * Luo uuden ruudukon ja lisää sen alustaPaneeliin
	 * @param leveys Ruutu ruutujen lukumäärä leveyssuunnassa
	 * @param pituus, Ruutu ruutujen lukumäärä pituussuunnassa
	 * @param pommiMaara, pommien määrä kentässä
	 */
	public void luoRuudukko(int leveys, int pituus, int pommiMaara) {
		//luodaan ruudukko
		ruudukko = new Ruudukko(this, leveys, pituus, pommiMaara);
		ruudukko.setAlignment(Pos.CENTER);
		alustaPaneeli.setCenter(ruudukko);
	}

	/**
	 * Aloittaa uuden pelin, käytetään yläpalkin uusi-peli nappulasta
	 * asettaa uudet arvot ruudukkoon
	 * nollaa ajan
	 */
	public void uusiPeli() {
		//luodaan uusi pienikenttäpeli
		luoRuudukko(8, 8,9);
		System.out.println("Uusi peli");
	}

	public Ylapalkki annaYlapalkki() {
		return ylapalkki;
	}

	public Ruudukko annaRuudukko() {
		return ruudukko;
	}
	/**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
