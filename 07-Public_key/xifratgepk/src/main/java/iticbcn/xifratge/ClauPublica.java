package iticbcn.xifratge;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class ClauPublica {
    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();

    }

    public byte[] xifraRSA(String msg, PublicKey claPublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE,claPublica);
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);
        return cipher.doFinal(msgBytes);
    }

    public String desxifraRSA(byte[] msgXifrat,PrivateKey clauPrivada) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);

        byte[] bytesDescifraos = cipher.doFinal(msgXifrat);
        return new String(bytesDescifraos,StandardCharsets.UTF_8);
    }
}
