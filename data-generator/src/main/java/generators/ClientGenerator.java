package generators;

import org.springframework.http.HttpMethod;
import ro.InnovaTeam.cemeteryApp.ClientDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static utils.Utils.*;
import static utils.Utils.getRandom;

/**
 * Created by robert on 1/16/2015.
 */
public class ClientGenerator {

    public static ClientSettings settings;

    public static class ClientSettings{
        private String[] firstName;
        private String[] lastName;
        private String[] religion;
        private Date dateOnStart;
        private Date dateOnEnd;
        private String[] address;

        public ClientSettings() {
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

    public ClientGenerator() {
        try {
            settings = readJsonFromFile("/personSettings.json", ClientSettings.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer[] generate(String[] tokens, final Integer count){
        List<Integer> ids = new ArrayList<>();

        for(int i = 0 ; i < count ; i++) {
            ids.add(getJSONRestTemplate().exchange("http://localhost:8080/rest-server/client", HttpMethod.PUT, authorizationWrapper(
                    new ClientDTO() {{
                        setFirstName(settings.getFirstName()[getRandom(0, settings.getFirstName().length-1)]);
                        setLastName(settings.getLastName()[getRandom(0, settings.getLastName().length - 1)]);
                        setCnp(Long.toString(new Date().getTime() % 10000000000000L));
                        setPhoneNumber(Long.toString(new Date().getTime() & 10000000000L));
                        setAddress(settings.getAddress()[getRandom(0, settings.getAddress().length-1)]);
                    }}
                    , tokens[getRandom(0, tokens.length - 1)]), Integer.class).getBody());
        }

        return ids.toArray(new Integer[ids.size()]);
    }
}
