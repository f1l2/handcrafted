package at.f1l2.laboratory.cli.command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import at.f1l2.laboratory.encryption.FileDecryption;
import at.f1l2.laboratory.encryption.FileEncryption;
import at.f1l2.laboratory.pdf.PDFMerge;

@ShellComponent
@ShellCommandGroup("f1l2 Commands")
public class MyCommands {

    @Autowired
    private FileEncryption fileEncyption;

    @Autowired
    private FileDecryption fileDecryption;

    @Autowired
    private PDFMerge pdfMerge;

    @ShellMethod("Encrypt file")
    public String encrypt(@ShellOption({ "-p", "--path" }) String filePath, @ShellOption({ "-p", "--password" }) String password) throws Exception {
        String newPath = fileEncyption.encrypt(password, filePath);
        return "Encryption finished successfully. File: " + newPath;
    }

    @ShellMethod("Decrypt file")
    public String decrypt(@ShellOption({ "-p", "--path" }) String filePath, @ShellOption({ "-p", "--password" }) String password) throws Exception {
        String newPath = fileDecryption.decrypt(password, filePath);
        return "Decryption finished successfully. File: " + newPath;
    }

    @ShellMethod("Property")
    public String property(String property) {
        return System.getProperty(property);
    }

    @ShellMethod("Clean")
    public String clean() throws IOException {
        Path path = Paths.get(System.getProperty("user.home"), "Downloads");

        for (File file : path.toFile().listFiles()) {
            if (file.isDirectory()) {
                FileUtils.deleteDirectory(file);
            } else {
                file.delete();
            }
        }
        return "Clean finished successfully.";
    }

    @ShellMethod("Merge PDFs")
    public String merge(@ShellOption({ "-p", "--path" }) String folderPath) throws Exception {
        if (Objects.isNull(folderPath)) {
            throw new OptionRequiredException("path");
        }
        String newPath = pdfMerge.merge(folderPath);
        return "Merge finished successfully. File: " + newPath;
    }
}
