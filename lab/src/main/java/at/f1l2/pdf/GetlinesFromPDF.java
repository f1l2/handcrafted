package at.f1l2.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class GetlinesFromPDF extends PDFTextStripper {

	static List<String> lines = new ArrayList<String>();

	public GetlinesFromPDF() throws IOException {
	}

	public static void main(String[] args) throws IOException {
		PDDocument document = null;
		String fileName = "C://Users//Manuel//Documents//test.pdf";
		try {
			document = PDDocument.load(new File(fileName));
			PDFTextStripper stripper = new GetlinesFromPDF();
			stripper.setSortByPosition(true);

			stripper.setStartPage(1);
			stripper.setEndPage(1);
			Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
			stripper.writeText(document, dummy);
			String qoute_number = lines.get(0);
			System.out.println(qoute_number);

			lines.clear();

			stripper.setStartPage(3);
			stripper.setEndPage(3);
			Writer dummy1 = new OutputStreamWriter(new ByteArrayOutputStream());
			stripper.writeText(document, dummy1);
			String qoute_number1 = lines.get(0);
			System.out.println(qoute_number1);
		}

		finally {
			if (document != null) {
				document.close();
			}
		}
	}

	@Override
	protected void writeString(String str, List<TextPosition> textPositions) throws IOException {
		lines.add(str);

	}
}
