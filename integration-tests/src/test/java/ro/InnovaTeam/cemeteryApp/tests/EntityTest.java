package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.After;
import ro.InnovaTeam.cemeteryApp.*;
import ro.InnovaTeam.cemeteryApp.tests.helpers.EntityCollection;
import ro.InnovaTeam.cemeteryApp.tests.helpers.EntityHolder;
import ro.InnovaTeam.cemeteryApp.tests.helpers.EntityTypes;

import static ro.InnovaTeam.cemeteryApp.tests.helpers.EntityTypes.*;

/**
 * Created by robert on 12/24/2014.
 */
public class EntityTest extends PerformTest {

    protected EntityCollection dbGarbageCollector = new EntityCollection();

    public interface Action<E, EL> {
        public E get(E obj) throws Exception;

        public E delete(E obj) throws Exception;

        public E update(E obj) throws Exception;

        public E create(E obj) throws Exception;

        public EL filter(FilterDTO obj) throws Exception;

        public EL filter(FilterDTO obj, String data) throws Exception;

        public EL filter(Integer parentId) throws Exception;

        public Integer count(FilterDTO obj) throws Exception;

        public Integer count(FilterDTO obj, String data) throws Exception;

    }

    protected Action<CemeteryDTO, CemeteryList> cemetery = new Action<CemeteryDTO, CemeteryList>() {
        public CemeteryDTO get(CemeteryDTO cemeteryDTO) throws Exception {
            return getEntity("/cemetery/", cemeteryDTO, CemeteryDTO.class);
        }

        public CemeteryDTO delete(CemeteryDTO cemeteryDTO) throws Exception {
            return deleteEntity("/cemetery/1/", cemeteryDTO, CemeteryDTO.class);
        }

        public CemeteryDTO update(CemeteryDTO cemeteryDTO) throws Exception {
            return updateEntity("/cemetery/", cemeteryDTO, CemeteryDTO.class);
        }

        public CemeteryDTO create(CemeteryDTO cemeteryDTO) throws Exception {
            return createEntity("/cemetery", cemeteryDTO);
        }

        public CemeteryList filter(FilterDTO filterDTO) throws Exception {
            return filterEntities("/cemeteries", filterDTO, CemeteryList.class);
        }

        public CemeteryList filter(Integer parentId) throws Exception {
            return null;
        }

        public CemeteryList filter(FilterDTO filterDTO, String status) throws Exception {
            return null;
        }

        public Integer count(FilterDTO filterDTO) throws Exception {
            return countEntities("/cemeteries/count", filterDTO);
        }

        public Integer count(FilterDTO filterDTO, String status) throws Exception {
            return null;
        }
    };

    protected Action<ParcelDTO, ParcelList> parcel = new Action<ParcelDTO, ParcelList>() {
        public ParcelDTO get(ParcelDTO parcelDTO) throws Exception {
            return getEntity("/parcel/", parcelDTO, ParcelDTO.class);
        }

        public ParcelDTO delete(ParcelDTO parcelDTO) throws Exception {
            return deleteEntity("/parcel/1/", parcelDTO, ParcelDTO.class);
        }

        public ParcelDTO update(ParcelDTO parcelDTO) throws Exception {
            return updateEntity("/parcel/", parcelDTO, ParcelDTO.class);
        }

        public ParcelDTO create(ParcelDTO parcelDTO) throws Exception {
            return createEntity("/parcel", parcelDTO);
        }

        public ParcelList filter(FilterDTO filterDTO) throws Exception {
            return filterEntities("/parcels", filterDTO, ParcelList.class);
        }

        public ParcelList filter(Integer parentId) throws Exception {
            return filterEntities("/parcels/cemetery/" + parentId, ParcelList.class);
        }

        public ParcelList filter(FilterDTO filterDTO, String status) throws Exception {
            return null;
        }

        public Integer count(FilterDTO filterDTO) throws Exception {
            return countEntities("/parcels/count", filterDTO);
        }

        public Integer count(FilterDTO filterDTO, String status) throws Exception {
            return null;
        }
    };

