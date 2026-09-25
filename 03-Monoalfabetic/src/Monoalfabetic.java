import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {
    private String lletres = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private  char[] alfabet = lletres.toUpperCase().toCharArray();
    private  char[] alfabetPermutat = permutaAlfabet(alfabet);
    public  void main(String[] args) {
        String msgs[] = {"àrbitre, coixí, Perímetre", "Taüll, DÍA, año", "Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        for (int i = 0; i < alfabet.length; i++) {
            System.out.print(alfabet[i]+" ");
        }
        
        System.out.println();

        for (int i = 0; i < alfabet.length; i++) {
            System.out.print(alfabetPermutat[i]+" ");
        }
        System.out.println("Xifratge: ");







        System.out.println("Desxifratge: ");

    }

        //àrbitre, coixí, Perímetre
    public String xifraMonoAlfa(String s){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) {
                for (int j = 0; j < alfabet.length; j++) {
                    if (c == alfabet[j]) {
                        result.append(Character.toLowerCase(alfabetPermutat[j]));
                    }
                }
            } else{
                for (int j = 0; j < alfabet.length; j++) {
                    if (c == alfabet[j]) {
                        result.append(Character.toLowerCase(alfabetPermutat[j]));
                    } else {
                        result.append(c);
                    }
                }
            }
        }


    }


    public String desxifraMonoAlfa(String s) {



    }


    public static char[] permutaAlfabet(char[] arr){
        List<Character> permutat = new ArrayList<>();
        for (char c: arr) {
            permutat.add(c)
        }

        Collections.shuffle(permutat);

        for (int i = 0; i < permutat.size(); i++) {
            arr[i] = permutat.get(i);
        } 
    
        return arr;
    
    }
}
