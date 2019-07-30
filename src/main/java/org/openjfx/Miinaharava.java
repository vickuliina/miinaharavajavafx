package org.openjfx;

import javafx.application.Application;
import javafx.stage.Stage;

public class Miinaharava extends Application {
	
	Stage nykyinen;
	Stage alkuvalikko;
	Stage pieniKentta;

	
    @Override
    public void start(Stage stage) throws Exception {
    	
    	Kentta kentta = new Kentta("testi", 10, 10);
    	nykyinen = kentta.annaKentta();

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
