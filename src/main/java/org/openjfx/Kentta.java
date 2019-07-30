package org.openjfx;

import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Kentta {
	Stage kentta;
	Scene scene;
	
	GridPane pohjaRuutuRuudukko;
	Ruutu[][] ruutuRuudukko;
	
	Integer kentanKoko;
	Integer pommiMaara;
	
	/**
	 * Luo uuden kentän
	 * @param nimi, kentän nimi
	 * @param koko, kentän koko pysty ja vaakasuunnassa. Esim koko=10 tarkoittaa 10x10 kokoista kenttää
	 * @param pommiMaara, pommien määrä kentässä
	 */
	public Kentta(String nimi, int kentanKoko, int pommiMaara) {
    	kentta = new Stage();
        kentta.setTitle(nimi);
        
        this.kentanKoko = kentanKoko;
        this.pommiMaara = pommiMaara;
        
    	luoRuudut();
    	asetaPommit();
    	asetaNumerot();
    	
		
		//TESTI, tulostaa pohjaruudukon
		for(int a=0; a<10; a++) {
			for(int b=0; b<10; b++) {
				System.out.print(ruutuRuudukko[b][a].annaArvo() + "  |  ");
			}
			System.out.println();
		}
		
	}

	/**
	 * Luo kenttaan ruudut ja asettaa ne näkyville
	 * @param koko, kentän koko pysty ja vaakasuunnassa. Esim koko=10 tarkoittaa 10x10 kokoista ruudukkoa
	 */
	public void luoRuudut() {
    	pohjaRuutuRuudukko = new GridPane();
    	ruutuRuudukko = new Ruutu[kentanKoko][kentanKoko];
    	
    	for (int y=0; y<kentanKoko; y++) {
    		for (int x=0; x<kentanKoko; x++) {
    			
    			Ruutu ruutu = new Ruutu(x, y, this.annaKentta());
    			ruutu.asetaArvo(0);
    			
    			ruutuRuudukko[x][y] = ruutu;
    			pohjaRuutuRuudukko.add(ruutuRuudukko[x][y], x, y);
    		}
    	}
    	
        scene = new Scene(pohjaRuutuRuudukko, 500, 500);
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
			
			int luku1 = random.nextInt(kentanKoko);
			int luku2 = random.nextInt(kentanKoko);
			
			if(ruutuRuudukko[luku1][luku2].annaArvo() == 9) {
				return;
			}
			else {
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
		for(int i=0; i<kentanKoko; i++) {
			for(int j=0; j<kentanKoko; j++) {
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
							ruutuRuudukko[reunaX[j]][reunaY[i]].annaRuutu().poistaRuutu();
							avaaNollat(ruutuRuudukko[reunaX[j]][reunaY[i]]);
						}
						else {
							ruutuRuudukko[reunaX[j]][reunaY[i]].annaRuutu().poistaRuutu();
						}
					
					}
				} catch (Exception e) {
				}
				
				
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
