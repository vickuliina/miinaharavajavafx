package org.openjfx;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Ylapalkki extends HBox {
    Miinaharava miinaharava;

    HBox ylapalkki;
    Label lippujenMaara;
    Button uusipeliNappula;
    Label aika;

    /**
     * Luo yläpalkin
     * @param miinaharava peli, johon yläpalkki laitetaan
     * @param leveys palkin leveys
     * @param pituus palkin pituus
     */
    public Ylapalkki(Miinaharava miinaharava, int leveys, int pituus) {
        this.miinaharava = miinaharava;
        this.setPrefSize(400,60);

        luoLippuLabel();
        luoUusipeliNappula();
        luoAikaLabel();

        //lisataan alueet ylapalkkiin
        this.getChildren().add(lippujenMaara);
        this.getChildren().add(uusipeliNappula);
        this.getChildren().add(aika);

    }

    public void luoLippuLabel() {
        //luodaan lippujenemäärän kertova alue
        lippujenMaara = new Label("Lippujen määrä");
        lippujenMaara.setPrefSize((400/3),40);
        lippujenMaara.setAlignment(Pos.CENTER_LEFT);
        lippujenMaara.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));

    }

    public void luoUusipeliNappula() {
        //luodaan "uusipeli"-nappula
        uusipeliNappula = new Button("Uusi Peli");
        uusipeliNappula.setPrefSize((400/3),40);
        uusipeliNappula.setOnMouseClicked( e -> {
            miinaharava.uusiPeli();
        });
    }
    public void luoAikaLabel() {
        //luodaan aika ruutu
        aika = new Label("AIKA");
        aika.setPrefSize((400/3),40);
        aika.setAlignment(Pos.CENTER_RIGHT);
        aika.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
    }
}

