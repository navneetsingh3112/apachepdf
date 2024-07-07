
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;

public class TamilDocument {

    public static void main(String[] args) throws IOException {
        TamilDocument documentCreation = new TamilDocument();

        PDDocument newDocument = new PDDocument();
        PDPage page1 = documentCreation.createPage(newDocument);
        PDPage page2 = documentCreation.createPage(newDocument);

        documentCreation.createContent(newDocument, page1);
        documentCreation.createContentForSecondPage(newDocument, page2);
        String outputFilePath = "c:/Users/Bhavani K/Desktop/TAMIL.pdf";
        documentCreation.savePDF(newDocument, outputFilePath);

        newDocument.close();
    }

    public PDPage createPage(PDDocument document) {
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        return page;
    }

    private void addJLGHeading(PDPageContentStream contentStream, float yOffset,PDDocument document) throws IOException {
        String heading = "JLG/SHG/IL - கடன் அட்டை மற்றும் உண்மை தாள்";
        File fontFile = new File("C:\\Users\\Bhavani K\\Desktop\\Lohit-Tamil.ttf");
        PDType0Font font = PDType0Font.load(document, fontFile);
        float stringWidth = font.getStringWidth(heading) * 15/ 1000;
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
        contentStream.newLineAtOffset(40, 793);
        contentStream.showText("we understand your world");
        contentStream.newLineAtOffset(10, -40);
        String[] lines = {
                "கிளைக் குறியீடு & பெயர்:",
                "கிளை விலாசம்:",
                "Regd.office: 6524726",
                "GST Regn: 7671671767715","CIN: 5276472547" ,
                "பான்: FETRET565"
        };
        for (String line : lines) {
            contentStream.newLine();
            contentStream.showText(line);
        }
        contentStream.endText();

        // branch details
        float widthInPoints = 14f * 28.3465f;
        float heightInPoints = 4.2f * 29.3465f;
        contentStream.addRect(40, 633, widthInPoints, heightInPoints);
        contentStream.stroke();

        //customer photo
        float photoWidth = 4.4f* 28.8465f;
        float photoHeight = 4.4f* 28.3465f;
        float gap = -60;
        contentStream.addRect(80 + widthInPoints - 35, 695 - heightInPoints-gap, photoWidth, photoHeight);
        contentStream.stroke();
        PDImageXObject Image = PDImageXObject.createFromFile("C:\\Users\\Bhavani K\\Downloads\\photo.jpg", document);
        contentStream.drawImage(Image, 80 + widthInPoints - 35, 695 - heightInPoints - gap, photoWidth, photoHeight);

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
                "வாடிக்கையாளர் பெயர் & ஐடி:",
                "கடன் கணக்கு எண்:",
                "குழு பெயர்:",
                "தயாரிப்பு:",
                "கணவர் பெயர்:",
                "வாடிக்கையாளர் முகவரி:",
                "விநியோகம் செய்யப்பட்ட தேதி:",
                "கடன் தொகை(INR):",
                "மொத்த வட்டி கட்டணம்(INR):",
                "மற்ற முன்கூட்டிய கட்டணங்கள்(INR):",
                "செயலாக்க கட்டணம்(INR):",
                "காப்பீட்டுத் தொகை(INR):",
                "மற்றவர்கள்(ஏதேனும் இருந்தால்)(INR):",
                "நிகரமாக வழங்கப்பட்ட தொகை(INR):",
                "செலுத்த வேண்டிய மொத்தத் தொகை(INR):",
                "வருடாந்திர வட்டி விகிதம்(%):",
                "வட்டி விகிதம்(%):"
        };
        for (String i : line) {
            contentStream.newLine();
            contentStream.showText(i);
        }
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(50 + midPoint + 10, newBoxYPosition + height);
        String[] additionalLines = {
                "மொபைல் எண்: 1234567890",
                "தந்தை பெயர்: ஜான் டோ சீனியர்.",
                "இணை விண்ணப்பதாரர் பெயர் (பொருந்தினால்): ",
                "    ஜேன் டோ",
                "சென்டர் பிளேஸ்(முகவரி): 1234 மெயின் செயின்ட்",
                "கடன் காலம் (மாதங்களில்): 12",
                "திரும்பச் செலுத்தும் அதிர்வெண்: மாதாந்திரம்",
                "திரும்பச் செலுத்தும் தவணைகளின் எண்ணிக்கை: 12",
                "திரும்பச் செலுத்தும் தேதி: 01-01-2023",
                "திரும்பச் செலுத்தும் தொகை (INR): 5000",
                "கடன் வகை: தனிநபர் கடன்",
                "கடன் சுழற்சி: 1",
                "வங்கி சட்டம் எண்: 123456789012",
                "நோக்கம்: வீடு புதுப்பித்தல்",
                "பயனாளி A/C பெயர்: ஜான் டோ",
                "வங்கி பெயர்: ஏபிசி வங்கி",
                "நெட்டாஃப் கணக்கு/தொகை: 123456789012"
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
        contentStream.showText("செயலாக்கக் கட்டணம் & முத்திரைக் கட்டணம்");
        contentStream.newLineAtOffset(250, 0);
        contentStream.showText("காப்பீட்டு விவரங்கள்:");
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(50, newBoxYPosition + height - 20);
        String[] line = {
                "மொத்த செயலாக்கக் கட்டணம் (INR): 1000",
                "", "முத்திரை வரி (INR) (ஜிஎஸ்டி உட்பட): 1000", "",
                "தற்செயலான கட்டணங்கள்: 500", "", "கடனை முன்கூட்டியே செலுத்துதல்",
                " (ஏதேனும் இருந்தால்):", "","தாமதமாக செலுத்தும் பட்சத்தில் அபராதக் கட்டணம்", "(ஏதேனும் இருந்தால்): 200",
                "", "பிற கட்டணங்கள் (ஏதேனும் இருந்தால்): 100", ""
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
                "காப்பீடு செய்தவரின் பெயர்: கரன்", "", "நாமினியின் பெயர்: கிரண்", "", "கடன் வாங்கியவருடனான உறவு: சகோதரர்", "", "காப்பீடு செய்தவரின் வயது: 30",
                "", "உறுதிப்படுத்தப்பட்ட தொகை(INR):100000", "", "காப்பீட்டு பிரீமியம்(INR): 20000", "", "காப்பீடு தொடங்கும் தேதி: 12/05/2024", "", "காப்பீடு முடிவுத் தேதி: 23 /4/2030", ""
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
        contentStream.showText("முக்கிய விதிமுறைகள் மற்றும் நிபந்தனைகள்:");
        contentStream.endText();
        addTermsAndConditions(contentStream, 70, textStartY -10);

        // Adding grievance redressal
        contentStream.beginText();
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(40, 130);
        contentStream.showText("குறை தீர்க்கும் முறை:");
        contentStream.newLineAtOffset(0, -10);
        contentStream.showText("ஏதேனும் குறை நிவர்த்திக்கு நீங்கள் தொடர்பு கொள்ளலாம்.");
        contentStream.endText();

        contentStream.addRect(40, 35, 530, 80);
        float midPoint1 = 40 + 530 / 2;
        contentStream.moveTo(midPoint1, 35);
        contentStream.lineTo(midPoint1, 115);

        //create row for grievance redressal
        float rowPosition1 =90;
        contentStream.moveTo(40, rowPosition1);
        contentStream.lineTo(40 + width + 50, rowPosition1);
        contentStream.stroke();

        contentStream.beginText();
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(50, 95);
        contentStream.showText("நோடல் அதிகாரி பெயர்");
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText("அருண்");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(320, 95);
        contentStream.showText("நோடல் அதிகாரி தொடர்பு எண்");
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText("1234567890");
        contentStream.endText();

        contentStream.stroke();

        contentStream.close();

    }


    private void addTermsAndConditions(PDPageContentStream contentStream, float x, float y) throws IOException {
        String[] termsAndConditions = {
                "1. வழக்கமான குழு கூட்டத்தை நடத்துதல்", "2. உறுப்பினர் வருகை மற்றும் பங்கேற்பு 100% இருக்க வேண்டும்",
                "3. வழக்கமான மாதாந்திர சேமிப்பு இருக்க வேண்டும்.", "4. அனைத்து சேமிப்புகளும் உள் கடன்களாக மாற்றப்பட வேண்டும் மற்றும் சிறந்த நிதிகள் ","எதுவும் இருக்கக்கூடாது.",
                "5. உள் மற்றும் வங்கிக் கடன் ஆகிய இரண்டும் கடனைத் திருப்பிச் செலுத்துதல் 100% மற்றும் சரியான", "நேரத்தில் இருக்க வேண்டும்.", "6. வழக்கமான புதுப்பித்தலுடன் புத்தகம் மற்றும் பதிவுகளை முறையாகப் பராமரித்தல்.",
                "7. குழு தவறாத / ஒழுங்கற்ற உறுப்பினர்கள் மீது அழுத்தம் கொடுக்க வேண்டும்.", "8. நல்ல தலைமை, உற்சாகமான மற்றும் ஒழுக்கமான உறுப்பினர்களின் நல்ல குழு உணர்வு.",
                "9. அனைத்து உறுப்பினர்களிடையே ஒத்துழைப்பு மற்றும் ஒற்றுமை.", "10. ஊழியர்களின் நடத்தைக்கு வங்கி பொறுப்பு."
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
                    {"Inst", "no"},
                    {"திருப்பிச்","செலுத்தும்", "தேதி"},
                    {"O/S", "முதன்மை", "(INR)"},
                    {"முதன்மை", "(INR)"},
                    {"வட்டி", "(INR)"},
                    {"நிதி", "தொகை", "(INR)"},
                    {"பணம்", "நிலை"},
                    {"தொகை", "சேகரிப்பு", "(INR)"},
                    {"தொகை", "சேகரிப்பு", "ஆன்"},
                    {"Emp.", "குறியீடு"},
                    {"Emp.கையெழுத்து"}

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
                        float text1 = x - 13 + j * cellWidth + (cellWidth - textWidth) / 20; // center alignment
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
                        float text1 = x - 13 + j * cellWidth + (cellWidth - textWidth) / 20; // center alignment
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
            contentStream.showText("மொத்தம்");
            contentStream.endText();
        }
    }

    public void savePDF(PDDocument document, String filePath) throws IOException {
        document.save(filePath);
    }
}
