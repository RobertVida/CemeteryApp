package generators;

import org.springframework.http.HttpMethod;
import ro.InnovaTeam.cemeteryApp.ParcelDTO;

import java.util.*;

import static utils.Utils.*;

/**
 * Created by robert on 1/16/2015.
 */
public class ParcelGenerator {

    Map<Integer, List<String>> parcels = new HashMap<Integer, List<String>>();

    public Integer[] generate(String[] tokens, final Integer[] cemeteryIds, final Integer count) {
        List<Integer> ids = new ArrayList<>();

        for(int i = 0 ; i < count ; i++) {
            ids.add(getJSONRestTemplate().exchange("http://localhost:8080/rest-server/parcel", HttpMethod.PUT, authorizationWrapper(
                    getEntity(cemeteryIds, count)
                    , tokens[getRandom(0, tokens.length - 1)]), Integer.class).getBody());
        }

        return ids.toArray(new Integer[ids.size()]);
    }

    private ParcelDTO getEntity(final Integer[] cemeteryIds, final Integer count) {
        String name = (char)(random.nextInt(26) + 'A') + " - " + getRandom(1, count);
        Integer cemeteryId = cemeteryIds[getRandom(0, cemeteryIds.length-1)];

        if(parcels.get(cemeteryId) != null){
            while(parcels.get(cemeteryId).contains(name)){
                name = (char)(random.nextInt(26) + 'A') + " - " + getRandom(1, count);
            }
        } else {
            parcels.put(cemeteryId, new ArrayList<String>());
        }

        parcels.get(cemeteryId).add(name);

        ParcelDTO parcel = new ParcelDTO();
        parcel.setName(name);
        parcel.setCemeteryId(cemeteryId);
        return parcel;
    }
}
