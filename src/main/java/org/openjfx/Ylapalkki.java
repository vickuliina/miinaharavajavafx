package org.openjfx;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Ylapalkki extends HBox {
    Miinaharava miinaharava;

    int alkuperainenLippumaara;

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

        this.alkuperainenLippumaara = miinaharava.annaRuudukko().pommiMaara;

        lippujenMaara = uusiLabel(Integer.toString(alkuperainenLippumaara), Pos.CENTER_LEFT);
        luoUusipeliNappula();
        aika = uusiLabel("aika", Pos.CENTER_RIGHT);


        //lisataan alueet ylapalkkiin
        this.getChildren().add(lippujenMaara);
        this.getChildren().add(uusipeliNappula);
        this.getChildren().add(aika);

    }

    /**
     * Luodaan ylapalkkiin label, jonka sisään laitetaan tieto pommien lukumaarasta
     * Lukumaara vahenee aina yhdella, silloin kun laitetaan ruudun paalle lippu
     * Lisaa labelin ylapalkkiin
     */
    public void luoLippuLabel() {
        //luodaan lippujenemäärän kertova alue
        lippujenMaara = uusiLabel("Lippujen määrä", Pos.CENTER_LEFT);
    }

    /**
     * Luodaan ylapalkkiin nappula, jonka avulla voidaan aloittaa uusi peli
     * Lisaa nappulan ylapalkkiin
     */
    public void luoUusipeliNappula() {
        //luodaan "uusipeli"-nappula
        uusipeliNappula = new Button("Uusi Peli");
        uusipeliNappula.setPrefSize((400/3),40);
        uusipeliNappula.setOnMouseClicked( e -> {
            miinaharava.uusiPeli();
        });
    }

    /**
     * Luodaan ylapalkkiin label, jonka sisään laitetaan kello (=aika)
     * Kello lähtee kayntiin, kun klikataan ensimmaista ruutua pelikentalla
     * Lisaa labelin ylapalkkiin
     */
    public void luoAikaLabel() {
        //luodaan aika ruutu
        aika = uusiLabel("AIKA", Pos.CENTER_RIGHT);
    }

    /**
     * Luodaan label, jolle annetaan otiskko ja otsikon paikka labelissa
     * Asetetaan jokaiselle labelille sama koko ja reunat
     * Palautetaan kyseinen label
     * @param otsikko ns. nimi labelille, tulee näkyviin
     * @param paikka otsikon sijainti labelin sisässä
     */
    public Label uusiLabel(String otsikko, Pos paikka) {
        Label label = new Label(otsikko);
        label.setPrefSize((400/3),40);
        label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
        label.setAlignment(paikka);

        return label;
    }

    public void vahennaLippu() {
        lippujenMaara.setText("toimiiko?");
    }

    public void lisaaLippu() {
        lippujenMaara.setText("toimiiko?");
    }
}

