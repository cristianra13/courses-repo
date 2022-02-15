package ar.com.mercadolibre.mutants.dao;

import ar.com.mercadolibre.mutants.exception.DbException;
import ar.com.mercadolibre.mutants.model.Subject;

/**
 * The user of this interface has control over some actions on the DB which are the Save and Count.
 *
 * @author vfuentes
 */
public interface DAOService {

    /**
     * Method that persists a subject on the DB
     *
     * @param subject subject to be saved on the DB
     * @throws DbException if there is a problem with the DB
     */
    void insert(Subject subject) throws DbException;

    /**
     * Method that returns the humans that exists on the DB
     *
     * @return the quantity of humans
     * @throws DbException if there is a problem with the DB
     */
    int getHumansCount() throws DbException;

    /**
     * Method that returns the mutants that exists on the DB
     *
     * @return the quantity of mutants
     * @throws DbException if there is a problem with the DB
     */
    int getMutantsCount() throws DbException;

}
