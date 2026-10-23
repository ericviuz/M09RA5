package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {

    public final String ALGORISME_XIFRAT = "AES";
    public final String ALGORISME_HASH = "SHA-256";
    public final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    public final int MIDA_IV = 16;
    public byte[] iv = new byte[MIDA_IV];

    private SecretKeySpec generaHash(String password) throws Exception {
        byte[] passthroughBytes = password.getBytes(StandardCharsets.UTF_8);
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = digest.digest(passthroughBytes);

        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }

    private IvParameterSpec generaIv() {
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    private byte[] extreureIv(byte[] bIvIMsgXifrat) {
        return Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
    }

    private byte[] getBytesXifrats(byte[] bIvIMsgXifrat) {
        return Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);
    }

    public byte[] xifraAES(String msg, String clau) throws Exception {
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);
        IvParameterSpec ivSpec = generaIv();
        SecretKeySpec secretKey = generaHash(clau);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] msgXifrat = cipher.doFinal(msgBytes);

        byte[] resultat = new byte[MIDA_IV + msgXifrat.length];
        System.arraycopy(iv, 0, resultat, 0, MIDA_IV);
        System.arraycopy(msgXifrat, 0, resultat, MIDA_IV, msgXifrat.length);

        return resultat;
    }

    public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        byte[] bytesIv = extreureIv(bIvIMsgXifrat);
        byte[] msgXifrat = getBytesXifrats(bIvIMsgXifrat);

        SecretKeySpec secretKey = generaHash(clau);

        IvParameterSpec ivSpec = new IvParameterSpec(bytesIv);
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);

        return new String(msgDesxifrat, StandardCharsets.UTF_8);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] res = xifraAES(msg, clau);
            return new TextXifrat(res);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            return desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }
}