import generators.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static utils.Utils.*;

/**
 * Created by robert on 1/16/2015.
 */
public class Generator {

    public static void main(String[] args) {
        new Generator().generate();
    }

    private String[] tokens;

    public Generator() {
    }



    public void generate(){

        tokens = new String[]{getLoggedInUserToken("admin", "admin"), getLoggedInUserToken("admin2", "admin2")};

        long start, end, total = 0;

        int parcelCount = 30,
                structureCount = 700,
                structureHistoryCount = 900,
                clientCount = 700,
                deceasedCount = 2000,
                requestCount = 400,
                contractCount = 300;


        start = System.currentTimeMillis();
        Integer[] cemeteryIds = new CemeteryGenerator().generate(tokens);
        end = System.currentTimeMillis();
        log("Cemeteries", end - start, cemeteryIds);
        total += end- start;


        start = System.currentTimeMillis();
        Integer[] parcelIds = new ParcelGenerator().generate(tokens, cemeteryIds, parcelCount);
        end = System.currentTimeMillis();
        log("Parcels", end - start, parcelIds);
        total += end- start;

        start = System.currentTimeMillis();
        Integer[] structuresIds = new StructureGenerator().generate(tokens, parcelIds, structureCount);
        end = System.currentTimeMillis();
        log("Structures", end - start, structuresIds);
        total += end- start;

        start = System.currentTimeMillis();
        Integer[] structureHistoryIds = new StructureHistoryGenerator().generate(tokens, structuresIds, structureHistoryCount);
        end = System.currentTimeMillis();
        log("StructureHistory", end - start, structureHistoryIds);
        total += end- start;

        start = System.currentTimeMillis();
        Integer[] clientIds = new ClientGenerator().generate(tokens, clientCount);
        end = System.currentTimeMillis();
        log("Clients", end - start, clientIds);
        total += end- start;

        start = System.currentTimeMillis();
        Integer[] deceasedIds = new DeceasedGenerator().generate(tokens, structuresIds, deceasedCount);
        end = System.currentTimeMillis();
        log("Deceased", end - start, deceasedIds);
        total += end- start;

        start = System.currentTimeMillis();
        Integer[] requestsIds = new RequestGenerator().generate(tokens, clientIds, requestCount);
        end = System.currentTimeMillis();
        log("Requests", end - start, requestsIds);
        total += end- start;

        start = System.currentTimeMillis();
        Integer[] contractsIds = new ContractGenerator().generate(tokens, structuresIds, requestsIds, contractCount);
        end = System.currentTimeMillis();
        log("Contracts", end - start, contractsIds);
        total += end- start;

        System.out.printf("TOTAL %d:%d:%d\n", total / (60 * 60 * 1000), total / (60 * 1000) % 60, total / 1000 % 60);
    }

    private void log(String entity, long interval, Integer[] cemeteryIds) {
        System.out.printf("%s %d added in %d:%d:%d\n", entity, cemeteryIds.length, interval / (60 * 60 * 1000), interval / (60 * 1000) % 60, interval / 1000 % 60);
    }


    private String formatDate(String format, Long date) {
        return new SimpleDateFormat(format).format(new Date(date));
    }

}
