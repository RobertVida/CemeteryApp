package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by robert on 12/25/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MonumentTest extends EntityTest {

    @Test
    public void test_Create_Get_Delete_Monument() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = readJsonFromFile("/cemeteries.json", CemeteryDTO[].class);
        cemeteryDTOs[0] = cemetery.create(cemeteryDTOs[0]);

        ParcelDTO[] parcelDTOs = readJsonFromFile("/parcels.json", ParcelDTO[].class);
        parcelDTOs[0].setCemeteryId(cemeteryDTOs[0].getId());
        parcelDTOs[0] = parcel.create(parcelDTOs[0]);

        //create
        MonumentDTO[] monumentDTOs = readJsonFromFile("/monuments.json", MonumentDTO[].class);
        monumentDTOs[0].setParcelId(parcelDTOs[0].getId());
        monumentDTOs[0] = monument.create(monumentDTOs[0]);

        //get
        MonumentDTO monumentDTO = monument.get(monumentDTOs[0]);
        assertThat(compare(monumentDTOs[0], monumentDTO), equalTo(true));

        //delete
        monumentDTO = monument.delete(monumentDTOs[0]);
        assertThat(compare(monumentDTOs[0], monumentDTO), equalTo(true));

        //get
        monumentDTO = monument.get(monumentDTOs[0]);
        assertThat(monumentDTO, equalTo(null));
    }

    @Test
    public void test_Filter_Monuments() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = setupCemeteries();
        ParcelDTO[] parcelDTOs = setupParcels(cemeteryDTOs);

        //create
        MonumentDTO[] monumentDTOs = setupMonuments(parcelDTOs);

        //filter
        MonumentList filterResult = monument.filter(getFilter());
        assertThat(filterResult.getContent().size(), equalTo(monumentDTOs.length));

        //filter
        filterResult = monument.filter(getFilter(1, 20, parcelDTOs[0].getId(), null));
        assertThat(filterResult.getContent().size(), equalTo(1));

        filterResult = monument.filter(getFilter(1, 20, parcelDTOs[1].getId(), null));
        assertThat(filterResult.getContent().size(), equalTo(monumentDTOs.length - 1));

        filterResult = monument.filter(getFilter(1, 20, null, "1"));
        assertThat(filterResult.getContent().size(), equalTo(2));

        filterResult = monument.filter(getFilter(1, 20, null, "y"));
        assertThat(filterResult.getContent().size(), equalTo(1));
    }

    @Test
    public void test_Update_Monument() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = readJsonFromFile("/cemeteries.json", CemeteryDTO[].class);
        cemeteryDTOs[0] = cemetery.create(cemeteryDTOs[0]);

        ParcelDTO[] parcelDTOs = readJsonFromFile("/parcels.json", ParcelDTO[].class);
        parcelDTOs[0].setCemeteryId(cemeteryDTOs[0].getId());
        parcelDTOs[0] = parcel.create(parcelDTOs[0]);

        //create
        MonumentDTO[] monumentDTOs = readJsonFromFile("/monuments.json", MonumentDTO[].class);
        monumentDTOs[0].setParcelId(parcelDTOs[0].getId());
        monumentDTOs[0] = monument.create(monumentDTOs[0]);

        //get
        MonumentDTO monumentDTO = monument.get(monumentDTOs[0]);
        assertThat(compare(monumentDTOs[0], monumentDTO), equalTo(true));

        //update
        monumentDTOs[0].setLength(66);
        monumentDTO = monument.get(monumentDTOs[0]);
        assertThat(compare(monumentDTOs[0], monumentDTO), equalTo(false));

        monument.update(monumentDTOs[0]);
        monumentDTO = monument.get(monumentDTOs[0]);
        assertThat(compare(monumentDTOs[0], monumentDTO), equalTo(true));
    }

    private Boolean compare(MonumentDTO request, MonumentDTO response) {
        return request.getId().equals(response.getId())
                && request.getType().equals(response.getType())
                && request.getCreatedOn().equals(response.getCreatedOn())
                && request.getWidth().equals(response.getWidth())
                && request.getLength().equals(response.getLength())
                && request.getParcelId().equals(response.getParcelId())
                && request.getName().equals(response.getName())
                && request.getDescription().equals(response.getDescription());

    }
}
