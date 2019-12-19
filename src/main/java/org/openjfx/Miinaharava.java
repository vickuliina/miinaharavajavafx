package org.openjfx;

import javafx.application.Application;
import javafx.stage.Stage;

public class Miinaharava extends Application {
	Stage nykyinen;
	Stage alkuvalikko;

	
	Kentta pieniKentta;
	Kentta keskiKentta;
	Kentta isoKentta;
	
    @Override
	/**
	 * Itse pelin aloitus, toimii alkuun vain pienellä kentalla, muut rakennetaan myohemmin
	 */
    public void start(Stage stage) throws Exception {
    	
    	pieniKentta = new Kentta("pieni", 8, 9, 400, 500);
    	//keskiKentta = new Kentta("keski", 16, 40, 640);
    	//isoKentta = new Kentta("iso", 24, 99, 700);
    	
    	nykyinen = pieniKentta.annaStage();

    	nykyinen.show();
    }

    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
