package ar.com.mercadolibre.mutants.service;

import ar.com.mercadolibre.mutants.dao.DAOService;
import ar.com.mercadolibre.mutants.detector.Detector;
import ar.com.mercadolibre.mutants.detector.MutantDetector;
import ar.com.mercadolibre.mutants.exception.DbException;
import ar.com.mercadolibre.mutants.exception.InputValidationException;
import ar.com.mercadolibre.mutants.exception.ServiceException;
import ar.com.mercadolibre.mutants.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class that implements the MutantsService interface
 */
@Service
public class MutantsServiceImpl implements MutantsService {

    @Autowired
    private DAOService daoService;

    /**
     * Method that analyze an ADN Sequence and tells me if the subject is a mutant or a Human and persist it on the DB
     *
     * @param dna the sequence of adn to be evaluated to see if the subject is mutant or not
     * @return True if the subject is mutant or False if its human
     * @throws InputValidationException if the adn is alien
     * @throws ServiceException         if there is a problem with the Service
     */
    @Override
    public boolean isMutant(String[] dna) throws InputValidationException, ServiceException {

        Detector mutantDetector = new MutantDetector();
        boolean isMutant;
        final String MUTANT = "Mutant";
        final String HUMAN = "Human";

        try {
            isMutant = mutantDetector.isMutant(dna);

            if (isMutant) {
                Subject mutant = new Subject(dna, MUTANT);
                daoService.insert(mutant);

            } else {
                Subject human = new Subject(dna, HUMAN);
                daoService.insert(human);
            }
        } catch (DbException e) {
            throw new ServiceException(e.getMessage());
        }

        return isMutant;
    }

}

