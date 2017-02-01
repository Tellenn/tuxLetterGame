
package game;

import java.io.IOException;
import org.xml.sax.SAXException;


public class Game {

    public static void main(String[] args) throws SAXException, IOException {
        //Instanciate a new Jeu
        Jeu jeu;
        jeu = new Jeu("src/data/joueur.xml");
        
        //Play the game
        jeu.jouer();
    }
    
}