    protected Action<GraveDTO, GraveList> grave = new Action<GraveDTO, GraveList>() {
        public GraveDTO get(GraveDTO parcelDTO) throws Exception {
            return getEntity("/grave/", parcelDTO, GraveDTO.class);
        }

        public GraveDTO delete(GraveDTO parcelDTO) throws Exception {
            return deleteEntity("/grave/1/", parcelDTO, GraveDTO.class);
        }

        public GraveDTO update(GraveDTO parcelDTO) throws Exception {
            return updateEntity("/grave/", parcelDTO, GraveDTO.class);
        }

        public GraveDTO create(GraveDTO parcelDTO) throws Exception {
            return createEntity("/grave", parcelDTO);
        }

        public GraveList filter(FilterDTO filterDTO) throws Exception {
            return filterEntities("/graves", filterDTO, GraveList.class);
        }

        public GraveList filter(Integer parentId) throws Exception {
            return null;
        }

        public GraveList filter(FilterDTO filterDTO, String status) throws Exception {
            return null;
        }

        public Integer count(FilterDTO filterDTO) throws Exception {
            return countEntities("/graves/count", filterDTO);
        }

        public Integer count(FilterDTO filterDTO, String status) throws Exception {
            return null;
        }
    };

    protected Action<MonumentDTO, MonumentList> monument = new Action<MonumentDTO, MonumentList>() {
        public MonumentDTO get(MonumentDTO monumentDTO) throws Exception {
            return getEntity("/monument/", monumentDTO, MonumentDTO.class);
        }

        public MonumentDTO delete(MonumentDTO monumentDTO) throws Exception {
            return deleteEntity("/monument/1/", monumentDTO, MonumentDTO.class);
        }

        public MonumentDTO update(MonumentDTO monumentDTO) throws Exception {
            return updateEntity("/monument/", monumentDTO, MonumentDTO.class);
        }

        public MonumentDTO create(MonumentDTO monumentDTO) throws Exception {
            return createEntity("/monument", monumentDTO);
        }

        public MonumentList filter(FilterDTO filterDTO) throws Exception {
            return filterEntities("/monuments", filterDTO, MonumentList.class);
        }

        public MonumentList filter(Integer parentId) throws Exception {
            return null;
        }

        public MonumentList filter(FilterDTO filterDTO, String status) throws Exception {
            return null;
        }

        public Integer count(FilterDTO filterDTO) throws Exception {
            return countEntities("/monuments/count", filterDTO);
        }

        public Integer count(FilterDTO filterDTO, String status) throws Exception {
            return null;
        }
    };

    protected Action<ClientDTO, ClientList> client = new Action<ClientDTO, ClientList>() {
        public ClientDTO get(ClientDTO clientDTO) throws Exception {
            return getEntity("/client/", clientDTO, ClientDTO.class);
        }

        public ClientDTO delete(ClientDTO clientDTO) throws Exception {
            return deleteEntity("/client/1/", clientDTO, ClientDTO.class);
        }

        public ClientDTO update(ClientDTO clientDTO) throws Exception {
            return updateEntity("/client/", clientDTO, ClientDTO.class);
        }

        public ClientDTO create(ClientDTO clientDTO) throws Exception {
            return createEntity("/client", clientDTO);
        }

        public ClientList filter(FilterDTO filterDTO) throws Exception {
            return filterEntities("/clients", filterDTO, ClientList.class);
        }

        public ClientList filter(Integer parentId) throws Exception {
            return null;
        }

        public ClientList filter(FilterDTO filterDTO, String status) throws Exception {
            return null;
        }

        public Integer count(FilterDTO filterDTO) throws Exception {
            return countEntities("/clients/count", filterDTO);
        }

        public Integer count(FilterDTO filterDTO, String status) throws Exception {
            return null;
        }
    };

