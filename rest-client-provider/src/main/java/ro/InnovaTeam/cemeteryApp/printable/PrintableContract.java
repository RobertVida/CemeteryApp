package ro.InnovaTeam.cemeteryApp.printable;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import ro.InnovaTeam.cemeteryApp.PrintableContractDTO;
import ro.InnovaTeam.cemeteryApp.restClient.PrintableRestClient;

import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.itextpdf.text.Font.FontFamily.TIMES_ROMAN;
import static ro.InnovaTeam.cemeteryApp.PrintableContractDTO.Fields.*;

/**
 * Created by Catalin Sorecau on 1/13/2015.
 */
public class PrintableContract extends TextPDFView {

    private static String TAB = "            ";
    private static Font defFont = new Font(TIMES_ROMAN, 11) {{
        setColor(BaseColor.BLACK);
    }};
    private static Font defFontBold = new Font(defFont){{setStyle(Font.BOLD);}};


    @Override
    protected void buildPdfDocument(Document document, PdfWriter writer, HttpServletRequest request) throws Exception {
        Integer contractId = (Integer) request.getSession().getAttribute("contractId");
        PrintableContractDTO dto = PrintableRestClient.getContractById(Integer.valueOf(contractId));

        document.add(addHeader());
        document.add(addTitle(dto));
        document.add(partOne(dto));
        document.add(partTwo(dto));
        document.add(partThree(dto));
        document.add(partFour(dto));
        document.add(partFive(dto));
        request.getSession().removeAttribute("contractId");
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

    private Element addTitle(PrintableContractDTO dto) {
        Paragraph paragraph = new Paragraph();

        paragraph.add(new Chunk("CONTRACT DE CONCESIUNE\n", new Font(TIMES_ROMAN, 12) {{
            setColor(BaseColor.GRAY);
        }}));
        paragraph.add(new Chunk("NR. ", defFontBold));
        paragraph.add(new Chunk("  " + dto.get(CONTRACT_ID).toString() + "  ", defFont) {{
            setUnderline(0.1f, -2f);
        }});
        paragraph.add(new Chunk(" din ", defFontBold));
        paragraph.add(new Chunk(formatDate("dd.MM.yyyy", (Long) dto.get(CONTRACT_SIGNED_ON)), defFont));

        paragraph.setAlignment(Element.ALIGN_CENTER);
        adjustSpacing(paragraph, 0, 12, 15);
        return paragraph;
    }

    private Element partOne(PrintableContractDTO dto) {
        Paragraph paragraph = new Paragraph();

        paragraph.add(new Chunk("I. Partile contractante\n", defFontBold));
        adjustSpacing(paragraph, 0, 12, 15);
        paragraph.add(new Chunk(TAB + "Art. 1. Municipiul CLUJ - NAPOCA", defFontBold));
        paragraph.add(new Chunk("prin Serviciul Administrare Cimitire Domeniul Public cu sediul in municipiul Cluj – Napoca, str. Avram Iancu nr. 26-28, telefon 0264.454.421, reprezentant de primar Emil Boc in calitate de concedent,  pe de o parte, si\n", defFont));

        Paragraph paragraph2 = new Paragraph();
        adjustSpacing(paragraph2, 0, 0, 15);
        paragraph2.add(new Chunk(TAB + "D-nul/D-na ", defFontBold));
        paragraph2.add(new Chunk("  " + dto.get(CLIENT_FNAME).toString() + " " + dto.get(CLIENT_LNAME) + "  ", defFont) {{
            setUnderline(0.1f, -2f);
        }});
        paragraph2.add(new Chunk(" CNP ", defFontBold));
        paragraph2.add(new Chunk("  " + dto.get(CLIENT_CNP) + "  ", defFont) {{
            setUnderline(0.1f, -2f);
        }});
        paragraph2.add(new Chunk(" cu domiciliul in ", defFont));
        paragraph2.add(new Chunk("  " + dto.get(CLIENT_ADDRESS) + "  ", defFont) {{
            setUnderline(0.1f, -2f);
        }});
        paragraph2.add(new Chunk(" , in calitate de concesionar pe de alta parte. \n", defFont));

        Paragraph paragraph3 = new Paragraph();
        adjustSpacing(paragraph3, 0, 0, 15);
        paragraph3.add(new Chunk(TAB + "In temeiul OUG nr. 54/2006 privind regimul contractelor de concesiune de bunuri proprietate publica, aprobata cu modificari prin Legea nr. 22/2007 si in conformitate cu HCL nr.300/2014 s-a incheiat prezentul contract de concesiune in urmatoarele conditii:\n", defFont));

        paragraph.add(paragraph2);
        paragraph.add(paragraph3);
        return paragraph;
    }

    private Element partTwo(PrintableContractDTO dto) {
        Paragraph paragraph = new Paragraph();
        adjustSpacing(paragraph, 0, 0, 15);

        paragraph.add(new Chunk("II. Obiectul contractului de concesiune\n", defFontBold));
        paragraph.add(new Chunk(TAB + "Art. 2. ", defFontBold));
        paragraph.add(new Chunk("Obiectul contractului este concesionarea locului de inhumare situat in cimitirul ", defFont));
        paragraph.add(new Chunk("  " + dto.get(CEMETERY_NAME) + "  ", defFont){{
            setUnderline(0.1f, -2f);
        }});
        paragraph.add(new Chunk(",parcela ", defFont));
        paragraph.add(new Chunk("  " + dto.get(PARCEL_NAME) + "  ", defFont){{
            setUnderline(0.1f, -2f);
        }});
        paragraph.add(new Chunk(",nr. ", defFont));
        paragraph.add(new Chunk("  " + dto.get(STRUCTURE_ID) + "  ", defFont){{
            setUnderline(0.1f, -2f);
        }});
        paragraph.add(new Chunk(",avand supreafata de ", defFont));
        paragraph.add(new Chunk("  " + dto.get(STRUCTURE_SIZE) + "  ", defFont){{
            setUnderline(0.1f, -2f);
        }});
        paragraph.add(new Chunk("mp.", defFont));
        return paragraph;
    }

    private Element partThree(PrintableContractDTO dto) {
        Paragraph paragraph = new Paragraph();
        adjustSpacing(paragraph, 0, 0, 15);

        paragraph.add(new Chunk("III. Termenul\n", defFontBold));
        paragraph.add(new Chunk(TAB + "Art. 3. ", defFontBold));
        paragraph.add(new Chunk("Durata concesiunii este de ", defFont));
        Integer years = Integer.parseInt(formatDate("yyyy", (Long)dto.get(CONTRACT_EXPIRES_ON))) - Integer.parseInt(formatDate("yyyy", (Long)dto.get(CONTRACT_SIGNED_ON)));
        paragraph.add(new Chunk(years.toString(), defFont));
        paragraph.add(new Chunk(" de ani, pentru perioada ", defFont));
        paragraph.add(new Chunk("  " + formatDate("yyyy", (Long)dto.get(CONTRACT_SIGNED_ON)) + " - " + formatDate("yyyy", (Long)dto.get(CONTRACT_EXPIRES_ON)) + ".\n", defFont));
        paragraph.add(new Chunk(TAB + "Art. 4. ", defFontBold));
        paragraph.add(new Chunk("Durata contractului de concesiune poate fi prelungita, prin act adiţional, în favoarea concesionarului sau a mostenitorilor acestuia pentru o perioada de inca 20 de ani, cu plata taxei de reconcesionare pana in data de ", defFont));
        paragraph.add(new Chunk("  " + formatDate("dd.MM.yyyy", (Long)dto.get(CONTRACT_EXPIRES_ON)) + "  ", defFont) {{
            setUnderline(0.1f, -2f);
        }});
        paragraph.add(new Chunk("a anului următor anului în care expiră durata contractului.", defFont));

        return paragraph;
    }

    private Element partFour(PrintableContractDTO dto) {
        Paragraph paragraph = new Paragraph();
        adjustSpacing(paragraph, 0, 0, 15);

        paragraph.add(new Chunk("IV. Redeventa\n", defFontBold));
        paragraph.add(new Chunk(TAB + "Art. 5. ", defFontBold));
        paragraph.add(new Chunk("Redeventa este de 100 lei/mp/20 de ani reprezentand plata concesiunii, 10 lei/mp/an reprezentand plata intretinerii cimitirului si 50 lei taxa de transcriere, conform HCL nr. 76/2010 si a fost achitata anticipat cu chitanta nr. ___________/________  in baza:\n", defFont));
        paragraph.add(new Chunk(TAB + " - cererii nr. ", defFont));
        paragraph.add(new Chunk("  " + dto.get(REQUEST_ID) + "/ " + formatDate("yyyy", (Long)dto.get(REQUEST_CREATED_ON)) + "  ", defFont) {{
            setUnderline(0.1f, -2f);
        }});
        paragraph.add(new Chunk("inregistrata la registratura Primariei Cluj-Napoca.\n", defFont));
        paragraph.add(new Chunk(TAB + " - adeverintei de inhumare nr. ", defFont));
        paragraph.add(new Chunk("  " + dto.get(REQUEST_INFOCET) + "  ", defFont) {{
            setUnderline(0.1f, -2f);
        }});
        paragraph.add(new Chunk("emisa de Primaria Cluj-Napoca.\n", defFont));
        paragraph.add(new Chunk(TAB + " - reconcesionarii cu prezentarea fotografiei locului de inhumare. ", defFont));

        return paragraph;
    }

    private Element partFive(PrintableContractDTO dto) {
        Paragraph paragraph = new Paragraph();
        adjustSpacing(paragraph, 0, 0, 15);

        paragraph.add(new Chunk("V. Plata redeventei\n", defFontBold));
        paragraph.add(new Chunk(TAB + "Art. 6. ", defFontBold));
        paragraph.add(new Chunk("Plata redeventei se face in numerar la casieria Serviciului Administrare Cimitire Domeniul Public.\n", defFont));
        paragraph.add(new Chunk(TAB + "Art. 7. ", defFontBold));
        paragraph.add(new Chunk("Neplata redeventei sau executarea cu intarziere a acestei obligatii conduce la incetarea concesionarii.\n", defFont));

        paragraph.add(new Chunk("VI. Drepturile partilor\nDrepturile concesionarului\n", defFontBold));
        paragraph.add(new Chunk(TAB + "Art. 8. ", defFontBold));
        paragraph.add(new Chunk("Concesionarul are dreptul de a exploata in mod direct, pe riscul si pe raspunderea sa, bunul proprietate publica ce face obiectul contractului de concesiune.\n", defFont));
        paragraph.add(new Chunk(TAB + "Art. 6. ", defFontBold));
        paragraph.add(new Chunk("Dreptul de concesiune asupra locului de inhumare se poate transmite prin mostenire legala sau testamentara sau prin donatie, în conditiile prevazute in Regulamentul de organizare si functionare a cimitirelor apartinand domeniului public aprobat prin HCL nr. 300/2014.\n", defFont));
        paragraph.add(new Chunk("Drepturile concedentului\n ", defFontBold));
        paragraph.add(new Chunk(TAB + "Art. 10. ", defFontBold));
        paragraph.add(new Chunk("Concedentul are urmatoarele drepturi:\n", defFont));
        paragraph.add(new Chunk(TAB + "   - sa inspecteze bunul concesionat, verificand respectarea obligatiilor asumate de concesionar;\n", defFont));
        paragraph.add(new Chunk(TAB + "   - sa modifice in mod unilateral partea reglementara a contractului de concesiune, din motive excepţionale legate de interesul naţional sau local;\n", defFont));
        paragraph.add(new Chunk(TAB + "   - sa încaseze redeventa. ", defFont));

        paragraph.add(new Chunk("VII. Obligatiile partilor\nObligatiile concesionarului\n", defFontBold));
        paragraph.add(new Chunk(TAB + "Art. 6. Concesionarii au urmatoarele obligatii:\n ", defFontBold));
        paragraph.add(new Chunk(TAB + "    a) sa cunoasca si sa respecte intocmai Regulamentul de organizare si funcţionare a cimitirelor apartinand domeniului public si aflate in administrarea Consiliului Local al municipiului Cluj-Napoca;\n", defFont));
        paragraph.add(new Chunk(TAB + "    b) sa efectueze lucrarile de constructii funerare doar in baza avizelor/autorizatiei prevazute de lege;\n", defFont));
        paragraph.add(new Chunk(TAB + "    c) sa instaleze un insemn care sa contina numele si prenumele decedatului sau al concesionarului, dupa caz, de la data luarii in folosinta;\n", defFont));
        paragraph.add(new Chunk(TAB + "    d) sa edifice o bordura din piatra/ciment care sa delimiteze perimetrul locului de inhumare, in termen de sase luni de la concesionare;\n", defFont));
        paragraph.add(new Chunk(TAB + "    e) sa asigure lizibilitatea inscrierilor de pe placile si tablele comemorative;\n", defFont));
        paragraph.add(new Chunk(TAB + "    f) sa ingrijeasca permanent locul de inhumare, sa intretina constructiile de orice fel existente la locul de inhumare precum si cararile dintre morminte si aleile secundare de acces in parcele;\n", defFont));


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
