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

        public EL filter(Integer parentId) throws Exception;
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

        public ParcelList filter(FilterDTO parcelDTO) throws Exception {
            return filterEntities("/parcels", parcelDTO, ParcelList.class);
        }

        public ParcelList filter(Integer parentId) throws Exception {
            return filterEntities("/parcels/cemetery/" + parentId, ParcelList.class);
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

        public GraveList filter(FilterDTO parcelDTO) throws Exception {
            return filterEntities("/graves", parcelDTO, GraveList.class);
        }

        public GraveList filter(Integer parentId) throws Exception {
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

        public ClientList filter(FilterDTO clientDTO) throws Exception {
            return filterEntities("/clients", clientDTO, ClientList.class);
        }

        public ClientList filter(Integer parentId) throws Exception {
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
        } else if (type.equals(CLIENT)) {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setId(id);
            client.delete(clientDTO);
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

    protected ClientDTO[] setupClients() throws Exception {
        ClientDTO[] clientDTOs = readJsonFromFile("/clients.json", ClientDTO[].class);
        for (int i = 0; i < clientDTOs.length; i++) {
            clientDTOs[i] = client.create(clientDTOs[i]);
        }
        return clientDTOs;
    }
}
