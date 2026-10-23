package iticbcn.xifratge;

import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {

    private final char[] alfabet = "AÀÁBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private char[] alfabetPermutat;
    private Random random;

    private void initRandom(long clau) {
        random = new Random(clau);
    }

    private void permutaAlfabet() {
        alfabetPermutat = alfabet.clone();

        for (int i = alfabetPermutat.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = alfabetPermutat[i];
            alfabetPermutat[i] = alfabetPermutat[j];
            alfabetPermutat[j] = temp;
        }
    }

    public String xifraPoliAlfa(String msg) {
        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            char lletraOriginal = msg.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletraOriginal);
            char lletraMajuscula = Character.toUpperCase(lletraOriginal);
            char lletraXifrada = lletraOriginal;

            permutaAlfabet();

            for (int j = 0; j < alfabet.length; j++) {
                if (alfabet[j] == lletraMajuscula) {
                    char lletraPermutada = alfabetPermutat[j];
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

    public String desxifraPoliAlfa(String msgXifrat) {
        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < msgXifrat.length(); i++) {
            char lletraXifrada = msgXifrat.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletraXifrada);
            char lletraMajuscula = Character.toUpperCase(lletraXifrada);
            char lletraOriginal = lletraXifrada;

            permutaAlfabet();

            for (int j = 0; j < alfabetPermutat.length; j++) {
                if (alfabetPermutat[j] == lletraMajuscula) {
                    char lletraDesxifrada = alfabet[j];
                    if (esMinuscula) {
                        lletraOriginal = Character.toLowerCase(lletraDesxifrada);
                    } else {
                        lletraOriginal = lletraDesxifrada;
                    }
                    break;
                }
            }

            resultat.append(lletraOriginal);
        }

        return resultat.toString();
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long clauLong;
        try {
            clauLong = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un enter");
        }

        initRandom(clauLong);
        String xifrat = xifraPoliAlfa(msg);
        return new TextXifrat(xifrat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long clauLong;
        try {
            clauLong = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un enter");
        }

        initRandom(clauLong);
        return desxifraPoliAlfa(xifrat.toString());
    }
}