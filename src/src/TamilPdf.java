import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

public class TamilPdf {

    public static void main(String[] args) throws IOException {
        TamilPdf documentCreation = new TamilPdf();

        PDDocument newDocument = new PDDocument();
        PDPage page1 = documentCreation.createPage(newDocument);
        PDPage page2 = documentCreation.createPage(newDocument);

        documentCreation.createContent(newDocument, page1);
        documentCreation.createContentForSecondPage(newDocument, page2);
        String outputFilePath = "templates/files/tamil.pdf";
        documentCreation.savePDF(newDocument, outputFilePath);

        newDocument.close();
    }

    public PDPage createPage(PDDocument document) {
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        return page;
    }

    private void addJLGHeading(PDPageContentStream contentStream, float yOffset,PDDocument document) throws IOException {
        File fontFile = new File("C:\\Users\\Bhavani K\\downloads\\NotoSansTamil-Medium.ttf");
        PDType0Font font = PDType0Font.load(document, fontFile);
        String file="templates/tamil.json";
        String heading = readLabelFromJson(file, "label_jlg_heading");
        float stringWidth = font.getStringWidth(heading) * 18/ 1000;
        float centerPosition = stringWidth /2;
        contentStream.beginText();
        contentStream.setFont(font, 8);
        contentStream.newLineAtOffset(centerPosition, yOffset);
        contentStream.showText(heading);
        contentStream.endText();
    }

