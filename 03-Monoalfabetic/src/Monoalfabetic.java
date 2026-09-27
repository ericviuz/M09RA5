import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    private String lletres = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private  char[] alfabet = lletres.toUpperCase().toCharArray();
    private  char[] alfabetPermutat = permutaAlfabet(alfabet);
    public  void main(String[] args) {
        String msgs[] = {"Test 01 àrbitre, coixí, Perímetre", "Test 02 Taüll, DÍA, año", "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];
        for (int i = 0; i < alfabet.length; i++) {
            System.out.print(alfabet[i]+" ");
        }
        
        System.out.println();

        for (int i = 0; i < alfabet.length; i++) {
            System.out.print(alfabetPermutat[i]+" ");
        }
        System.out.println("\nXifratge: ");
        for (int i = 0; i < msgs.length; i++) {
            msgsXifrats[i] = xifraMonoAlfa(msgs[i]);
            System.out.printf("%-35s -> %s%n",msgs[i],xifraMonoAlfa(msgs[i]));
        }

        System.out.println("Desxifratge: ");
        for (String msg : msgsXifrats) {
            System.out.printf("%-35s -> %s%n",msg,desxifraMonoAlfa(msg));
        }
    }

    //AoHola
    public String manipulaMonoAlfa(String s, char[] origen, char[] desti){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {
                for (int j = 0; j < origen.length; j++) {
                    if (Character.toUpperCase(c) == origen[j]) {
                        result.append(Character.toLowerCase(desti[j]));
                    }
                }
            } else if (Character.isUpperCase(c))  {
                for (int j = 0; j < origen.length; j++) {
                    if (c == origen[j]) {
                        result.append(Character.toUpperCase(desti[j]));
                    }
                }
            } else {
                result.append(c);
            }
        }

        String str = result.toString();
        return str;

    }

    public String xifraMonoAlfa(String s){
        return manipulaMonoAlfa(s, alfabet, alfabetPermutat);
    }

    public String desxifraMonoAlfa(String s) {
        return manipulaMonoAlfa(s, alfabetPermutat, alfabet);
    }


    public char[] permutaAlfabet(char[] arr){
        List<Character> permutat = new ArrayList<>();
        for (char c: arr) {
            permutat.add(c);
        }

        Collections.shuffle(permutat);
        char[] resultat = new char[arr.length];
        for (int i = 0; i < permutat.size(); i++) {
            resultat[i] = permutat.get(i);
        } 
    
        return resultat;
    
    }
}
