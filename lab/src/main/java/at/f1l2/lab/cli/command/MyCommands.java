package at.f1l2.lab.cli.command;

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

import at.f1l2.lab.encryption.FileDecryption;
import at.f1l2.lab.encryption.FileEncryption;
import at.f1l2.lab.pdf.PDFConverter;
import at.f1l2.lab.pdf.PDFMerge;

@ShellComponent
@ShellCommandGroup("f1l2 Commands")
public class MyCommands {

	@Autowired
	private FileEncryption fileEncyption;

	@Autowired
	private FileDecryption fileDecryption;

	@Autowired
	private PDFMerge pdfMerge;

	@Autowired
	private PDFConverter pdfConverter;

	@ShellMethod("Encrypt file")
	public String encrypt(@ShellOption({ "-p", "--path" }) String filePath,
			@ShellOption({ "-p", "--password" }) String password) throws Exception {
		String newPath = fileEncyption.encrypt(password, filePath);
		return "Encryption finished successfully. File: " + newPath;
	}

	@ShellMethod("Decrypt file")
	public String decrypt(@ShellOption({ "-p", "--path" }) String filePath,
			@ShellOption({ "-p", "--password" }) String password) throws Exception {
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

	@ShellMethod("Merge PDFs.\n\t\tOption 'path' defines full qualified path for pdf folder. Files are sorted by name and have to be of type PDF.")
	public String merge(@ShellOption({ "-p", "--path" }) String folderPath) throws Exception {
		if (Objects.isNull(folderPath)) {
			throw new OptionRequiredException("path");
		}
		String newPath = pdfMerge.merge(folderPath);
		return "Merge finished successfully. File: " + newPath;
	}

	@ShellMethod("Image to PDF.\n\t\tOption 'path' defines full qualified path for image file.")
	public String imageToPDF(@ShellOption({ "-p", "--path" }) String filePath) throws Exception {
		if (Objects.isNull(filePath)) {
			throw new OptionRequiredException("path");
		}
		String newPath = pdfConverter.createPDFFromImage(filePath);
		return "Convertion finished successfully. File: " + newPath;
	}
}
