package org.openjfx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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

	HBox ylapalkki;
	Label lippujenMaara;
	Button uusipeliNappula;
	Label aika;

    @Override
	/**
	 * Itse pelin aloitus, toimii alkuun vain pienellä kentalla, muut rakennetaan myohemmin
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
		int ikkunanKoko = 500;
		//asettaa ikkunan hieman leveämmäksi, kuin mitä kenttä
		miinaharava.setWidth(ikkunanKoko);
		miinaharava.setHeight(ikkunanKoko);

		luoRuudukko(8,8,9,400);
    	luoYlapalkki();


		miinaharava.setScene(pieniPelikentta);
		miinaharava.show();
    }


	/**
	 * Luo uuden ruudukon ja lisää sen alustaPaneeliin
	 * @param leveys Ruutu ruutujen lukumäärä leveyssuunnassa
	 * @param pituus, Ruutu ruutujen lukumäärä pituussuunnassa
	 * @param pommiMaara, pommien määrä kentässä
	 * @param ruudukonKoko ruudukon koko, minkä kokoisen ruudukon halutaan tehdä
	 */
	public void luoRuudukko(int leveys, int pituus, int pommiMaara, int ruudukonKoko) {
		//luodaan ruudukko
		ruudukko = new Ruudukko(leveys, pituus, pommiMaara, ruudukonKoko);
		ruudukko.annaRuudukko().setAlignment(Pos.CENTER);
		alustaPaneeli.setCenter(ruudukko.annaRuudukko());
	}


	/**
	 * Tekee yläpalkin ja lisää sen alustaPaneeliin
	 */
	public void luoYlapalkki() {
		ylapalkki = new HBox();
		ylapalkki.setPrefSize(400,60);
		
		//luodaan lippujenemäärän kertova alue
		lippujenMaara = new Label("Lippujen määrä");
		lippujenMaara.setPrefSize((400/3),40);
		lippujenMaara.setAlignment(Pos.CENTER_LEFT);
		lippujenMaara.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));

		//luodaan "uusipeli"-nappula
		uusipeliNappula = new Button("Uusi Peli");
		uusipeliNappula.setPrefSize((400/3),40);
		uusipeliNappula.setOnMouseClicked( e -> {
			uusiPeli();
		});

		//luodaan aika ruutu
		aika = new Label("AIKA");
		aika.setPrefSize((400/3),40);
		aika.setAlignment(Pos.CENTER_RIGHT);
		aika.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));


		//lisataan alueet ylapalkkiin
		ylapalkki.getChildren().add(lippujenMaara);
		ylapalkki.getChildren().add(uusipeliNappula);
		ylapalkki.getChildren().add(aika);


		ylapalkki.setAlignment(Pos.CENTER);
		alustaPaneeli.setTop(ylapalkki);
	}

	/**
	 * Aloittaa uuden pelin, käytetään yläpalkin uusi-peli nappulasta
	 * asettaa uudet arvot ruudukkoon
	 * nollaa ajan
	 */
	public void uusiPeli() {
		//luodaan uusi pienikenttäpeli
		luoRuudukko(8, 8,9, 400);
		System.out.println("Uusi peli");
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
