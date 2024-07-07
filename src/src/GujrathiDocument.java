import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;

public class GujrathiDocument{

    public static void main(String[] args) throws IOException {
        GujrathiDocument documentCreation = new GujrathiDocument();

        PDDocument newDocument = new PDDocument();
        PDPage page1 = documentCreation.createPage(newDocument);
        PDPage page2 = documentCreation.createPage(newDocument);

        documentCreation.createContent(newDocument, page1);
        documentCreation.createContentForSecondPage(newDocument, page2);
        String outputFilePath = "c:/Users/Bhavani K/Desktop/Gujrathi.pdf";
        documentCreation.savePDF(newDocument, outputFilePath);

        newDocument.close();
    }

    public PDPage createPage(PDDocument document) {
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        return page;
    }

    private void addJLGHeading(PDPageContentStream contentStream, float yOffset,PDDocument document) throws IOException {
        String heading = "JLG/SHG/IL - લોન કાર્ડ કમ ફેક્ટ શીટ";
        File fontFile = new File("C:\\Users\\Bhavani K\\Desktop\\Nirmala.ttf");
        PDType0Font font = PDType0Font.load(document, fontFile);
        float stringWidth = font.getStringWidth(heading) * 30/ 1000;
        float centerPosition = stringWidth /2;
        contentStream.beginText();
        contentStream.setFont(font, 8);
        contentStream.newLineAtOffset(centerPosition, yOffset);
        contentStream.showText(heading);
        contentStream.endText();
    }

    public void createContent(PDDocument document, PDPage page) throws IOException {
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Adding image
        PDImageXObject pdImage = PDImageXObject.createFromFile("C:\\Users\\Bhavani K\\Downloads\\image (1).png", document);
        contentStream.drawImage(pdImage, 40, 800, 100, 20);

        // Adding JLG heading
        addJLGHeading(contentStream, PDRectangle.A4.getHeight() - 30,document);

        // Adding branch details
        contentStream.beginText();
        contentStream.setLeading(13f);
        contentStream.newLineAtOffset(40, 790);
        contentStream.showText("We understand your world");
        contentStream.newLineAtOffset(10, -40);
        String[] lines = {
                "બ્રાંચ કોડ અને નામ: શાલિની", "બ્રાંચ એડ્રેસ: બેલાંદુર, બેંગ્લોર", "Regd.office: 6524726", "GST Regn: 7671671767715", "CIN: 5276472547" ,
                "PAN: FETRET565"
        };
        for (String line : lines) {
            contentStream.newLine();
            contentStream.showText(line);
        }
        contentStream.endText();

        // branch details
        float widthInPoints = 14f * 28.3465f;
        float heightInPoints = 4.2f* 29.3465f;
        contentStream.addRect(40, 632, widthInPoints, heightInPoints);
        contentStream.stroke();

        //customer photo
        float photoWidth = 4.4f* 28.8465f;
        float photoHeight = 4.4f* 28.3465f;
        float gap = -60;
        contentStream.addRect(80 + widthInPoints-35, 695 - heightInPoints-gap, photoWidth, photoHeight);
        contentStream.stroke();
        PDImageXObject Image = PDImageXObject.createFromFile("C:\\Users\\Bhavani K\\Downloads\\photo.jpg", document);
        contentStream.drawImage(Image, 45+ widthInPoints , 695 - heightInPoints - gap, photoWidth, photoHeight);

        contentStream.beginText();
        contentStream.newLineAtOffset(90 + widthInPoints , 650 - gap );
        contentStream.showText("");
        contentStream.endText();

        //  customer details
        float width = 18.7f * 28.3465f;
        float height = 7 * 29.3465f;
        float newBoxYPosition = 700 - heightInPoints - gap - height - 10;
        contentStream.addRect(40, newBoxYPosition, width , height);
        contentStream.stroke();

        float midPoint = (width) / 2;
        contentStream.moveTo(40 + midPoint, newBoxYPosition);
        contentStream.lineTo(40 + midPoint, newBoxYPosition + height);
        contentStream.stroke();

        contentStream.beginText();
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(50, newBoxYPosition + height);
        String[] line = {
                "ગ્રાહકનું નામ અને ID: જ્હોન ડો - 1234",
                "લોન એકાઉન્ટ નંબર: 5678901234",
                "જૂથનું નામ: આલ્ફાગ્રુપ",
                "ઉત્પાદન: આલ્ફાલોન",
                "પતિનું નામ: રિચાર્ડ રો",
                "ગ્રાહકનું સરનામું: 14 મુખ્ય ,જયનગર",
                "",
                "વિતરિત તારીખ: 01-01-2022",
                "લોન રકમ (INR): 50000",
                "કુલ વ્યાજ ચાર્જ (INR): 5000",
                "અન્ય અપ-ફ્રન્ટ શુલ્ક (INR): 1000",
                "પ્રોસેસિંગ ફી (INR): 200",
                "વીમાની રકમ (INR): 300",
                "અન્ય (જો કોઈ હોય તો) (INR): 100",
                "નેટ વિતરિત રકમ (INR): 55000",
                "ચુકવવાની કુલ રકમ (INR): 60000",
                "વાર્ષિક દર (%): 10",
                "વ્યાજ દર (%): 10"
        };
        for (String i : line) {
            contentStream.newLine();
            contentStream.showText(i);
        }
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(60 + midPoint , newBoxYPosition + height);
        String[] additionalLines = {
                "મોબાઇલ નંબર: 1234567890",
                "પિતાનું નામ: જોન ડો સિનિયર.",
                "સહ-અરજદારનું નામ (જો લાગુ હોય તો): જેન ડો",
                "સેન્ટર પ્લેસ(સરનામું): 1234 Main St, Anytown, Anystate",
                "",
                "લોન ટર્મ (મહિનાઓમાં): 12",
                "ચુકવણી આવર્તન: માસિક",
                "ચુકવણીના હપ્તાની સંખ્યા: 12",
                "ચુકવણી તારીખ: 01-01-2023",
                "ચુકવણીની રકમ (INR): 5000",
                "ધિરાણનો પ્રકાર: વ્યક્તિગત લોન",
                "લોન સાયકલ: 1",
                "બેંક એકટ નંબર: 123456789012",
                "હેતુ: ઘર નવીનીકરણ",
                "લાભાર્થી એ/સી નામ: જોન ડો",
                "બેંકનું નામ: ABC બેંક",
                "નેટઓફ એકાઉન્ટ/રકમ: 123456789012"
        };
        for (String j : additionalLines) {
            contentStream.newLine();
            contentStream.showText(j);
        }
        contentStream.endText();
        createTable(contentStream, 40, newBoxYPosition - 5, 530, 400, 10, 12, true, false,1,document);
        contentStream.close();
    }

