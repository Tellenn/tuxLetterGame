
package test;
import management.Dico;

public class TestDico {

    public static void main(String[] args) {
        Dico dico;
        dico = new Dico("dico.xml");
        boolean b;
        String w1 = "ajouter";
        String w11 = "nuit";
        String w3 = "jazz";
        String w5 = "hippopotame";
        String word1;
        String word5;
        
        dico.addWordToDico(1,w1);
        dico.addWordToDico(1,w11);
        dico.addWordToDico(3,w3);
        dico.addWordToDico(5,w5);
        
        word1 = dico.getWordFromListLevel(1);
        word5 = dico.getWordFromListLevel(5);
        System.out.println(word5);
        
        word1 = dico.getWordFromListLevel(1);
        System.out.println(word1);
        
        word1 = dico.getWordFromListLevel(1);
        System.out.println(word1);
        
        word1 = dico.getWordFromListLevel(1);        
        System.out.println(word1);
        
    }
    
}
