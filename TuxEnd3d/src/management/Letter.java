
package management;

import env3d.EnvObject;

public class Letter extends EnvObject {
    char letter;
    
    public Letter (char l, double x, double z) {
        letter=l;
        setX(x);
        setZ(z);
        setY(4.0);
        setScale(1);
        if (l==' ') {
            setTexture("models/cube/cube.png");
        } else {
            setTexture("models/letter/" + l + ".png");
        }
        setModel("models/cube/cube.obj");
    }
    
    private void setLetter(char l){
        setTexture("models/letter/" + l + ".png");
    }
    
}
