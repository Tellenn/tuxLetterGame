
package game;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;


public class Partie {
    private String date;
    private String mot;
    private int niveau;
    private int trouve;
    private int temps;
    
    public Partie(String date, String mot, int level) {
        this.date = date;
        this.mot = mot;
        niveau = level;
    }
    
    public Partie(Element domPartie) {
        this.date = domPartie.getAttribute("date");
        Element word = (Element)(domPartie.getElementsByTagName("word").item(0));
        this.mot = word.getTextContent();
        this.niveau = Integer.parseInt(word.getAttribute("level"));
        if(domPartie.getElementsByTagName("time").getLength()==1) {
            this.temps = Integer.parseInt(domPartie.getElementsByTagName("time").item(0).getTextContent());
        } else {
            this.temps = -1;    //si pas de "time"
        }
        if (domPartie.hasAttribute("found")) {
            this.trouve = Integer.parseInt(domPartie.getAttribute("found"));
        } else {
            this.trouve = -1;   //si pas d'attribut "found" 
        }
    }
        
    public Element getDomElement(Document doc) {
       Element monElement = doc.createElement("game");
       monElement.setAttribute("date", this.date);
       if(this.trouve > -1 && this.trouve < 100){
           monElement.setAttribute("found", String.valueOf(this.trouve));
       }
       if(this.temps != -1){
        Element time = doc.createElement("time");
        time.setTextContent(String.valueOf(this.temps));
        monElement.appendChild(time);
        }
       
       Element word = doc.createElement("word");
       word.setTextContent(String.valueOf(this.mot));
       word.setAttribute("level", String.valueOf(this.niveau));
       monElement.appendChild(word);
       
       return monElement;
    }
    
    public void setTrouve(int nbLettresRestantes){
        double temp = (double)(nbLettresRestantes) /(double) (mot.length()) *100.0;
        int pourcentage = 100-(int)(temp);
        this.trouve = pourcentage;
    }
    
    public int getTrouve(){
        return this.trouve;
    }
    
    public void setTemps(int temps) {
        this.temps=temps;
    }
    
    public int getTemps (){
        return this.temps;
    }
    
    public int getNiveau() {
        return this.niveau;
    }
    
            
    
    
    public String toString() {
        
        return "null";
    }
    
    
    
}
