package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by robert on 1/1/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class StructureHistoryEntryTest extends EntityTest {

    @Test
    public void test_Create_Get_Delete_StructureHistoryEntry() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = readJsonFromFile("/cemeteries.json", CemeteryDTO[].class);
        cemeteryDTOs[0] = cemetery.create(cemeteryDTOs[0]);

        ParcelDTO[] parcelDTOs = readJsonFromFile("/parcels.json", ParcelDTO[].class);
        parcelDTOs[0].setCemeteryId(cemeteryDTOs[0].getId());
        parcelDTOs[0] = parcel.create(parcelDTOs[0]);

        GraveDTO[] graveDTOs = readJsonFromFile("/graves.json", GraveDTO[].class);
        graveDTOs[0].setParcelId(parcelDTOs[0].getId());
        graveDTOs[0] = grave.create(graveDTOs[0]);

        //create
        StructureHistoryEntryDTO[] entryDTOs = readJsonFromFile("/structureHistory.json", StructureHistoryEntryDTO[].class);
        entryDTOs[0].setStructureId(graveDTOs[0].getId());
        entryDTOs[0] = structureHistory.create(entryDTOs[0]);
        assertThat(structureHistory.count(getFilter()), equalTo(1));

        //get
        StructureHistoryEntryDTO entryDTO = structureHistory.get(entryDTOs[0]);
        assertThat(compare(entryDTOs[0], entryDTO), equalTo(true));

        //delete
        entryDTO = structureHistory.delete(entryDTOs[0]);
        assertThat(compare(entryDTOs[0], entryDTO), equalTo(true));

        //get
        entryDTO = structureHistory.get(entryDTOs[0]);
        assertThat(entryDTO, equalTo(null));
        assertThat(structureHistory.count(getFilter()), equalTo(0));
    }

    @Test
    public void test_Filter_StructureHistoryEntry() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = setupCemeteries();
        ParcelDTO[] parcelDTOs = setupParcels(cemeteryDTOs);
        GraveDTO[] graveDTOs = setupGraves(parcelDTOs);

        //create
        StructureHistoryEntryDTO[] entryDTOs = setupHistoryEntries(graveDTOs);

        //filter
        Integer entryCount = structureHistory.count(getFilter());
        assertThat(entryCount, equalTo(entryDTOs.length));

        //filter
        StructureHistoryEntryList filterResult = structureHistory.filter(getFilter());
        assertThat(filterResult.getContent().size(), equalTo(entryCount));

        filterResult = structureHistory.filter(getFilter(1, 20, graveDTOs[0].getId(), null));
        assertThat(filterResult.getContent().size(), equalTo(1));
        assertThat(filterResult.getContent().size(), not(equalTo(entryCount)));

        filterResult = structureHistory.filter(getFilter(1, 20, graveDTOs[1].getId(), null));
        assertThat(filterResult.getContent().size(), equalTo(structureHistory.count(getFilter(1, 20, graveDTOs[1].getId(), null))));
        assertThat(filterResult.getContent().size(), not(equalTo(entryCount)));

        filterResult = structureHistory.filter(getFilter(1, 20, null, "x"));
        assertThat(filterResult.getContent().size(), equalTo(2));
        assertThat(filterResult.getContent().size(), not(equalTo(entryCount)));

        filterResult = structureHistory.filter(getFilter(1, 20, null, "y"));
        assertThat(filterResult.getContent().size(), equalTo(structureHistory.count(getFilter(1, 20, null, "y"))));
        assertThat(filterResult.getContent().size(), not(equalTo(entryCount)));
    }

    @Test
    public void test_Update_StructureHistoryEntry() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = readJsonFromFile("/cemeteries.json", CemeteryDTO[].class);
        cemeteryDTOs[0] = cemetery.create(cemeteryDTOs[0]);

        ParcelDTO[] parcelDTOs = readJsonFromFile("/parcels.json", ParcelDTO[].class);
        parcelDTOs[0].setCemeteryId(cemeteryDTOs[0].getId());
        parcelDTOs[0] = parcel.create(parcelDTOs[0]);

        GraveDTO[] graveDTOs = readJsonFromFile("/graves.json", GraveDTO[].class);
        graveDTOs[0].setParcelId(parcelDTOs[0].getId());
        graveDTOs[0] = grave.create(graveDTOs[0]);

        //create
        StructureHistoryEntryDTO[] entryDTOs = readJsonFromFile("/structureHistory.json", StructureHistoryEntryDTO[].class);
        entryDTOs[0].setStructureId(graveDTOs[0].getId());
        entryDTOs[0] = structureHistory.create(entryDTOs[0]);
        assertThat(structureHistory.count(getFilter()), equalTo(1));

        //get
        StructureHistoryEntryDTO entryDTO = structureHistory.get(entryDTOs[0]);
        assertThat(compare(entryDTOs[0], entryDTO), equalTo(true));

        //update
        entryDTOs[0].setDescription("new descr");
        entryDTO = structureHistory.get(entryDTOs[0]);
        assertThat(compare(entryDTOs[0], entryDTO), equalTo(false));

        structureHistory.update(entryDTOs[0]);
        entryDTO = structureHistory.get(entryDTOs[0]);
        assertThat(compare(entryDTOs[0], entryDTO), equalTo(true));
    }

    private Boolean compare(StructureHistoryEntryDTO request, StructureHistoryEntryDTO response) {
        return request.getId().equals(response.getId())
                && request.getStructureId().equals(response.getStructureId())
                && request.getDate().equals(response.getDate())
                && request.getDescription().equals(response.getDescription());

    }
}
