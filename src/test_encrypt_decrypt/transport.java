package test_encrypt_decrypt;

import javax.crypto.Cipher;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class transport {
    private static PrivateKey getPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Files.readAllBytes(Paths.get("/Users/venbatchu/IdeaProjects/realtime_fork/Cryptography/src/test_encrypt_decrypt/private_key.der"));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    private static PublicKey getPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Files.readAllBytes(Paths.get("/Users/venbatchu/IdeaProjects/realtime_fork/Cryptography/src/test_encrypt_decrypt/public_key.der"));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    private static void printEncoded(byte[] bytes) {
        System.out.println(new String(Base64.getEncoder().encode(bytes)));
    }

    public static void main(String[] __) throws Exception {
        PublicKey publicKey = getPublicKey();
        PrivateKey privateKey = getPrivateKey();

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] input = "Hello, world".getBytes();
        cipher.update(input);

        byte[] cipherText = cipher.doFinal();

        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        System.out.println(new String(cipher.doFinal(cipherText)));

        printEncoded(privateKey.getEncoded());
    }
}
