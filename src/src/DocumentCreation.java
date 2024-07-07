import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;

public class DocumentCreation {

    public static void main(String[] args) throws IOException {
        DocumentCreation documentCreation = new DocumentCreation();

        PDDocument newDocument = new PDDocument();
        PDPage page1 = documentCreation.createPage(newDocument);
        PDPage page2 = documentCreation.createPage(newDocument);

        documentCreation.createContent(newDocument, page1);
        documentCreation.createContentForSecondPage(newDocument, page2);
        String outputFilePath = "c:/Users/Bhavani K/Desktop/English.pdf";
        documentCreation.savePDF(newDocument, outputFilePath);

        newDocument.close();
    }

    public PDPage createPage(PDDocument document) {
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        return page;
    }

    private void addJLGHeading(PDPageContentStream contentStream, float yOffset) throws IOException {
        String heading = "JLG/SHG/IL – LOAN CARD CUM FACT SHEET";
        PDFont font = new PDType1Font(Standard14Fonts.FontName.TIMES_BOLD);
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
        addJLGHeading(contentStream, PDRectangle.A4.getHeight() - 30);

        // Adding branch details
        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 8);
        contentStream.setLeading(13f);
        contentStream.newLineAtOffset(40, 793);
        contentStream.showText("We understand your world");
        contentStream.newLineAtOffset(10, -40);
        String[] lines = {
                "Branch Code & Name: Shalini", "Branch Address: Bellandur,Banglore", "Regd.office: 6524726", "GST Regn: 7671671767715","CIN: 5276472547" ,
                "PAN: FETRET565"
        };
        for (String line : lines) {
            contentStream.newLine();
            contentStream.showText(line);
        }
        contentStream.endText();

        // branch details
        float widthInPoints = 14f * 28.3465f;
        float heightInPoints = 4 * 29.3465f;
        contentStream.addRect(40, 643, widthInPoints, heightInPoints);
        contentStream.stroke();

