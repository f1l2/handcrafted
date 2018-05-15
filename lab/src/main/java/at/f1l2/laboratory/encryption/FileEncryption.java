package at.f1l2.laboratory.encryption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class FileEncryption extends BaseDeEncryption {

    public String encrypt(String password, String path) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte[] text = Files.readAllBytes(Paths.get(path));

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, generateSecretKeySpec(password));
        byte[] encrypted = cipher.doFinal(text);

        String newPath = getEncryptedPath(path);
        FileUtils.writeByteArrayToFile(new File(newPath), encrypted);

        return newPath;
    }
}
