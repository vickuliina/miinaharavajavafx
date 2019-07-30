package org.openjfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Ruutu extends Pane {
	Kentta kentta;
	
	Button nappula;
	Label label;
	
	boolean onkoPommi;
	boolean onkoMerkattu;
	int arvo;
	
	boolean nappulaPois;
	
	//kertoo mikä nappula on nappulaRuudukossa kyseessä
	int x;
	int y;
	
	public Ruutu(int x, int y, Kentta kentta) {
		this.kentta = kentta;
		this.x = x;
		this.y = y;
		
		nappulaPois = false;
		
		nappula = new Button();
		nappula.setVisible(true);
		
		this.getChildren().add(nappula);
		
		luoLabel();
		label.setVisible(false);
		
		this.getChildren().add(label);
		
		//nappulan toiminnallisuus
        nappula.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	
            	if(arvo == 0) {
            		nappula.setVisible(false);
            		nappulaPois = true;
            		kentta.avaaNollat(annaRuutu());
            	}
            	else {
            	
	                nappula.setVisible(false);
	                if(arvo == 9) {
	                	label.setText("*");
	                }
	                else {
	                    label.setText(Integer.toString(arvo));
	                }
            	}
                
                label.setVisible(true);

            }
        });
        
        if(kentta.annaKentta().pommiMaara == 9) {
        	nappula.setPrefSize(50, 50);
        }
        else if(kentta.annaKentta().pommiMaara == 40) {
        	nappula.setPrefSize(40, 40);
        }
        else if(kentta.annaKentta().pommiMaara == 99) {
        	nappula.setPrefSize(31, 31);
        }
	}
	
	/**
	 * Luo label kentän, johon voidaan ruutujen nappuloiden poituessa kirjoittaa tekstiä.
	 * Alustetaan label tietyntyyppiseksi
	 */
	public void luoLabel() {
		label = new Label();
		
		label.setBorder(new Border(new BorderStroke(Color.LIGHTGREY, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
       
		if(kentta.annaKentta().pommiMaara == 9) {
			label.setMinWidth(50);
			label.setMinHeight(50);
			label.setFont(Font.font(20));
        }
        else if(kentta.annaKentta().pommiMaara == 40) {
    		label.setMinWidth(40);
    		label.setMinHeight(40);
    		label.setFont(Font.font(18));
        }
        else if(kentta.annaKentta().pommiMaara == 99) {
    		label.setMinWidth(30);
    		label.setMinHeight(30);
    		label.setFont(Font.font(14));
        }
		
		label.setAlignment(Pos.CENTER);
	}
	
	public void asetaArvo(int uusiArvo) {
		this.arvo = uusiArvo;
	}
	
	public int annaArvo() {
		return this.arvo;
	}
	
	public void poistaRuutu() {
        nappula.setVisible(false);
        
        if(annaArvo() != 0) {
        	label.setText(Integer.toString(arvo));
        }
        label.setVisible(true);
        nappulaPois = true;
	}
	

	
	public Ruutu annaRuutu() {
		return this;
	}
	
	public int annaX() {
		return x;
	}
	
	public int annaY() {
		return y;
	}

	/**
	 * return true, jos nappula on poistettu ja false, jos nappula on vielä paikallaan
	 */
	public boolean nappulaPoistettu() {
		return nappulaPois;
	}
	
	public Button annaNappula() {
		return nappula;
	}
	
}
