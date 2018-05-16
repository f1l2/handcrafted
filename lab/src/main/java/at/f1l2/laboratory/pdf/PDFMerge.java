package at.f1l2.laboratory.pdf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Component;

import at.f1l2.laboratory.utility.AlphanumComparator;

@Component
public class PDFMerge {

    public String merge(String folderPath) throws IOException {

        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            throw new PDFMergeException("Parameter 'path' does not define a folder.");
        }

        AlphanumComparator alphanumComparator = new AlphanumComparator();

        final List<File> sortedFileList = Arrays.stream(folder.listFiles()).sorted((f1, f2) -> alphanumComparator.compare(f1.getName(), f2.getName())).collect(Collectors.toList());

        final PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
        for (File file : sortedFileList) {
            pdfMergerUtility.addSource(file);
            System.out.println(file.getName());
        }

        Path path = Paths.get(folderPath, "Merged.pdf");

        pdfMergerUtility.setDestinationFileName(path.toString());
        pdfMergerUtility.mergeDocuments(null);

        return path.toString();

    }
}
