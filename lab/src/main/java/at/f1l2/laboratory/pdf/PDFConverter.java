package at.f1l2.laboratory.pdf;

import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Component;

@Component
public class PDFConverter {

	public String createPDFFromImage(String filePath) throws IOException {
		try (PDDocument doc = new PDDocument()) {
			// we will add the image to the first page.
			PDPage page = new PDPage();
			doc.addPage(page);

			// createFromFile is the easiest way with an image file
			// if you already have the image in a BufferedImage,
			// call LosslessFactory.createFromImage() instead
			PDImageXObject pdImage = PDImageXObject.createFromFile(filePath, doc);

			try (PDPageContentStream contentStream = new PDPageContentStream(doc, page, AppendMode.APPEND, true,
					true)) {
				// contentStream.drawImage(ximage, 20, 20 );
				// better method inspired by
				// http://stackoverflow.com/a/22318681/535646
				// reduce this value if the image is too large
				float scale = 1f;

				if (pdImage.getWidth() > 555) {
					scale = 555f / (float) pdImage.getWidth();
				}

				contentStream.drawImage(pdImage, 20, 20, pdImage.getWidth() * scale, pdImage.getHeight() * scale);
			}

			String newPath = getPDFPath(filePath);
			doc.save(getPDFPath(filePath));
			return newPath;
		}
	}

	protected String getPDFPath(String path) {
		String filename = FilenameUtils.getName(path);
		String newFilename = filename + "_pdf.pdf";
		return path.replace(filename, newFilename);
	}
}
