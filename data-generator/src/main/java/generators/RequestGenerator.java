package generators;

import org.springframework.http.HttpMethod;
import ro.InnovaTeam.cemeteryApp.RestingPlaceRequestDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static utils.Utils.*;

/**
 * Created by robert on 1/16/2015.
 */
public class RequestGenerator {

    private static RequestSettings settings;

    private static class RequestSettings{
        private Date dateOnStart;
        private Date dateOnEnd;
        private String[] status;

        private RequestSettings() {
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

        public String[] getStatus() {
            return status;
        }

        public void setStatus(String[] status) {
            this.status = status;
        }
    }

    public RequestGenerator() {
        try {
            settings = readJsonFromFile("/requestSettings.json", RequestSettings.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer[] generate(String[] tokens, final Integer[] clientIds, final Integer count) {
        List<Integer> ids = new ArrayList<>();

        for(int i = 0 ; i < count ; i++) {
            ids.add(getJSONRestTemplate().exchange("http://localhost:8080/rest-server/request", HttpMethod.PUT, authorizationWrapper(
                    new RestingPlaceRequestDTO() {{
                        setClientId(clientIds[getRandom(0, clientIds.length - 1)]);
                        setCreatedOn(getRangedDate());
                        setInfocetNumber(getRandom(0, count));
                        setStatus(settings.getStatus()[getRandom(0, settings.getStatus().length - 1)]);
                    }}
                    , tokens[getRandom(0, tokens.length - 1)]), Integer.class).getBody());
        }

        return ids.toArray(new Integer[ids.size()]);
    }

    private Date getRangedDate() {
        return new Date(getRandom(settings.getDateOnStart().getTime(), settings.getDateOnEnd().getTime()));
    }
}
