package at.f1l2.laboratory.encryption;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class FileDecryption extends BaseDeEncryption {

    public String decrypt(String password, String path) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte[] encryptedFile = FileUtils.readFileToByteArray(new File(path));
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, generateSecretKeySpec(password));

        byte[] decryptedFile = cipher.doFinal(encryptedFile);

        String newPath = getDecryptedPath(path);

        FileUtils.writeByteArrayToFile(new File(newPath), decryptedFile);

        return newPath;

    }

}