    protected Action<RestingPlaceRequestDTO, RestingPlaceRequestList> request = new Action<RestingPlaceRequestDTO, RestingPlaceRequestList>() {
        public RestingPlaceRequestDTO get(RestingPlaceRequestDTO requestDTO) throws Exception {
            return getEntity("/request/", requestDTO, RestingPlaceRequestDTO.class);
        }

        public RestingPlaceRequestDTO delete(RestingPlaceRequestDTO requestDTO) throws Exception {
            return deleteEntity("/request/1/", requestDTO, RestingPlaceRequestDTO.class);
        }

        public RestingPlaceRequestDTO update(RestingPlaceRequestDTO requestDTO) throws Exception {
            return updateEntity("/request/", requestDTO, RestingPlaceRequestDTO.class);
        }

        public RestingPlaceRequestDTO create(RestingPlaceRequestDTO requestDTO) throws Exception {
            return createEntity("/request", requestDTO);
        }

        public RestingPlaceRequestList filter(FilterDTO filterDTO) throws Exception {
            return filterEntities("/requests", filterDTO, RestingPlaceRequestList.class);
        }

        public RestingPlaceRequestList filter(Integer parentId) throws Exception {
            return null;
        }

        public RestingPlaceRequestList filter(FilterDTO filterDTO, String status) throws Exception {
            return filterEntities("/requests/" + status, filterDTO, RestingPlaceRequestList.class);
        }

        public Integer count(FilterDTO filterDTO) throws Exception {
            return countEntities("/requests/count", filterDTO);
        }

        public Integer count(FilterDTO filterDTO, String status) throws Exception {
            return countEntities("/requests/" + status + "/count", filterDTO);
        }
    };

    protected Action<DeceasedDTO, DeceasedList> deceased = new Action<DeceasedDTO, DeceasedList>() {
        @Override
        public DeceasedDTO get(DeceasedDTO deceasedDTO) throws Exception {
            return getEntity("/deceased/", deceasedDTO, DeceasedDTO.class);
        }

        @Override
        public DeceasedDTO delete(DeceasedDTO deceasedDTO) throws Exception {
            return deleteEntity("/deceased/1/", deceasedDTO, DeceasedDTO.class);
        }

        @Override
        public DeceasedDTO update(DeceasedDTO deceasedDTO) throws Exception {
            return updateEntity("/deceased/", deceasedDTO, DeceasedDTO.class);
        }

        @Override
        public DeceasedDTO create(DeceasedDTO deceasedDTO) throws Exception {
            return createEntity("/deceased", deceasedDTO);
        }

        @Override
        public DeceasedList filter(FilterDTO filterDTO) throws Exception {
            return filterEntities("/deceased", filterDTO, DeceasedList.class);
        }

        @Override
        public DeceasedList filter(FilterDTO filterDTO, String data) throws Exception {
            return null;
        }

        @Override
        public DeceasedList filter(Integer parentId) throws Exception {
            return null;
        }

        @Override
        public Integer count(FilterDTO filterDTO) throws Exception {
            return countEntities("/deceased/count", filterDTO);
        }

        @Override
        public Integer count(FilterDTO filterDTO, String data) throws Exception {
            return null;
        }
    };

    protected Action<LogEntryDTO, LogEntryList> log = new Action<LogEntryDTO, LogEntryList>() {
        public LogEntryDTO get(LogEntryDTO logEntryDTO) throws Exception {
            return getEntity("/log/", logEntryDTO, LogEntryDTO.class);
        }

        public LogEntryDTO delete(LogEntryDTO logEntryDTO) throws Exception {
            return null;
        }

        public LogEntryDTO update(LogEntryDTO logEntryDTO) throws Exception {
            return null;
        }

        public LogEntryDTO create(LogEntryDTO logEntryDTO) throws Exception {
            return null;
        }

        public LogEntryList filter(FilterDTO filterDTO) throws Exception {
            return filterEntities("/logs", filterDTO, LogEntryList.class);
        }

        public LogEntryList filter(FilterDTO filterDTO, String data) throws Exception {
            return filterEntities("/logs/" + data, filterDTO, LogEntryList.class);
        }

        public LogEntryList filter(Integer parentId) throws Exception {
            return null;
        }

        public Integer count(FilterDTO filterDTO) throws Exception {
            return countEntities("/logs/count", filterDTO);
        }

        public Integer count(FilterDTO filterDTO, String data) throws Exception {
            return countEntities("/logs/" + data + "/count", filterDTO);
        }
    };

