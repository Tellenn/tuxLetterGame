
package game;

import management.Letter;
import env3d.Env;
import env3d.EnvObject;
import org.lwjgl.input.Keyboard;

public class Tux extends EnvObject {
    public Tux (double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
        setScale(1);
        setTexture("models/tux/tux.png");
        setModel("models/tux/tux.obj");
    }
    
    public void move (Env env) {
        double step = 0.25;
        if ((env.getKeyDown(Keyboard.KEY_Z) && env.getKeyDown(Keyboard.KEY_S) )|| 
            (env.getKeyDown(Keyboard.KEY_UP) && env.getKeyDown(Keyboard.KEY_DOWN))){
            //Message d'erreur
        }else if ((env.getKeyDown(Keyboard.KEY_Q) && env.getKeyDown(Keyboard.KEY_D))|| 
                 (env.getKeyDown(Keyboard.KEY_LEFT) && env.getKeyDown(Keyboard.KEY_RIGHT))){
            //Message d'erreur
        }else if ((env.getKeyDown(Keyboard.KEY_Z) && env.getKeyDown(Keyboard.KEY_Q))|| 
                 (env.getKeyDown(Keyboard.KEY_UP) && env.getKeyDown(Keyboard.KEY_LEFT))){
            //Haut Gauche
            this.setRotateY(225);
            if(this.getZ() > 2.0 ){
                this.setZ(this.getZ() - step/2);
            }
            if(this.getX() > 1.0 ){
                this.setX(this.getX() - step/2);
            }
            
        }else if ((env.getKeyDown(Keyboard.KEY_Z) && env.getKeyDown(Keyboard.KEY_D))|| 
                 (env.getKeyDown(Keyboard.KEY_UP) && env.getKeyDown(Keyboard.KEY_RIGHT))){
            //Haut Droit
            this.setRotateY(135);
            if(this.getZ() > 2.0 ){
                this.setZ(this.getZ() - step/2);
            }
            if(this.getX() < 39.0 ){
                this.setX(this.getX() + step/2);
            }
            
        }else if ((env.getKeyDown(Keyboard.KEY_S) && env.getKeyDown(Keyboard.KEY_Q))|| 
                 (env.getKeyDown(Keyboard.KEY_DOWN) && env.getKeyDown(Keyboard.KEY_LEFT))){
            //Bas Gauche
            this.setRotateY(315);
            if(this.getZ() < 49.0 ){
                this.setZ(this.getZ() + step/2);
            }
            if(this.getX() > 1.0 ){
                this.setX(this.getX() - step/2);
            }
            
        }else if ((env.getKeyDown(Keyboard.KEY_S) && env.getKeyDown(Keyboard.KEY_D))|| 
                 (env.getKeyDown(Keyboard.KEY_DOWN) && env.getKeyDown(Keyboard.KEY_RIGHT))){
            //Bas Droit
            this.setRotateY(45);
            if(this.getZ() < 49.0 ){
                this.setZ(this.getZ() + step/2);
            }
            if(this.getX() < 39.0 ){
                this.setX(this.getX() + step/2);
            }
            
        }else if (env.getKeyDown(Keyboard.KEY_Z) || env.getKeyDown(Keyboard.KEY_UP) ) {
            this.setRotateY(180);
            if(this.getZ() > 2.0 ){
                this.setZ(this.getZ() - step);
            }
            
        }else if (env.getKeyDown(Keyboard.KEY_S) || env.getKeyDown(Keyboard.KEY_DOWN) ) {
            this.setRotateY(0);
            if(this.getZ() < 49.0 ){ //TODO REACTIVE ROOM
                this.setZ(this.getZ() + step);
            }
            
        }else if (env.getKeyDown(Keyboard.KEY_Q) || env.getKeyDown(Keyboard.KEY_LEFT) ) {
            this.setRotateY(270);
            if(this.getX() > 1.0 ){
                this.setX(this.getX() - step);
            }
            
        }else if (env.getKeyDown(Keyboard.KEY_D) || env.getKeyDown(Keyboard.KEY_RIGHT) ) {
            this.setRotateY(90);
            if(this.getX() < 39.0 ){
                this.setX(this.getX() + step);
            }
        }
    }
    public void jump (Env env) {
        env.soundPlay("/sounds/jump.ogg");        
        for(int i=0;i<10;i++){
            this.setY(this.getY() + 0.4);
            env.advanceOneFrame();
        }
       for(int i=0;i<10;i++){
            this.setY(this.getY() - 0.4);
            env.advanceOneFrame();
        }
    }
    public void jump (Env env, Letter lettre) {
        env.soundPlay("/sounds/jump.ogg");
        env.soundPlay("/sounds/wrong.ogg");        
        for(int i=0;i<10;i++){
            this.setY(this.getY() + 0.4);
            lettre.setY(lettre.getY() + 0.4);
            env.advanceOneFrame();
        }
       for(int i=0;i<10;i++){
            this.setY(this.getY() - 0.4);
            lettre.setY(lettre.getY() - 0.4);
            env.advanceOneFrame();
        }
    }
    
}
