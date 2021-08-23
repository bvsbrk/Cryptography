import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class mac {
    public static void main(String[] __) throws NoSuchAlgorithmException, InvalidKeyException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecureRandom secRandom = new SecureRandom();
        keyGen.init(secRandom);
        Key key = keyGen.generateKey();

        System.out.println(new String(key.getEncoded()));

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(key);


        System.out.println(new String(mac.doFinal("Hello, world".getBytes())));
    }
}