    public void createContentForSecondPage(PDDocument document, PDPage page) throws IOException {
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Adding JLG heading
        addJLGHeading(contentStream, PDRectangle.A4.getHeight() - 30,document);
        createTable(contentStream, 40, 800, 530, 300, 8, 12, true, true,2,document);

        float width = 17 * 28.3465f;
        float height = 6.5f* 28.3465f;
        float gap = 195;
        float newBoxYPosition = 700 - gap - height - 10;
        contentStream.addRect(40, newBoxYPosition, width + 50, height);
        contentStream.stroke();

        float rowPosition = newBoxYPosition + height - 40 / 2;
        contentStream.moveTo(40, rowPosition);
        contentStream.lineTo(40 + width + 50, rowPosition);
        contentStream.stroke();

        // Adding text for processing fee and insurance details
        contentStream.beginText();
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(50, newBoxYPosition + height - 10);
        contentStream.showText("પ્રોસેસિંગ ફી અને સ્ટેમ્પ ડ્યુટી શુલ્ક");
        contentStream.newLineAtOffset(250, 0);
        contentStream.showText("વીમા વિગતો:");
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(50, newBoxYPosition + height - 20);
        String[] line = {
                "કુલ પ્રોસેસિંગ ફી (INR): 1000",
                "", "સ્ટેમ્પ ડ્યુટી (INR) (GST સહિત): 1000", "",
                "આકસ્મિક શુલ્ક: 500", "", "લોનની પૂર્વચુકવણી (જો કોઈ હોય તો):", "", "વિલંબિત ચુકવણીના કિસ્સામાં દંડ (જો કોઈ હોય તો): 200",
                "", "અન્ય શુલ્ક (જો કોઈ હોય તો): 100", ""
        };
        for (String i : line) {
            contentStream.newLine();
            contentStream.showText(i);
        }
        contentStream.endText();

        contentStream.beginText();
        contentStream.setLeading(10f);
        contentStream.newLineAtOffset(300, newBoxYPosition + height - 20);
        String[] line1 = {
                "વીમેદારનું નામ: કરણ", "", "નોમિનીનું નામ: કિરણ", "", "ઉધાર લેનાર સાથે સંબંધ: ભાઈ", "", "વીમાધારકની ઉંમર: 30",
                "", "સમ એશ્યોર્ડ(INR):100000", "", "વીમા પ્રીમિયમ(INR): 20000", "", "વીમાની શરૂઆતની તારીખ: 12/05/2024", "", "વીમા સમાપ્તિ તારીખ: 23 /4/2030", ""
        };
        for (String i : line1) {
            contentStream.newLine();
            contentStream.showText(i);
        }
        contentStream.endText();

        float midPoint = (width + 20) / 2;
        contentStream.moveTo(40 + midPoint, newBoxYPosition);
        contentStream.lineTo(40 + midPoint, newBoxYPosition + height);
        contentStream.stroke();

        // Adding key terms and conditions
        contentStream.beginText();
        contentStream.setLeading(11f);
        float textStartY = newBoxYPosition - 15;
        contentStream.newLineAtOffset(40, textStartY);
        contentStream.showText("મુખ્ય નિયમો અને શરતો:");
        contentStream.endText();
        addTermsAndConditions(contentStream, 70, textStartY - 10);

        // Adding grievance redressal
        contentStream.beginText();
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(40, 150);
        contentStream.showText("ફરિયાદ નિવારણ:");
        contentStream.newLineAtOffset(0, -10);
        contentStream.showText("કોઈપણ ફરિયાદ નિવારણ માટે તમે કૃપા કરીને સંપર્ક કરી શકો છો.");
        contentStream.endText();

        contentStream.addRect(40, 50, 530, 80);
        float midPoint1 = 40 + 530 / 2;
        contentStream.moveTo(midPoint1, 50);
        contentStream.lineTo(midPoint1, 130);

        //create row for grievance redressal
        float rowPosition1 =110;
        contentStream.moveTo(40, rowPosition1);
        contentStream.lineTo(40 + width + 50, rowPosition1);
        contentStream.stroke();

        contentStream.beginText();
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(50, 120);
        contentStream.showText("નોડલ અધિકારીનું નામ");
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText("અરુણ");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(320, 120);
        contentStream.showText("નોડલ ઓફિસર સંપર્ક નં");
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText("1234567890");
        contentStream.endText();

        contentStream.stroke();

        contentStream.close();

    }


