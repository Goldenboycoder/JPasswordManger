package src.main.java.passwordmanager.Utils;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Encryption {

    public static String encryptWithPrivateKey(String privateKey, String textToEncrypt)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {

        String result = "";
        byte[] data = textToEncrypt.getBytes();

        // Get PublicKey object
        byte[] privKeyBytes = Base64.getDecoder().decode(privateKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(privKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privKey = keyFactory.generatePrivate(keySpec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privKey);
        result = Base64.getEncoder()
                .encodeToString(cipher.doFinal(data));

        return result;
    }

    public static String encryptWithPublicKey(String publicKey, String textToEncrypt)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {

        String result = "";
        byte[] data = textToEncrypt.getBytes();

        // Get PublicKey object
        byte[] pubKeyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        result = Base64.getEncoder()
                .encodeToString(cipher.doFinal(data));

        return result;
    }

    public static String decryptWithPublicKey(String publicKey, String cipherText)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        String result = "";
        byte[] data = Base64.getDecoder().decode(cipherText); // cipherText.getBytes();

        // Get PublicKey object
        byte[] pubKeyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        result = new String(cipher.doFinal(data));

        return result;
    }

    public static String decryptWithPrivateKey(String privateKey, String cipherText)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        String result = "";
        byte[] data = Base64.getDecoder().decode(cipherText); // cipherText.getBytes();

        // Get PublicKey object
        byte[] privKeyBytes = Base64.getDecoder().decode(privateKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(privKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privKey = keyFactory.generatePrivate(keySpec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privKey);
        result = new String(cipher.doFinal(data));

        return result;
    }


    public static String[] generateKeyPair(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privKey = keyPair.getPrivate();

        String[] keys = new String[2];

        keys[0] = Base64.getEncoder().encodeToString(privKey.getEncoded());
        keys[1] = Base64.getEncoder().encodeToString(pubKey.getEncoded());

        return keys;

    }
}
