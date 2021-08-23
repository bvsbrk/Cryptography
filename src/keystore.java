import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Enumeration;

public class keystore {
    public static void main(String[] __) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        /*
         * cacerts file contains all root CA(certificate authority) certificates that jvm trusts
         * If jvm has to trust a new certificate, it's root CA cert or intermediary CA cert must be present in cacerts
         */
        String cacerts = "/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/security/cacerts";

        keyStore.load(new FileInputStream(cacerts), "changeit".toCharArray());

        Enumeration e = keyStore.aliases();
        // Prints all certificates
        while (e.hasMoreElements()) {
            System.out.print(e.nextElement() + " ");
        }

        Certificate c = keyStore.getCertificate("digicertglobalrootca [jdk]");
        // Shows everything about certificate
        System.out.println(c);
        System.out.println(c.getPublicKey());

        // keytool -v -list -keystore /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/security/cacerts
        // Above will print list

        System.out.println(keyStore.getProvider());

        KeyStore.ProtectionParameter protectionParam = new KeyStore.PasswordProtection("changeit".toCharArray());
        SecretKey mySecretKey = new SecretKeySpec("new password".getBytes(), "DSA");
        System.out.println(mySecretKey);

        KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(mySecretKey);

        keyStore.setEntry("my new cert", secretKeyEntry, protectionParam);
        // Prints all certificates
        // Now it should have one extra entry
        Enumeration<String> e2 = keyStore.aliases();
        while (e2.hasMoreElements()) {
            System.out.print(e2.nextElement() + " ");
        }

        //Storing the KeyStore object. To store new cacerts with an additional cert
//        java.io.FileOutputStream fos = null;
//        fos = new java.io.FileOutputStream("newKeyStoreName");
//        keyStore.store(fos, password);
//        System.out.println("data stored");

    }
}
