import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class DocumentCreation {

    public static void main(String[] args) throws IOException {
        DocumentCreation documentCreation = new DocumentCreation();

        PDDocument newDocument = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        newDocument.addPage(page);

        documentCreation.createContent(newDocument, page);

        String outputFilePath = "c:/Users/Bhavani K/Desktop/English.pdf";
        documentCreation.savePDF(newDocument, outputFilePath);

        newDocument.close();
    }

    public String extractTextFromPDF(String filePath) throws IOException {
        File inputFile = new File(filePath);
        PDDocument document = Loader.loadPDF(inputFile);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();
        return text;
    }

    public void createContent(PDDocument document, PDPage page) throws IOException {
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);

        contentStream.beginText();
        String heading = "JLG/SHG/IL – LOAN CARD CUM FACT SHEET";
        PDFont font= new PDType1Font(Standard14Fonts.FontName.HELVETICA);
        float stringWidth = font.getStringWidth(heading) * 12 / 1000;
        float centerPosition = (PDRectangle.A4.getWidth() - stringWidth) / 2;
        contentStream.newLineAtOffset(centerPosition, PDRectangle.A4.getHeight() - 50);
        contentStream.showText(heading);

        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(-centerPosition + 60, -10);
        String[] lines = {
                "Branch Code & Name:",
                "Branch Address:",
                "Regd.office:",
        };
        for (String line : lines) {
            contentStream.newLine();
            contentStream.showText(line);
        }
        contentStream.endText();

        float widthInPoints = 10 * 28.3465f;
        float heightInPoints = 3 * 28.3465f;
        contentStream.addRect(50, 700, widthInPoints, heightInPoints);
        contentStream.stroke();

        contentStream.close();
    }

    public void savePDF(PDDocument document, String filePath) throws IOException {
        document.save(filePath);
    }
}
