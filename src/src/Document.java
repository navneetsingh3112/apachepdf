import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;

public class Document  {

    public static void main(String[] args) throws IOException {
        Document documentCreation = new Document();

        PDDocument newDocument = new PDDocument();
        PDPage page1= documentCreation.createPage(newDocument);
        PDPage page2= documentCreation.createPage(newDocument);

        documentCreation.createContent(newDocument, page1);
        documentCreation.createContentForSecondPage(newDocument, page2);
        String outputFilePath = "c:/Users/Bhavani K/Desktop/Hindi.pdf";
        documentCreation.savePDF(newDocument, outputFilePath);

        newDocument.close();
    }

    private PDPage createPage(PDDocument document) throws IOException {
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        return page;
    }

    private void addJLGHeading(PDPageContentStream contentStream, float yOffset,PDDocument document) throws IOException {
        String heading = "जेएलजी/एसएचजी/आईएल – ऋण कार्ड सह तथ्य पत्रक";
        File fontFile = new File("C:\\Users\\Bhavani K\\Desktop\\Hind-Regular.ttf");
        PDType0Font font = PDType0Font.load(document, fontFile);
        float stringWidth = font.getStringWidth(heading) * 18/ 1000;
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
        contentStream.showText("हम आपकी दुनिया को समझते हैं");
        contentStream.newLineAtOffset(10, -40);
        String[] lines = {
                "शाखा कोड और नाम: शालिनी", "शाखा पता: बेलंदूर, बैंगलोर", "पंजीकृत कार्यालय: 6524726", "जीएसटी पंजीकरण: 7671671767715", "सीआईएन: 5276472547",
                "पैन: FETRET565"
        };
        for (String line : lines) {
            contentStream.newLine();
            contentStream.showText(line);
        }
        contentStream.endText();

        // branch details
        float widthInPoints = 14f * 28.3465f;
        float heightInPoints = 4f * 29.3465f;
        contentStream.addRect(40, 643, widthInPoints, heightInPoints);
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
        float height = 7* 29.3465f;
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
                "ग्राहक का नाम और आईडी: जॉन डो - 1234",
                "ऋण खाता संख्या: 5678901234",
                "समूह का नाम: अल्फाग्रुप",
                "उत्पाद: अल्फालोन",
                "पति का नाम: रिचर्ड रो",
                "ग्राहक का पता: 1234 मेन सेंट, एनीटाउन, एनीस्टेट",
                "",
                "वितरित तिथि: 01-01-2022",
                "ऋण राशि (INR): 50000",
                "कुल ब्याज शुल्क (INR): 5000",
                "अन्य अग्रिम शुल्क (INR): 1000",
                "प्रसंस्करण शुल्क (INR): 200",
                "बीमा राशि (INR): 300",
                "अन्य (यदि कोई हो) (INR): 100",
                "शुद्ध वितरित राशि (INR): 55000",
                "कुल राशि भुगतान किया जाएगा (INR): 60000",
                "वार्षिक ब्याज दर (%): 10",
                "ब्याज दर (%): 10"
        };
        for (String i : line) {
            contentStream.newLine();
            contentStream.showText(i);
        }
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(50 + midPoint + 10, newBoxYPosition + height);
        String[] additionalLines = {
                "मोबाइल नंबर: 1234567890",
                "पिता का नाम: जॉन डो सीनियर",
                "सह-आवेदक का नाम (यदि लागू हो): जेन डो",
                "सेंटर प्लेस (पता): 1234 मेन सेंट, एनीटाउन, एनीस्टेट",
                "",
                "ऋण अवधि (महीनों में): 12",
                "पुनर्भुगतान आवृत्ति: मासिक",
                "पुनर्भुगतान की किस्तों की संख्या: 12",
                "पुनर्भुगतान तिथि: 01-01-2023",
                "पुनर्भुगतान राशि (INR): 5000",
                "ऋण प्रकार: व्यक्तिगत ऋण",
                "ऋण चक्र: 1",
                "बैंक खाता संख्या: 123456789012",
                "उद्देश्य: गृह नवीनीकरण",
                "लाभार्थी खाता नाम: जॉन डो",
                "बैंक का नाम: ABC बैंक",
                "नेटऑफ खाता/राशि: 123456789012"
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
        contentStream.newLineAtOffset(50, newBoxYPosition + height -10);
        contentStream.showText("प्रसंस्करण शुल्क और स्टाम्प ड्यूटी शुल्क");
        contentStream.newLineAtOffset(250, 0);
        contentStream.showText("बीमा विवरण:");
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(50, newBoxYPosition + height - 20);
        String[] line = {
                "कुल प्रसंस्करण शुल्क (INR): 1000",
                "", "स्टाम्प ड्यूटी (INR) (जीएसटी सहित): 1000", "",
                "आकस्मिक शुल्क: 500", "", "ऋण का पूर्व भुगतान (यदि कोई हो):", "", "देरी से भुगतान के मामले में दंडात्मक शुल्क (यदि कोई हो): 200",
                "", "अन्य शुल्क (यदि कोई हो): 100", ""
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
                "बीमाधारक का नाम: करण", "", "नामांकित व्यक्ति का नाम: किरण", "", "उधारकर्ता के साथ संबंध: भाई", "", "बीमाधारक की आयु: 30",
                "", "बीमा राशि (INR): 100000", "", "बीमा प्रीमियम (INR): 20000", "", "बीमा आरंभ तिथि: 12/05/2024", "", "बीमा समाप्ति तिथि: 23/4/2030", ""
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
        float textStartY = newBoxYPosition - 10;
        contentStream.newLineAtOffset(40, textStartY);
        contentStream.showText("मुख्य नियम व शर्तें:");
        contentStream.endText();
        addTermsAndConditions(contentStream, 70, textStartY - 10);

        // Adding grievance redressal
        contentStream.beginText();
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(40, 130);
        contentStream.showText("शिकायत निवारण:");
        contentStream.newLineAtOffset(0, -10);
        contentStream.showText("किसी भी शिकायत निवारण के लिए आप कृपया संपर्क कर सकते हैं।");
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
        contentStream.showText("नोडल अधिकारी का नाम");
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText("अरुण");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(320, 120);
        contentStream.showText("नोडल अधिकारी का संपर्क नंबर");
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText("1234567890");
        contentStream.endText();

        contentStream.stroke();

        contentStream.close();

    }


    private void addTermsAndConditions(PDPageContentStream contentStream, float x, float y) throws IOException {
        String[] termsAndConditions = {
                "1. नियमित समूह बैठक आयोजित करना", "2. सदस्यों की उपस्थिति और भागीदारी 100% होनी चाहिए", "3. नियमित मासिक बचत होनी चाहिए।", "4. सभी बचत को आंतरिक ऋण के रूप में परिवर्तित किया जाना चाहिए और कोई आदर्श निधि नहीं होनी चाहिए।",
                "5. आंतरिक और बैंक ऋण दोनों का ऋण चुकौती 100% और समय पर होनी चाहिए।", "6. नियमित अद्यतन के साथ पुस्तक और अभिलेखों का उचित रखरखाव।", "7. समूह को चूककर्ता/अनियमित सदस्यों पर दबाव डालना चाहिए।",
                "8. अच्छा नेतृत्व, उत्साही और अनुशासित सदस्य और अच्छी टीम भावना।", "9. सभी सदस्यों के बीच सहयोग और एकता।", "10. बैंक कर्मचारियों के आचरण के लिए जिम्मेदार है।"
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


    private void createTable(PDPageContentStream contentStream, float x, float y, float tableWidth, float tableHeight, int rows, int cols, boolean withHeader, boolean withFooter,int page, PDDocument document) throws IOException {
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
                    {"इंस्ट", "नंबर"},
                    {"पुनर्भुगतान", "तारीख"},
                    {"ओ/एस", "मूलधन", "(आईएनआर)"},
                    {"मूलधन", "(आईएनआर)"},
                    {"ब्याज", "(आईएनआर)"},
                    {"इंस्ट", "राशि", "(आईएनआर)"},
                    {"भुगतान", "स्थिति"},
                    {"राशि", "एकत्रित", "(आईएनआर)"},
                    {"राशि", "एकत्रित", "चालू"},
                    {"कर्मचारी.", "कोड"},
                    {"कर्मचारी.हस्ताक्षर"}
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
                File fontFile = new File("C:\\Users\\Bhavani K\\Desktop\\Hind-Regular.ttf");
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
                File fontFile = new File("C:\\Users\\Bhavani K\\Desktop\\Hind-Regular.ttf");
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
            contentStream.showText("कुल");
            contentStream.endText();
        }
    }
    //
    public void savePDF(PDDocument document, String filePath) throws IOException {
        document.save(filePath);
    }
}
