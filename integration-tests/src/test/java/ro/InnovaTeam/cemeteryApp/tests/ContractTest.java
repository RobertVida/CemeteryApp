package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.*;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by robert on 1/2/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ContractTest extends EntityTest {

    @Test
    public void test_Create_Get_Delete_Contract() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = readJsonFromFile("/cemeteries.json", CemeteryDTO[].class);
        cemeteryDTOs[0] = cemetery.create(cemeteryDTOs[0]);

        ParcelDTO[] parcelDTOs = readJsonFromFile("/parcels.json", ParcelDTO[].class);
        parcelDTOs[0].setCemeteryId(cemeteryDTOs[0].getId());
        parcelDTOs[0] = parcel.create(parcelDTOs[0]);

        GraveDTO[] graveDTOs = readJsonFromFile("/graves.json", GraveDTO[].class);
        graveDTOs[0].setParcelId(parcelDTOs[0].getId());
        graveDTOs[0] = grave.create(graveDTOs[0]);

        ClientDTO[] clientDTOs = readJsonFromFile("/clients.json", ClientDTO[].class);
        clientDTOs[0] = client.create(clientDTOs[0]);

        RestingPlaceRequestDTO[] requestDTOs = readJsonFromFile("/requests.json", RestingPlaceRequestDTO[].class);
        requestDTOs[0].setClientId(clientDTOs[0].getId());
        requestDTOs[0] = request.create(requestDTOs[0]);

        //create
        ContractDTO[] contractDTOs = readJsonFromFile("/contracts.json", ContractDTO[].class);
        contractDTOs[0].setRequestId(requestDTOs[0].getId());
        contractDTOs[0].setStructureId(graveDTOs[0].getId());
        contractDTOs[0] = contract.create(contractDTOs[0]);
        assertThat(contract.count(getFilter()), equalTo(1));

        //get
        ContractDTO contractDTO = contract.get(contractDTOs[0]);
        assertThat(compare(contractDTOs[0], contractDTO), equalTo(false));
        contractDTOs[0].setExpiresOn(calculateExpireDate(contractDTOs[0].getSignedOn()));
        assertThat(compare(contractDTOs[0], contractDTO), equalTo(true));

        //delete
        contractDTO = contract.delete(contractDTOs[0]);
        assertThat(compare(contractDTOs[0], contractDTO), equalTo(true));

        //get
        contractDTO = contract.get(contractDTOs[0]);
        assertThat(contractDTO, equalTo(null));
        assertThat(contract.count(getFilter()), equalTo(0));
    }

    @Test
    public void test_Filter_Contract_Graves() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = setupCemeteries();
        ParcelDTO[] parcelDTOs = setupParcels(cemeteryDTOs);
        GraveDTO[] graveDTOs = setupGraves(parcelDTOs);
        ClientDTO[] clientDTOs = setupClients();
        RestingPlaceRequestDTO[] requestDTOs = setupRequests(clientDTOs);

        //create
        ContractDTO[] contractDTOs = setupContract(graveDTOs, requestDTOs);

        //filter
        Integer contractCount = contract.count(getFilter());
        assertThat(contractCount, equalTo(contractDTOs.length));
        assertThat(contract.filter(getFilter(1, Integer.MAX_VALUE, null, null)).getContent().size(), equalTo(contractDTOs.length));

        //filter
        ContractList filterResult = contract.filter(getFilter(1, 20, graveDTOs[0].getId(), null));
        assertThat(filterResult.getContent().size(), equalTo(1));
        assertThat(filterResult.getContent().size(), not(equalTo(contractCount)));
    }

    @Test
    public void test_Filter_Contract_Monument() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = setupCemeteries();
        ParcelDTO[] parcelDTOs = setupParcels(cemeteryDTOs);
        MonumentDTO[] monumentDTOs = setupMonuments(parcelDTOs);
        ClientDTO[] clientDTOs = setupClients();
        RestingPlaceRequestDTO[] requestDTOs = setupRequests(clientDTOs);

        //create
        ContractDTO[] contractDTOs = setupContract(monumentDTOs, requestDTOs);

        //filter
        Integer contractCount = contract.count(getFilter());
        assertThat(contractCount, equalTo(contractDTOs.length));
        assertThat(contract.filter(getFilter(1, Integer.MAX_VALUE, null, null)).getContent().size(), equalTo(contractDTOs.length));

        //filter
        ContractList filterResult = contract.filter(getFilter(1, 20, monumentDTOs[0].getId(), null));
        assertThat(filterResult.getContent().size(), equalTo(1));
        assertThat(filterResult.getContent().size(), not(equalTo(contractCount)));
    }

    @Test
    public void test_Update_Contract() throws Exception {
        //setup
        CemeteryDTO[] cemeteryDTOs = readJsonFromFile("/cemeteries.json", CemeteryDTO[].class);
        cemeteryDTOs[0] = cemetery.create(cemeteryDTOs[0]);

        ParcelDTO[] parcelDTOs = readJsonFromFile("/parcels.json", ParcelDTO[].class);
        parcelDTOs[0].setCemeteryId(cemeteryDTOs[0].getId());
        parcelDTOs[0] = parcel.create(parcelDTOs[0]);

        GraveDTO[] graveDTOs = readJsonFromFile("/graves.json", GraveDTO[].class);
        graveDTOs[0].setParcelId(parcelDTOs[0].getId());
        graveDTOs[0] = grave.create(graveDTOs[0]);

        ClientDTO[] clientDTOs = readJsonFromFile("/clients.json", ClientDTO[].class);
        clientDTOs[0] = client.create(clientDTOs[0]);

        RestingPlaceRequestDTO[] requestDTOs = readJsonFromFile("/requests.json", RestingPlaceRequestDTO[].class);
        requestDTOs[0].setClientId(clientDTOs[0].getId());
        requestDTOs[0] = request.create(requestDTOs[0]);

        //create
        ContractDTO[] contractDTOs = readJsonFromFile("/contracts.json", ContractDTO[].class);
        contractDTOs[0].setRequestId(requestDTOs[0].getId());
        contractDTOs[0].setStructureId(graveDTOs[0].getId());
        contractDTOs[0] = contract.create(contractDTOs[0]);
        assertThat(contract.count(getFilter()), equalTo(1));

        //get
        ContractDTO contractDTO = contract.get(contractDTOs[0]);
        assertThat(compare(contractDTOs[0], contractDTO), equalTo(false));
        contractDTOs[0].setExpiresOn(calculateExpireDate(contractDTOs[0].getSignedOn()));
        assertThat(compare(contractDTOs[0], contractDTO), equalTo(true));

        //update
        contractDTOs[0].setUpdatedOn(new Date());
        contractDTO = contract.get(contractDTOs[0]);
        assertThat(compare(contractDTOs[0], contractDTO), equalTo(false));

        contract.update(contractDTOs[0]);
        contractDTO = contract.get(contractDTOs[0]);
        contractDTOs[0].setUpdatedOn(contractDTO.getUpdatedOn());
        assertThat(compare(contractDTOs[0], contractDTO), equalTo(true));
    }

    private Date calculateExpireDate(Date signedOn) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(signedOn);
        calendar.add(Calendar.YEAR, 20);
        return calendar.getTime();
    }

    private Boolean compare(ContractDTO request, ContractDTO response) {
        return request.getId().equals(response.getId())
                && request.getRequestId().equals(response.getRequestId())
                && request.getStructureId().equals(response.getStructureId())
                && request.getSignedOn().equals(response.getSignedOn())
                && ((request.getUpdatedOn() == null && response.getUpdatedOn() == null) ||
                    (request.getUpdatedOn() != null && request.getUpdatedOn().equals(response.getUpdatedOn())))
                && ((request.getExpiresOn() == null && response.getExpiresOn() == null) ||
                    (request.getExpiresOn() != null && request.getExpiresOn().equals(response.getExpiresOn())));
    }
}
