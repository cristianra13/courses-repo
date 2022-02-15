package ar.com.mercadolibre.mutants.dao;

import ar.com.mercadolibre.mutants.controller.MutantsController;
import ar.com.mercadolibre.mutants.model.Subject;
import ar.com.mercadolibre.mutants.repository.SubjectRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MongoDB Implementation of the data access layer
 *
 * @author vfuentes
 */
@Service
public class DAOServiceImpl implements DAOService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MutantsController.class);

    @Autowired
    private SubjectRepository subjectRepository;

    public DAOServiceImpl() {
        super();
    }

    /**
     * Method that saves the Subject on the DB
     *
     * @param subject to be saved
     */
    @Override
    public void insert(Subject subject) {
        subjectRepository.save(subject);
        logger.info("Se persistio en la BD el siguiente sujeto:" + subject);
    }

    /**
     * Method that returns the quantity of Humans
     *
     * @return an integer that represents the quantity of humans on the table
     */
    @Override
    public int getHumansCount() {

        return subjectRepository.countBySubjectType("Human").intValue();

    }

    /**
     * Method that returns the quantity of Mutants
     *
     * @return an integer that represents the quantity of mutants on the table
     */
    @Override
    public int getMutantsCount() {

        return subjectRepository.countBySubjectType("Mutant").intValue();


    }

}
