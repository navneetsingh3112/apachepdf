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

    private PDPage createPage(PDDocument document) {
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        return page;
    }

    private void addJLGHeading(PDPageContentStream contentStream, float yOffset) throws IOException {
        String heading = "JLG/SHG/IL – LOAN CARD CUM FACT SHEET";
        PDFont font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
        float stringWidth = font.getStringWidth(heading) * 13 / 1000;
        float centerPosition = (PDRectangle.A4.getWidth() - stringWidth) / 2;
        contentStream.beginText();
        contentStream.setFont(font, 8);
        contentStream.newLineAtOffset(centerPosition, yOffset);
        contentStream.showText(heading);
        contentStream.endText();
    }

    public void createContent(PDDocument document, PDPage page) throws IOException {
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 8);

        // Adding image
        PDImageXObject pdImage = PDImageXObject.createFromFile("C:\\Users\\Bhavani K\\Downloads\\image (1).png", document);
        contentStream.drawImage(pdImage, 20, 810, 100, 20);

        // Adding JLG heading
        addJLGHeading(contentStream, PDRectangle.A4.getHeight() - 30);

        // Adding branch details
        contentStream.beginText();
        contentStream.setLeading(12f);
        contentStream.newLineAtOffset(20, 800);
        contentStream.showText("We understand your world");
        contentStream.newLineAtOffset(30, -20);
        String[] lines = {
                "Branch Code & Name: bhavani", "Branch Address:", "Regd.office:", "GST Regn:        CIN:      PAN:"
        };
        for (String line : lines) {
            contentStream.newLine();
            contentStream.showText(line);
        }
        contentStream.endText();

        // branch details
        float widthInPoints = 14 * 28.3465f;
        float heightInPoints = 3 * 28.3465f;
        contentStream.addRect(40, 700, widthInPoints, heightInPoints);
        contentStream.stroke();

        //customer photo
        float squareSizeInPointsWidth = 3 * 28.3465f;
        float squareSizeInPointsHeight = 4 * 28.3465f;
        float gap = -60;
        contentStream.addRect(90 + widthInPoints - 20, 700 - heightInPoints - gap, squareSizeInPointsWidth, squareSizeInPointsHeight);
        contentStream.stroke();

        contentStream.beginText();
        contentStream.newLineAtOffset(90 + widthInPoints - 10, 700 - gap - 10);
        contentStream.showText("Customer Photo");
        contentStream.endText();

        //  customer details
        float width = 15 * 28.3465f;
        float height = 7 * 29.3465f;
        float newBoxYPosition = 700 - heightInPoints - gap - height - 10;
        contentStream.addRect(40, newBoxYPosition, width + squareSizeInPointsWidth + 20, height);
        contentStream.stroke();

        float midPoint = (width + squareSizeInPointsWidth + 20) / 2;
        contentStream.moveTo(40 + midPoint, newBoxYPosition);
        contentStream.lineTo(40 + midPoint, newBoxYPosition + height);
        contentStream.stroke();

        contentStream.beginText();
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(50, newBoxYPosition + height);
        String[] line = {
                "Customer Name & ID:", "Loan Account No:", "Group Name:", "Product:", "Husband Name:", "Customer Address:", "",
                "Disbursed Date:", "Loan Amount (INR):", "Total Interest Charge (INR):", "Other Up-front charges (INR):",
                "Processing Fees (INR):", "Insurance Amount (INR):", "Others(if any) (INR):", "Net Disbursed Amount (INR):",
                "Total Amount to be Paid (INR):", "Annualized Int.Rate (%):", "Rate of Interest (%):"
        };
        for (String i : line) {
            contentStream.newLine();
            contentStream.showText(i);
        }
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(50 + midPoint + 10, newBoxYPosition + height);
        String[] additionalLines = {
                "Mobile No:", "Father Name:", "Co-Applicant Name(if applicable):", "Centre Place(Address):", "",
                "Loan Term (in Months):", "Repayment Frequency:", "No of Instalments of Repayment:", "Repayment Date:",
                "Repayment Amount (INR):", "Lending Type:", "Loan Cycle:", "Bank Acct No:", "Purpose:",
                "Beneficiary A/C Name:", "Bank Name:", "Netoff Account/Amount:"
        };
        for (String j : additionalLines) {
            contentStream.newLine();
            contentStream.showText(j);
        }
        contentStream.endText();
        createTable(contentStream, 40, newBoxYPosition - 5, 530, 400, 10, 12, true, false);
        contentStream.close();
    }

    public void createContentForSecondPage(PDDocument document, PDPage page) throws IOException {
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 8);

        // Adding JLG heading
        addJLGHeading(contentStream, PDRectangle.A4.getHeight() - 30);
        createTable(contentStream, 40, 800, 530, 300, 8, 12, false, true);

        float width = 17 * 28.3465f;
        float height = 6 * 28.3465f;
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
                "", "Total Processing Fee (INR):", "", "Stamp duty (INR) (including GST):", "",
                "Contingent Charges", "", "Prepayment of loan (if any):", "", "Penal Charges in case of delayed payments (if any):",
                "", "Other Charges (if any):", ""
        };
        for (String i : line) {
            contentStream.newLine();
            contentStream.showText(i);
        }
        contentStream.endText();

        contentStream.beginText();
        contentStream.setLeading(9f);
        contentStream.newLineAtOffset(300, newBoxYPosition + height - 20);
        String[] line1 = {
                "Name of the Insured:", "", "Name of Nominee:", "", "Relationship with borrower:", "", "Age of the Insured:",
                "", "Sum Assured(INR):", "", "Insurance Premium(INR):", "", "Insurance Start Date:", "", "Insurance End Date:", ""
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
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 8);
        contentStream.setLeading(11f);
        float textStartY = newBoxYPosition - 10;
        contentStream.newLineAtOffset(40, textStartY);
        contentStream.showText("Key terms & Conditions:");
        contentStream.endText();
        addTermsAndConditions(contentStream, 100, textStartY - 20);

        // Adding grievance redressal
        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 8);
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(40, 150);
        contentStream.showText("Grievance Redressal:");
        contentStream.newLineAtOffset(0, -10);
        contentStream.showText("For any grievance redressal you may please get in touch.");
        contentStream.endText();

        contentStream.addRect(40, 50, 530, 80);
        float midPoint1 = 40 + 530 / 2;
        contentStream.moveTo(midPoint1, 50);
        contentStream.lineTo(midPoint1, 50 + 80);

        //create row for grievance redressal
        float rowPosition1 = 40 / 2 + 50 + 80 / 2;
        contentStream.moveTo(40, rowPosition1);
        contentStream.lineTo(40 + width + 50, rowPosition1);
        contentStream.stroke();

        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 8);
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(50, 50 + 80 - 10);
        contentStream.showText("Nodal officer Name");
        contentStream.newLineAtOffset(260, 0);
        contentStream.showText("Nodal officer contact no");
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
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 8);
        contentStream.setLeading(12f);
        contentStream.newLineAtOffset(x, y);

        for (String term : termsAndConditions) {
            contentStream.showText(term);
            contentStream.newLine();
        }
        contentStream.endText();
    }


    private void createTable(PDPageContentStream contentStream, float x, float y, float tableWidth, float tableHeight, int rows, int cols, boolean withHeader, boolean withFooter) throws IOException {
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
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 8);
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
            for (int i = 0; i < cols - 1; i++) {
                float colWidth;
                if (i == 0) {
                    colWidth = firstColWidth;
                } else if (i == cols - 1) {
                    colWidth = lastColWidth;
                } else {
                    colWidth = remainingColWidth;
                }

                float textX = currentX + 2;
                contentStream.newLineAtOffset(textX, textY);
                for (String line : columnNames[i % columnNames.length]) {
                    contentStream.showText(line);
                    contentStream.newLineAtOffset(0, -10f);
                }

                contentStream.endText();
                contentStream.beginText();
                currentX += colWidth;
            }
            }
        if (withFooter) {
            contentStream.beginText();
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 8);
            contentStream.setLeading(11f);
            float textx = x+5;
            float texty = y - tableHeight-rowHeight+50;
            contentStream.newLineAtOffset(textx, texty);
            contentStream.showText("Total");
            contentStream.endText();
        }

    }

    public void savePDF(PDDocument document, String filePath) throws IOException {
        document.save(filePath);
    }
}