    private void addTermsAndConditions(PDPageContentStream contentStream, float x, float y) throws IOException {
        String[] termsAndConditions = {
                "1. નિયમિત ગ્રૂપ મીટિંગ યોજવી", "2. સભ્યની હાજરી અને સહભાગિતા 100% હોવી જોઈએ",
                "3. નિયમિત માસિક બચત હોવી જોઈએ.", "4. બધી બચત આંતરિક લોન તરીકે રૂપાંતરિત થવી જોઈએ અને કોઈ આદર્શ ભંડોળ ન હોવું જોઈએ.",
                "5. લોનની ચુકવણી આંતરિક અને બેંક લોન બંને 100% અને સમયસર હોવી જોઈએ.", "6. નિયમિત અપડેટ સાથે પુસ્તક અને રેકોર્ડની યોગ્ય જાળવણી.",
                "7. જૂથે ડિફોલ્ટિંગ / અનિયમિત સભ્યો પર દબાણ કરવું જોઈએ.", "8. સારું નેતૃત્વ, ઉત્સાહી અને શિસ્તબદ્ધ સભ્ય સારી ટીમ ભાવના સાથે.",
                "9. તમામ સભ્યો વચ્ચે સહકાર અને એકતા.", "10. બેંક કર્મચારીઓના વર્તન માટે જવાબદાર છે."
        };

        contentStream.beginText();
        contentStream.setLeading(12f);
        contentStream.newLineAtOffset(x, y);

        for (String term : termsAndConditions) {
            contentStream.showText(term);
            contentStream.newLine();
        }
        contentStream.endText();
    }


