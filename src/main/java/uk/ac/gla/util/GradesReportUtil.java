package uk.ac.gla.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import uk.ac.gla.controller.SearchIdController;
import uk.ac.gla.model.GradeConversionModel;
import uk.ac.gla.model.ReadCsvModel;
import uk.ac.gla.view.MainView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Shangzhen Wei
 * @version 1.3
 */

/*
 * Using the iText library to write the template of individual student grade reports
 */

public class GradesReportUtil {
    private static final String path = MainView.getText1().getText();
    public Document document = new Document(PageSize.A4);
    private String gradeReportName = ReadCsvModel.getModuleName(path) + "_" + ReadCsvModel.readCsvToList(path).get(SearchIdController.indexModel.getIndex()).get(0) + "_" + ReadCsvModel.readCsvToList(path).get(SearchIdController.indexModel.getIndex()).get(1);

    public GradesReportUtil() {
    }

    public void generatePdf(Document document) throws Exception {

        Font titlefont = new Font(Font.FontFamily.HELVETICA, 19, Font.BOLD, BaseColor.BLACK);
        Font moduleFont = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD, BaseColor.BLACK);
        Font baseFont = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL, BaseColor.BLACK);

        savePdf();

        document.open();

        Paragraph reportTitle = new Paragraph("My Grade Report", titlefont);
        reportTitle.setAlignment(1);
        reportTitle.setIndentationLeft(12);
        reportTitle.setIndentationRight(12);
        reportTitle.setFirstLineIndent(24);
        reportTitle.setLeading(20f);
        reportTitle.setSpacingBefore(5f);
        reportTitle.setSpacingAfter(10f);

        Paragraph splitLine = new Paragraph();
        splitLine.add(new Chunk(new DottedLineSeparator()));


        Paragraph moduleNameTitle = new Paragraph("Module Name ", titlefont);
        Paragraph studentInformation = new Paragraph(ReadCsvModel.getStudentInformation(path, SearchIdController.indexModel.getIndex()), titlefont);
        Paragraph overallGrade = new Paragraph("Overall Grade", titlefont);
        GradeConversionModel.changeGrade();
        Paragraph grade = new Paragraph(GradeConversionModel.calculateGpa(GradeConversionModel.assessmentsCredits, GradeConversionModel.toPoints), moduleFont);

        Image image = Image.getInstance("src/main/resources/Picture.jpg");
        image.setAbsolutePosition(470, 750);
        image.scalePercent(8);

        Paragraph moduleName = new Paragraph(ReadCsvModel.getModuleName(path), moduleFont);
        Paragraph results = new Paragraph("Outline of Assessments and Results", titlefont);

        PdfPTable table = new PdfPTable(4);
        table.setTotalWidth(300);
        table.setWidths(new float[]{90, 35, 35, 30});

        String[] tableTitleName = {"Assessment Title", "Credits", "Final Grade", "Note"};

        for (int i = 0; i < tableTitleName.length; i++) {
            table.addCell(createCell(tableTitleName[i], moduleFont));
        }


        for (int i = 4; i < ReadCsvModel.readCsvToList(path).get(0).size(); i++) {
            table.addCell(createCell(ReadCsvModel.readCsvToList(path).get(0).get(i), baseFont));
            table.addCell(createCell(ReadCsvModel.readCsvToList(path).get(1).get(i), baseFont));
            table.addCell(createCell(GradeConversionModel.changeGrade().get(i), baseFont));
            table.addCell(createCell("", baseFont));
        }

        table.setSpacingBefore(10f);


        document.add(reportTitle);
        document.add(studentInformation);
        document.add(splitLine);
        document.add(moduleNameTitle);
        document.add(moduleName);
        document.add(splitLine);
        document.add(overallGrade);
        document.add(grade);
        document.add(splitLine);
        document.add(results);
        document.add(image);
        document.add(table);

        document.close();

        System.out.println("Report generated successful!");


    }

    public PdfPCell createCell(String str, Font font) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_LEFT);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPhrase(new Phrase(str, font));
        cell.setMinimumHeight(35);
        return cell;
    }


    public void savePdf() throws IOException, DocumentException {

        File file = new File("src/main/resources/" + gradeReportName + ".pdf");
        file.createNewFile();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));

        writer.setPageEvent(new WatermarkUtil("Assessment and Grade Report"));
        writer.setPageEvent(new HeaderAndFooterUtil());

    }

}

