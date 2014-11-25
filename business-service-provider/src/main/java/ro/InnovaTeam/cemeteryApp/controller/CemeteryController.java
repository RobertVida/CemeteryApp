package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.cemetery.CemeteryDTO;
import ro.InnovaTeam.cemeteryApp.cemetery.rest.CemeteryList;
import ro.InnovaTeam.cemeteryApp.client.FilterDTO;
import ro.InnovaTeam.cemeteryApp.model.Cemetery;
import ro.InnovaTeam.cemeteryApp.service.CemeteryService;
import ro.InnovaTeam.cemeteryApp.util.CemeteryUtil;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by robert on 11/18/2014.
 */
@Controller
public class CemeteryController{

    public static final String CEMETERY_URL = "/cemetery";
    public static final String CEMETERIES_URL = "/cemeteries";
    public static final String SPECIFIC_CEMETERY_URL = CEMETERY_URL + "/{cemeteryId}";

    @Autowired
    private CemeteryService cemeteryService;

    @RequestMapping(value = CEMETERY_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer create(@RequestBody @Valid CemeteryDTO cemeteryDTO) {
        Cemetery cemetery = new Cemetery();
        CemeteryUtil.setCemeteryFromDTO(cemetery, cemeteryDTO);
        return cemeteryService.create(cemetery);
    }

    @RequestMapping(value = SPECIFIC_CEMETERY_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CemeteryDTO delete(@PathVariable Integer cemeteryId) {
        Cemetery cemetery = cemeteryService.delete(cemeteryId);
        return CemeteryUtil.getCemeteryDTO(cemetery);
    }

    @RequestMapping(value = SPECIFIC_CEMETERY_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CemeteryDTO update(@PathVariable Integer cemeteryId, @RequestBody @Valid CemeteryDTO cemeteryDTO) {
        Cemetery cemetery = new Cemetery();

        cemeteryDTO.setId(cemeteryId);
        CemeteryUtil.setCemeteryFromDTO(cemetery, cemeteryDTO);
        Cemetery cemetery1 = cemeteryService.update(cemetery);
        return CemeteryUtil.getCemeteryDTO(cemetery1);
    }
    
    @RequestMapping(value = SPECIFIC_CEMETERY_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CemeteryDTO findById(@PathVariable Integer cemeteryId) {
        return CemeteryUtil.getCemeteryDTO(cemeteryService.findById(cemeteryId));
    }

    @RequestMapping(value = CEMETERIES_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CemeteryList findByFilter(@RequestBody FilterDTO filterDTO) {
        CemeteryList cemeteryList = new CemeteryList();
        List<CemeteryDTO> cemeteryDTOs = CemeteryUtil.getCemeteryDTOs(cemeteryService.findByFilter());
        cemeteryList.setContent(cemeteryDTOs);

        return cemeteryList;
    }

}
