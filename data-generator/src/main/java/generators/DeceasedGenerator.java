package generators;

import org.springframework.http.HttpMethod;
import ro.InnovaTeam.cemeteryApp.DeceasedDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static utils.Utils.*;
import static utils.Utils.getRandom;

/**
 * Created by robert on 1/16/2015.
 */
public class DeceasedGenerator {

    public static DeceasedSettings settings;

    public static class DeceasedSettings {
        private String[] firstName;
        private String[] lastName;
        private String[] religion;
        private Date dateOnStart;
        private Date dateOnEnd;
        private String[] address;

        public DeceasedSettings() {
        }

        public String[] getFirstName() {
            return firstName;
        }

        public void setFirstName(String[] firstName) {
            this.firstName = firstName;
        }

        public String[] getLastName() {
            return lastName;
        }

        public void setLastName(String[] lastName) {
            this.lastName = lastName;
        }

        public String[] getReligion() {
            return religion;
        }

        public void setReligion(String[] religion) {
            this.religion = religion;
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

        public String[] getAddress() {
            return address;
        }

        public void setAddress(String[] address) {
            this.address = address;
        }
    }

    public DeceasedGenerator() {
        try {
            settings = readJsonFromFile("/personSettings.json", DeceasedSettings.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer[] generate(String[] tokens, final Integer[] structureIds, final Integer count){
        List<Integer> ids = new ArrayList<>();

        for(int i = 0 ; i < count ; i++) {
            ids.add(getJSONRestTemplate().exchange("http://localhost:8080/rest-server/deceased", HttpMethod.PUT, authorizationWrapper(
                    new DeceasedDTO() {{
                        setFirstName(settings.getFirstName()[getRandom(0, settings.getFirstName().length - 1)]);
                        setLastName(settings.getLastName()[getRandom(0, settings.getLastName().length - 1)]);
                        setCnp(Long.toString(new Date().getTime() % 10000000000000L));
                        setReligion(settings.getReligion()[getRandom(0, settings.getReligion().length - 1)]);
                        setDiedOn(getRangedDate());
                        setStructureId(structureIds[getRandom(0, structureIds.length - 1)]);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(getDiedOn());
                        calendar.add(Calendar.DATE, 3);
                        setBurialOn(calendar.getTime());
                        if (getRandom(0, 10) >= 8) {
                            setHasCaregiver(false);
                            setCertificateId(getRandom(0, count));
                            setRequestIMLid(getRandom(0, count));
                        }
                    }}
                    , tokens[getRandom(0, tokens.length - 1)]), Integer.class).getBody());
        }

        return ids.toArray(new Integer[ids.size()]);
    }

    private Date getRangedDate() {
        return new Date(getRandom(settings.getDateOnStart().getTime(), settings.getDateOnEnd().getTime()));
    }
}
