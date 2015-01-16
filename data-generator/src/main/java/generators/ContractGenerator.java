package generators;

import org.springframework.http.HttpMethod;
import ro.InnovaTeam.cemeteryApp.ContractDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static utils.Utils.*;

/**
 * Created by robert on 1/16/2015.
 */
public class ContractGenerator {

    private static ContractSettings settings;
    private static List<Integer> structureIdsNotUsed;
    private static List<Integer> requestIdsNotUsed;

    private static class ContractSettings {
        private Date dateOnStart;
        private Date dateOnEnd;

        private ContractSettings() {
        }

        public Date getDateOnStart() {
            return dateOnStart;
        }

        public void setDateOnStart(Date dateOnStart) {
            this.dateOnStart = dateOnStart;
        }

        public Date getDateOnEnd() {
            return dateOnEnd;
        }

        public void setDateOnEnd(Date dateOnEnd) {
            this.dateOnEnd = dateOnEnd;
        }
    }

    public ContractGenerator() {
        try {
            settings = readJsonFromFile("/contractSettings.json", ContractSettings.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer[] generate(String[] tokens, final Integer[] structureIds, Integer[] requestIds, Integer count) {
        List<Integer> ids = new ArrayList<>();

        structureIdsNotUsed = new ArrayList<>(Arrays.asList(structureIds));
        requestIdsNotUsed = new ArrayList<>(Arrays.asList(requestIds));

        for(int i = 0 ; i < count ; i++) {
            ids.add(getJSONRestTemplate().exchange("http://localhost:8080/rest-server/contract", HttpMethod.PUT, authorizationWrapper(
                    getEntity(structureIds, count)
                    , tokens[getRandom(0, tokens.length - 1)]), Integer.class).getBody());
        }

        return ids.toArray(new Integer[ids.size()]);
    }

    private ContractDTO getEntity(final Integer[] structureIds, final Integer count) {
        ContractDTO contract = new ContractDTO();

        contract.setStructureId(structureIdsNotUsed.get(getRandom(0, structureIdsNotUsed.size() - 1)));
        structureIdsNotUsed.remove(contract.getStructureId());

        contract.setRequestId(requestIdsNotUsed.get(getRandom(0, requestIdsNotUsed.size() - 1)));
        requestIdsNotUsed.remove(contract.getRequestId());

        contract.setSignedOn(getRangedDate());
        return contract;
    }

    private Date getRangedDate() {
        return new Date(getRandom(settings.getDateOnStart().getTime(), settings.getDateOnEnd().getTime()));
    }
}
