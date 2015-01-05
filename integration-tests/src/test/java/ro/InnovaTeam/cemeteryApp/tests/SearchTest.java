package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.*;
import ro.InnovaTeam.cemeteryApp.registers.BurialRegistry;
import ro.InnovaTeam.cemeteryApp.registers.GraveRegistry;
import ro.InnovaTeam.cemeteryApp.registers.Registry;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by robert on 1/5/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SearchTest extends EntityTest{

    CemeteryDTO[] cemeteryDTOs;
    ParcelDTO[] parcelDTOs;
    GraveDTO[] graveDTOs;
    MonumentDTO[] monumentDTOs;
    StructureHistoryEntryDTO[] historyEntryDTOs;
    ClientDTO[] clientDTOs;
    RestingPlaceRequestDTO[] requestDTOs;
    ContractDTO[] contractDTOs;
    DeceasedDTO[] deceasedDTOs;

    public void setup() throws Exception{
        cemeteryDTOs = setupCemeteries();
        parcelDTOs = setupParcels(cemeteryDTOs);
        graveDTOs = setupGraves(parcelDTOs);
        monumentDTOs = setupMonuments(parcelDTOs);
        historyEntryDTOs = setupHistoryEntries(graveDTOs);
        historyEntryDTOs = setupHistoryEntries(monumentDTOs);
        clientDTOs = setupClients();
        requestDTOs = setupRequests(clientDTOs);
        contractDTOs = setupContract(graveDTOs, requestDTOs);
        deceasedDTOs = setupDeceased(graveDTOs);
    }

    @Test
    public void searchBurials() throws Exception{
        setup();

        Integer count = getCount("/burialRegistry/count", getFilter());
        assertThat(getContent("/burialRegistry", getFilter(), BurialRegistry.class).getContent().size(), equalTo(count));
        assertThat(getContent("/burialRegistry", getFilter(), BurialRegistry.class).getContent().size(), equalTo(3));

        count = getCount("/burialRegistry/count", getFilter(1, 20, null, "ortodox"));
        assertThat(getContent("/burialRegistry", getFilter(1, 20, null, "ortodox"), BurialRegistry.class).getContent().size(), equalTo(count));
        assertThat(getContent("/burialRegistry", getFilter(1, 20, null, "ortodox"), BurialRegistry.class).getContent().size(), equalTo(2));
    }

    @Test
    public void searchGraves() throws Exception{
        setup();

        Integer count = getCount("/graveRegistry/count", getFilter());
        assertThat(getContent("/graveRegistry", getFilter(), GraveRegistry.class).getContent().size(), equalTo(count));
        assertThat(getContent("/graveRegistry", getFilter(), GraveRegistry.class).getContent().size(), equalTo(4));

        count = getCount("/graveRegistry/count", getFilter(1, 20, null , "address1"));
        assertThat(getContent("/graveRegistry", getFilter(1, 20, null , "address1"), GraveRegistry.class).getContent().size(), equalTo(count));
        assertThat(getContent("/graveRegistry", getFilter(1, 20, null , "address1"), GraveRegistry.class).getContent().size(), equalTo(1));
    }

    private <T extends Registry> T getContent(String url, FilterDTO filter, Class<T> clazz) throws Exception {
        return getResultAsObject(performFilter(url, filter).andReturn(), clazz);
    }

    private Integer getCount(String url, FilterDTO filter) throws Exception {
        return getResultAsInt(performFilter(url, filter).andReturn());
    }
}
