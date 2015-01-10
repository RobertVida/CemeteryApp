package ro.InnovaTeam.cemeteryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.InnovaTeam.cemeteryApp.ContractDTO;
import ro.InnovaTeam.cemeteryApp.ContractList;
import ro.InnovaTeam.cemeteryApp.FilterDTO;
import ro.InnovaTeam.cemeteryApp.model.Contract;
import ro.InnovaTeam.cemeteryApp.service.ContractService;

import javax.validation.Valid;

import static ro.InnovaTeam.cemeteryApp.util.ContractUtil.toDB;
import static ro.InnovaTeam.cemeteryApp.util.ContractUtil.toDTO;
import static ro.InnovaTeam.cemeteryApp.util.FilterUtil.toDB;

/**
 * Created by robert on 1/2/2015.
 */
@Controller
public class ContractController extends ExceptionHandledController {

    public static final String CONTRACT_URL = "/contract";
    public static final String CONTRACTS_URL = "/contracts";
    public static final String SPECIFIC_CONTRACT_URL = CONTRACT_URL + "/{contractId}";
    public static final String SPECIFIC_USER_CONTRACT_URL = CONTRACT_URL + "/{userId}/{contractId}";

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = CONTRACT_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer create(@RequestBody @Valid ContractDTO contractDTO) {
        return contractService.create(toDB(contractDTO));
    }

    @RequestMapping(value = SPECIFIC_USER_CONTRACT_URL, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ContractDTO delete(@PathVariable Integer userId, @PathVariable Integer contractId) {
        return toDTO(contractService.delete(userId, contractId));
    }

    @RequestMapping(value = SPECIFIC_CONTRACT_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ContractDTO update(@PathVariable Integer contractId, @RequestBody @Valid ContractDTO contractDTO) {
        Contract contract = toDB(contractDTO);
        contract.setId(contractId);
        return toDTO(contractService.update(contract));
    }

    @RequestMapping(value = SPECIFIC_CONTRACT_URL, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ContractDTO findById(@PathVariable Integer contractId) {
        return toDTO(contractService.findById(contractId));
    }

    @RequestMapping(value = CONTRACTS_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ContractList findByFilter(@RequestBody FilterDTO filterDTO) {
        return new ContractList(toDTO(contractService.findByFilter(toDB(filterDTO))));
    }

    @RequestMapping(value = CONTRACTS_URL + "/count", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer countByFilter(@RequestBody FilterDTO filterDTO) {
        return contractService.countByFilter(toDB(filterDTO));
    }
}