    protected Action<StructureHistoryEntryDTO, StructureHistoryEntryList> structureHistory = new Action<StructureHistoryEntryDTO, StructureHistoryEntryList>() {

        @Override
        public StructureHistoryEntryDTO get(StructureHistoryEntryDTO entryDTO) throws Exception {
            return getEntity("/structureHistory/", entryDTO, StructureHistoryEntryDTO.class);
        }

        @Override
        public StructureHistoryEntryDTO delete(StructureHistoryEntryDTO entryDTO) throws Exception {
            return deleteEntity("/structureHistory/1/", entryDTO, StructureHistoryEntryDTO.class);
        }

        @Override
        public StructureHistoryEntryDTO update(StructureHistoryEntryDTO entryDTO) throws Exception {
            return updateEntity("/structureHistory/", entryDTO, StructureHistoryEntryDTO.class);
        }

        @Override
        public StructureHistoryEntryDTO create(StructureHistoryEntryDTO entryDTO) throws Exception {
            return createEntity("/structureHistory", entryDTO);
        }

        @Override
        public StructureHistoryEntryList filter(FilterDTO filterDTO) throws Exception {
            return filterEntities("/structureHistories", filterDTO, StructureHistoryEntryList.class);
        }

        @Override
        public StructureHistoryEntryList filter(FilterDTO filterDTO, String data) throws Exception {
            return null;
        }

        @Override
        public StructureHistoryEntryList filter(Integer parentId) throws Exception {
            return null;
        }

        @Override
        public Integer count(FilterDTO filterDTO) throws Exception {
            return countEntities("/structureHistories/count", filterDTO);
        }

        @Override
        public Integer count(FilterDTO filterDTO, String data) throws Exception {
            return null;
        }
    };

    protected Action<ContractDTO, ContractList> contract = new Action<ContractDTO, ContractList>() {

        @Override
        public ContractDTO get(ContractDTO contractDTO) throws Exception {
            return getEntity("/contract/", contractDTO, ContractDTO.class);
        }

        @Override
        public ContractDTO delete(ContractDTO contractDTO) throws Exception {
            return deleteEntity("/contract/1/", contractDTO, ContractDTO.class);
        }

        @Override
        public ContractDTO update(ContractDTO contractDTO) throws Exception {
            return updateEntity("/contract/", contractDTO, ContractDTO.class);
        }

        @Override
        public ContractDTO create(ContractDTO contractDTO) throws Exception {
            return createEntity("/contract", contractDTO);
        }

        @Override
        public ContractList filter(FilterDTO filterDTO) throws Exception {
            return filterEntities("/contracts", filterDTO, ContractList.class);
        }

        @Override
        public ContractList filter(FilterDTO filterDTO, String data) throws Exception {
            return null;
        }

        @Override
        public ContractList filter(Integer parentId) throws Exception {
            return null;
        }

        @Override
        public Integer count(FilterDTO filterDTO) throws Exception {
            return countEntities("/contracts/count", filterDTO);
        }

        @Override
        public Integer count(FilterDTO filterDTO, String data) throws Exception {
            return null;
        }
    };

    //=================================================================================ENTITY
    private <T extends BaseDTO> T getEntity(String url, T obj, Class<T> targetClass) throws Exception {
        return getResultAsObject(performGet(url + obj.getId()).andReturn(), targetClass);
    }

