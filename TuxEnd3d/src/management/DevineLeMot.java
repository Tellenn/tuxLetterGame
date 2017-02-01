
package management;

import env3d.Env;
import game.Room;
import game.Tux;
import java.util.ArrayList;
import game.Partie;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
//temp
import org.lwjgl.input.Keyboard;



public class DevineLeMot {
    
    Env env;
    Tux tux;
    ArrayList<Letter> letters;
    ArrayList<Letter> lettersSurplus;
    int nbLettresRestantes;
    int temps;
    Chronometre chrono;
    boolean surplus;
    Partie partieActuelle;
    
     public DevineLeMot(String mot, Env env, Room room, boolean surplus,int level) {
        this.env = env;
        int x;
        int y;
        int c;
        char lettreSurplus;
        Letter l;
        env.setRoom(room);
        this.surplus=surplus;
        tux = new Tux(20.0,60.0,20.0);
        chrono = new Chronometre(9000000);  
        letters = new ArrayList<Letter>(); //limite chrono à modifier
        lettersSurplus = new ArrayList<Letter>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        partieActuelle = new Partie(String.valueOf(dateFormat.format(date)),mot,level);
        //initialisation de letters
        env.addObject(tux);
        
        char[] lettres = mot.toCharArray();// A MODIFIER POUR RESOUDRE LE BUG
        int[][] tableau;
        tableau = new int[13][16];
        for(int j=0; j<10 ;j++){
            for(int k=0; k<15 ;k++){
                    tableau[j][k]=0;
            }
        }
        for (int i=0 ; i< lettres.length; i++) {// TODO BUG
            do{
                x = (int)(Math.random()*12);
                y = (int)(Math.random()*15);
            }while(tableau[x][y]==1);
            tableau[x][y]=1;
            l = new Letter(lettres[i],x*3+2,y*3+2);
            letters.add(l);
            env.addObject(letters.get(i));
        }
        if(surplus){
            for(int i=0;i<lettres.length/2;i++){
                do{
                x = (int)(Math.random()*12);
                y = (int)(Math.random()*15);
            }while(tableau[x][y]==1);
            c = (int)(Math.random()*25);
            tableau[x][y]=1;
            lettreSurplus=(char)((int)('a')+c);
            l = new Letter(lettreSurplus,x*3+2,y*3+2);
            lettersSurplus.add(l);
            env.addObject(l);
            }
        }
        nbLettresRestantes = mot.length();
        
    }
    
    public Partie jouer() {
        // Insert Tux
       // env.addObject(tux);
        //System.out.println("Tux affiché");
        // Add the letters
        env.soundLoop("/sounds/game.ogg");
        for(int i = 0; i < nbLettresRestantes;i++){
            env.addObject(letters.get(i));
        }
    // Start chrono
        env.advanceOneFrame();
        //llint mode = LectureClavier.lireEntier();
            chrono.start();
        // The main game loop
        
        for(int i=0;i<59;i++){
            tux.setY(tux.getY() - 1);
            env.advanceOneFrame();
        }
        tux.jump(env);
        do {
            for(int i=0;i<letters.size();i++){
                if(this.collision(tux,letters.get(i))){
                       letters.get(i).setRotateY(letters.get(i).getRotateY()-2);
                }
            }
            if(surplus){
                for(int i=0;i<lettersSurplus.size();i++){
                    if(this.collision(tux,lettersSurplus.get(i))){
                          lettersSurplus.get(i).setRotateY(lettersSurplus.get(i).getRotateY()-2);
                    }
                }
            }
            env.setDisplayStr("Temps restant : "+(60-chrono.getSeconds()));
            // Ask for user input, check if it collides and remove letters if necessary
            this.checkUserKey();
            // Update display
            env.advanceOneFrame();
        } while (env.getKey() != 1 && nbLettresRestantes != 0 && (60-chrono.getSeconds())>0 );
        partieActuelle.setTemps(chrono.getSeconds());
        chrono.stop();
        //Post-Process: game is finished
        if(nbLettresRestantes==0){
            env.soundPlay("/sounds/win.ogg");
            for(int i=0;i<72;i++){
                tux.setRotateY(tux.getRotateY()+5);
                env.advanceOneFrame();
            }
            while(tux.getRotateY()%360 != 0){
                tux.setRotateY(tux.getRotateY()+5);
                env.advanceOneFrame();
                System.out.println(tux.getRotateY());
            }
            tux.jump(env);
        }else{
            int b = (int) (90-tux.getRotateX())/45;
            env.soundPlay("/sounds/lose.ogg");
            for(int i=0;i<45;i++){
                tux.setRotateX(tux.getRotateX()-b);
                tux.setY(tux.getY()-0.01);
                env.advanceOneFrame();
            }
            

        }
        for(int i=0;i<90;i++){
            env.advanceOneFrame();
        }
        env.soundStop("/sounds/game.ogg");
        //we have to keep the data to save our score (chrono, temps, nbLettresRestantes) 
        partieActuelle.setTrouve(nbLettresRestantes);
        return partieActuelle;
    }
    
