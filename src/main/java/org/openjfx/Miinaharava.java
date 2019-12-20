package org.openjfx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Miinaharava extends Application {
	Stage miinaharava;
	//Stage alkuvalikko;	//toteutetaan myöhemmin
	//	Stage nykyinen;	//toteutetaan myöhemmin

	Scene pieniPelikentta;
	//Scene keskiKentta;	//toteutetaan myöhemmin
	//Scene isoKentta;	//toteutetaan myöhemmin
	BorderPane alustaPaneeli;

	Ruudukko ruudukko;

	HBox ylapalkki;
	//joku tekstikentta lippujen lukumäärälle
	Button uusipeliNappula;
	//joku aikalabel

	int ruudukonKoko;
	int ikkunanKoko;
	
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
		miinaharava.setTitle("miinaharava");

		//luodaan alustapaneeli ja annetaan sille tyylikansio käytettäväksi
		alustaPaneeli = new BorderPane();
		pieniPelikentta = new Scene(alustaPaneeli, ruudukonKoko, ruudukonKoko);
		pieniPelikentta.getStylesheets().add("tyyli.css");


		//500 on pienen pelikentän ikkunakoko
		this.ikkunanKoko = 500;
		//asettaa ikkunan hieman leveämmäksi, kuin mitä kenttä
		miinaharava.setWidth(ikkunanKoko);
		miinaharava.setHeight(ikkunanKoko);

		luoRuudukko(8,8,9,400);
    	luoYlapalkki();


		miinaharava.setScene(pieniPelikentta);
		miinaharava.show();
    }


	/**
	 * Luo uuden ruudukon
	 * @param leveys kentän koko leveyssuunnassa
	 * @param pituus, kentan koko pituussuunnassa
	 * @param pommiMaara, pommien määrä kentässä
	 * @param ruudukonKoko ruudukon koko, minkä kokoisen ruudukon halutaan tehdä
	 */
	public void luoRuudukko(int leveys, int pituus, int pommiMaara, int ruudukonKoko) {
		//luodaan ruudukko
		ruudukko = new Ruudukko(leveys, pituus, pommiMaara, ruudukonKoko);
		alustaPaneeli.setCenter(ruudukko.annaRuudukko());

		alustaPaneeli.setCenter(ruudukko.annaRuudukko());
	}


	/**
	 * Tekee yläpalkin ja lisää sen alustaPaneeliin
	 */
	public void luoYlapalkki() {
		ylapalkki = new HBox();

		//luodaan lippujenemäärän kertova alue
		TextField testiTeksti = new TextField("Testi2");
		//testiTeksti.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THICK)));

		//luodaan "uusipeli"-nappula
		uusipeliNappula = new Button("Uusi Peli");
		uusipeliNappula.setPrefSize(80,40);
		uusipeliNappula.setOnMouseClicked( e -> {
			uusiPeli();
		});

		//luodaan aika ruutu


		//lisataan alueet ylapalkkiin
		ylapalkki.getChildren().add(testiTeksti);
		ylapalkki.getChildren().add(uusipeliNappula);


		ylapalkki.setAlignment(Pos.CENTER);
		alustaPaneeli.setTop(ylapalkki);
	}

	/**
	 * Aloittaa uuden pelin, käytetään yläpalkin uusi-peli nappulasta
	 * asettaa uudet arvot ruudukkoon
	 * asettaa ajan nollaan
	 */
	public void uusiPeli() {
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
