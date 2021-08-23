import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class hashing {
    public static void main(String[] __) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        String normal = "Hello, world!";

        md5.update(normal.getBytes());
        sha256.update(normal.getBytes());
        sha1.update(normal.getBytes());

        System.out.println(Arrays.toString(md5.digest()));
        System.out.println(Arrays.toString(sha1.digest()));
        System.out.println(Arrays.toString(sha256.digest()));

    }
}