    public void checkUserKey() {
        Letter lettre;
        Letter fail=null;
        Letter temporaire;
        int row;
        int column;
        boolean changement = false;
        //méthode pour bouger tux
        tux.move(env);
        lettre = this.tuxMeetsLetter(letters);
       /* if(lettre == null){
            lettre = this.tuxMeetsLetter(lettersSurplus);
            changement = true;
        }*/
        
        if(lettre != null && env.getKeyDown(Keyboard.KEY_SPACE)){
           /* if(changement){
                int indexSurplus=lettersSurplus.indexOf(lettre);
                int index = letters.size()-nbLettresRestantes;
                temporaire = letters.get(index);
                letters.set(index, lettersSurplus.get(indexSurplus));
                lettersSurplus.set(indexSurplus, temporaire);
                
            }*/
           
        row=(letters.size()-nbLettresRestantes)/20;
        column=row;
        lettre.setX((2*(letters.size()-nbLettresRestantes)+1)-(column*40));
        lettre.setY(9.0-(row*2));
        lettre.setZ(-0.9);
        lettre.setRotateY(0.0);
        tux.jump(env);
        this.nbLettresRestantes--;
            //afficher la lettre ailleurs pour dire que c'est gagné
        }else if (env.getKeyDown(Keyboard.KEY_SPACE)){
            for(int i=0;i<this.letters.size();i++){
                if(this.collision(tux, this.letters.get(i)))
                    fail=this.letters.get(i);
            }
            if(fail!=null){
                tux.jump(env,fail);
            }else{
                if(surplus){
                    lettre=null;
                    for(int i=0; i<lettersSurplus.size() && lettre==null;i++){
                        if(this.collision(tux,lettersSurplus.get(i))){
                            lettre=lettersSurplus.get(i);
                            }
                    }
                    if(lettre!=null){
                        tux.jump(env,lettre);
                    }
                    else{
                        tux.jump(env);
                    }
                }else{
                    tux.jump(env);
                }
            }
        }
    }
    
    private Letter tuxMeetsLetter(ArrayList<Letter> l) {
        int lettresok ;
        Letter res = null;
        lettresok = l.size()-nbLettresRestantes;
        for(int i=0;i<l.size() && res == null;i++){
            if (this.collision(tux, l.get(i)) && 
                l.get(i).letter == l.get(lettresok).letter){
                res = l.get(i);
            }
        }
        return res;
    }
    
    private double distance(Tux tux, Letter letter) {
        double dist;
        dist = Math.abs(tux.getX()-letter.getX())+Math.abs(tux.getZ()-letter.getZ());
        return dist;
    }
    
    private boolean collision(Tux tux, Letter letter) {
        return (this.distance(tux, letter) - tux.getScale() -letter.getScale() )< 0 && Math.abs(tux.getY()-letter.getY())<5;
    }
    
    private int getNbLettresRestantes() {
        return this.nbLettresRestantes;
    }
    
    public int getTemps() {
        return chrono.getSeconds();
    }
    
    
}
