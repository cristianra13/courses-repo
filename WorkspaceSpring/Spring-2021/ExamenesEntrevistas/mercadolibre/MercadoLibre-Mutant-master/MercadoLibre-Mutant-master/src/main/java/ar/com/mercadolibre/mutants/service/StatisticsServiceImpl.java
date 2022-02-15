package ar.com.mercadolibre.mutants.service;

import ar.com.mercadolibre.mutants.dao.DAOService;
import ar.com.mercadolibre.mutants.exception.DbException;
import ar.com.mercadolibre.mutants.exception.ServiceException;
import ar.com.mercadolibre.mutants.model.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class that implements the StatisticsService interface
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private DAOService daoService;

    /**
     * Method that obtains the statistics of the app.
     *
     * @return the Statistics of the app.
     * @throws ServiceException if there is a problem with the Service
     */
    @Override
    public Statistics getStats() throws ServiceException {

        Statistics stats;

        try {

            stats = new Statistics(daoService.getMutantsCount(), daoService.getHumansCount());

        } catch (DbException e) {
            throw new ServiceException(e.getMessage());
        }
        return stats;
    }


}
