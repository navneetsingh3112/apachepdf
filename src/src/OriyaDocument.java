import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;

public class OriyaDocument{

    public static void main(String[] args) throws IOException {
        OriyaDocument documentCreation = new OriyaDocument();

        PDDocument newDocument = new PDDocument();
        PDPage page1 = documentCreation.createPage(newDocument);
        PDPage page2 = documentCreation.createPage(newDocument);

        documentCreation.createContent(newDocument, page1);
        documentCreation.createContentForSecondPage(newDocument, page2);
        String outputFilePath = "c:/Users/Bhavani K/Desktop/Oriya.pdf";
        documentCreation.savePDF(newDocument, outputFilePath);

        newDocument.close();
    }

    public PDPage createPage(PDDocument document) {
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        return page;
    }

    private void addJLGHeading(PDPageContentStream contentStream, float yOffset,PDDocument document) throws IOException {
        String heading = "JLG/SHG/IL - an ଣ କାର୍ଡ କମ ଫ୍ୟାକ୍ଟ ସିଟ୍ ";
        File fontFile = new File("C:\\Users\\Bhavani K\\Desktop\\Nirmala.ttf");
        PDType0Font font = PDType0Font.load(document, fontFile);
        float stringWidth = font.getStringWidth(heading) * 25/ 1000;
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
                "ଶାଖା କୋଡ୍ ଏବଂ ନାମ: ଶାଲିନି", "ଶାଖା ଠିକଣା: ବେଲାଣ୍ଡୁର, ବାଙ୍ଗଲୋର", "ରେଗଡ ଅଫିସ୍: 6524726", "ଜିଏସ୍ଟି ରେଗନ୍: 7671671767715", "CIN: 5276472547",
                "ପାନ୍: FETRET565"
        };
        for (String line : lines) {
            contentStream.newLine();
            contentStream.showText(line);
        }
        contentStream.endText();

        // branch details
        float widthInPoints = 14f * 28.3465f;
        float heightInPoints = 4.2f * 29.3465f;
        contentStream.addRect(40, 632, widthInPoints, heightInPoints);
        contentStream.stroke();