    private void createTable(PDPageContentStream contentStream, float x, float y, float tableWidth, float tableHeight, int rows, int cols, boolean withHeader, boolean withFooter,int page,PDDocument document) throws IOException {
        float rowHeight = tableHeight / rows;

        float firstColWidth = tableWidth * 0.05f;
        float lastColWidth = tableWidth * 0.2f;
        float remainingTableWidth = tableWidth - firstColWidth - lastColWidth;
        float remainingColWidth = remainingTableWidth / (cols - 3);
        contentStream.setLineWidth(1);

        // Draw rows
        for (int i = 0; i <= rows; i++) {
            contentStream.moveTo(x, y - i * rowHeight);
            contentStream.lineTo(x + tableWidth, y - i * rowHeight);
            contentStream.stroke();
        }

        // Draw columns
        contentStream.moveTo(x, y);
        contentStream.lineTo(x, y - tableHeight);
        contentStream.stroke();

        float currentX = x + firstColWidth;
        for (int i = 1; i < cols - 1; i++) {
            contentStream.moveTo(currentX, y);
            contentStream.lineTo(currentX, y - tableHeight);
            contentStream.stroke();
            currentX += remainingColWidth;
        }

        contentStream.moveTo(x + tableWidth, y);
        contentStream.lineTo(x + tableWidth, y - tableHeight);
        contentStream.stroke();

        if (withHeader) {
            // Add text to the header row
            contentStream.beginText();
            contentStream.setLeading(11f);
            String[][] columnNames = {
                    {"ઇન્સ્ટ", "ના"},
                    {"ચુકવણી", "તારીખ"},
                    {"ઓ/એસ", "પ્રિન્સિપાલ", "(INR)"},
                    {"પ્રિન્સિપાલ", "(INR)"},
                    {"વ્યાજ", "(INR)"},
                    {"Inst", "રાકમ", "(INR)"},
                    {"ચુકવેલ", "સ્થિતિ"},
                    {"રકમ", "એકત્રિત", "(INR)"},
                    {"રકમ", "એકત્રિત", "ચાલુ"},
                    {"એમ્પ.", "કોડ"},
                    {"એમ્પ.સહી"}
            };

            float textY = y - rowHeight / 3;

            // Calculating the x offset for each column and add the text
            currentX = x;
            for (int k = 0; k < columnNames.length; k++) { // Change cols - 1 to columnNames.length
                float colWidth;
                if (k == 0) {
                    colWidth = firstColWidth;
                } else if (k == columnNames.length - 1) { // Change cols - 1 to columnNames.length - 1
                    colWidth = lastColWidth;
                } else {
                    colWidth = remainingColWidth;
                }

                float textX = currentX + 2;
                contentStream.newLineAtOffset(textX, textY);
                for (String line : columnNames[k]) {
                    contentStream.showText(line);
                    contentStream.newLineAtOffset(0, -10f);
                }

                contentStream.endText();
                contentStream.beginText();
                currentX += colWidth;
            }
            contentStream.endText();

            if (page == 1) {
                String[][] cellContent1 = {
                        {"1", "01-01-2022", "50000", "5000", "5000", "5000", "Paid", "5000", "01-01-2023", "1234", ""},
                        {"2", "01-02-2022", "45000", "5000", "4500", "4500", "Paid", "4500", "01-02-2023", "1234", ""},
                        {"3", "01-03-2022", "40000", "5000", "4000", "4000", "Paid", "4000", "01-03-2023", "1234", ""},
                        {"4", "01-04-2022", "35000", "5000", "3500", "3500", "Paid", "3500", "01-04-2023", "1234", ""},
                        {"5", "01-05-2022", "30000", "5000", "3000", "3000", "Paid", "3000", "01-05-2023", "1234", ""},
                        {"6", "01-06-2022", "25000", "5000", "2500", "2500", "Paid", "2500", "01-06-2023", "1234", ""},
                        {"7", "01-07-2022", "20000", "5000", "2000", "2000", "Paid", "2000", "01-07-2023", "1234", ""},
                        {"8", "01-08-2022", "15000", "5000", "1500", "1500", "Paid", "1500", "01-08-2023", "1234", ""},
                        {"9", "01-09-2022", "10000", "5000", "1000", "1000", "Paid", "1000", "01-09-2023", "1234", ""},


                };
                File fontFile = new File("C:\\Users\\Bhavani K\\Desktop\\Nirmala.ttf");
                PDType0Font font = PDType0Font.load(document, fontFile);
                float fontSize = 8f;
                for (int i = 0; i < cellContent1.length; i++) {
                    for (int j = 0; j < cellContent1[i].length; j++) {
                        float cellWidth = tableWidth / cols;
                        float textWidth = font.getStringWidth(cellContent1[i][j]) / 1000 * fontSize;
                        float text1 = x - 16 + j * cellWidth + (cellWidth - textWidth) / 20; // center alignment
                        float text2 = y - (i + 1) * rowHeight + rowHeight / 50;
                        contentStream.beginText();
                        contentStream.setFont(font, fontSize);
                        contentStream.newLineAtOffset(text1, text2 - 20);
                        if (j == 0 && i != 10) {
                            contentStream.newLineAtOffset(20, 0);
                            contentStream.showText(cellContent1[i][j]);
                        } else {
                            contentStream.showText(cellContent1[i][j]);
                        }
                        contentStream.endText();
                    }
                }
            }
            if (page == 2) {
                String[][] cellContent = {
                        {"10", "01-01-2022", "50000", "5000", "5000", "5000", "Paid", "5000", "01-01-2023", "1234", ""},
                        {"11", "01-02-2022", "45000", "5000", "4500", "4500", "Paid", "4500", "01-02-2023", "1234", ""},
                        {"12", "01-03-2022", "40000", "5000", "4000", "4000", "Paid", "4000", "01-03-2023", "1234", ""},
                        {"13", "01-04-2022", "35000", "5000", "3500", "3500", "Paid", "3500", "01-04-2023", "1234", ""},
                        {"14", "01-05-2022", "30000", "5000", "3000", "3000", "Paid", "3000", "01-05-2023", "1234", ""},
                        {"15", "01-06-2022", "25000", "5000", "2500", "2500", "Paid", "2500", "01-06-2023", "1234", ""},
                        { "","", "20000", "5000", "2000", "2000", "Paid", "2000", "01-07-2023", "1234", ""},
                };
                File fontFile = new File("C:\\Users\\Bhavani K\\Desktop\\Nirmala.ttf");
                PDType0Font font = PDType0Font.load(document, fontFile);
                float fontSize = 8f;
                for (int i = 0; i < cellContent.length; i++) {
                    for (int j = 0; j < cellContent[i].length; j++) {
                        float cellWidth = tableWidth / cols;
                        float textWidth = font.getStringWidth(cellContent[i][j]) / 1000 * fontSize;
                        float text1 = x - 16 + j * cellWidth + (cellWidth - textWidth) / 20; // center alignment
                        float text2 = y - (i + 1) * rowHeight + rowHeight / 50;
                        contentStream.beginText();
                        contentStream.setFont(font, fontSize);
                        contentStream.newLineAtOffset(text1, text2 - 20);
                        if (j == 0 && i != 10) {
                            contentStream.newLineAtOffset(20, 0);
                            contentStream.showText(cellContent[i][j]);
                        } else {
                            contentStream.showText(cellContent[i][j]);
                        }
                        contentStream.endText();

                    }
                }
            }
        }


        if (withFooter) {
            contentStream.beginText();
            contentStream.setLeading(11f);
            float textX = x + 5;
            float textY = y - tableHeight - rowHeight + 50;
            contentStream.newLineAtOffset(textX, textY);
            contentStream.showText("કુલ");
            contentStream.endText();
        }
    }

    public void savePDF(PDDocument document, String filePath) throws IOException {
        document.save(filePath);
    }
}
