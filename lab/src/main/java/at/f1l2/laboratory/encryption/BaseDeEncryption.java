package at.f1l2.laboratory.encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.FilenameUtils;

public abstract class BaseDeEncryption {

    protected SecretKeySpec generateSecretKeySpec(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        byte[] key = (password).getBytes("UTF-8");
        // aus dem Array einen Hash-Wert erzeugen mit MD5 oder SHA
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        key = sha.digest(key);
        // nur die ersten 128 bit nutzen
        key = Arrays.copyOf(key, 16);
        // der fertige Schluessel
        return new SecretKeySpec(key, "AES");
    }

    protected String getEncryptedPath(String path) {
        String filename = FilenameUtils.getBaseName(path);
        String newFilename = filename + "_enc";
        return path.replace(filename, newFilename);
    }

    protected String getDecryptedPath(String path) {
        String filename = FilenameUtils.getBaseName(path);
        String newFilename = filename + "_dec";
        return path.replace(filename, newFilename);
    }

}