        //customer photo
        float photoWidth = 4.2f* 28.8465f;
        float photoHeight = 4.2f* 28.3465f;
        float gap = -60;
        contentStream.addRect(80 + widthInPoints - 30, 700 - heightInPoints-gap, photoWidth, photoHeight);
        contentStream.stroke();
        PDImageXObject Image = PDImageXObject.createFromFile("C:\\Users\\Bhavani K\\Downloads\\photo.jpg", document);
        contentStream.drawImage(Image, 80 + widthInPoints - 30, 700 - heightInPoints - gap, photoWidth, photoHeight);

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
                "Customer Name & ID: John Doe - 1234",
                "Loan Account No: 5678901234",
                "Group Name: AlphaGroup",
                "Product: AlphaLoan",
                "Husband Name: Richard Roe",
                "Customer Address: 1234 Main St, Anytown, Anystate",
                "",
                "Disbursed Date: 01-01-2022",
                "Loan Amount (INR): 50000",
                "Total Interest Charge (INR): 5000",
                "Other Up-front charges (INR): 1000",
                "Processing Fees (INR): 200",
                "Insurance Amount (INR): 300",
                "Others(if any) (INR): 100",
                "Net Disbursed Amount (INR): 55000",
                "Total Amount to be Paid (INR): 60000",
                "Annualized Int.Rate (%): 10",
                "Rate of Interest (%): 10"
        };
        for (String i : line) {
            contentStream.newLine();
            contentStream.showText(i);
        }
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(50 + midPoint + 10, newBoxYPosition + height);
        String[] additionalLines = {
    "Mobile No: 1234567890",
    "Father Name: John Doe Sr.",
    "Co-Applicant Name(if applicable): Jane Doe",
    "Centre Place(Address): 1234 Main St, Anytown, Anystate",
    "",
    "Loan Term (in Months): 12",
    "Repayment Frequency: Monthly",
    "No of Instalments of Repayment: 12",
    "Repayment Date: 01-01-2023",
    "Repayment Amount (INR): 5000",
    "Lending Type: Personal Loan",
    "Loan Cycle: 1",
    "Bank Acct No: 123456789012",
    "Purpose: Home Renovation",
    "Beneficiary A/C Name: John Doe",
    "Bank Name: ABC Bank",
    "Netoff Account/Amount: 123456789012"
};
        for (String j : additionalLines) {
            contentStream.newLine();
            contentStream.showText(j);
        }
        contentStream.endText();
        createTable(contentStream, 40, newBoxYPosition - 5, 530, 400, 10, 12, true, false,1);
        contentStream.close();
    }

    public void createContentForSecondPage(PDDocument document, PDPage page) throws IOException {
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 8);

        // Adding JLG heading
        addJLGHeading(contentStream, PDRectangle.A4.getHeight() - 30);
        createTable(contentStream, 40, 800, 530, 300, 8, 12, true, true,2);

        float width = 17 * 28.3465f;
        float height = 6.5f* 28.3465f;
        float gap = 210;
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
        contentStream.showText("Processing Fee & Stamp Duty charges");
        contentStream.newLineAtOffset(250, 0);
        contentStream.showText("Insurance Details:");
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(50, newBoxYPosition + height - 20);
        String[] line = {
                 "Total Processing Fee (INR): 1000",
                    "", "Stamp duty (INR) (including GST): 1000", "",
                "Contingent Charges: 500", "", "Prepayment of loan (if any):", "", "Penal Charges in case of delayed payments (if any): 200",
                "", "Other Charges (if any): 100", ""
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
                "Name of the Insured: Karan", "", "Name of Nominee: Kiran", "", "Relationship with borrower: Brother", "", "Age of the Insured: 30",
                "", "Sum Assured(INR):100000", "", "Insurance Premium(INR): 20000", "", "Insurance Start Date: 12/05/2024", "", "Insurance End Date: 23/4/2030", ""
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
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 8);
        contentStream.setLeading(11f);
        float textStartY = newBoxYPosition - 10;
        contentStream.newLineAtOffset(40, textStartY);
        contentStream.showText("Key terms & Conditions:");
        contentStream.endText();
        addTermsAndConditions(contentStream, 70, textStartY - 10);

        // Adding grievance redressal
        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 8);
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(40, 150);
        contentStream.showText("Grievance Redressal:");
        contentStream.newLineAtOffset(0, -10);
        contentStream.showText("For any grievance redressal you may please get in touch.");
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
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 8);
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(50, 120);
        contentStream.showText("Nodal officer Name");
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText("Arun");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 8);
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(320, 120);
        contentStream.showText("Nodal officer contact no");
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText("1234567890");
        contentStream.endText();

        contentStream.stroke();

        contentStream.close();

    }


    private void addTermsAndConditions(PDPageContentStream contentStream, float x, float y) throws IOException {
        String[] termsAndConditions = {
                "1. Conducting regular group meeting", "2. Member’s attendance and participation should be 100 %",
                "3. Regular monthly savings should be there.", "4. All savings should be converted as Internal Loans and no ideal funds should be there.",
                "5. Loan Repayment both internal & bank loan should be 100 % and on time.", "6. Proper maintenance of book and records with regular updation.",
                "7. Group should exercise pressure on defaulting / irregular members.", "8. Good leadership, enthusiastic and disciplined member’s with good team spirit.",
                "9. Co-operation and unity among all the members.", "10. Bank is responsible for the conduct of the employees."
        };

        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 8);
        contentStream.setLeading(12f);
        contentStream.newLineAtOffset(x, y);

        for (String term : termsAndConditions) {
            contentStream.showText(term);
            contentStream.newLine();
        }
        contentStream.endText();
    }


    private void createTable(PDPageContentStream contentStream, float x, float y, float tableWidth, float tableHeight, int rows, int cols, boolean withHeader, boolean withFooter,int page) throws IOException {
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
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 8);
            contentStream.setLeading(11f);
            String[][] columnNames = {
                    {"Inst", "no"},
                    {"Repayment", "Date"},
                    {"O/S", "Principal", "(INR)"},
                    {"Principal", "(INR)"},
                    {"Interest", "(INR)"},
                    {"Inst", "Amount", "(INR)"},
                    {"Paid", "Status"},
                    {"Amount", "Collected", "(INR)"},
                    {"Amount", "Collected", "on"},
                    {"Emp.", "Code"},
                    {"Emp.Signature"}
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
                PDFont font = new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN);
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
                PDFont font = new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN);
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
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 8);
                    contentStream.setLeading(11f);
                    float textX = x + 5;
                    float textY = y - tableHeight - rowHeight + 50;
                    contentStream.newLineAtOffset(textX, textY);
                    contentStream.showText("Total");
                    contentStream.endText();
                }
        }

    public void savePDF(PDDocument document, String filePath) throws IOException {
        document.save(filePath);
    }
}
