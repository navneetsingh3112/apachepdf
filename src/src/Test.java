import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        Test documentCreation = new Test();

        PDDocument newDocument = new PDDocument();
        PDPage page1 = documentCreation.createPage(newDocument);

        documentCreation.createContent(newDocument, page1);
        String outputFilePath = "templates/files/test.pdf";
        documentCreation.savePDF(newDocument, outputFilePath);
        newDocument.close();
    }

    public PDPage createPage(PDDocument document) {
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        return page;
    }

    public void createContent(PDDocument document, PDPage page) throws IOException {
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();

        // Load a font that supports Kannada characters
        PDType0Font font = PDType0Font.load(document, new File("templates/Mallige Normal.ttf"));
        contentStream.setFont(font, 12);

        // Set the initial position for the text
        contentStream.newLineAtOffset(50, 750);

        // Kannada characters
        String kannadaText = "ಅಆಇಈಉಊಋಎಏಐಒಓಔಕಖಗಘಙಚಛಜಝಞಟಠಡಢಣತಥದಧನಪಫಬಭಮಯರಲವಶಷಸಹಳ";

        String a="ಜಿಎಲ್‌ಜಿ/ಎಸ್‌ಹೆಚ್‌ಜಿ/ಐಎಲ್ ಲೋನ್ ಕಾರ್ಡ್ ಮತ್ತು ತಥ್ಯ ಪತ್ರ";
        a=a.replace("\u200C", "");
        String abc="ಕ, ಕಾ, ಕಿ, ಕೀ, ಕು, ಕೂ, ಕೃ, ಕೄ, ಕೆ, ಕೇ, ಕೈ, ಕೊ, ಕೋ, ಕೌ, ಕಂ, ಕಃ, ಕಾಕಿ, ಕಾಕೀ, ಕಾಕು, ಕಾಕೂ, ಕಾಕೃ, ಕಾಕೄ, ಕಾಕೆ, ಕಾಕೇ, ಕಾಕೈ, ಕಾಕೊ, ಕಾಕೋ, ಕಾಕೌ, ಕಾಕಂ, ಕಾಕಃ, ಕಿಕ, ಕಿಕಾ, ಕಿಕಿ, ಕಿಕೀ, ಕಿಕು, ಕಿಕೂ, ಕಿಕೃ, ಕಿಕೄ, ಕಿಕೆ, ಕಿಕೇ, ಕಿಕೈ, ಕಿಕೊ, ಕಿಕೋ, ಕಿಕೌ, ಕಿಕಂ, ಕಿಕಃ, ಕೀಕ, ಕೀಕಾ, ಕೀಕಿ, ಕೀಕೀ, ಕೀಕು, ಕೀಕೂ, ಕೀಕೃ, ಕೀಕೄ, ಕೀಕೆ, ಕೀಕೇ, ಕೀಕೈ, ಕೀಕೊ, ಕೀಕೋ, ಕೀಕೌ, ಕೀಕಂ, ಕೀಕಃ, ಕುಕ, ಕುಕಾ, ಕ";


        // Write the Kannada characters to the PDF
//        contentStream.showText(kannadaText);
//        contentStream.newLine();
        contentStream.showText(a);

        contentStream.endText();
        contentStream.close();
    }

    public void savePDF(PDDocument document, String filePath) throws IOException {
        document.save(filePath);
    }
}