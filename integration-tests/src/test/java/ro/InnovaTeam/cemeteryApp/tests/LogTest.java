package ro.InnovaTeam.cemeteryApp.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.InnovaTeam.cemeteryApp.CemeteryDTO;
import ro.InnovaTeam.cemeteryApp.LogEntryDTO;
import ro.InnovaTeam.cemeteryApp.LogEntryList;
import ro.InnovaTeam.cemeteryApp.model.LogEntry;
import ro.InnovaTeam.cemeteryApp.service.LogEntryService;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;

/**
 * Created by robert on 12/26/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class LogTest extends EntityTest {

    @Autowired
    private LogEntryService logEntryService;

    @Test
    public void test_Create_Update_Delete_Log() throws Exception {
        //setup
        LogEntry logEntry = new LogEntry();
        logEntry.setUserId(1);
        logEntry.setAction("CREATE");
        logEntry.setIdAffected(1);
        logEntry.setNewValue("NONE");
        logEntry.setOldValue("NONE");
        logEntry.setTableChanged("NONE");
        logEntry.setTookPlaceOn(new Date());

        //create
        Integer initLogCount = logEntryService.countByFilter(toDB(getFilter()));
        logEntry.setId(logEntryService.create(logEntry));
        LogEntry dbEntry = logEntryService.findById(logEntry.getId());
        logEntry.setTookPlaceOn(dbEntry.getTookPlaceOn());
        assertThat(compare(logEntry, dbEntry), equalTo(true));
        assertThat(initLogCount, equalTo(logEntryService.countByFilter(toDB(getFilter())) - 1));

        //update
        logEntry.setTableChanged("NONE AGAIN");
        dbEntry = logEntryService.findById(logEntry.getId());
        assertThat(compare(logEntry, dbEntry), equalTo(false));

        logEntryService.update(logEntry);
        dbEntry = logEntryService.findById(logEntry.getId());
        assertThat(compare(logEntry, dbEntry), equalTo(true));

        //delete
        dbEntry = logEntryService.delete(logEntry.getUserId(), logEntry.getId());
        assertThat(compare(logEntry, dbEntry), equalTo(true));

        dbEntry = logEntryService.findById(logEntry.getId());
        assertThat(dbEntry, equalTo(null));
        assertThat(initLogCount, equalTo(logEntryService.countByFilter(toDB(getFilter()))));
    }

    @Test
    public void test_Get_Log() throws Exception {

        CemeteryDTO[] cemeteryDTOs = readJsonFromFile("/cemeteries.json", CemeteryDTO[].class);
        cemetery.create(cemeteryDTOs[0]);

        LogEntryList filterResult = log.filter(getFilter(1, Integer.MAX_VALUE, null, null));
        LogEntryDTO logEntry = log.get(filterResult.getContent().get(0));
        assertThat(compare(filterResult.getContent().get(0), logEntry), equalTo(true));
    }

    @Test
    public void test_Filter_Logs() throws Exception {

        LogEntryList filterResult = log.filter(getFilter(1, Integer.MAX_VALUE, null, null));
        Integer initialSize = filterResult.getContent().size();

        CemeteryDTO[] cemeteryDTOs = readJsonFromFile("/cemeteries.json", CemeteryDTO[].class);
        cemetery.create(cemeteryDTOs[0]);

        filterResult = log.filter(getFilter(1, Integer.MAX_VALUE, null, null));
        assertThat(filterResult.getContent().size(), not(equalTo(initialSize)));
        assertThat(filterResult.getContent().size(), equalTo(initialSize + 1));
        assertThat(filterResult.getContent().size(), equalTo(log.count(getFilter(1, Integer.MAX_VALUE, null, null))));
    }

    @Test
    public void test_Filter_Entity_Logs() throws Exception {

        LogEntryList filterResult = log.filter(getFilter(1, Integer.MAX_VALUE, null, null), "cemeteries");
        Integer initialSize = filterResult.getContent().size();

        CemeteryDTO[] cemeteryDTOs = setupCemeteries();
        setupParcels(cemeteryDTOs);

        filterResult = log.filter(getFilter(1, Integer.MAX_VALUE, null, null), "cemeteries");
        assertThat(filterResult.getContent().size(), not(equalTo(initialSize)));
        assertThat(filterResult.getContent().size(), equalTo(initialSize + cemeteryDTOs.length));
        assertThat(filterResult.getContent().size(), equalTo(log.count(getFilter(1, Integer.MAX_VALUE, null, null), "cemeteries")));
    }


    @Test
    public void test_Filter_Entity_Id_Logs() throws Exception {

        CemeteryDTO[] cemeteryDTOs = setupCemeteries();
        setupParcels(cemeteryDTOs);

        LogEntryList filterResult = log.filter(getFilter(1, Integer.MAX_VALUE, null, null), "cemeteries/" + cemeteryDTOs[2].getId());
        Integer initialSize = filterResult.getContent().size();

        cemeteryDTOs[2].setName("new name");
        cemeteryDTOs[2] = cemetery.update(cemeteryDTOs[2]);
        cemeteryDTOs[2] = cemetery.delete(cemeteryDTOs[2]);

        filterResult = log.filter(getFilter(1, Integer.MAX_VALUE, null, null), "cemeteries/" + cemeteryDTOs[2].getId());
        assertThat(filterResult.getContent().size(), not(equalTo(initialSize)));
        assertThat(filterResult.getContent().size(), equalTo(initialSize + 2));
        assertThat(filterResult.getContent().get(0).getAction(), equalTo("CREATE"));
        assertThat(filterResult.getContent().get(1).getAction(), equalTo("UPDATE"));
        assertThat(filterResult.getContent().get(2).getAction(), equalTo("DELETE"));
        assertThat(filterResult.getContent().size(), equalTo(log.count(getFilter(1, Integer.MAX_VALUE, null, null), "cemeteries/" + cemeteryDTOs[2].getId())));
    }

    private Boolean compare(LogEntryDTO request, LogEntryDTO response) {
        return request.getId().equals(response.getId())
                && request.getTableChanged().equals(response.getTableChanged())
                && request.getIdAffected().equals(response.getIdAffected())
                && request.getTookPlaceOn().equals(response.getTookPlaceOn())
                && request.getAction().equals(response.getAction())
                && request.getOldValue().equals(response.getOldValue())
                && request.getNewValue().equals(response.getNewValue());
    }

    private Boolean compare(LogEntry request, LogEntry response) {
        return request.getId().equals(response.getId())
                && request.getTableChanged().equals(response.getTableChanged())
                && request.getIdAffected().equals(response.getIdAffected())
                && request.getTookPlaceOn().equals(response.getTookPlaceOn())
                && request.getAction().equals(response.getAction())
                && request.getOldValue().equals(response.getOldValue())
                && request.getNewValue().equals(response.getNewValue());
    }
}
