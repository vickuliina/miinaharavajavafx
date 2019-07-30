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

	public void avaaNollat(Ruutu ruutu) {
		int x = ruutu.annaX();
		int y = ruutu.annaY();
	
		try {
			if(ruutuRuudukko[x-1][y-1].annaArvo() == 0) {
				ruutuRuudukko[x-1][y-1].annaRuutu().poistaNollaRuutu();
				avaaNollat(ruutuRuudukko[x-1][y-1]);
			}
		} catch (Exception e) {
		}
		
		
		
		/*
		try {
			if(ruutuRuudukko[x-1][y].annaArvo() == 0) {
				ruutuRuudukko[x-1][y].annaRuutu().poistaNollaRuutu();
				avaaNollat(ruutuRuudukko[x-1][y]);
			}
		} catch (Exception e) {
		}
		
		try {
			if(ruutuRuudukko[x][y-1].annaArvo() == 0) {
				ruutuRuudukko[x][y-1].annaRuutu().poistaNollaRuutu();
				avaaNollat(ruutuRuudukko[x][y-1]);
			}
		} catch (Exception e) {
		}
		
		try {
			if(ruutuRuudukko[x+1][y+1].annaArvo() == 0) {
				ruutuRuudukko[x+1][y+1].annaRuutu().poistaNollaRuutu();
				avaaNollat(ruutuRuudukko[x+1][y+1]);
			}
		} catch (Exception e) {
		}
		
		try {
			if(ruutuRuudukko[x+1][y].annaArvo() == 0) {
				ruutuRuudukko[x+1][y].annaRuutu().poistaNollaRuutu();
				avaaNollat(ruutuRuudukko[x+1][y]);
			}
		} catch (Exception e) {
		}
		
		try {
			if(ruutuRuudukko[x][y+1].annaArvo() == 0) {
				ruutuRuudukko[x][y+1].annaRuutu().poistaNollaRuutu();
				avaaNollat(ruutuRuudukko[x][y+1]);
			}
		} catch (Exception e) {
		}
		
		try {
			if(ruutuRuudukko[x-1][y+1].annaArvo() == 0) {
				ruutuRuudukko[x-1][y+1].annaRuutu().poistaNollaRuutu();
				avaaNollat(ruutuRuudukko[x-1][y+1]);
			}
		} catch (Exception e) {
		}
		
		try {
			if(ruutuRuudukko[x+1][y-1].annaArvo() == 0) {
				ruutuRuudukko[x+1][y-1].annaRuutu().poistaNollaRuutu();
				avaaNollat(ruutuRuudukko[x+1][y-1]);
			}
		} catch (Exception e) {
		}
*/
	}
	
	public Stage annaStage() {
		return kentta;
	}
	
	public Kentta annaKentta() {
		return this;
	}
}
