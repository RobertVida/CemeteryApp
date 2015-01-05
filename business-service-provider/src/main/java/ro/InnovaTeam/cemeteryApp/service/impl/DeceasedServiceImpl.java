package ro.InnovaTeam.cemeteryApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.InnovaTeam.cemeteryApp.eao.BurialDocumentEAO;
import ro.InnovaTeam.cemeteryApp.eao.DeceasedEAO;
import ro.InnovaTeam.cemeteryApp.eao.NoCaregiverDocumentEAO;
import ro.InnovaTeam.cemeteryApp.model.BurialDocument;
import ro.InnovaTeam.cemeteryApp.model.Deceased;
import ro.InnovaTeam.cemeteryApp.model.Filter;
import ro.InnovaTeam.cemeteryApp.model.NoCaregiverDocument;
import ro.InnovaTeam.cemeteryApp.service.DeceasedService;
import ro.InnovaTeam.cemeteryApp.service.LogEntryService;

import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
@Transactional
@Service
public class DeceasedServiceImpl extends LoggableService<Deceased, DeceasedEAO, LogEntryService> implements DeceasedService {

    @Autowired
    private DeceasedEAO deceasedEAO;
    @Autowired
    private BurialDocumentEAO documentEAO;
    @Autowired
    private NoCaregiverDocumentEAO noCaregiverDocumentEAO;
    @Autowired
    private LogEntryService logService;

    @Override
    public Integer create(Deceased deceased) {
        Deceased newDeceased = findById(deceasedEAO.create(deceased));

        newDeceased.setDocument(documentEAO.findById(documentEAO.create(deceased.getDocument())));
        newDeceased.setNoCaregiverDocument(deceased.getNoCaregiverDocument() != null ?
                noCaregiverDocumentEAO.findById(noCaregiverDocumentEAO.create(deceased.getNoCaregiverDocument())) : null);
        newDeceased.setUserId(deceased.getUserId());

        logService.logCreate(newDeceased);
        return newDeceased.getId();
    }

    @Override
    public Deceased delete(Integer userId, Integer id) {
        BurialDocument deletedDocument = documentEAO.findByDeceasedId(id);
        NoCaregiverDocument deletedNoCaregiverDocument = noCaregiverDocumentEAO.findByDeceasedId(id);
        documentEAO.delete(deletedDocument.getId());
        if(deletedNoCaregiverDocument != null ) {
            noCaregiverDocumentEAO.delete(deletedNoCaregiverDocument.getId());
        }

        Deceased deletedDeceased = deceasedEAO.delete(id);
        deletedDeceased.setDocument(deletedDocument);
        deletedDeceased.setNoCaregiverDocument(deletedNoCaregiverDocument);

        logService.logDelete(userId, deletedDeceased);
        return deletedDeceased;
    }

    @Override
    public Deceased update(Deceased deceased) {
        Deceased oldEntity = findById(deceased.getId());

        Deceased updatedEntity = deceasedEAO.update(deceased);
        updatedEntity.setDocument(documentEAO.update(deceased.getDocument()));
        updateNoCaregiverDocument(deceased);
        updatedEntity.setUserId(deceased.getUserId());

        logService.logUpdate(oldEntity, updatedEntity);
        return updatedEntity;
    }

    @Override
    public Deceased findById(Integer id) {
        Deceased deceased = deceasedEAO.findById(id);
        if(deceased != null) {
            deceased.setDocument(documentEAO.findByDeceasedId(id));
            deceased.setNoCaregiverDocument(noCaregiverDocumentEAO.findByDeceasedId(id));
        }
        return deceased;
    }

    @Override
    public List<Deceased> findByFilter(Filter filter) {
        return deceasedEAO.findByFilter(filter);
    }

    @Override
    public Integer countByFilter(Filter filter) {
        return deceasedEAO.countByFilter(filter);
    }

    private void updateNoCaregiverDocument(Deceased deceased) {
        NoCaregiverDocument dbDocument = noCaregiverDocumentEAO.findByDeceasedId(deceased.getId());
        if(removeCaregiverDocument(deceased, dbDocument)){
            noCaregiverDocumentEAO.delete(dbDocument.getId());
            deceased.setNoCaregiverDocument(null);
        } else if(createCaregiverDocument(deceased, dbDocument)) {
            Integer id = noCaregiverDocumentEAO.create(deceased.getNoCaregiverDocument());
            deceased.setNoCaregiverDocument(noCaregiverDocumentEAO.findById(id));
        } else if(deceased.getNoCaregiverDocument() != null){
            noCaregiverDocumentEAO.update(deceased.getNoCaregiverDocument());
        }
    }

    private boolean createCaregiverDocument(Deceased deceased, NoCaregiverDocument dbDocument) {
        return deceased.getNoCaregiverDocument() != null && dbDocument == null;
    }

    private boolean removeCaregiverDocument(Deceased deceased, NoCaregiverDocument dbDocument) {
        return deceased.getNoCaregiverDocument() == null && dbDocument != null;
    }
}