        //customer photo
        float photoWidth = 4.4f* 28.8465f;
        float photoHeight = 4.4f* 28.3465f;
        float gap = -60;
        contentStream.addRect(45 + widthInPoints , 695 - heightInPoints-gap, photoWidth, photoHeight);
        contentStream.stroke();
        PDImageXObject Image = PDImageXObject.createFromFile("C:\\Users\\Bhavani K\\Downloads\\photo.jpg", document);
        contentStream.drawImage(Image, 45 + widthInPoints , 695 - heightInPoints - gap, photoWidth, photoHeight);

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
                "ଗ୍ରାହକଙ୍କ ନାମ ଏବଂ ID: ଜନ୍ ଡୋ - 1234",
                "ଖାତା ଣ ଖାତା ନଂ: 5678901234",
                "ଗୋଷ୍ଠୀ ନାମ: ଆଲଫା ଗ୍ରୁପ୍",
                "ଉତ୍ପାଦ: ଆଲଫା ଲୋନ୍",
                "ସ୍ୱାମୀ ନାମ: ରିଚାର୍ଡ ରୋ",
                "ଗ୍ରାହକ ଠିକଣା: 1234 ମୁଖ୍ୟ ଷ୍ଟ, ଜୟନଗର",
                "",
                "ବିତରଣ ତାରିଖ: 01-01-2022",
                "ଋଣ ଣ ପରିମାଣ (INR): 50000",
                "ମୋଟ ସୁଧ ଦେୟ (INR): 5000",
                "ଅନ୍ୟାନ୍ୟ ଅପ୍-ଫ୍ରଣ୍ଟ୍ ଚାର୍ଜ (INR): 1000",
                "ପ୍ରକ୍ରିୟାକରଣ ଦେୟ (INR): 200",
                "ବୀମା ରାଶି (INR): 300",
                "ଅନ୍ୟମାନେ (ଯଦି ଥାଏ) (INR): 100",
                "ନିଟ୍ ବିତରଣ ପରିମାଣ (INR): 55000",
                "ଦେୟ ଦେବାକୁ ଥିବା ସମୁଦାୟ ରାଶି (INR): 60000",
                "ବାର୍ଷିକ Int.Rate (%): 10",
                "ସୁଧ ହାର (%): 10"
        };
        for (String i : line) {
            contentStream.newLine();
            contentStream.showText(i);
        }
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(50 + midPoint + 10, newBoxYPosition + height);
        String[] additionalLines = {
                "ମୋବାଇଲ୍ ନଂ: 1234567890",
                "ପିତାଙ୍କ ନାମ: ଜନ୍ ଡୋ ଶ୍ରୀ।",
                "ସହ-ଆବେଦନକାରୀଙ୍କ ନାମ (ଯଦି ପ୍ରଯୁଜ୍ୟ): ଜେନ ଡୋ",
                "କେନ୍ଦ୍ର ସ୍ଥାନ (ଠିକଣା): 1234 ମେନ୍ ଷ୍ଟ, ଆନିଟାଉନ୍, ଆନ୍ଷ୍ଟେଟ୍",
                "",
                "ଅବଧି ଣ ଅବଧି (ମାସରେ): 12",
                "ପରିଶୋଧ ଫ୍ରିକ୍ୱେନ୍ସି: ମାସିକ",
                "ପରିଶୋଧର କିସ୍ତି ସଂଖ୍ୟା: 12",
                "ପରିଶୋଧ ତାରିଖ: 01-01-2023",
                "ପରିଶୋଧ ପରିମାଣ (INR): 5000",
                "Ing ଣ ପ୍ରକାର: ବ୍ୟକ୍ତିଗତ an ଣ",
                "Cy ଣ ଚକ୍ର: 1",
                "ବ୍ୟାଙ୍କ ଆକ୍ଟ ନଂ: 123456789012",
                "ଉଦ୍ଦେଶ୍ୟ: ଗୃହ ନବୀକରଣ",
                "ହିତାଧିକାରୀ A / C ନାମ: ଜନ୍ ଡୋ",
                "ବ୍ୟାଙ୍କ ନାମ: ଏବିସି ବ୍ୟାଙ୍କ",
                "ନେଟଫ୍ ଆକାଉଣ୍ଟ / ପରିମାଣ: 123456789012"
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
        contentStream.showText("ଫି ଏବଂ ଷ୍ଟାମ୍ପ ଡ୍ୟୁଟି ଚାର୍ଜ ପ୍ରକ୍ରିୟାକରଣ |");
        contentStream.newLineAtOffset(250, 0);
        contentStream.showText("ବୀମା ବିବରଣୀ:");
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(50, newBoxYPosition + height - 20);
        String[] line = {
                "ମୋଟ ପ୍ରକ୍ରିୟାକରଣ ଦେୟ (INR): 1000",
                "", "ଷ୍ଟାମ୍ପ ଡ୍ୟୁଟି (INR) (ଜିଏସ୍ଟି ଅନ୍ତର୍ଭୁକ୍ତ): 1000", "",
                "କଣ୍ଟିଜେଣ୍ଟ୍ ଚାର୍ଜ: 500", "", "loan ଣର ଦେୟ (ଯଦି ଥାଏ):", "", "ବିଳମ୍ବିତ ଦେୟ ମାମଲାରେ ଦଣ୍ଡବିଧାନ ଚାର୍ଜ (ଯଦି ଥାଏ): 200",
                "", "ଅନ୍ୟ ଚାର୍ଜଗୁଡିକ (ଯଦି ଥାଏ): 100", ""
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
                "ବୀମାଭୁକ୍ତ ବ୍ୟକ୍ତିଙ୍କ ନାମ: କରଣ", "", "ନାମାଙ୍କନ ନାମ: କିରଣ", "", "orr ଣ ନେଇଥିବା ବ୍ୟକ୍ତିଙ୍କ ସହ ସମ୍ପର୍କ: ଭାଇ", "", "ବୀମାଭୁକ୍ତ ବ୍ୟକ୍ତିଙ୍କ ବୟସ: 30",
                "", "ରାଶି ନିଶ୍ଚିତ (INR): 100000", "", "ବୀମା ପ୍ରିମିୟମ୍ (INR): 20000", "", "ବୀମା ଆରମ୍ଭ ତାରିଖ: 12/05/2024", "", "ବୀମା ଶେଷ ତାରିଖ: 23 / 4/2030 "," "
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
        contentStream.showText("ମୁଖ୍ୟ ସର୍ତ୍ତାବଳୀ ଏବଂ ସର୍ତ୍ତଗୁଡିକ:");
        contentStream.endText();
        addTermsAndConditions(contentStream, 70, textStartY - 10);

        // Adding grievance redressal
        contentStream.beginText();
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(40, 150);
        contentStream.showText("ଅଭିଯୋଗ ସମାଧାନ:");
        contentStream.newLineAtOffset(0, -10);
        contentStream.showText("ଯେକ any ଣସି ଅଭିଯୋଗର ସମାଧାନ ପାଇଁ ଆପଣ ଦୟାକରି ଯୋଗାଯୋଗ କରିପାରିବେ |");
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
        contentStream.showText("ନୋଡାଲ୍ ଅଫିସର ନାମ |");
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText("ଅରୁଣ |");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(320, 120);
        contentStream.showText("ନୋଡାଲ ଅଧିକାରୀ ଯୋଗାଯୋଗ ନମ୍ବର |");
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText("1234567890");
        contentStream.endText();

        contentStream.stroke();

        contentStream.close();

    }


    private void addTermsAndConditions(PDPageContentStream contentStream, float x, float y) throws IOException {
        String[] termsAndConditions = {
                "1. ନିୟମିତ ଗୋଷ୍ଠୀ ବ ସଭା ଠକ କରିବା", "2. ସଦସ୍ୟଙ୍କ ଉପସ୍ଥାନ ଏବଂ ଅଂଶଗ୍ରହଣ 100% ହେବା ଉଚିତ",
                "3. ନିୟମିତ ମାସିକ ସଞ୍ଚୟ ସେଠାରେ ରହିବା ଉଚିତ୍।", "4. ସମସ୍ତ ସଞ୍ଚୟକୁ ଆଭ୍ୟନ୍ତରୀଣ ans ଣ ଭାବରେ ରୂପାନ୍ତର କରାଯିବା ଉଚିତ ଏବଂ ସେଠାରେ କ ideal ଣସି ଆଦର୍ଶ ପାଣ୍ଠି ରହିବା ଉଚିତ୍ ନୁହେଁ।",
                "5. ଆଭ୍ୟନ୍ତରୀଣ ଣ ପରିଶୋଧ ଉଭୟ ଆଭ୍ୟନ୍ତରୀଣ ଏବଂ ବ୍ୟାଙ୍କ ଋଣ ଣ 100% ଏବଂ ଠିକ୍ ସମୟରେ ହେବା ଉଚିତ୍।", "6. ନିୟମିତ ଅଦ୍ୟତନ ସହିତ ପୁସ୍ତକ ଏବଂ ରେକର୍ଡଗୁଡିକର ସଠିକ୍ ରକ୍ଷଣାବେକ୍ଷଣ।",
                "7. ଗୋଷ୍ଠୀ ଡିଫଲ୍ଟ / ଅନିୟମିତ ସଦସ୍ୟଙ୍କ ଉପରେ ଚାପ ପ୍ରୟୋଗ କରିବା ଉଚିତ୍।", "8.ଉତ୍ତମ ନେତୃତ୍ୱ, ଉତ୍ସାହୀ ଏବଂ ଶୃଙ୍ଖଳିତ ସଦସ୍ୟଙ୍କ ଉତ୍ତମ ଦଳ ଆତ୍ମା \u200B\u200Bସହିତ |",
                "9. ସମସ୍ତ ସଦସ୍ୟଙ୍କ ମଧ୍ୟରେ ସହଯୋଗ ଏବଂ ଏକତା।", "10. କର୍ମଚାରୀଙ୍କ ଆଚରଣ ପାଇଁ ବ୍ୟାଙ୍କ ଦାୟୀ ଅଟେ।"
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
                    {"Inst", "ଇନଷ୍ଟ", "ନା"},
                    {"ପରିଶୋଧ", "ତାରିଖ"},
                    {"O/S", "ପ୍ରିନ୍ସିପାଲ୍", "(IN)"},
                    {"ପ୍ରିନ୍ସିପାଲ୍", "(INR)"},
                    {"ଆଗ୍ରହ", "ଆଗ୍ରହ", "(INR)"},
                    {"କିସ୍ତି ","ପରିମାଣ ","(INR)"},
                    {"ଦେୟ", "ସ୍ଥିତି"},
                    {"ପରିମାଣ", "ସଂଗୃହିତ", "(INR)"},
                    {"ପରିମାଣ", "ସଂଗୃହିତ", "ଉପରେ"},
                    {"ଏମ୍", "କୋଡ୍"},
                    {"ଏମ୍.ଦସ୍ତଖତ"}
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
            contentStream.showText("ସମୁଦାୟ");
            contentStream.endText();
        }
    }

    public void savePDF(PDDocument document, String filePath) throws IOException {
        document.save(filePath);
    }
}
