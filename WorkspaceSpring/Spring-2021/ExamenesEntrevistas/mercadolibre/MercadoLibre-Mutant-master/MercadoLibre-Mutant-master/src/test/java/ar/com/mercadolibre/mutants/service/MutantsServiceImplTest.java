package ar.com.mercadolibre.mutants.service;

import ar.com.mercadolibre.mutants.dao.DAOService;
import ar.com.mercadolibre.mutants.exception.DbException;
import ar.com.mercadolibre.mutants.exception.InputValidationException;
import ar.com.mercadolibre.mutants.exception.ServiceException;
import ar.com.mercadolibre.mutants.model.Subject;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Class that test the MutantsServiceImpl
 *
 * @author vfuentes
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class MutantsServiceImplTest {

    @InjectMocks
    private MutantsServiceImpl service;

    @Mock
    private DAOService daoService;

    private String[] dnaAlien1 = new String[]{"ABGCGA", "CAGTGC", "TTATGG", "AGAAGG", "CCCCTA", "TCGCTG"};

    private String[] dnaAlien2 = new String[]{"ABG", "CAG", "TTA", "AGA", "CCC", "TCG"};

    private String[] dnaMutant1 = new String[]{"TTTTGA", "ATGTGC", "AGTTGG", "AGATGG", "CCCCTA", "TCGCTG"};

    private String[] dnaMutant2 = new String[]{"GGGCGA", "CACAGC", "TCAAGG", "CGAAAG", "CCCATA", "TCCCCG"};

    private String[] dnaHuman1 = new String[]{"AAAAAA", "TCCTTC", "GTCTGG", "TGTTTG", "ACAGTA", "ACTCAG"};

    private String[] dnaHuman2 = new String[]{"AATGGA", "AATTGC", "TTGTGG", "CGGTAG", "CAAGTA", "TTTTTG"};

    /**
     * Method that retrives the MutantDNA and mocks the DB persistence doing nothing
     *
     * @param dna mutantDNA
     * @return dna
     * @throws DbException
     */
    private String[] getMutantDNA(String[] dna) throws DbException {

        Subject mutant = new Subject(dna, "mutant");
        Mockito.doNothing().when(daoService).insert(Mockito.eq(mutant));

        return dna;
    }

    /**
     * Method that retrives the HumanDNA and mocks the DB persistence doing nothing
     *
     * @param dna humanDNA
     * @return dna
     * @throws DbException
     */
    private String[] getHumanDNA(String[] dna) throws DbException {

        Subject human = new Subject(dna, "human");
        Mockito.doNothing().when(daoService).insert(Mockito.eq(human));

        return dna;
    }

    /**
     * This method test that the exception created to handle the Alien DNA it works fine
     *
     * @throws InputValidationException
     * @throws ServiceException
     */
    @Test(expected = InputValidationException.class)
    public void testIsAlien1() throws InputValidationException, ServiceException {

        service.isMutant(dnaAlien1);
    }

    /**
     * This method test that the exception created to handle the Alien DNA it works fine
     *
     * @throws InputValidationException
     * @throws ServiceException
     */
    @Test(expected = InputValidationException.class)
    public void testIsAlien2() throws InputValidationException, ServiceException {

        service.isMutant(dnaAlien2);
    }

    /**
     * This method test that when the MutantsServiceImple receives a MutantDNA returns True
     *
     * @throws InputValidationException
     * @throws ServiceException
     * @throws DbException
     */
    @Test
    public void testIsMutant_Success_Mutant1() throws InputValidationException, ServiceException, DbException {

        boolean result = service.isMutant(getMutantDNA(dnaMutant1));
        TestCase.assertTrue(result);
    }

    /**
     * This method test that when the MutantsServiceImple receives a MutantDNA returns True
     *
     * @throws InputValidationException
     * @throws ServiceException
     * @throws DbException
     */
    @Test
    public void testIsMutant_Success_Mutant2() throws InputValidationException, ServiceException, DbException {

        boolean result = service.isMutant(getMutantDNA(dnaMutant2));
        TestCase.assertTrue(result);
    }

    /**
     * This method test that when the MutantsServiceImple receives a HumanDNA returns False
     *
     * @throws InputValidationException
     * @throws ServiceException
     * @throws DbException
     */
    @Test
    public void testIsMutant_Error_Human1() throws InputValidationException, ServiceException, DbException {

        boolean result = service.isMutant(getHumanDNA(dnaHuman1));
        TestCase.assertFalse(result);
    }

    /**
     * This method test that when the MutantsServiceImple receives a HumanDNA returns False
     *
     * @throws InputValidationException
     * @throws ServiceException
     * @throws DbException
     */
    @Test
    public void testIsMutant_Error_Human2() throws InputValidationException, ServiceException, DbException {

        boolean result = service.isMutant(getHumanDNA(dnaHuman2));
        TestCase.assertFalse(result);
    }


}