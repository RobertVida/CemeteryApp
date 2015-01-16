package generators;

import org.springframework.http.HttpMethod;
import ro.InnovaTeam.cemeteryApp.StructureHistoryEntryDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static utils.Utils.*;
import static utils.Utils.getRandom;

/**
 * Created by robert on 1/16/2015.
 */
public class StructureHistoryGenerator {

    private static StructureHistorySettings settings;

    private static class StructureHistorySettings{
        private String[] description;
        private Date createdOnStart;
        private Date createdOnEnd;

        private StructureHistorySettings() {
        }

        public String[] getDescription() {
            return description;
        }

        public void setDescription(String[] description) {
            this.description = description;
        }

        public Date getCreatedOnStart() {
            return createdOnStart;
        }

        public void setCreatedOnStart(Date createdOnStart) {
            this.createdOnStart = createdOnStart;
        }

        public Date getCreatedOnEnd() {
            return createdOnEnd;
        }

        public void setCreatedOnEnd(Date createdOnEnd) {
            this.createdOnEnd = createdOnEnd;
        }
    }

    public StructureHistoryGenerator() {
        try {
            settings = readJsonFromFile("/structureHistorySettings.json", StructureHistorySettings.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer[] generate(String[] tokens, final Integer[] structureIds, final Integer count) {
        List<Integer> ids = new ArrayList<>();

        for(int i = 0 ; i < count ; i++) {
            ids.add(getJSONRestTemplate().exchange("http://localhost:8080/rest-server/structureHistory", HttpMethod.PUT, authorizationWrapper(
                    new StructureHistoryEntryDTO() {{
                        setStructureId(structureIds[getRandom(0, structureIds.length - 1)]);
                        setDate(getRangedDate());
                        setDescription(settings.getDescription()[getRandom(0, settings.getDescription().length - 1)]);
                    }}
                    , tokens[getRandom(0, tokens.length - 1)]), Integer.class).getBody());
        }

        return ids.toArray(new Integer[ids.size()]);
    }

    private Date getRangedDate() {
        return new Date(getRandom(settings.getCreatedOnStart().getTime(), settings.getCreatedOnEnd().getTime()));
    }
}
