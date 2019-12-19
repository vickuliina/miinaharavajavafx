package org.openjfx;

import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Kentta {
	Stage kentta;
	Scene scene;
	
	GridPane pohjaRuudukko;
	Ruutu[][] ruutuRuudukko;
	
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
        
    	luoRuudut();
    	asetaPommit();
    	asetaNumerot();
    	
		
		//TESTI, tulostaa pohjaruudukon komentoriville
		for(int a=0; a<leveys; a++) {
			for(int b=0; b<pituus; b++) {
				System.out.print(ruutuRuudukko[a][b].annaArvo() + "  |  ");
			}
			System.out.println();
		}
        
        //tulostaa pommien maaran komentoriville
        System.out.println("POMMIEN MÄÄRÄ: " + pommiMaara);
        
		
	}

	/**
	 * Luo kenttaan ruudut ja asettaa ne näkyville
	 */
	public void luoRuudut() {
    	pohjaRuudukko = new GridPane();
    	pohjaRuudukko.setAlignment(Pos.CENTER);

    	ruutuRuudukko = new Ruutu[leveys][pituus];

    	//asettaa ruudut ruuturuudukkoon ja lisaa ruuturuudukon pohjaruuturuudukkoon, eli GridPaneen
    	for (int x=0; x<leveys; x++) {
    		for (int y=0; y<pituus; y++) {
    			
    			Ruutu ruutu = new Ruutu(x, y, this.annaKentta());
    			ruutu.asetaArvo(0);
    			
    			//ei laita hehkua nappuloiden ympärille, ei kuitenkaan poista sita kokonaan. Ei toimiva!
    			//ruutu.annaNappula().setStyle("-fx-focus-color: transparent; -fx-background-insets: -1.4, 0, 1, 2;");
    			
    			ruutuRuudukko[x][y] = ruutu;
    			pohjaRuudukko.add(ruutuRuudukko[x][y], x, y);
    		}
    	}

        scene = new Scene(pohjaRuudukko, ruudukonKoko, ruudukonKoko);
        kentta.setScene(scene);
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
			
			if(ruutuRuudukko[luku1][luku2].annaArvo() != 9) {
				ruutuRuudukko[luku1][luku2].asetaArvo(9);
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
				
				if(ruutuRuudukko[i][j].annaArvo() != 9) {
					
					try {
						if(ruutuRuudukko[i-1][j-1].annaArvo() == 9) {
							summa += 1;
						}
					} catch (Exception e) {
					}
					
					try {
						if(ruutuRuudukko[i-1][j].annaArvo() == 9) {
							summa += 1;
						}
					} catch (Exception e) {
					}
					
					try {
						if(ruutuRuudukko[i][j-1].annaArvo() == 9) {
							summa += 1;
						}
					} catch (Exception e) {
					}
					
					try {
						if(ruutuRuudukko[i+1][j+1].annaArvo() == 9) {
							summa += 1;
						}
					} catch (Exception e) {
					}
					
					try {
						if(ruutuRuudukko[i+1][j].annaArvo() == 9) {
							summa += 1;
						}
					} catch (Exception e) {
					}
					
					try {
						if(ruutuRuudukko[i][j+1].annaArvo() == 9) {
							summa += 1;
						}
					} catch (Exception e) {
					}
					
					try {
						if(ruutuRuudukko[i-1][j+1].annaArvo() == 9) {
							summa += 1;
						}
					} catch (Exception e) {
					}
					
					try {
						if(ruutuRuudukko[i+1][j-1].annaArvo() == 9) {
							summa += 1;
						}
					} catch (Exception e) {
					}
					
					ruutuRuudukko[i][j].asetaArvo(summa);
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
					if(ruutuRuudukko[reunaX[j]][reunaY[i]].annaRuutu().nappulaPoistettu() == false) {
						
						if(ruutuRuudukko[reunaX[j]][reunaY[i]].annaRuutu().annaArvo() == 0) {
							ruutuRuudukko[reunaX[j]][reunaY[i]].annaRuutu().poistaNappula();
							avaaNollat(ruutuRuudukko[reunaX[j]][reunaY[i]]);
						}
						else {
							ruutuRuudukko[reunaX[j]][reunaY[i]].annaRuutu().poistaNappula();
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
				ruutuRuudukko[i][j].poistaNappula();
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