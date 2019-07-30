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
import javafx.scene.text.TextAlignment;

public class Ruutu extends Pane{
	Button nappula;
	Label label;
	
	boolean onkoPommi;
	boolean onkoMerkattu;
	int arvo;
	
	public Ruutu() {
		nappula = new Button();
		nappula.setVisible(true);
		
		this.getChildren().add(nappula);
		
		luoLabel();
		label.setVisible(false);
		
		this.getChildren().add(label);
		
        nappula.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	
                nappula.setVisible(false);
                if(arvo == 9) {
                	label.setText("*");
                }
                else {
                    label.setText(Integer.toString(arvo));
                }
                
                label.setVisible(true);

            }
        });
		nappula.setPrefSize(50, 50);
		
	}
	
	public void luoLabel() {
		label = new Label();
		
		label.setBorder(new Border(new BorderStroke(Color.LIGHTGREY, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		label.setMinWidth(50);
		label.setMinHeight(50);
		label.setAlignment(Pos.CENTER);
		label.setFont(Font.font(20));
	}
	
	public void asetaArvo(int uusiArvo) {
		this.arvo = uusiArvo;
	}
	
	public int annaArvo() {
		return this.arvo;
	}
	
	public Ruutu annaRuutu() {
		return this;
	}

	
}