    private <T extends BaseDTO> T deleteEntity(String url, T obj, Class<T> targetClass) throws Exception {
        T deletedObj = getResultAsObject(performDelete(url + obj.getId()).andReturn(), targetClass);
        removeFromGarbageCollector(deletedObj);
        return deletedObj;
    }

    private <T extends BaseDTO> T updateEntity(String url, T obj, Class<T> targetClass) throws Exception {
        return getResultAsObject(performUpdate(url + obj.getId(), obj).andReturn(), targetClass);
    }

    private <T extends BaseDTO> T createEntity(String url, T obj) throws Exception {
        obj.setId(getResultAsInt(performCreate(url, obj).andReturn()));
        addToGarbageCollector(obj);
        return obj;
    }

    private <T extends BaseList> T filterEntities(String url, FilterDTO filter, Class<T> targetClass) throws Exception {
        return getResultAsObject(performFilter(url, filter).andReturn(), targetClass);
    }

    private <T extends BaseList> T filterEntities(String url, Class<T> targetClass) throws Exception {
        return getResultAsObject(performFilter(url).andReturn(), targetClass);
    }

    public Integer countEntities(String url, FilterDTO filter) throws Exception {
        return getResultAsInt(performCount(url, filter).andReturn());
    }

    //=================================================================================GARBAGE-COLLECTOR
    private <T extends BaseDTO> void addToGarbageCollector(T obj) {
        dbGarbageCollector.push(EntityTypes.getTypeForClass(obj.getClass()), obj.getId());
    }

    private <T extends BaseDTO> void removeFromGarbageCollector(T obj) {
        dbGarbageCollector.eliminate(EntityTypes.getTypeForClass(obj.getClass()), obj.getId());
    }

    @After
    public void cleanup() throws Exception {
        EntityHolder holder;
        while ((holder = dbGarbageCollector.pop()) != null) {
            handle(holder.getType(), holder.getId());
        }
    }

    private void handle(EntityTypes type, Integer id) throws Exception {
        if (type.equals(CEMETERY)) {
            CemeteryDTO cemeteryDTO = new CemeteryDTO();
            cemeteryDTO.setId(id);
            cemetery.delete(cemeteryDTO);
        } else if (type.equals(PARCEL)) {
            ParcelDTO parcelDTO = new ParcelDTO();
            parcelDTO.setId(id);
            parcel.delete(parcelDTO);
        } else if (type.equals(GRAVE)) {
            GraveDTO graveDTO = new GraveDTO();
            graveDTO.setId(id);
            grave.delete(graveDTO);
        } else if (type.equals(MONUMENT)) {
            MonumentDTO monumentDTO = new MonumentDTO();
            monumentDTO.setId(id);
            monument.delete(monumentDTO);
        }else if (type.equals(CLIENT)) {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setId(id);
            client.delete(clientDTO);
        } else if (type.equals(REQUEST)) {
            RestingPlaceRequestDTO requestDTO = new RestingPlaceRequestDTO();
            requestDTO.setId(id);
            request.delete(requestDTO);
        } else if (type.equals(DECEASED)) {
            DeceasedDTO deceasedDTO = new DeceasedDTO();
            deceasedDTO.setId(id);
            deceased.delete(deceasedDTO);
        } else if (type.equals(STRUCTURE_HISTORY)) {
            StructureHistoryEntryDTO entryDTO = new StructureHistoryEntryDTO();
            entryDTO.setId(id);
            structureHistory.delete(entryDTO);
        } else if (type.equals(CONTRACT)) {
            ContractDTO contractDTO = new ContractDTO();
            contractDTO.setId(id);
            contract.delete(contractDTO);
        }
    }

    //=================================================================================FILTER
    public FilterDTO getFilter() {
        return getFilter(1, 20, null, null);
    }

    public FilterDTO getFilter(Integer pageNo, Integer pageSize, Integer parentId, String criteria) {
        FilterDTO filterDTO = new FilterDTO();
        filterDTO.setPageNo(pageNo);
        filterDTO.setPageSize(pageSize);
        filterDTO.setParentId(parentId);
        filterDTO.setSearchCriteria(criteria);

        return filterDTO;
    }

