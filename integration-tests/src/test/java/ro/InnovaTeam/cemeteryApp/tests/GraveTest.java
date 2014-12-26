package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.CemeteryDTO;
import ro.InnovaTeam.cemeteryApp.GraveDTO;
import ro.InnovaTeam.cemeteryApp.GraveList;
import ro.InnovaTeam.cemeteryApp.ParcelDTO;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by robert on 12/25/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class GraveTest extends EntityTest {

    @Test
    public void test_Create_Get_Delete_Grave() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = readJsonFromFile("/cemeteries.json", CemeteryDTO[].class);
        cemeteryDTOs[0] = cemetery.create(cemeteryDTOs[0]);

        ParcelDTO[] parcelDTOs = readJsonFromFile("/parcels.json", ParcelDTO[].class);
        parcelDTOs[0].setCemeteryId(cemeteryDTOs[0].getId());
        parcelDTOs[0] = parcel.create(parcelDTOs[0]);

        //create
        GraveDTO[] graveDTOs = readJsonFromFile("/graves.json", GraveDTO[].class);
        graveDTOs[0].setParcelId(parcelDTOs[0].getId());
        graveDTOs[0] = grave.create(graveDTOs[0]);

        //get
        GraveDTO graveDTO = grave.get(graveDTOs[0]);
        assertThat(compare(graveDTOs[0], graveDTO), equalTo(true));

        //delete
        graveDTO = grave.delete(graveDTOs[0]);
        assertThat(compare(graveDTOs[0], graveDTO), equalTo(true));

        //get
        graveDTO = grave.get(graveDTOs[0]);
        assertThat(graveDTO, equalTo(null));
    }

    @Test
    public void test_Filter_Graves() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = setupCemeteries();
        ParcelDTO[] parcelDTOs = setupParcels(cemeteryDTOs);

        //create
        GraveDTO[] graveDTOs = setupGraves(parcelDTOs);

        //filter
        GraveList filterResult = grave.filter(getFilter());
        assertThat(filterResult.getContent().size(), equalTo(graveDTOs.length));

        //filter
        filterResult = grave.filter(getFilter(1, 20, parcelDTOs[0].getId(), null));
        assertThat(filterResult.getContent().size(), equalTo(1));

        filterResult = grave.filter(getFilter(1, 20, parcelDTOs[1].getId(), null));
        assertThat(filterResult.getContent().size(), equalTo(graveDTOs.length - 1));
    }

    @Test
    public void test_Update_Grave() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = readJsonFromFile("/cemeteries.json", CemeteryDTO[].class);
        cemeteryDTOs[0] = cemetery.create(cemeteryDTOs[0]);

        ParcelDTO[] parcelDTOs = readJsonFromFile("/parcels.json", ParcelDTO[].class);
        parcelDTOs[0].setCemeteryId(cemeteryDTOs[0].getId());
        parcelDTOs[0] = parcel.create(parcelDTOs[0]);

        //create
        GraveDTO[] graveDTOs = readJsonFromFile("/graves.json", GraveDTO[].class);
        graveDTOs[0].setParcelId(parcelDTOs[0].getId());
        graveDTOs[0] = grave.create(graveDTOs[0]);

        //get
        GraveDTO graveDTO = grave.get(graveDTOs[0]);
        assertThat(compare(graveDTOs[0], graveDTO), equalTo(true));

        //update
        graveDTOs[0].setLength(66);
        graveDTO = grave.get(graveDTOs[0]);
        assertThat(compare(graveDTOs[0], graveDTO), equalTo(false));

        grave.update(graveDTOs[0]);
        graveDTO = grave.get(graveDTOs[0]);
        assertThat(compare(graveDTOs[0], graveDTO), equalTo(true));
    }

    private Boolean compare(GraveDTO request, GraveDTO response) {
        return request.getId().equals(response.getId())
                && request.getType().equals(response.getType())
                && request.getCreatedOn().equals(response.getCreatedOn())
                && request.getWidth().equals(response.getWidth())
                && request.getLength().equals(response.getLength())
                && request.getParcelId().equals(response.getParcelId());

    }
}
