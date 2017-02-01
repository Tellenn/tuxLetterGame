package game;

import management.LectureClavier;
import management.DevineLeMot;
import management.Dico;
import env3d.Env;
import java.io.IOException;
import management.DicoHandler;
import management.Letter;
import org.lwjgl.input.Keyboard;
import org.xml.sax.SAXException;

public class Jeu {

    Env env;
    boolean finished;
    Tux tux;
    Letter l;
    int level;
    DicoHandler dico;
    Profile profile;
    String filename;
    
    public Jeu() {
        // Create the new environment.  Must be done in the same
        // method as the game loop
        env = new Env();
        // Sets up the camera
        env.setCameraPitch(-38);
        // Turn off the default controls
        env.setDefaultControl(false);
        //initialize
        finished = true;
        dico = new DicoHandler();
        dico.readDictionnary("dico.xml");
    }
    
    public Jeu(String filename) throws SAXException, IOException {
        // Create the new environment.  Must be done in the same
        // method as the game loop
        env = new Env();
        // Sets up the camera
        env.setCameraPitch(-38);
        // Turn off the default controls
        env.setDefaultControl(false);
        //initialisation dictionnaire
        // this.dico = new Dico("dico.xml");
        //initialisation attribut profile
        this.profile = new Profile(filename);
        this.filename=filename;
        //initialize
        dico = new DicoHandler();
        finished = true;
    }

    public void jouer() throws SAXException, IOException {
        boolean menu;
        DevineLeMot gamemode1;
        Room room;
        int mode;
        int niveau;
        String wordNiveau;     //pour mot aléatoire
        boolean surplus;
        Partie maPartie;
        finished = false;
        while (!finished) {
            env.soundLoop("/sounds/menu.ogg");
            menu=false;
            mode=-1;
            niveau=-1;
            surplus=false;
            maPartie=null;
            env.setDisplayStr("Choisissez la liste des modes de jeux disponible : \n"
                            + "0/ Quitter\n"
                            + "1/ Devine le mot\n"
                            + "2/ Devine le mot avec surplus\n");
            env.setCameraXYZ(00, 30, -50);
            env.advanceOneFrame();
            
            // récupération du mode de jeu
            while(  mode != Keyboard.KEY_NUMPAD0 &&
                    mode != Keyboard.KEY_0 &&
                    mode != Keyboard.KEY_NUMPAD1 &&
                    mode != Keyboard.KEY_1 &&
                    mode != Keyboard.KEY_NUMPAD2 &&
                    mode != Keyboard.KEY_2 ){
                mode=env.getKey();
                env.advanceOneFrame();
            }
            //mode = LectureClavier.lireEntier();
            env.setDisplayStr("");
            room = new Room();
            
            //selon le mode jeu
            switch (mode){
                case Keyboard.KEY_2:
                case Keyboard.KEY_NUMPAD2:
                        surplus=true;       //pour jouer avec surplus
                case Keyboard.KEY_1:
                case Keyboard.KEY_NUMPAD1:
                    env.setDisplayStr("Choisissez le niveau voulu : \n"
                            + "0/ Retour\n"
                            + "1/ Niveau 1\n"
                            + "2/ Niveau 2\n"
                            + "3/ Niveau 3\n"
                            + "4/ Niveau 4\n"
                            + "5/ Niveau 5\n");
                    //récupération du niveau
                    while(  niveau != Keyboard.KEY_NUMPAD0 &&
                            niveau != Keyboard.KEY_0 &&
                            niveau != Keyboard.KEY_NUMPAD1 &&
                            niveau != Keyboard.KEY_1 &&
                            niveau != Keyboard.KEY_NUMPAD2 &&
                            niveau != Keyboard.KEY_2 &&
                            niveau != Keyboard.KEY_NUMPAD3 && 
                            niveau != Keyboard.KEY_3 &&
                            niveau != Keyboard.KEY_NUMPAD4 && 
                            niveau != Keyboard.KEY_4 &&
                            niveau != Keyboard.KEY_NUMPAD5 &&
                            niveau != Keyboard.KEY_5){
                        niveau=env.getKey();
                        env.advanceOneFrame();
                    }
                    env.setDisplayStr("");
                    if(niveau != Keyboard.KEY_NUMPAD0){
                        env.soundStop("/sounds/menu.ogg");
                    }
                    env.setRoom(room);
                    env.setCameraXYZ(20, 30, 65);
                    //jeu selon le niveau choisi précedemment
                    switch (niveau){
                        //niveau = 1
                        case Keyboard.KEY_1:
                        case Keyboard.KEY_NUMPAD1:
                            //wordNiveau = mot du niveau 1 sélectionné
                            //aléatoirement dans le dico (version Dico.java)
                            wordNiveau = dico.getWordFromListLevel(1);
                            gamemode1 = new DevineLeMot(wordNiveau,env,room,surplus,1);
                            maPartie=gamemode1.jouer();
                            break;
                        //niveau = 2
                        case Keyboard.KEY_2:
                        case Keyboard.KEY_NUMPAD2:
                            wordNiveau = dico.getWordFromListLevel(2);
                            gamemode1 = new DevineLeMot(wordNiveau,env,room,surplus,2);
                            maPartie=gamemode1.jouer();
                            break;
                        //niveau = 3
                        case Keyboard.KEY_3:
                        case Keyboard.KEY_NUMPAD3:
                            wordNiveau = dico.getWordFromListLevel(3);
                            gamemode1 = new DevineLeMot(wordNiveau,env,room,surplus,3);
                            maPartie=gamemode1.jouer();
                            break;
                        //niveau = 4
                        case Keyboard.KEY_4:
                        case Keyboard.KEY_NUMPAD4:
                            wordNiveau = dico.getWordFromListLevel(4);
                            gamemode1 = new DevineLeMot(wordNiveau,env,room,surplus,4);
                            maPartie=gamemode1.jouer();
                            break;
                        //niveau = 5
                        case Keyboard.KEY_5:
                        case Keyboard.KEY_NUMPAD5:
                            wordNiveau = dico.getWordFromListLevel(5);
                            gamemode1 = new DevineLeMot(wordNiveau,env,room,surplus,5);
                            maPartie=gamemode1.jouer();
                            break;
                        default : 
                    }
                    break;
                
                default : 
                    finished=true;
            }
            //Remplir les profils
            if(maPartie!=null){
                profile.ajouterPartie(maPartie);
            }
        }
        // Just a little clean up
        env.setDisplayStr("Souhaitez vous sauvegarder cette partie ? \n"
                            + "0/ Non\n"
                            + "1/ Oui\n");
            env.setCameraXYZ(00, 30, -50);
            env.advanceOneFrame();
            int save = -1;
            while(  save != Keyboard.KEY_NUMPAD0 &&
                    save != Keyboard.KEY_NUMPAD1 &&
                    save != Keyboard.KEY_0 &&
                    save != Keyboard.KEY_1){
                save=env.getKey();
                env.advanceOneFrame();
            }
            if(save == Keyboard.KEY_NUMPAD1 || save == Keyboard.KEY_1){
                profile.save(filename);
            }
        env.exit();
    }

}