    //=================================================================================SETUP
    protected CemeteryDTO[] setupCemeteries() throws Exception {
        CemeteryDTO[] cemeteryDTOs = readJsonFromFile("/cemeteries.json", CemeteryDTO[].class);
        for (int i = 0; i < cemeteryDTOs.length; i++) {
            cemeteryDTOs[i] = cemetery.create(cemeteryDTOs[i]);
        }
        return cemeteryDTOs;
    }

    protected ParcelDTO[] setupParcels(CemeteryDTO[] cemeteryDTOs) throws Exception {
        ParcelDTO[] parcelDTOs = readJsonFromFile("/parcels.json", ParcelDTO[].class);
        parcelDTOs[0].setCemeteryId(cemeteryDTOs[0].getId());
        parcelDTOs[0] = parcel.create(parcelDTOs[0]);
        for (int i = 1; i < parcelDTOs.length; i++) {
            parcelDTOs[i].setCemeteryId(cemeteryDTOs[1].getId());
            parcelDTOs[i] = parcel.create(parcelDTOs[i]);
        }
        return parcelDTOs;
    }

    protected GraveDTO[] setupGraves(ParcelDTO[] parcelDTOs) throws Exception {
        GraveDTO[] graveDTOs = readJsonFromFile("/graves.json", GraveDTO[].class);
        graveDTOs[0].setParcelId(parcelDTOs[0].getId());
        graveDTOs[0] = grave.create(graveDTOs[0]);
        for (int i = 1; i < graveDTOs.length; i++) {
            graveDTOs[i].setParcelId(parcelDTOs[1].getId());
            graveDTOs[i] = grave.create(graveDTOs[i]);
        }
        return graveDTOs;
    }

    protected MonumentDTO[] setupMonuments(ParcelDTO[] parcelDTOs) throws Exception {
        MonumentDTO[] monumentDTOs = readJsonFromFile("/monuments.json", MonumentDTO[].class);
        monumentDTOs[0].setParcelId(parcelDTOs[0].getId());
        monumentDTOs[0] = monument.create(monumentDTOs[0]);
        for (int i = 1; i < monumentDTOs.length; i++) {
            monumentDTOs[i].setParcelId(parcelDTOs[1].getId());
            monumentDTOs[i] = monument.create(monumentDTOs[i]);
        }
        return monumentDTOs;
    }

    protected ClientDTO[] setupClients() throws Exception {
        ClientDTO[] clientDTOs = readJsonFromFile("/clients.json", ClientDTO[].class);
        for (int i = 0; i < clientDTOs.length; i++) {
            clientDTOs[i] = client.create(clientDTOs[i]);
        }
        return clientDTOs;
    }

    protected DeceasedDTO[] setupDeceased(GraveDTO[] graveDTOs) throws Exception {
        DeceasedDTO[] deceasedDTOs = readJsonFromFile("/deceased.json", DeceasedDTO[].class);
        deceasedDTOs[0].setStructureId(graveDTOs[0].getId());
        deceasedDTOs[0] = deceased.create(deceasedDTOs[0]);
        deceasedDTOs[0].setBurialDocumentId(deceased.get(deceasedDTOs[0]).getBurialDocumentId());
        for(int i = 1 ; i < deceasedDTOs.length ; i++){
            deceasedDTOs[i].setStructureId(graveDTOs[1].getId());
            deceasedDTOs[i] = deceased.create(deceasedDTOs[i]);
            deceasedDTOs[i].setBurialDocumentId(deceased.get(deceasedDTOs[i]).getBurialDocumentId());
        }
        return deceasedDTOs;
    }

