public class Rot13 {

    private String lletres = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private char[] minuscules = lletres.toCharArray();
    private char[] majuscules = lletres.toUpperCase().toCharArray();

    public void main(String[] args) {
        String msgs[] = {"ABC","XYZ","Hola, Mr. calçot","Perdó, per tu què és?"};
        String msgXifrats[] = new String[msgs.length];

        System.out.println("\nXifrat\n------------");

        for (int i = 0; i<msgs.length;i++) {
            msgXifrats[i] = xifraRot13(msgs[i]);
            System.out.printf("%-23s => %s%n",msgs[i],xifraRot13(msgs[i]));
        }
        System.out.println("\nDesxifrat\n---------");

        for(String msg:msgXifrats) {
            System.out.printf("%-23s => %s%n",msg,desxifraRot13(msg));
        }

    }

    public String xifraRot13(String s) {
        String resultat = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    if(c == majuscules[j]){
                        resultat += majuscules[(j+13)%majuscules.length];
                        break;
                    }
                }
            } else if (Character.isLowerCase(c)) {
                for (int j = 0; j < minuscules.length; j++) {
                    if(c == minuscules[j]){
                        resultat += minuscules[(j+13)%minuscules.length];
                        break;
                    }
                }
            } else {
                resultat+=c;
            }

        }
        return resultat;
    }

    public String desxifraRot13(String s){
        String resultat = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if(Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (c==majuscules[j]) {
                        resultat+=majuscules[(j-13+majuscules.length)%majuscules.length];
                        break;
                    }
                }
            } else if (Character.isLowerCase(c)){
                for (int j = 0; j < minuscules.length; j++) {
                    if(c==minuscules[j]) {
                        resultat+= minuscules[(j-13+minuscules.length)%minuscules.length];
                        break;
                    }
                }
            } else {
                resultat+=c;
            }
        }
        return resultat;

    }

    

}
