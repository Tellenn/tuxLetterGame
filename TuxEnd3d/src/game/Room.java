
package game;

public class Room {
    int depth;
    int height;
    int width;
    String textureBottom;
    String textureNorth;
    String textureEast;
    String textureWest;
    String textureTop;
    String textureSouth;
    
    Room(){
        depth = 50;
        width = 40;
        height = 50;
        textureBottom="textures/floor/dirt1.png";
        textureNorth="textures/skybox/fantasy/north.png";
        textureEast="textures/skybox/fantasy/east.png";
        textureWest="textures/skybox/fantasy/west.png";
    }

}
