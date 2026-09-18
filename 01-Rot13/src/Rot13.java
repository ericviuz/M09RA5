public class Rot13 {

    private String lletres = "aáàbçdeéèfghiíïjklmnoóòpqrstuúüvwxyz";
    private char[] minuscules = lletres.toCharArray();
    private char[] majuscules = lletres.toUpperCase().toCharArray();

    public static void main(String[] args) {
        String msgs[] = {"ABC","XYZ","Hola, Mr. calçot","Perdó, per tu què és"};
        String msgXifrats[] = new String[msg.length];

        System.out.println("\nxifrat\n------------\n");

        for (int i = 0; i<msgs.length;i++) {
            msgXifrats[i] = xifraRot13(msgs[i]);
        }
        System.out.println("\nDesxifrat\n---------");

        for(String msg:msgXifrats) {
            System.out.printf("%-23s => %s%n",msg,desxifraRot13(msg));
        }

    }

    public static String xifraRot13(String s) {
        for (int i = 0; i < s.length(); i++) {
            
        }
    }

    public static String desxifraRot13(String s){

    }

}
