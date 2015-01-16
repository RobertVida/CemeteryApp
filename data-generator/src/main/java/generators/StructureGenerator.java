package generators;

import org.springframework.http.HttpMethod;
import ro.InnovaTeam.cemeteryApp.GraveDTO;
import ro.InnovaTeam.cemeteryApp.MonumentDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static utils.Utils.*;

/**
 * Created by robert on 1/16/2015.
 */
public class StructureGenerator {

    private static StructureSettings settings;

    private static class StructureSettings {
        private String[] name1;
        private String[] name2;
        private String[] description;
        private Date createdOnStart;
        private Date createdOnEnd;
        private Integer sizeMaximum;

        private StructureSettings() {
        }

        public String[] getName1() {
            return name1;
        }

        public void setName1(String[] name1) {
            this.name1 = name1;
        }

        public String[] getName2() {
            return name2;
        }

        public void setName2(String[] name2) {
            this.name2 = name2;
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

        public Integer getSizeMaximum() {
            return sizeMaximum;
        }

        public void setSizeMaximum(Integer sizeMaximum) {
            this.sizeMaximum = sizeMaximum;
        }
    }

    public StructureGenerator() {
        try {
            settings = readJsonFromFile("/structureSettings.json", StructureSettings.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer[] generate(String[] tokens, Integer[] parcelIds, Integer count) {
        List<Integer> ids = new ArrayList<>();

        for(int i = 0 ; i < count ; i++) {
            ids.add(getRandom(0, 10) < 8 ? generateGrave(tokens, parcelIds) : generateMonuments(tokens, parcelIds));
        }

        return ids.toArray(new Integer[ids.size()]);
    }

    private Integer generateGrave(String[] tokens, final Integer[] parcelIds) {
        return getJSONRestTemplate().exchange("http://localhost:8080/rest-server/grave", HttpMethod.PUT, authorizationWrapper(
                new GraveDTO() {{
                    setParcelId(parcelIds[getRandom(0, parcelIds.length - 1)]);
                    setCreatedOn(getRangedDate());
                    setType("Grave");
                    setWidth(getRandom(1, settings.getSizeMaximum()));
                    setLength(getRandom(1, settings.getSizeMaximum()));
                }}
                , tokens[getRandom(0, tokens.length - 1)]), Integer.class).getBody();
    }

    private Integer generateMonuments(String[] tokens, final Integer[] parcelIds) {
        return getJSONRestTemplate().exchange("http://localhost:8080/rest-server/monument", HttpMethod.PUT, authorizationWrapper(
                new MonumentDTO() {{
                    setParcelId(parcelIds[getRandom(0, parcelIds.length - 1)]);
                    setCreatedOn(getRangedDate());
                    setType("Monument");
                    setWidth(getRandom(1, settings.getSizeMaximum()));
                    setLength(getRandom(1, settings.getSizeMaximum()));
                    setName("Monumentul " + getHintedName());
                    setDescription(settings.getDescription()[getRandom(0, settings.getDescription().length - 1)]);
                }}
                , tokens[getRandom(0, tokens.length - 1)]), Integer.class).getBody();
    }

    private Date getRangedDate() {
        return new Date(getRandom(settings.getCreatedOnStart().getTime(), settings.getCreatedOnEnd().getTime()));
    }

    private String getHintedName() {
        return settings.getName1()[getRandom(0, settings.getName1().length-1)] + " " +
                settings.getName2()[getRandom(0, settings.getName2().length-1)];
    }
}
