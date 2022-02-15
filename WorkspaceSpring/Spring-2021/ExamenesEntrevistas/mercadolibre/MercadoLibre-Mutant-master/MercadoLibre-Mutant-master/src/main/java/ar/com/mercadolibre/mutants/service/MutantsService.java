package ar.com.mercadolibre.mutants.service;


import ar.com.mercadolibre.mutants.exception.InputValidationException;
import ar.com.mercadolibre.mutants.exception.ServiceException;

/**
 * The user of this interface has control to determinate if the ADN of a Subject is Human, Mutant or Alien.
 *
 * @author vfuentes
 */
public interface MutantsService {

    /**
     * Determines from a given adn if the subject is human or mutant
     *
     * @param dna the sequence of adn to be evaluated to see if the subject is mutant or not
     * @return True if the subject is mutant or False if its human
     * @throws InputValidationException if the adn is alien
     * @throws ServiceException         if there is a problem with the Service
     */
    boolean isMutant(String[] dna) throws ServiceException, InputValidationException;

}
