package ro.InnovaTeam.cemeteryApp.util;

import ro.InnovaTeam.cemeteryApp.DeceasedDTO;
import ro.InnovaTeam.cemeteryApp.model.Deceased;
import ro.InnovaTeam.cemeteryApp.model.NoCaregiverDocument;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin Sorecau on 11/24/2014.
 */
public abstract class DeceasedUtil {

    public static Deceased toDB(DeceasedDTO deceasedDTO) {
        if (deceasedDTO == null) {
            return null;
        }
        Deceased deceased = new Deceased();
        deceased.setId(deceasedDTO.getId());
        deceased.setFirstName(deceasedDTO.getFirstName());
        deceased.setLastName(deceasedDTO.getLastName());
        deceased.setCnp(deceasedDTO.getCnp());
        deceased.setReligion(deceasedDTO.getReligion());
        deceased.setDiedOn(deceasedDTO.getDiedOn());
        deceased.setBurialDocumentId(deceasedDTO.getBurialDocumentId());
        deceased.setStructureId(deceasedDTO.getStructureId());
        deceased.setBurialOn(deceasedDTO.getBurialOn());
        deceased.setUserId(deceasedDTO.getUserId());
        deceased.setNoCaregiverDocument(handleCaregiver(deceasedDTO, deceased));

        return deceased;
    }

    private static NoCaregiverDocument handleCaregiver(DeceasedDTO deceasedDTO, Deceased deceased) {
        NoCaregiverDocument document = null;
        if(!deceasedDTO.getHasCaregiver()) {
            document = new NoCaregiverDocument();
            document.setId(deceasedDTO.getNoCaregiverDocumentId());
            document.setDeceasedId(deceasedDTO.getId());
            document.setCertificateId(deceasedDTO.getCertificateId());
            document.setRequestIMLid(deceasedDTO.getRequestIMLid());
            deceasedDTO.setHasCaregiver(false);
        }
        return document;
    }

    public static DeceasedDTO toDTO(Deceased deceased) {
        if (deceased == null) {
            return null;
        }
        DeceasedDTO deceasedDTO = new DeceasedDTO();
        deceasedDTO.setId(deceased.getId());
        deceasedDTO.setFirstName(deceased.getFirstName());
        deceasedDTO.setLastName(deceased.getLastName());
        deceasedDTO.setCnp(deceased.getCnp());
        deceasedDTO.setReligion(deceased.getReligion());
        deceasedDTO.setDiedOn(deceased.getDiedOn());
        deceasedDTO.setBurialDocumentId(deceased.getBurialDocumentId());
        deceasedDTO.setStructureId(deceased.getStructureId());
        deceasedDTO.setBurialOn(deceased.getBurialOn());
        handleCaregiver(deceased, deceasedDTO);

        return deceasedDTO;
    }

    private static void handleCaregiver(Deceased deceased, DeceasedDTO deceasedDTO) {
        if(deceased.getNoCaregiverDocument() != null){
            deceasedDTO.setNoCaregiverDocumentId(deceased.getNoCaregiverDocument().getId());
            deceasedDTO.setRequestIMLid(deceased.getNoCaregiverDocument().getRequestIMLid());
            deceasedDTO.setCertificateId(deceased.getNoCaregiverDocument().getCertificateId());
        }
    }

    public static List<Deceased> toDB(List<DeceasedDTO> deceasedDTOs) {
        if (deceasedDTOs == null) {
            return null;
        }
        List<Deceased> deceased = new ArrayList<Deceased>();
        for (DeceasedDTO deceasedDTO : deceasedDTOs) {
            deceased.add(toDB(deceasedDTO));
        }
        return deceased;
    }

    public static List<DeceasedDTO> toDTO(List<Deceased> deceased) {
        if (deceased == null) {
            return null;
        }
        List<DeceasedDTO> deceasedDTOs = new ArrayList<DeceasedDTO>();
        for (Deceased d : deceased) {
            deceasedDTOs.add(toDTO(d));
        }
        return deceasedDTOs;
    }
}
