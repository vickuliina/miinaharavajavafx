package org.openjfx;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class Ruudukko {
    GridPane ruudukko;
    Ruutu[][] ruudut;

    int leveys;
    int pituus;
    int pommiMaara;
    int ruudukonKoko;

    /**
     * Luodaan ruudukko eli luodaan Ruutu-olioita ja lisataan ne ruudukkoon
     * @param leveys Ruutu ruutujen lukumäärä leveyssuunnassa
     * @param pituus, Ruutu ruutujen lukumäärä pituussuunnassa
     * @param pommiMaara, pommien määrä kentässä
     * @param ruudukonKoko ruudukon koko, minkä kokoisen ruudukon halutaan tehdä
     */
    public Ruudukko(int leveys, int pituus, int pommiMaara, int ruudukonKoko) {
        ruudukko = new GridPane();
        ruudut = new Ruutu[leveys][pituus];

        this.leveys = leveys;
        this.pituus = pituus;
        this.pommiMaara = pommiMaara;
        this.ruudukonKoko = ruudukonKoko;

        //luo ja asettaa Ruutu-oliot(eli ruudut) ruudukkoon
        for (int y=0; y<pituus; y++) {
            for (int x=0; x<leveys; x++) {

                Ruutu ruutu = new Ruutu(x, y, this);
                ruutu.asetaArvo(0);
                ruudut[x][y] = ruutu;
                ruudukko.add(ruudut[x][y], x, y);
            }
        }

        //asetetaan ruudukkoon pommit ja numerot, jotka kertovat viereisten pommien lukumäärän
        asetaPommit();
        asetaNumerot();

        //TESTI, tulostaa pohjaruudukon komentoriville
        for(int y=0; y<pituus; y++) {
            for(int x=0; x<leveys; x++) {
                System.out.print(ruudut[x][y].annaArvo() + "  |  ");
            }
            System.out.println();
        }

        //TESTI, tulostaa pommien maaran komentoriville
        System.out.println("POMMIEN MÄÄRÄ: " + pommiMaara);
    }


    /**
     * Asetetaan randomisti pommit ruudukkoon
     * eli pommien lukumäärän verran lisää randomisti ruudukon Ruutu-olioille pommeja (eli arvon 9)
     * pommin arvo = 9
     */
    public void asetaPommit() {
        //uutta pelia varten
        int pommit = pommiMaara;

        while(pommit > 0) {
            Random random = new Random();

            int luku1 = random.nextInt(leveys);
            int luku2 = random.nextInt(pituus);

            if(ruudut[luku1][luku2].annaArvo() != 9) {
                ruudut[luku1][luku2].asetaArvo(9);
                pommit -= 1;
            }
        }
    }


    /**
     * Asettaa ruudukon jokaiselle ruudulle arvon,
     * sen mukaan kuinka monta pommia (eli arvoa 9) on kyseisen Ruutu-olion ympärillä
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
    public void avaaRuudukko() {
        for(int i=0; i<leveys; i++) {
            for(int j=0; j<pituus; j++) {
                ruudut[i][j].poistaNappula();
            }
        }
    }

    /**
     * Palauttaa ruudukon
     * @return palautettu ruudukko GridPanenina
     */
    public GridPane annaRuudukko() {
        return ruudukko;
    }

}
