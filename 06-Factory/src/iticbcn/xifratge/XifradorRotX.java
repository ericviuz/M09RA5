package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {

    private final String lletres = "AÀÁBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    private final char[] minuscules = lletres.toLowerCase().toCharArray();
    private final char[] majuscules = lletres.toCharArray();

    public String xifraRotX(String cadena, int desplaçament) {
        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);

            for (int j = 0; j < majuscules.length; j++) {
                if (majuscules[j] == lletra) {
                    int novaPosicio = (j + desplaçament) % majuscules.length;
                    lletra = majuscules[novaPosicio];
                    break;
                }
            }

            for (int j = 0; j < minuscules.length; j++) {
                if (minuscules[j] == lletra) {
                    int novaPosicio = (j + desplaçament) % minuscules.length;
                    lletra = minuscules[novaPosicio];
                    break;
                }
            }

            resultat.append(lletra);
        }

        return resultat.toString();
    }

    public String desxifraRotX(String cadena, int desplaçament) {
        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);

            for (int j = 0; j < majuscules.length; j++) {
                if (majuscules[j] == lletra) {
                    int novaPosicio = (j - desplaçament + majuscules.length) % majuscules.length;
                    lletra = majuscules[novaPosicio];
                    break;
                }
            }

            for (int j = 0; j < minuscules.length; j++) {
                if (minuscules[j] == lletra) {
                    int novaPosicio = (j - desplaçament + minuscules.length) % minuscules.length;
                    lletra = minuscules[novaPosicio];
                    break;
                }
            }

            resultat.append(lletra);
        }

        return resultat.toString();
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplaçament;
        try {
            desplaçament = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un enter de 0 a 40");
        }

        if (desplaçament < 0 || desplaçament > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un enter de 0 a 40");
        }

        String xifrat = xifraRotX(msg, desplaçament);
        return new TextXifrat(xifrat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplaçament;
        try {
            desplaçament = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un enter de 0 a 40");
        }

        if (desplaçament < 0 || desplaçament > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un enter de 0 a 40");
        }

        return desxifraRotX(xifrat.toString(), desplaçament);
    }
}