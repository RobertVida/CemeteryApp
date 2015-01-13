package ro.InnovaTeam.cemeteryApp.printable;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import ro.InnovaTeam.cemeteryApp.PrintableContractDTO;
import ro.InnovaTeam.cemeteryApp.restClient.PrintableRestClient;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Catalin Sorecau on 1/13/2015.
 */
public class PrintableContract extends TextPDFView {

    @Override
    protected void buildPdfDocument(Document document, PdfWriter writer, HttpServletRequest request) throws Exception {
        Integer contractId = (Integer) request.getSession().getAttribute("contractId");
        PrintableContractDTO dto = PrintableRestClient.getContractById(Integer.valueOf(contractId));
        document.add(new Paragraph(dto.get(PrintableContractDTO.Fields.TITLE).toString()));
        request.getSession().removeAttribute("contractId");
    }
}
