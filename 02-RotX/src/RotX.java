public class RotX {

    private String lletres = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private char[] minuscules = lletres.toCharArray();
    private char[] majuscules = lletres.toUpperCase().toCharArray();

    public void main(String[] args) {
        String msgs[] = {"ABC","XYZ","Hola, Mr. calçot","Perdó, per tu què és?"};
        String msgXifrats[] = new String[msgs.length];

        System.out.println("\nXifrat\n------------");
        int index = 0;
        for (int i = 0; i<msgs.length;i++) {
            msgXifrats[i] = xifraRotX(msgs[i], index);
            System.out.printf("%-23s => %s%n",msgs[i],xifraRotX(msgs[i],index));
            index+=2;
        }
        System.out.println("\nDesxifrat\n---------");

        for(String msg:msgXifrats) {
            System.out.printf("%-23s => %s%n",msg,desxifraRotX(msg,index));
        }

    }

    public String xifraRotX(String s, int desp) {
        String resultat = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    if(c == majuscules[j]){
                        resultat += majuscules[(j+desp)%majuscules.length];
                        break;
                    }
                }
            } else if (Character.isLowerCase(c)) {
                for (int j = 0; j < minuscules.length; j++) {
                    if(c == minuscules[j]){
                        resultat += minuscules[(j+desp)%minuscules.length];
                        break;
                    }
                }
            } else {
                resultat+=c;
            }

        }
        return resultat;
    }

    public String desxifraRotX(String s,int desp){
        String resultat = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if(Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (c==majuscules[j]) {
                        resultat+=majuscules[(j-desp+majuscules.length)%majuscules.length];
                        break;
                    }
                }
            } else if (Character.isLowerCase(c)){
                for (int j = 0; j < minuscules.length; j++) {
                    if(c==minuscules[j]) {
                        resultat+= minuscules[(j-desp+minuscules.length)%minuscules.length];
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
