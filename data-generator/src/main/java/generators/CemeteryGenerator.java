package generators;

import org.springframework.http.HttpMethod;
import ro.InnovaTeam.cemeteryApp.CemeteryDTO;

import java.util.ArrayList;
import java.util.List;

import static utils.Utils.authorizationWrapper;
import static utils.Utils.getJSONRestTemplate;
import static utils.Utils.getRandom;

/**
 * Created by robert on 1/16/2015.
 */
public class CemeteryGenerator {

    public Integer[] generate(String[] tokens) {
        List<Integer> ids = new ArrayList<>();
        ids.add(getJSONRestTemplate().exchange("http://localhost:8080/rest-server/cemetery", HttpMethod.PUT, authorizationWrapper(
                new CemeteryDTO() {{
                    setName("Cimitirul Central");
                    setAddress("Strada Matei Basarab Cluj-Napoca");
                }}
                , tokens[getRandom(0, tokens.length - 1)]), Integer.class).getBody());

        ids.add(getJSONRestTemplate().exchange("http://localhost:8080/rest-server/cemetery", HttpMethod.PUT, authorizationWrapper(
                new CemeteryDTO() {{
                    setName("Cimitirul Militar");
                    setAddress("Drumul Pepiniera Cluj-Napoca");
                }}
                , tokens[getRandom(0, tokens.length - 1)]), Integer.class).getBody());

        ids.add(getJSONRestTemplate().exchange("http://localhost:8080/rest-server/cemetery", HttpMethod.PUT, authorizationWrapper(
                new CemeteryDTO() {{
                    setName("Cimitirul Hajongard");
                    setAddress("Strada Avram Iancu Cluj-Napoca");
                }}
                , tokens[getRandom(0, tokens.length - 1)]), Integer.class).getBody());
        return ids.toArray(new Integer[ids.size()]);
    }
}
