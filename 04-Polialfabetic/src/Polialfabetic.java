import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {
    private String lletres = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private  char[] alfabet = lletres.toUpperCase().toCharArray();
        private Random random;
    private  char[] alfabetPermutat;
    private long clauSecreta = 1234;
    public void main(String[] args) {
        String msgs[] = {"Test 01 àrbitre, coixí, Perímetre", "Test 02 Taüll, DÍA, año", "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n------------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    public void initRandom(long clauSecreta) {
        random = new Random(clauSecreta);
    }

    //AoHola
    public String manipulaPoliAlfa(String s, boolean xifrar){
        char[] origen = new char[alfabet.length];
        char[] desti = new char[alfabet.length];


        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            permutaAlfabet(alfabet);

            if (xifrar) {
                origen = alfabet;
                desti = alfabetPermutat;
            } else {
                origen = alfabetPermutat;
                desti = alfabet;
            }
                
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

    public String xifraPoliAlfa(String s){
        return manipulaPoliAlfa(s, true);
    }

    public String desxifraPoliAlfa(String s) {
        return manipulaPoliAlfa(s, false);
    }


    public void permutaAlfabet(char[] arr){
        List<Character> permutat = new ArrayList<>();
        for (char c: arr) {
            permutat.add(c);
        }

        Collections.shuffle(permutat,random);
        char[] resultat = new char[arr.length];
        for (int i = 0; i < permutat.size(); i++) {
            resultat[i] = permutat.get(i);
        } 
        alfabetPermutat = resultat;
    }
}