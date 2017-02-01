
package game;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;


public class Profile {
    private String nom;
    private String dateNaissance;
    private int avatar;
    private ArrayList<Partie> parties;
    
    public Profile(String nom, String dateNaissance) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        avatar = 1;
        parties = new ArrayList<Partie>();
    }
    
    //prend en paramètre le nom d'un fichier xml d'un joueur
    public Profile(String filename) throws SAXException, IOException {
        DOMParser parser = new DOMParser();
        parser.parse(filename);
        Document doc = parser.getDocument();
        parties = new ArrayList<Partie>();
        
        this.nom = doc.getElementsByTagName("name").item(0).getTextContent();
        this.dateNaissance = doc.getElementsByTagName("birthday").item(0).getTextContent();
        this.avatar = Integer.parseInt(doc.getElementsByTagName("avatar").item(0).getTextContent());
        int nbGame = doc.getElementsByTagName("game").getLength();
        for(int i=0; i<nbGame; i++) {
            Partie maPartie;
            maPartie = new Partie((Element)(doc.getElementsByTagName("game").item(i)));
            this.ajouterPartie(maPartie);
        }
    }
    
    public void ajouterPartie(Partie p) {
        parties.add(p);
    }
    
    //non utilisée car choix de niveau dans le menu au départ
    public int getLastLevel() {
        return parties.get(parties.size()-1).getNiveau();
    }
    
    public String toString() {
        return "null";
    }
    
    public void save(String filename) throws SAXException, IOException {
        try {
            DOMParser parser = new DOMParser();
            parser.parse(filename);
            Document doc = parser.getDocument();
            int debut = doc.getElementsByTagName("game").getLength();
            int fin = parties.size();
            for (int i = debut;i<fin;i++) {
                 Partie maPartie=parties.get(i);
                 doc.getElementsByTagName("games").item(0).appendChild(maPartie.getDomElement(doc));
                 System.out.println(maPartie);
            }
            //Faire l'écriture sur le fichier.
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            System.out.println("-----------Modified File-----------");
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e1) {
            System.out.println("Pas de fichier utilisateur trouvé, creation d'un nouveau");
            try {
                DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = 
                    dbFactory.newDocumentBuilder();
                Document doc = dBuilder.newDocument();

                // root element
                Element rootElement = doc.createElement("profile");
                rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
                rootElement.setAttribute("xmlns", "http://myGame/profile");
                rootElement.setAttribute("xsi:schemaLocation", "http://myGame/profile file:/home/p/perrink/S5/LangagesPourLeWeb/Tux/Tux/src/xml/joueur.xsd");
                doc.appendChild(rootElement);

                Element name = doc.createElement("name");
                name.setTextContent(this.nom);
                rootElement.appendChild(name);

                Element avatar = doc.createElement("avatar");
                avatar.setTextContent(String.valueOf(this.avatar));
                rootElement.appendChild(avatar);

                Element birthday = doc.createElement("birthday");
                birthday.setTextContent(this.dateNaissance);
                rootElement.appendChild(birthday);

                Element games = doc.createElement("games");
                for(int i=0;i<parties.size();i++){
                    Partie maPartie=parties.get(i);
                    games.appendChild(maPartie.getDomElement(doc));
                }
                rootElement.appendChild(games);


                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(filename));
                transformer.transform(source, result);
                // Output to console for testing
                StreamResult consoleResult = new StreamResult(System.out);
                transformer.transform(source, consoleResult);
            } catch (Exception e2) {
                System.out.println(e2);
            }
        }
    }
    
    private String xmlDateToProfileDate(String xmlDate) {
        String date;
        // récupérer le jour
        date = xmlDate.substring(xmlDate.lastIndexOf("-") + 1, xmlDate.length());
        date += "/";
        // récupérer le mois
        date += xmlDate.substring(xmlDate.indexOf("-") + 1, xmlDate.lastIndexOf("-"));
        date += "/";
        // récupérer l'année
        date += xmlDate.substring(0, xmlDate.indexOf("-"));
        return date;
    }
    
    private String DateToXmlDate(String profileDate) {
        String date;
        // Récupérer l'année
        date = profileDate.substring(profileDate.lastIndexOf("/") + 1, profileDate.length());
        date += "-";
        // Récupérer  le mois
        date += profileDate.substring(profileDate.indexOf("/") + 1, profileDate.lastIndexOf("/"));
        date += "-";
        // Récupérer le jour
        date += profileDate.substring(0, profileDate.indexOf("/"));
        return date;
    }
    
    
}
