package ro.InnovaTeam.cemeteryApp.tests;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.*;
import ro.InnovaTeam.cemeteryApp.registers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
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

    public void setupNoCaregivers() throws Exception {
        List<DeceasedDTO> deceasedAll = new ArrayList<DeceasedDTO>();
        Collections.addAll(deceasedAll, deceasedDTOs);
        Collections.addAll(deceasedAll, setupDeceasedNoCaregiver(monumentDTOs));

        deceasedDTOs = deceasedAll.toArray(new DeceasedDTO[deceasedAll.size()]);
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

    @Test
    public void searchMonuments() throws Exception{
        setup();

        Integer count = getCount("/monumentRegistry/count", getFilter());
        assertThat(getContent("/monumentRegistry", getFilter(), MonumentRegistry.class).getContent().size(), equalTo(count));
        assertThat(getContent("/monumentRegistry", getFilter(), MonumentRegistry.class).getContent().size(), equalTo(3));

        count = getCount("/monumentRegistry/count", getFilter(1, 20, null , "address1"));
        assertThat(getContent("/monumentRegistry", getFilter(1, 20, null , "address1"), MonumentRegistry.class).getContent().size(), equalTo(count));
        assertThat(getContent("/monumentRegistry", getFilter(1, 20, null , "address1"), MonumentRegistry.class).getContent().size(), equalTo(0));
    }

    @Test
    public void searchDeceased() throws Exception{
        setup();
        setupNoCaregivers();

        Integer count = getCount("/deceasedRegistry/ASC/DESC/count", getFilter());
        assertThat(count, lessThan(deceasedDTOs.length));
        assertThat(count, equalTo(3));

        DeceasedRegistry registry = getContent("/deceasedRegistry/ASC/DESC", getFilter(), DeceasedRegistry.class);
        assertThat(count, Matchers.equalTo(registry.getContent().size()));

        for(int i = 1 ; i < registry.getContent().size() ; i++){
            assertThat(registry.getContent().get(i-1).getLastName().compareTo(registry.getContent().get(i).getLastName()), lessThanOrEqualTo(0));
        }

        registry = getContent("/deceasedRegistry/DESC/DESC", getFilter(), DeceasedRegistry.class);
        assertThat(count, Matchers.equalTo(registry.getContent().size()));

        for(int i = 1 ; i < registry.getContent().size() ; i++){
            assertThat(registry.getContent().get(i-1).getLastName().compareTo(registry.getContent().get(i).getLastName()), greaterThanOrEqualTo(0));
        }
    }

    @Test
    public void searchDeceasedNoCaregiver() throws Exception{
        setup();
        setupNoCaregivers();

        Integer count = getCount("/deceasedRegistryNoCaregiver/ASC/DESC/count", getFilter());
        assertThat(count, lessThan(deceasedDTOs.length));
        assertThat(count, equalTo(4));

        DeceasedNoCaregiverRegistry registry = getContent("/deceasedRegistryNoCaregiver/ASC/DESC", getFilter(), DeceasedNoCaregiverRegistry.class);
        assertThat(count, Matchers.equalTo(registry.getContent().size()));

        for(int i = 1 ; i < registry.getContent().size() ; i++){
            assertThat(registry.getContent().get(i-1).getLastName().compareTo(registry.getContent().get(i).getLastName()), lessThanOrEqualTo(0));
        }

        registry = getContent("/deceasedRegistryNoCaregiver/DESC/DESC", getFilter(), DeceasedNoCaregiverRegistry.class);
        assertThat(count, Matchers.equalTo(registry.getContent().size()));

        for(int i = 1 ; i < registry.getContent().size() ; i++){
            assertThat(registry.getContent().get(i-1).getLastName().compareTo(registry.getContent().get(i).getLastName()), greaterThanOrEqualTo(0));
        }
    }

    @Test
    public void searchRequests() throws Exception{
        setup();

        Integer count = getCount("/requestRegistry/count", getFilter());
        assertThat(getContent("/requestRegistry", getFilter(), RequestRegistry.class).getContent().size(), equalTo(count));
        assertThat(getContent("/requestRegistry", getFilter(), RequestRegistry.class).getContent().size(), equalTo(3));

        count = getCount("/requestRegistry/count", getFilter(1, 20, null , "1"));
        assertThat(getContent("/requestRegistry", getFilter(1, 20, null , "1"), RequestRegistry.class).getContent().size(), equalTo(count));
        assertThat(getContent("/requestRegistry", getFilter(1, 20, null , "1"), RequestRegistry.class).getContent().size(), equalTo(1));

        count = getCount("/requestRegistry/count", getFilter(1, 20, null , "activa"));
        assertThat(getContent("/requestRegistry", getFilter(1, 20, null , "activa"), RequestRegistry.class).getContent().size(), equalTo(count));
        assertThat(getContent("/requestRegistry", getFilter(1, 20, null , "activa"), RequestRegistry.class).getContent().size(), equalTo(2));
    }

    @Test
    public void searchContracts() throws Exception{
        setup();

        Integer count = getCount("/contractRegistry/count", getFilter());
        assertThat(getContent("/contractRegistry", getFilter(), ContractRegistry.class).getContent().size(), equalTo(count));
        assertThat(getContent("/contractRegistry", getFilter(), ContractRegistry.class).getContent().size(), equalTo(3));

        count = getCount("/contractRegistry/count", getFilter(1, 20, null , "1"));
        assertThat(getContent("/contractRegistry", getFilter(1, 20, null , "1"), ContractRegistry.class).getContent().size(), equalTo(count));
        assertThat(getContent("/contractRegistry", getFilter(1, 20, null , "1"), ContractRegistry.class).getContent().size(), equalTo(1));
    }

    private <T extends Registry> T getContent(String url, FilterDTO filter, Class<T> clazz) throws Exception {
        return getResultAsObject(performFilter(url, filter).andReturn(), clazz);
    }

    private Integer getCount(String url, FilterDTO filter) throws Exception {
        return getResultAsInt(performFilter(url, filter).andReturn());
    }
}
