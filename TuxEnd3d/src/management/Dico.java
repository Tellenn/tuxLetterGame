package management;

import java.util.ArrayList;
//import com.sun.org.apache.xerces.internal.parsers.DOMParser;
//import org.w3c.dom.Document;

public class Dico {

    ArrayList<String> listLevel1;
    ArrayList<String> listLevel2;
    ArrayList<String> listLevel3;
    ArrayList<String> listLevel4;
    ArrayList<String> listLevel5;
    String pathToDicoFile;

    public Dico(String pathToDicoFile) {
        this.pathToDicoFile = pathToDicoFile;
        listLevel1 = new ArrayList<String>() ;
        listLevel2 = new ArrayList<String>() ;
        listLevel3 = new ArrayList<String>() ;
        listLevel4 = new ArrayList<String>() ;
        listLevel5 = new ArrayList<String>() ;
    }

    public String getWordFromListLevel(int level) {
        String word;
        int index;

        double rdm = Math.random(); //renvoie double entre 0 et 1
        switch (level) {
            case 1:
                // Que faire lorsque level == 1
                rdm = rdm * listLevel1.size();
                index = (int) rdm;
                word = listLevel1.get(index);
                break;
            case 2:
                // Que faire lorsque level == 2
                rdm = rdm * listLevel2.size();
                index = (int) rdm;
                word = listLevel2.get(index);
                break;
            case 3:
                rdm = rdm * listLevel3.size();
                index = (int) rdm;
                word = listLevel3.get(index);
                break;
            case 4:
                rdm = rdm * listLevel4.size();
                index = (int) rdm;
                word = listLevel4.get(index);
                break;
            case 5:
                rdm = rdm * listLevel5.size();
                index = (int) rdm;
                word = listLevel5.get(index);
                break;
            default:
                word = "";
                break;
        }
        return word;
    }

    public boolean addWordToDico(int level, String word) {
        boolean ok = true;
        switch (level) {
            case 1:
                listLevel1.add(word);
                break;
            case 2:
                listLevel2.add(word);

                break;
            case 3:
                listLevel3.add(word);

                break;
            case 4:
                listLevel4.add(word);

                break;
            case 5:
                listLevel5.add(word);

                break;
            default:
                ok = false;
                break;
        }
        return ok;
    }

    public String getPathToDico() {
        return pathToDicoFile;
    }

}
