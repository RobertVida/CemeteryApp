package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.CemeteryDTO;
import ro.InnovaTeam.cemeteryApp.CemeteryList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by robert on 11/24/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CemeteryTest extends EntityTest {

    @Test
    public void test_Create_Get_Delete_Cemetery() throws Exception {
        //create
        CemeteryDTO[] cemeteryDTOs = readJsonFromFile("/cemeteries.json", CemeteryDTO[].class);
        cemeteryDTOs[0] = cemetery.create(cemeteryDTOs[0]);

        //get
        CemeteryDTO cemeteryDTO = cemetery.get(cemeteryDTOs[0]);
        assertThat(compare(cemeteryDTOs[0], cemeteryDTO), equalTo(true));

        //delete
        cemeteryDTO = cemetery.delete(cemeteryDTOs[0]);
        assertThat(compare(cemeteryDTOs[0], cemeteryDTO), equalTo(true));

        //get
        cemeteryDTO = cemetery.get(cemeteryDTOs[0]);
        assertThat(cemeteryDTO, equalTo(null));
    }

    @Test
    public void test_Filter_Cemeteries() throws Exception {
        //create
        CemeteryDTO[] cemeteryDTOs = setupCemeteries();

        //filter
        CemeteryList filterResult = cemetery.filter(getFilter());
        assertThat(filterResult.getContent().size(), equalTo(cemeteryDTOs.length));

        filterResult = cemetery.filter(getFilter(1, 20, null, "1"));
        assertThat(filterResult.getContent().size(), equalTo(2));
    }

    @Test
    public void test_Update_Cemetery() throws Exception {
        //create
        CemeteryDTO[] cemeteryDTOs = readJsonFromFile("/cemeteries.json", CemeteryDTO[].class);
        cemeteryDTOs[0] = cemetery.create(cemeteryDTOs[0]);

        //get
        CemeteryDTO cemeteryDTO = cemetery.get(cemeteryDTOs[0]);
        assertThat(compare(cemeteryDTOs[0], cemeteryDTO), equalTo(true));

        //update
        cemeteryDTOs[0].setName("newName");
        cemeteryDTO = cemetery.get(cemeteryDTOs[0]);
        assertThat(compare(cemeteryDTOs[0], cemeteryDTO), equalTo(false));

        cemetery.update(cemeteryDTOs[0]);
        cemeteryDTO = cemetery.get(cemeteryDTOs[0]);
        assertThat(compare(cemeteryDTOs[0], cemeteryDTO), equalTo(true));
    }

    private Boolean compare(CemeteryDTO request, CemeteryDTO response) {
        return request.getId().equals(response.getId())
                && request.getName().equals(response.getName())
                && request.getAddress().equals(response.getAddress());
    }


}