    protected DeceasedDTO[] setupDeceased(MonumentDTO[] monumentDTOs) throws Exception {
        DeceasedDTO[] deceasedDTOs = readJsonFromFile("/deceased.json", DeceasedDTO[].class);
        deceasedDTOs[0].setStructureId(monumentDTOs[0].getId());
        deceasedDTOs[0] = deceased.create(deceasedDTOs[0]);
        deceasedDTOs[0].setBurialDocumentId(deceased.get(deceasedDTOs[0]).getBurialDocumentId());
        for(int i = 1 ; i < deceasedDTOs.length ; i++){
            deceasedDTOs[i].setStructureId(monumentDTOs[1].getId());
            deceasedDTOs[i] = deceased.create(deceasedDTOs[i]);
            deceasedDTOs[i].setBurialDocumentId(deceased.get(deceasedDTOs[i]).getBurialDocumentId());
        }
        return deceasedDTOs;
    }

    protected RestingPlaceRequestDTO[] setupRequests(ClientDTO[] clientDTOs) throws Exception {
        RestingPlaceRequestDTO[] requestDTOs = readJsonFromFile("/requests.json", RestingPlaceRequestDTO[].class);
        requestDTOs[0].setClientId(clientDTOs[0].getId());
        requestDTOs[0] = request.create(requestDTOs[0]);
        for (int i = 1; i < requestDTOs.length; i++) {
            requestDTOs[i].setClientId(clientDTOs[1].getId());
            requestDTOs[i] = request.create(requestDTOs[i]);
        }
        return requestDTOs;
    }

    protected StructureHistoryEntryDTO[] setupHistoryEntries(GraveDTO[] graveDTOs) throws Exception {
        StructureHistoryEntryDTO[] historyEntryDTOs = readJsonFromFile("/structureHistory.json", StructureHistoryEntryDTO[].class);
        historyEntryDTOs[0].setStructureId(graveDTOs[0].getId());
        historyEntryDTOs[0] = structureHistory.create(historyEntryDTOs[0]);
        for(int i = 1 ; i < historyEntryDTOs.length ; i++){
            historyEntryDTOs[i].setStructureId(graveDTOs[1].getId());
            historyEntryDTOs[i] = structureHistory.create(historyEntryDTOs[i]);
        }
        return historyEntryDTOs;
    }

    protected StructureHistoryEntryDTO[] setupHistoryEntries(MonumentDTO[] monumentDTOs) throws Exception {
        StructureHistoryEntryDTO[] historyEntryDTOs = readJsonFromFile("/structureHistory.json", StructureHistoryEntryDTO[].class);
        historyEntryDTOs[0].setStructureId(monumentDTOs[0].getId());
        historyEntryDTOs[0] = structureHistory.create(historyEntryDTOs[0]);
        for(int i = 1 ; i < historyEntryDTOs.length ; i++){
            historyEntryDTOs[i].setStructureId(monumentDTOs[1].getId());
            historyEntryDTOs[i] = structureHistory.create(historyEntryDTOs[i]);
        }
        return historyEntryDTOs;
    }

    protected ContractDTO[] setupContract(GraveDTO[] graveDTOs, RestingPlaceRequestDTO[] requestDTOs) throws Exception {
        ContractDTO[] contractDTOs = readJsonFromFile("/contracts.json", ContractDTO[].class);
        for(int i = 0 ; i < contractDTOs.length ; i++){
            contractDTOs[i].setStructureId(graveDTOs[i].getId());
            contractDTOs[i].setRequestId(requestDTOs[i].getId());
            contractDTOs[i] = contract.create(contractDTOs[i]);
        }
        return contractDTOs;
    }

    protected ContractDTO[] setupContract(MonumentDTO[] monumentDTOs, RestingPlaceRequestDTO[] requestDTOs) throws Exception {
        ContractDTO[] contractDTOs = readJsonFromFile("/contracts.json", ContractDTO[].class);
        for(int i = 0 ; i < contractDTOs.length ; i++){
            contractDTOs[i].setStructureId(monumentDTOs[i].getId());
            contractDTOs[i].setRequestId(requestDTOs[i].getId());
            contractDTOs[i] = contract.create(contractDTOs[i]);
        }
        return contractDTOs;
    }
}
