package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.*;
import ro.InnovaTeam.cemeteryApp.eao.BurialDocumentEAO;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;

/**
 * Created by robert on 12/31/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class DeceasedTest extends EntityTest{

    @Autowired
    private BurialDocumentEAO documentEAO;

    @Test
    public void test_Create_Get_Delete_Deceased_Caregiver() throws Exception {
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
        DeceasedDTO[] deceasedDTOs = readJsonFromFile("/deceased.json", DeceasedDTO[].class);
        deceasedDTOs[0].setStructureId(graveDTOs[0].getId());
        deceasedDTOs[0] = deceased.create(deceasedDTOs[0]);
        assertThat(deceased.count(getFilter()), equalTo(1));

        //get
        DeceasedDTO deceasedDTO = deceased.get(deceasedDTOs[0]);
        deceasedDTOs[0].setBurialDocumentId(deceasedDTO.getBurialDocumentId());
        assertThat(compare(deceasedDTOs[0], deceasedDTO), equalTo(true));

        //delete
        deceasedDTO = deceased.delete(deceasedDTOs[0]);
        assertThat(compare(deceasedDTOs[0], deceasedDTO), equalTo(true));

        //get
        deceasedDTO = deceased.get(deceasedDTOs[0]);
        assertThat(deceasedDTO, equalTo(null));
        assertThat(deceased.count(getFilter()), equalTo(0));
    }

    @Test
    public void test_Create_Get_Delete_Deceased_NoCaregiver() throws Exception {
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
        DeceasedDTO[] deceasedDTOs = readJsonFromFile("/deceasedWithNoCaregiver.json", DeceasedDTO[].class);
        deceasedDTOs[0].setStructureId(graveDTOs[0].getId());
        deceasedDTOs[0] = deceased.create(deceasedDTOs[0]);
        assertThat(deceased.count(getFilter()), equalTo(1));

        //get
        DeceasedDTO deceasedDTO = deceased.get(deceasedDTOs[0]);
        deceasedDTOs[0].setBurialDocumentId(deceasedDTO.getBurialDocumentId());
        assertThat(compare(deceasedDTOs[0], deceasedDTO), equalTo(true));
        assertThat(deceasedDTO.getNoCaregiverDocumentId(), not(equalTo(null)));

        //delete
        deceasedDTO = deceased.delete(deceasedDTOs[0]);
        assertThat(compare(deceasedDTOs[0], deceasedDTO), equalTo(true));

        //get
        deceasedDTO = deceased.get(deceasedDTOs[0]);
        assertThat(deceasedDTO, equalTo(null));
        assertThat(deceased.count(getFilter()), equalTo(0));
    }

    @Test
    public void test_Filter_Deceased_Grave() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = setupCemeteries();
        ParcelDTO[] parcelDTOs = setupParcels(cemeteryDTOs);
        GraveDTO[] graveDTOs = setupGraves(parcelDTOs);

        //create
        DeceasedDTO[] deceasedDTOs = setupDeceased(graveDTOs);

        //filter
        Integer deceasedCount = deceased.count(getFilter());
        assertThat(deceasedCount, equalTo(deceasedDTOs.length));

        //filter
        DeceasedList filterResult = deceased.filter(getFilter(1, 20, null, "catolic"));
        assertThat(filterResult.getContent().size(), equalTo(1));
        assertThat(filterResult.getContent().size(), not(equalTo(deceasedCount)));

        filterResult = deceased.filter(getFilter(1, 20, null, "ortodox"));
        assertThat(filterResult.getContent().size(), equalTo(2));
        assertThat(filterResult.getContent().size(), not(equalTo(deceasedCount)));

//        ToDo: implement find by parentId
//        filterResult = deceased.filter(getFilter(1, 20, graveDTOs[0].getId(), null));
//        assertThat(filterResult.getContent().size(), equalTo(1));
//        assertThat(filterResult.getContent().size(), not(equalTo(deceasedCount)));
//
//        filterResult = deceased.filter(getFilter(1, 20, graveDTOs[1].getId(), null));
//        assertThat(filterResult.getContent().size(), equalTo(2));
//        assertThat(filterResult.getContent().size(), not(equalTo(deceasedCount)));
    }

    @Test
    public void test_Filter_Deceased_Monument() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = setupCemeteries();
        ParcelDTO[] parcelDTOs = setupParcels(cemeteryDTOs);
        MonumentDTO[] monumentDTOs = setupMonuments(parcelDTOs);

        //create
        DeceasedDTO[] deceasedDTOs = setupDeceased(monumentDTOs);

        //filter
        Integer deceasedCount = deceased.count(getFilter());
        assertThat(deceasedCount, equalTo(deceasedDTOs.length));

        //filter
        DeceasedList filterResult = deceased.filter(getFilter(1, 20, null, "catolic"));
        assertThat(filterResult.getContent().size(), equalTo(1));
        assertThat(filterResult.getContent().size(), not(equalTo(deceasedCount)));

        filterResult = deceased.filter(getFilter(1, 20, null, "ortodox"));
        assertThat(filterResult.getContent().size(), equalTo(2));
        assertThat(filterResult.getContent().size(), not(equalTo(deceasedCount)));

//        ToDo: implement find by parentId
//        filterResult = deceased.filter(getFilter(1, 20, monumentDTOs[0].getId(), null));
//        assertThat(filterResult.getContent().size(), equalTo(1));
//        assertThat(filterResult.getContent().size(), not(equalTo(deceasedCount)));
//
//        filterResult = deceased.filter(getFilter(1, 20, monumentDTOs[1].getId(), null));
//        assertThat(filterResult.getContent().size(), equalTo(2));
//        assertThat(filterResult.getContent().size(), not(equalTo(deceasedCount)));
    }

    @Test
    public void test_Update_Grave() throws Exception {
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
        DeceasedDTO[] deceasedDTOs = readJsonFromFile("/deceased.json", DeceasedDTO[].class);
        deceasedDTOs[0].setStructureId(graveDTOs[0].getId());
        deceasedDTOs[0] = deceased.create(deceasedDTOs[0]);
        assertThat(deceased.count(getFilter()), equalTo(1));

        //get
        DeceasedDTO deceasedDTO = deceased.get(deceasedDTOs[0]);
        deceasedDTOs[0].setBurialDocumentId(deceasedDTO.getBurialDocumentId());
        assertThat(compare(deceasedDTOs[0], deceasedDTO), equalTo(true));

        //update
        deceasedDTOs[0].setFirstName("new Name");
        deceasedDTO = deceased.get(deceasedDTOs[0]);
        assertThat(compare(deceasedDTOs[0], deceasedDTO), equalTo(false));

        deceased.update(deceasedDTOs[0]);
        deceasedDTO = deceased.get(deceasedDTOs[0]);
        assertThat(compare(deceasedDTOs[0], deceasedDTO), equalTo(true));
    }

    private Boolean compare(DeceasedDTO request, DeceasedDTO response) {
        return request.getId().equals(response.getId())
                && request.getFirstName().equals(response.getFirstName())
                && request.getLastName().equals(response.getLastName())
                && request.getCnp().equals(response.getCnp())
                && request.getReligion().equals(response.getReligion())
                && request.getDiedOn().equals(response.getDiedOn())
                && request.getBurialDocumentId().equals(response.getBurialDocumentId())
                && request.getStructureId().equals(response.getStructureId())
                && request.getBurialOn().equals(response.getBurialOn());

    }
}
