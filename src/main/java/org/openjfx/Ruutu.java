package org.openjfx;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Ruutu extends Pane {
	Ruudukko ruudukko;
	
	Button nappula;
	Label label;
	
	boolean onkoPommi;
	boolean onkoMerkattu;
	int arvo;
	
	boolean nappulaPois;
	
	//kertoo mikä nappula on nappulaRuudukossa kyseessä
	int x; //leveys
	int y; //pituus

	/**
	 * luo ruudun
	 * @param x, ruudun vaakarivin paikka ruudukossa
	 * @param y, ruudun pystyrivin poikka ruudukossa
	 * @param ruudukko, ruudukko, johon ruudut asetetaan
	 */
	public Ruutu(int x, int y, Ruudukko ruudukko) {
		this.ruudukko = ruudukko;
		this.x = x;
		this.y = y;
		
		nappulaPois = false;
		nappula = new Button();
		nappula.setVisible(true);

		this.getChildren().add(nappula);
		
		luoLabel();
		label.setVisible(false);
		this.getChildren().add(label);

		nappula.setOnMouseClicked(e -> {

			//nappulaa painetaan hiiren oikealla painikkeella
			//Asettaa tai poistaa nappulan paalta kirjaimen, joka on pelissa ns. liputettu ruutu
			if(e.getButton() == MouseButton.SECONDARY) {
				if(nappula.getText().equals("")) {
					nappula.setText("L");
				}
				else {
					nappula.setText("");
				}
				
			}
			//nappulaa painetaan hiiren vasemmalla painikkeella
			else if (e.getButton() == MouseButton.PRIMARY) {

				//nappula on liputettu
				if(nappula.getText().equals("L")) {
					return;
				}

				//nappulan alla olevan labelin arvo on 0
            	if(arvo == 0) {
            		nappula.setVisible(false);
            		nappulaPois = true;
            		ruudukko.avaaNollat(annaRuutu());
            	}

            	//nappula poistetaan ja paljastetaan sen alla oleva label
            	else {
	                nappula.setVisible(false);
	                if(arvo == 9) {
	                	//peli päättyy
						ruudukko.avaaRuudukko();
						System.out.println("Peli loppui");
	                }
	                else {
	                    label.setText(Integer.toString(arvo));
	                }
            	}
                
                label.setVisible(true);

			}
		});

		nappula.setPrefSize(50, 50);
	}
	
	/**
	 * Luo label kentän, johon voidaan ruutujen nappuloiden poituessa kirjoittaa tekstiä.
	 * Alustetaan label tietyntyyppiseksi
	 */
	public void luoLabel() {
		label = new Label();
		
		label.setBorder(new Border(new BorderStroke(Color.LIGHTGREY, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
       
		label.setPrefSize(50, 50);
		label.setAlignment(Pos.CENTER);
	}

	/**
	 * Poistaa nappulan näkyvyyden ja asettaa näkyviin kyseisen ruudun Labelin arvon
	 */
	public void poistaNappula() {
        nappula.setVisible(false);
        
        if(annaArvo() != 0) {
        	label.setText(Integer.toString(arvo));
        }
        label.setVisible(true);
        nappulaPois = true;
	}

	/**
	 * asettaa nappulan takaisin näkyväksi ja piilottaa sen "alla olevan" Labelin arvon
	 */
	public void asetaNappula() {
		nappula.setVisible(true);
		label.setVisible(false);
		nappulaPois = false;
	}

	/**
	 * Asetetaan ruudulle arvo
	 * @param uusiArvo arvo, joka halutaan lisätä ruudulle
	 */
	public void asetaArvo(int uusiArvo) {
		this.arvo = uusiArvo;
	}

	/**
	 * Palauttaa ruudun arvo
	 * @return ruudun arvo
	 */
	public int annaArvo() {
		return this.arvo;
	}

	/**
	 * Palauttaa kyseisen Ruutu-olion
	 * @return Ruutu-olio
	 */
	public Ruutu annaRuutu() {
		return this;
	}

	/**
	 * Antaa ruudun x-koordinaatin
	 * @return x-koodrinaatin arvo kokonaislukuna
	 */
	public int annaX() {
		return x;
	}

	/**
	 * Antaa ruudun y-koordinaatin
	 * @return y-koodrinaatin arvo kokonaislukuna
	 */
	public int annaY() {
		return y;
	}

	/**
	 * return true, jos nappula on poistettu ja false, jos nappula on vielä paikallaan
	 */
	public boolean nappulaPoistettu() {
		return nappulaPois;
	}
}
