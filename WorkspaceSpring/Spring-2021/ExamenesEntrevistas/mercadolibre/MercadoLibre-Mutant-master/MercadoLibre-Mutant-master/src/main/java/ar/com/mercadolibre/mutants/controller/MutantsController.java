package ar.com.mercadolibre.mutants.controller;

import ar.com.mercadolibre.mutants.exception.InputValidationException;
import ar.com.mercadolibre.mutants.exception.ServiceException;
import ar.com.mercadolibre.mutants.model.DNASequence;
import ar.com.mercadolibre.mutants.service.MutantsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the MutantsService
 *
 * @author vfuentes
 */
@RestController
public class MutantsController {

    private static final Logger logger = LoggerFactory.getLogger(MutantsController.class);

    @Autowired
    private MutantsService mutantsService;

    /**
     * Method that analyze if the subject is Mutant or Human
     *
     * @param dna sequence of DNA to analyze
     * @return if the subject is Mutant 200 (OK) if its Human 403(Forbidden)
     */
    @RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public ResponseEntity<String> analyzeMutantCandidateSubject(@RequestBody DNASequence dna) {

        ResponseEntity<String> responseEntity;
        boolean isMutant;

        try {
            isMutant = mutantsService.isMutant(dna.getDna());

            if (isMutant) {
                responseEntity = new ResponseEntity<>(HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }


        } catch (ServiceException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error(e.getMessage());
        } catch (InputValidationException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            logger.error(e.getMessage());
        }

        return responseEntity;
    }
}