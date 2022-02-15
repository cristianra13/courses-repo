package ar.com.mercadolibre.mutants.service;

import ar.com.mercadolibre.mutants.dao.DAOService;
import ar.com.mercadolibre.mutants.exception.DbException;
import ar.com.mercadolibre.mutants.exception.ServiceException;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Class that test the StatisticsServiceImpl
 */
@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceImplTest {


    @InjectMocks
    private StatisticsServiceImpl statisticsService;

    @Mock
    private DAOService daoService;

    /**
     * This method test that the StatisticsServiceImpl return the stats
     *
     * @throws DbException
     * @throws ServiceException
     */
    @Test
    public void testStatsReturnStatsSuccessfully() throws DbException, ServiceException {

        // Mocks the content of the BD and return the quantity of Mutants
        Mockito.when(daoService.getMutantsCount()).thenReturn(1);
        // Mocks the content of the BD and return the quantity of Humans
        Mockito.when(daoService.getHumansCount()).thenReturn(10);

        // Return the stats Object
        TestCase.assertNotNull(statisticsService.getStats());
    }
}