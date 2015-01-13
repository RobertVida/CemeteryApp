package ro.InnovaTeam.cemeteryApp.printable;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import ro.InnovaTeam.cemeteryApp.PrintableStatisticsDTO;
import ro.InnovaTeam.cemeteryApp.restClient.PrintableRestClient;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Catalin Sorecau on 1/13/2015.
 */
public class PrintableStatistics extends TextPDFView {

    @Override
    protected void buildPdfDocument(Document document, PdfWriter writer, HttpServletRequest request) throws Exception {
        PrintableStatisticsDTO dto = PrintableRestClient.getStatistics();
        document.add(new Paragraph("I works works works!"));
        document.add(new Paragraph(dto.get(PrintableStatisticsDTO.Fields.TITLE).toString()));
    }
}
