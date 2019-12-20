package org.openjfx;

import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Kentta {
	Stage kentta;
	Scene scene;

	BorderPane alustaPaneeli;

	GridPane ruudukko;
	Ruutu[][] ruudut;

	HBox ylapalkki;
	//joku tekstikentta lippujen lukumäärälle
	Button uusipeliNappula;
	//joku aikalabel

	int leveys;
	int pituus;
	int pommiMaara;
	int ruudukonKoko;
	int ikkunanKoko;
	
	/**
	 * Luo uuden kentän
	 * @param nimi, kentän nimi
	 * @param leveys kentän koko leveyssuunnassa
	 * @param pituus, kentan koko pituussuunnassa
	 * @param pommiMaara, pommien määrä kentässä
	 * @param ruudukonKoko ruudukon koko, minkä kokoisen ruudukon halutaan tehdä
	 */
	public Kentta(String nimi, int leveys, int pituus, int pommiMaara, int ruudukonKoko, int ikkunanKoko) {
    	kentta = new Stage();
        kentta.setTitle(nimi);

        this.ikkunanKoko = ikkunanKoko;

        //asettaa ikkunan hieman leveämmäksi, kuin mitä kenttä
        kentta.setWidth(ikkunanKoko);
        kentta.setHeight(ikkunanKoko);
        
        this.leveys = leveys;
        this.pituus = pituus;
        this.pommiMaara = pommiMaara;
        this.ruudukonKoko = ruudukonKoko;

        luoAlustaPaneeli();
        luoYlapalkki();
    	luoRuudukko();
    	asetaPommit();
    	asetaNumerot();
    	
		
		//TESTI, tulostaa pohjaruudukon komentoriville
		for(int y=0; y<pituus; y++) {
			for(int x=0; x<leveys; x++) {
				System.out.print(ruudut[x][y].annaArvo() + "  |  ");
			}
			System.out.println();
		}
        
        //tulostaa pommien maaran komentoriville
        System.out.println("POMMIEN MÄÄRÄ: " + pommiMaara);
        
		
	}

	/**
	 * Luo alustapaneelin ja lisää sen Kentta-sceneen
	 * Annetaan myös scenelle tyylikansio käytettäväksi
	 */
	public void luoAlustaPaneeli() {
		alustaPaneeli = new BorderPane();
		scene = new Scene(alustaPaneeli, ruudukonKoko, ruudukonKoko);
		scene.getStylesheets().add("tyyli.css");

		kentta.setScene(scene);
	}

	/**
	 * Luo kentan peliruudukon ja lisää sen alustaPaneeliin
	 */
	public void luoRuudukko() {
    	ruudukko = new GridPane();
    	ruudut = new Ruutu[leveys][pituus];

    	//luo ja asettaa Ruutu-oliot(eli ruudut) ruudukkoon
    	for (int y=0; y<pituus; y++) {
    		for (int x=0; x<leveys; x++) {
    			
    			Ruutu ruutu = new Ruutu(x, y, this.annaKentta());
    			ruutu.asetaArvo(0);
    			ruudut[x][y] = ruutu;
    			ruudukko.add(ruudut[x][y], x, y);
    		}
    	}

    	//lisataan ruudukko alustapaneeliin
		ruudukko.setAlignment(Pos.CENTER);
    	alustaPaneeli.setCenter(ruudukko);
	}

	/**
	 * Tekee yläpalkin ja lisää sen alustaPaneeliin
	 */
	public void luoYlapalkki() {
		ylapalkki = new HBox();

		//luodaan lippujenemäärän kertova alue
		Text testiTeksti = new Text("Testi2");


		//luodaan "uusipeli"-nappula
		uusipeliNappula = new Button("Uusi Peli");
		uusipeliNappula.setPrefSize(80,40);

		//luodaan aika ruutu


		//lisataan alueet ylapalkkiin
		ylapalkki.getChildren().add(testiTeksti);
		ylapalkki.getChildren().add(uusipeliNappula);


		ylapalkki.setAlignment(Pos.CENTER);
		alustaPaneeli.setTop(ylapalkki);
	}

	
	/**
	 * Asetetaan randomisti pommit ruutuRuudukkoon
	 * Asettaa ruutuRuudukkoon jokaiselle ruudulle arvon.
	 * pommin arvo = 9
	 */
	public void asetaPommit() {
		while(pommiMaara > 0) {
			Random random = new Random();
			
			int luku1 = random.nextInt(leveys);
			int luku2 = random.nextInt(pituus);
			
			if(ruudut[luku1][luku2].annaArvo() != 9) {
				ruudut[luku1][luku2].asetaArvo(9);
				pommiMaara -= 1;
			}
		}
	}
	
	
	/**
	 * Asettaa ruutuRuudukkoon jokaiselle ruudulle arvon, 
	 * sen mukaan kuinka monta pommia (eli arvoa 9) on ruudun ympärillä
	 */
	public void asetaNumerot() {
		
		//käydään läpi löytyykö vierestä pommeja
		for(int i=0; i<leveys; i++) {
			for(int j=0; j<pituus; j++) {
				int summa = 0;
				
				if(ruudut[i][j].annaArvo() != 9) {
					
					try {
						if(ruudut[i-1][j-1].annaArvo() == 9) {
							summa += 1;
						}
					} catch (Exception e) {
					}
					
					try {
						if(ruudut[i-1][j].annaArvo() == 9) {
							summa += 1;
						}
					} catch (Exception e) {
					}
					
					try {
						if(ruudut[i][j-1].annaArvo() == 9) {
							summa += 1;
						}
					} catch (Exception e) {
					}
					
					try {
						if(ruudut[i+1][j+1].annaArvo() == 9) {
							summa += 1;
						}
					} catch (Exception e) {
					}
					
					try {
						if(ruudut[i+1][j].annaArvo() == 9) {
							summa += 1;
						}
					} catch (Exception e) {
					}
					
					try {
						if(ruudut[i][j+1].annaArvo() == 9) {
							summa += 1;
						}
					} catch (Exception e) {
					}
					
					try {
						if(ruudut[i-1][j+1].annaArvo() == 9) {
							summa += 1;
						}
					} catch (Exception e) {
					}
					
					try {
						if(ruudut[i+1][j-1].annaArvo() == 9) {
							summa += 1;
						}
					} catch (Exception e) {
					}
					
					ruudut[i][j].asetaArvo(summa);
				}
			}
		}
	}

	
	/**
	 * Avaa ruudun vierekkäiset ruudut ja mikäli jokin ympärillä on myös 0, eli tyhjä,
	 * kutsuu metodi itseään uudella arvolla.
	 * @param ruutu, ruutu jonka ympäriltä halutaan nappulat pois
	 */
	public void avaaNollat(Ruutu ruutu) {
		int x = ruutu.annaX();
		int y = ruutu.annaY();
		
		int reunaX[] = {x-1, x, x+1};
		int reunaY[] = {y-1, y, y+1};
		
		
		for(int i=0; i<reunaY.length; i++) {
			for(int j=0; j<reunaX.length; j++) {
				
				//Heittää errorin jos nappulaa ei ole olemassa, eli se on poissa
				try {
					if(ruudut[reunaX[j]][reunaY[i]].annaRuutu().nappulaPoistettu() == false) {
						
						if(ruudut[reunaX[j]][reunaY[i]].annaRuutu().annaArvo() == 0) {
							ruudut[reunaX[j]][reunaY[i]].annaRuutu().poistaNappula();
							avaaNollat(ruudut[reunaX[j]][reunaY[i]]);
						}
						else {
							ruudut[reunaX[j]][reunaY[i]].annaRuutu().poistaNappula();
						}
					
					}
				} catch (Exception e) {
				}
				
				
			}
		}
	}

	/**
	 * Avaa kentän kaikki ruudut
	 * Käytetään kun peli loppuu
	 */
	public void avaaRuudut() {
		for(int i=0; i<leveys; i++) {
			for(int j=0; j<pituus; j++) {
				ruudut[i][j].poistaNappula();
			}
		}
	}

	public Stage annaStage() {
		return kentta;
	}
	
	public Kentta annaKentta() {
		return this;
	}
}