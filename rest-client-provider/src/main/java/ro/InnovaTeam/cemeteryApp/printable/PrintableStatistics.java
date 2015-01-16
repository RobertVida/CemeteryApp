package ro.InnovaTeam.cemeteryApp.printable;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import ro.InnovaTeam.cemeteryApp.PrintableStatisticsDTO;
import ro.InnovaTeam.cemeteryApp.restClient.PrintableRestClient;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.itextpdf.text.Font.FontFamily.TIMES_ROMAN;
import static ro.InnovaTeam.cemeteryApp.PrintableStatisticsDTO.Fields.*;

/**
 * Created by Catalin Sorecau on 1/13/2015.
 */
public class PrintableStatistics extends TextPDFView {

    private static String TAB = "            ";
    private static Font defFont = new Font(TIMES_ROMAN, 13) {{
        setColor(BaseColor.BLACK);
    }};
    private static Font defFontBold = new Font(defFont){{setStyle(Font.BOLD);}};

    @Override
    protected void buildPdfDocument(Document document, PdfWriter writer, HttpServletRequest request) throws Exception {
        PrintableStatisticsDTO dto = PrintableRestClient.getStatistics();

        document.add(addHeader());
        document.add(addTitle());
        document.add(addContent(dto));
    }

    private Element addHeader() throws Exception{
        Paragraph paragraph = new Paragraph("ROMÂNIA\nMUNICIPIUL CLUJ-NAPOCA\nStr. Avram Iancu 26-28, 400001, Cluj-Napoca, România;\nTelefon: +40-(0) 45 44 21, Web: ",
                new Font(TIMES_ROMAN, 8) {{
                    setColor(BaseColor.GRAY);
                }});

        Image logo = Image.getInstance(PrintableContract.class.getResource("/sigla.jpg"));
        logo.scalePercent(15f);
        logo.setSpacingAfter(0);
        logo.setSpacingBefore(0);
        logo.setAlignment(Image.LEFT | Image.TOP | Image.TEXTWRAP);
        logo.setAbsolutePosition(40f, 766f);
        paragraph.add(logo);

        Anchor anchor = new Anchor("www.primariaclujnapoca.ro", new Font(TIMES_ROMAN, 8) {{
            setStyle(FontStyle.UNDERLINE.toString());
            setColor(BaseColor.BLUE);
        }});
        anchor.setReference("http://www.primariaclujnapoca.ro/");
        paragraph.add(anchor);
        adjustSpacing(paragraph, 0, 0, 10);
        paragraph.setAlignment(Element.ALIGN_CENTER);

        paragraph.add(new LineSeparator() {{
            setLineColor(BaseColor.DARK_GRAY);
        }});
        return paragraph;
    }

    private Element addTitle() {
        Paragraph paragraph = new Paragraph();

        paragraph.add(new Chunk("STATISTICI\n", new Font(TIMES_ROMAN, 12) {{
            setColor(BaseColor.GRAY);
        }}));

        paragraph.setAlignment(Element.ALIGN_CENTER);
        adjustSpacing(paragraph, 0, 12, 15);
        return paragraph;
    }

    private Element addContent(PrintableStatisticsDTO dto) {
        Paragraph paragraph = new Paragraph();

        paragraph.add(new Chunk("\n\n\nPe data de ", defFont));
        paragraph.add(new Chunk(formatDate("dd.MM.yyyy", new Date().getTime()), defFontBold));
        paragraph.add(new Chunk(" in baza de date sunt : \n", defFont));

        paragraph.add(new Chunk(TAB + " - " + dto.get(TOTAL_CEMETERIES) + " cimitire\n", defFont));

        paragraph.add(new Chunk(TAB + " - " + dto.get(TOTAL_PARCELS) + " parcele (aproximativ " +
                dto.get(AVERAGE_PARCELS_PER_CEMETERIES) + " parcele per cimitir)\n", defFont));

        paragraph.add(new Chunk(TAB + " - " + dto.get(TOTAL_STRUCTURES) + " structuri, (aproximativ " +
                dto.get(AVERAGE_STRUCTURE_PER_PARCEL) + " structuri per parcela) din care :\n" +
                TAB + TAB + "+ " + dto.get(TOTAL_GRAVES) + " morminte\n" +
                TAB + TAB + "+ " + dto.get(TOTAL_MONUMENTS)+ " monumente\n", defFont));

        paragraph.add(new Chunk(TAB + " - " + dto.get(TOTAL_DECEASED) + " decedati, (aproximativ " +
                dto.get(AVERAGE_DECEASED_STRUCTURE) + " decedati per structura) din care :\n" +
                TAB + TAB + "+ " + dto.get(DECEASED_CAREGIVER) + " cu apartinatori\n" +
                TAB + TAB + "+ " + dto.get(DECEASED_NO_CAREGIVER) + " fara apartinatori\n", defFont));

        paragraph.add(new Chunk(TAB + " - " + dto.get(TOTAL_CLIENTS) + " clienti\n", defFont));

        paragraph.add(new Chunk(TAB + " - " + dto.get(TOTAL_REQUESTS) + " cereri, din care :\n" +
                TAB + TAB + "+ " + dto.get(REQUESTS_FAVORABLE) + " in stadiul 'Favorabil'\n" +
                TAB + TAB + "+ " + dto.get(REQUESTS_NOT_FAVORABLE) + " in stadiul 'Nefavorabil'\n" +
                TAB + TAB + "+ " + dto.get(REQUESTS_PARTIAL) + " in stadiul 'Partial'\n" +
                TAB + TAB + "+ " + dto.get(REQUESTS_DECLINED) + " in stadiul 'Declinat'\n" +
                TAB + TAB + "+ " + dto.get(REQUESTS_INTERN) + " in stadiul 'Intern'\n", defFont));

        paragraph.add(new Chunk(TAB + " - " + dto.get(TOTAL_CONTRACTS) + " contracte (aproximativ " +
                dto.get(AVERAGE_CONTRACT_PER_CLIENT) + " contracte per client)\n", defFont));

        adjustSpacing(paragraph, 0, 12, 20);
        return paragraph;
    }

    private String formatDate(String format, Long date) {
        return new SimpleDateFormat(format).format(new Date(date));
    }

    private void adjustSpacing(Paragraph paragraph, int spacingAfter, int spacingBefore, int lineSpacing) {
        paragraph.setSpacingAfter(spacingAfter);
        paragraph.setSpacingBefore(spacingBefore);
        paragraph.setLeading(lineSpacing);
    }
}
