package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.CemeteryDTO;
import ro.InnovaTeam.cemeteryApp.ParcelDTO;
import ro.InnovaTeam.cemeteryApp.ParcelList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by robert on 12/25/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ParcelTest extends EntityTest {

    @Test
    public void test_Create_Get_Delete_Parcel() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = readJsonFromFile("/cemeteries.json", CemeteryDTO[].class);
        cemeteryDTOs[0] = cemetery.create(cemeteryDTOs[0]);

        //create
        ParcelDTO[] parcelDTOs = readJsonFromFile("/parcels.json", ParcelDTO[].class);
        parcelDTOs[0].setCemeteryId(cemeteryDTOs[0].getId());
        parcelDTOs[0] = parcel.create(parcelDTOs[0]);
        assertThat(parcel.count(getFilter()), equalTo(1));

        //get
        ParcelDTO parcelDTO = parcel.get(parcelDTOs[0]);
        assertThat(compare(parcelDTOs[0], parcelDTO), equalTo(true));

        //delete
        parcelDTO = parcel.delete(parcelDTOs[0]);
        assertThat(compare(parcelDTOs[0], parcelDTO), equalTo(true));

        //get
        parcelDTO = parcel.get(parcelDTOs[0]);
        assertThat(parcelDTO, equalTo(null));
        assertThat(parcel.count(getFilter()), equalTo(0));
    }

    @Test
    public void test_Filter_Parcels() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = setupCemeteries();

        //create
        ParcelDTO[] parcelDTOs = setupParcels(cemeteryDTOs);

        //filter
        Integer parcelCount = parcel.count(getFilter());
        assertThat(parcelCount, equalTo(parcelDTOs.length));

        //filter
        ParcelList filterResult = parcel.filter(cemeteryDTOs[0].getId());
        assertThat(filterResult.getContent().size(), equalTo(1));

        filterResult = parcel.filter(cemeteryDTOs[1].getId());
        assertThat(filterResult.getContent().size(), equalTo(parcelDTOs.length - 1));
        assertThat(filterResult.getContent().size(), not(equalTo(parcelCount)));

        filterResult = parcel.filter(getFilter(1, 20, null, "1"));
        assertThat(filterResult.getContent().size(), equalTo(3));
        assertThat(filterResult.getContent().size(), not(equalTo(parcelCount)));

        filterResult = parcel.filter(getFilter(1, 20, null, "1 3"));
        assertThat(filterResult.getContent().size(), equalTo(2));
        assertThat(filterResult.getContent().size(), not(equalTo(parcelCount)));

        filterResult = parcel.filter(getFilter(1, 20, null, "1 3 2"));
        assertThat(filterResult.getContent().size(), equalTo(1));
        assertThat(filterResult.getContent().size(), not(equalTo(parcelCount)));

        filterResult = parcel.filter(getFilter(1, 20, cemeteryDTOs[1].getId(), "1"));
        assertThat(filterResult.getContent().size(), equalTo(2));
        assertThat(filterResult.getContent().size(), not(equalTo(parcelCount)));
    }

    @Test
    public void test_Update_Parcel() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = readJsonFromFile("/cemeteries.json", CemeteryDTO[].class);
        cemeteryDTOs[0] = cemetery.create(cemeteryDTOs[0]);

        //create
        ParcelDTO[] parcelDTOs = readJsonFromFile("/parcels.json", ParcelDTO[].class);
        parcelDTOs[0].setCemeteryId(cemeteryDTOs[0].getId());
        parcelDTOs[0] = parcel.create(parcelDTOs[0]);
        assertThat(parcel.count(getFilter()), equalTo(1));

        //get
        ParcelDTO parcelDTO = parcel.get(parcelDTOs[0]);
        assertThat(compare(parcelDTOs[0], parcelDTO), equalTo(true));

        //update
        parcelDTOs[0].setName("new Name");
        parcelDTO = parcel.get(parcelDTOs[0]);
        assertThat(compare(parcelDTOs[0], parcelDTO), equalTo(false));

        parcel.update(parcelDTOs[0]);
        parcelDTO = parcel.get(parcelDTOs[0]);
        assertThat(compare(parcelDTOs[0], parcelDTO), equalTo(true));
    }

    private Boolean compare(ParcelDTO request, ParcelDTO response) {
        return request.getId().equals(response.getId())
                && request.getName().equals(response.getName())
                && request.getCemeteryId().equals(response.getCemeteryId());
    }
}
