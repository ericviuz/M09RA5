
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES
 */
public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguïs";

    public static void main(String[] args) {

        String msgs[] = {
            "Lorem ipsum dicet",
            "Hola Andrés com està tu cuñado",
            "Àgora illa Òtto"
        };

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

    public static byte[] xifraAES(String msg, String password)
            throws Exception {

        // Obtenir els bytes del String
        byte[] stringBytes = msg.getBytes();

        // Generar IV
        generaIV();

        // Preparar l'IV
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Generar hash de la contrasenya
        SecretKeySpec key = generaHash(password);

        // Xifrar el missatge
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

        byte[] msgXifrat = cipher.doFinal(stringBytes);

        // Combinar IV i missatge xifrat
        byte[] resultat = new byte[iv.length + msgXifrat.length];

        System.arraycopy(iv, 0, resultat, 0, iv.length);
        System.arraycopy(msgXifrat, 0, resultat, iv.length,
                msgXifrat.length);

        return resultat;
    }

    public static String desxifraAES(byte[] bMsgxifrat, String password)
            throws Exception {

        // Extreure l'IV
        byte[] ivExtret = extreureIV(bMsgxifrat);

        // Extreure la part xifrada
        byte[] msgXifrat = getBytesXifrats(bMsgxifrat);

        // Generar hash de la contrasenya
        SecretKeySpec key = generaHash(password);

        // Preparar l'IV extret
        IvParameterSpec ivSpec = new IvParameterSpec(ivExtret);

        // Desxifrar
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

        byte[] bytesDesxifrats = cipher.doFinal(msgXifrat);

        // Convertir els bytes a String
        return new String(bytesDesxifrats);
    }

    private static void generaIV() {
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
    }

    private static SecretKeySpec generaHash(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(password.getBytes());
        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }

    private static byte[] extreureIV(byte[] bMsgxifrat) {
        return Arrays.copyOfRange(bMsgxifrat, 0, MIDA_IV);
    }

    private static byte[] getBytesXifrats(byte[] bMsgxifrat) {
        return Arrays.copyOfRange(
                bMsgxifrat, MIDA_IV, bMsgxifrat.length);
    }
}