package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Random;

public class XifradorMonoalfabetic implements Xifrador {

    private final char[] alfabet = "AÀÁBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private String alfabetPermutat = "";

    public XifradorMonoalfabetic() {
        this.alfabetPermutat = new String(permutaAlfabet(alfabet));
    }

    private char[] permutaAlfabet(char[] alfabet) {
        ArrayList<Character> lista = new ArrayList<>();
        for (char c : alfabet) {
            lista.add(c);
        }

        Random random = new Random();

        for (int i = 0; i < lista.size(); i++) {
            int posicio = random.nextInt(lista.size());

            Character temporal = lista.get(i);
            lista.set(i, lista.get(posicio));
            lista.set(posicio, temporal);
        }

        char[] resultat = new char[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            resultat[i] = lista.get(i);
        }

        return resultat;
    }

    public String xifraMonoAlfa(String cadena) {
        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {
            char lletraOriginal = cadena.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletraOriginal);

            char lletraMajuscula = Character.toUpperCase(lletraOriginal);
            char lletraXifrada = lletraOriginal;

            for (int j = 0; j < alfabet.length; j++) {
                if (alfabet[j] == lletraMajuscula) {
                    char lletraPermutada = alfabetPermutat.charAt(j);
                    if (esMinuscula) {
                        lletraXifrada = Character.toLowerCase(lletraPermutada);
                    } else {
                        lletraXifrada = lletraPermutada;
                    }
                    break;
                }
            }
            resultat.append(lletraXifrada);
        }

        return resultat.toString();
    }

    public String desxifraMonoAlfa(String cadena) {
        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            boolean trobada = false;

            for (int j = 0; j < alfabetPermutat.length(); j++) {
                if (alfabetPermutat.charAt(j) == Character.toUpperCase(lletra)) {
                    char lletraDesxifrada = alfabet[j];

                    if (Character.isLowerCase(lletra)) {
                        lletraDesxifrada = Character.toLowerCase(lletraDesxifrada);
                    }

                    resultat.append(lletraDesxifrada);
                    trobada = true;
                    break;
                }
            }
            if (!trobada) {
                resultat.append(lletra);
            }
        }

        return resultat.toString();
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("El xifrat monoalfabètic no suporta clau de xifrat");
        }
        String xifrat = xifraMonoAlfa(msg);
        return new TextXifrat(xifrat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("El xifrat monoalfabètic no suporta clau de xifrat");
        }
        return desxifraMonoAlfa(xifrat.toString());
    }
}