package ar.com.mercadolibre.mutants.service;

import ar.com.mercadolibre.mutants.exception.ServiceException;
import ar.com.mercadolibre.mutants.model.Statistics;

/**
 * The user of this interface has control to access the statistics of the app.
 *
 * @author vfuentes
 */
public interface StatisticsService {

    /**
     * Obtains the statistics of the app
     *
     * @return the statistics of the app
     * @throws ServiceException if there is a problem with the Service
     */
    Statistics getStats() throws ServiceException;

}