    public String readLabelFromJson(String filePath, String key) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
        Map<String, String> labels = objectMapper.readValue(content, new TypeReference<>() {
        });
        return labels.getOrDefault(key, "");
    }

    public void createContent(PDDocument document, PDPage page) throws IOException {
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Adding image
        PDImageXObject pdImage = PDImageXObject.createFromFile("templates/HDFC logo.png", document);
        contentStream.drawImage(pdImage, 40, 800, 100, 20);

        // Adding JLG heading
        addJLGHeading(contentStream, PDRectangle.A4.getHeight() - 30,document);

        // Adding branch details
        contentStream.beginText();
        contentStream.setLeading(13f);
        contentStream.newLineAtOffset(40, 793);
        contentStream.showText("We understand your world");
        contentStream.newLineAtOffset(10, -50);

        String jsonFilePath = "templates/tamil.json";
        String[] jsonKeys = {
                "label_branch_name_and_code",
                "label_branch_address",
                "label_regd_office",
                "label_gst_regn",
                "label_cin",
                "label_pan"
        };
        for (String key : jsonKeys) {
            String value = readLabelFromJson(jsonFilePath, key)+":";
            contentStream.showText(value);
            contentStream.newLine();
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
        contentStream.newLineAtOffset(50, newBoxYPosition + height-10);
        String[] line = {
                "label_customer_name_and_code",
                "label_loan_accountNumber",
                "label_group_name",
                "label_product",
                "label_husband_name",
                "label_customer_address",
                "label_disbursed_date",
                "label_loan_amount",
                "label_total_interest_charge",
                "label_other_upfront_charges",
                "label_processing_fees",
                "label_insurance_amount",
                "label_others",
                "label_net_disbursed_amount",
                "label_total_amount_to_be_paid",
                "label_annualised_interest_rate",
                "label_rate_of_interest"
        };
        for (String key : line) {
            String value = readLabelFromJson(jsonFilePath, key)+":";
            contentStream.showText(value);
            contentStream.newLine();
        }
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(60 + midPoint , newBoxYPosition + height-10);
        String[] additionalLines = {
                "label_mobile_no",
                "label_father_name",
                "label_co_applicant_name",
                "label_centre_place_address",
                "label_loan_term_in_months",
                "label_repayment_frequency",
                "label_no_of_instalments_of_repayment",
                "label_repayment_date",
                "label_repayment_amount_inr",
                "label_lending_type",
                "label_loan_cycle",
                "label_bank_acct_no",
                "label_purpose",
                "label_beneficiary_ac_name",
                "label_bank_name",
                "label_netoff_account_amount"
        };
        for (String key : additionalLines) {
            String value = readLabelFromJson(jsonFilePath, key)+":";
            contentStream.showText(value);
            contentStream.newLine();
        }
        contentStream.endText();
        createTable(contentStream, 40, newBoxYPosition - 5, 530, 400, 10, 12, true, false,1);
        contentStream.close();
    }

    public void createContentForSecondPage(PDDocument document, PDPage page) throws IOException {
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        String file="templates/tamil.json";

        // Adding JLG heading
        addJLGHeading(contentStream, PDRectangle.A4.getHeight() - 30,document);
        createTable(contentStream, 40, 800, 530, 300, 8, 12, true, true,2);

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
        String label= readLabelFromJson(file, "label_processing_fees");
        contentStream.showText(label);
        contentStream.newLineAtOffset(250, 0);
        String label2= readLabelFromJson(file, "label_insurance_details");
        contentStream.showText(label2);
        contentStream.endText();

        contentStream.beginText();
        contentStream.newLineAtOffset(50, newBoxYPosition + height -30);
        String[] lines = {
                "label_total_processing_fee",
                "",
                "label_stamp_duty",
                "",
                "label_contingent_charges",
                "",
                "label_prepayment_of_loan",
                "",
                "label_penal_charges_in_case_of_delayed_payments",
                "",
                "label_other_charges"

        };
        for (String key : lines) {
            if(key.equals(""))
                contentStream.newLine();
            else{
                String value = readLabelFromJson(file, key)+":";
                contentStream.showText(value);
                contentStream.newLine();
            }
        }
        contentStream.endText();

        contentStream.beginText();
        contentStream.setLeading(10f);
        contentStream.newLineAtOffset(300, newBoxYPosition + height - 30);
        String[] line1 = {
                "label_name_of_insured",
                "",
                "label_name_of_nominee",
                "",
                "label_relationship_with_borrower",
                "",
                "label_age_of_insured",
                "",
                "label_sum_assured",
                "",
                "label_insurance_premium",
                "",
                "label_insurance_start_date",
                "",
                "label_insurance_end_date"
        };
        for (String key : line1) {
            if(key.equals(""))
                contentStream.newLine();
            else{
                String value = readLabelFromJson(file, key)+":";
                contentStream.showText(value);
                contentStream.newLine();
            }
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
        String value= readLabelFromJson(file, "label_keyterms_and_conditions");
        contentStream.showText(value);
        contentStream.endText();
        addTermsAndConditions(contentStream, 70, textStartY - 10);

        // Adding grievance redressal
        contentStream.beginText();
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(40, 150);
        String value1= readLabelFromJson(file, "label_grievance_redressal");
        contentStream.showText(value1);
        contentStream.newLineAtOffset(0, -10);
        String value2= readLabelFromJson(file, "label_text");
        contentStream.showText(value2);
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
        String value3= readLabelFromJson(file, "label_nodal_officer");
        contentStream.showText(value3);
        contentStream.endText();

        contentStream.beginText();
        contentStream.setLeading(11f);
        contentStream.newLineAtOffset(320, 120);
        String value4= readLabelFromJson(file, "label_contact_details");
        contentStream.showText(value4);
        contentStream.endText();

        contentStream.stroke();
        contentStream.close();

    }


    private void addTermsAndConditions(PDPageContentStream contentStream, float x, float y) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File("templates/termsandconditions_tamil.json"));
        JsonNode termsAndConditions = rootNode.get("termsAndConditions");
        contentStream.beginText();
        contentStream.setLeading(12f);
        contentStream.newLineAtOffset(x, y);

        if (termsAndConditions.isArray()) {
            for (JsonNode term : termsAndConditions) {
                String terms = term.asText();
                contentStream.showText(terms);
                contentStream.newLine();
            }
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
            contentStream.setLeading(11f);
            String jsonFilePath = "templates/tamil.json";
            String[] table_headers = {
                    "label_inst_number",
                    "label_repay_date",
                    "label_o/s_prinicipal",
                    "label_principal",
                    "label_interest",
                    "label_inst_amount",
                    "label_paid_status",
                    "label_amount_collected",
                    "label_amount_collected_on",
                    "label_emp_code",
                    "label_emp_signature"

            };

            float textY = y - rowHeight / 3;

            // Calculating the x offset for each column and add the text
            currentX = x;
            for (String headerKey : table_headers) {
                float colWidth;
                if (headerKey.equals(table_headers[0])) {
                    colWidth = firstColWidth;
                } else if (headerKey.equals(table_headers[table_headers.length - 1])) {
                    colWidth = lastColWidth;
                } else {
                    colWidth = remainingColWidth;
                }

                float textX = currentX + 2;
                contentStream.newLineAtOffset(textX, textY);
                String headerValue = readLabelFromJson(jsonFilePath, headerKey);
                if (Arrays.asList(table_headers).contains(headerKey)) {
                    String[] values = headerValue.split(" ");
                    for (String v : values) {
                        contentStream.showText(v);
                        contentStream.newLineAtOffset(0, -11);
                    }
                } else {
                    contentStream.showText(headerValue);
                }
                contentStream.endText();
                contentStream.beginText();
                currentX += colWidth;
            }
            contentStream.endText();
        }
        if (withFooter) {
            contentStream.beginText();
            contentStream.setLeading(11f);
            float textX = x + 5;
            float textY = y - tableHeight - rowHeight + 50;
            contentStream.newLineAtOffset(textX, textY);
            String jsonFilePath= "templates/tamil.json";
            String value = readLabelFromJson(jsonFilePath, "label_total");
            contentStream.showText(value);

            contentStream.endText();
        }
    }

    public void savePDF(PDDocument document, String filePath) throws IOException {
        document.save(filePath);
    }
}
