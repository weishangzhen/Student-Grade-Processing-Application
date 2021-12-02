package uk.ac.gla.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author Shangzhen Wei
 * @version 1.3
 */

/*
 * Adding a watermark to the application's output of individual student grade reports
 */

public class WatermarkUtil extends PdfPageEventHelper {
    Font font = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD, new GrayColor(0.9f));
    private String waterCont;

    public WatermarkUtil() {

    }

    public WatermarkUtil(String waterCont) {
        this.waterCont = waterCont;
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ColumnText.showTextAligned(writer.getDirectContentUnder(),
                        Element.ALIGN_CENTER,
                        new Phrase(this.waterCont == null ? "Assessment and Grade Report" : this.waterCont, font),
                        (50.5f + i * 350),
                        (40.0f + j * 150),
                        writer.getPageNumber() % 2 == 1 ? 45 : -45);
            }
        }
    }
}