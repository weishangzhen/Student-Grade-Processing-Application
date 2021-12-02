package uk.ac.gla.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

/**
 * @author Shangzhen Wei
 * @version 1.3
 */

/*
 * Adding headers and footers to the application's output of individual student grade reports
 */

public class HeaderAndFooterUtil extends PdfPageEventHelper {
    PdfTemplate totalPage;
    Font headerAndFooterFont = new Font(Font.FontFamily.COURIER, 8, Font.BOLD, BaseColor.GRAY);

    public void onOpenDocument(PdfWriter writer, Document document) {
        PdfContentByte contentByte = writer.getDirectContent();
        totalPage = contentByte.createTemplate(30, 16);
    }

    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable table = new PdfPTable(3);

        try {
            table.setTotalWidth(PageSize.A4.getWidth() - 100);
            try {
                table.setWidths(new int[]{24, 24, 3});
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            table.setLockedWidth(true);
            table.getDefaultCell().setFixedHeight(-10);
            table.getDefaultCell().setBorder(Rectangle.BOTTOM);

            table.addCell(new Paragraph("Assessment and Grade Report", headerAndFooterFont));
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(new Paragraph("Page" + " " + writer.getPageNumber() + " " + "of", headerAndFooterFont));

            PdfPCell cell = null;
            try {
                cell = new PdfPCell(Image.getInstance(totalPage));
            } catch (BadElementException e) {
                e.printStackTrace();
            }
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);

            table.writeSelectedRows(0, -1, 50, PageSize.A4.getHeight() - 20, writer.getDirectContent());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onCloseDocument(PdfWriter writer, Document document) {
        String text = "Total" + " " + (writer.getPageNumber()) + "Page";
        ColumnText.showTextAligned(totalPage, Element.ALIGN_LEFT, new Paragraph(text, headerAndFooterFont), 2, 2, 0);
    }

}
