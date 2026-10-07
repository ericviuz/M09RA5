import java.security.SecureRandom;

/**
 * AES
 */
public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] IV = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguïs";

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                "Hola Andrés com està tu cuñado",
                "Àgora illa Òtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                        + e.getLocalizedMessage());
            }

            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("ENC: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }

    public static byte[] xifraAES(String msg, String password) {
        //Obtenir els bytes del String
        byte[] stringBytes = msg.getBytes();
        //Generar IVparametreSpec

        //Genera Hash

        //Encrypt

        //Combinar IV i part xifrada

        //return iv+msgxifrat

        SecureRandom



    }

    public static String desxifaAES(byte[] bMsgxifrat, String password) {



    }
    
}