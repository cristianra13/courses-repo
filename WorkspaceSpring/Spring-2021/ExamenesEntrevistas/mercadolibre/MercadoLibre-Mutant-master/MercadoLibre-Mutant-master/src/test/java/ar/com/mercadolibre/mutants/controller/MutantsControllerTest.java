package ar.com.mercadolibre.mutants.controller;

import ar.com.mercadolibre.mutants.exception.InputValidationException;
import ar.com.mercadolibre.mutants.exception.ServiceException;
import ar.com.mercadolibre.mutants.service.MutantsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Class that test the MutantsController
 *
 * @author vfuentes
 */
public class MutantsControllerTest {

    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String URI = "/mutant";
    @InjectMocks
    private MutantsController mutantsController;
    @Mock
    private MutantsServiceImpl mutantsService;
    private MockMvc mockMvc;

    private String[] dnaMutant = new String[]{"TTTTGA", "ATGTGC", "AGTTGG", "AGATGG", "CCCCTA", "TCGCTG"};

    private String[] dnaHuman = new String[]{"AAAAAA", "TCCTTC", "GTCTGG", "TGTTTG", "ACAGTA", "ACTCAG"};

    private String[] invalidDNA = new String[]{"ABGCGA", "CAGTGC", "TTATGG", "AGAAGG", "CCCCTA"};

    /**
     * This method initialize the Mock Server so we can test the Mutant Endpoint
     */
    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mutantsController)
                .build();
    }

    /**
     * This method test when the mutantsService receives a MutantDNA it returns a 200 status code
     *
     * @throws Exception
     */
    @Test
    public void testAGivenDNAIsRecognizedAsMutant() throws Exception {

        String mockMutantBody = "{\"dna\":[\"TTTTGA\",\"ATGTGC\",\"AGTTGG\",\"AGATGG\",\"CCCCTA\",\"TCGCTG\"]}";

        // Mutant DNA to analyze with the Mutant Service that is mocked
        mockIsMutantService(dnaMutant, true);

        // It calls the mutant endpoint
        ResultActions resultActions = mockMvc.perform(post(URI).contentType(CONTENT_TYPE_JSON).content(mockMutantBody));

        // After the Call to the Mutant Endpoint, the response must be empty with a 200 status code (OK)
        MvcResult result = resultActions.andExpect(status().isOk()).andReturn();
        Assert.isTrue(result.getResponse().getContentAsString().isEmpty(), "Response body must be empty");

    }

    /**
     * This method test when the mutantsService receives a HumanDNA it returns a 403 status code
     *
     * @throws Exception
     */
    @Test
    public void testAGivenDNAIsRecognizedAsHuman() throws Exception {

        String mockHumanBody = "{\"dna\":[\"AAAAAA\",\"TCCTTC\",\"GTCTGG\",\"TGTTTG\",\"ACAGTA\",\"ACTCAG\"]}";

        // Human DNA to analyze with the Mutant Service that is mocked
        mockIsMutantService(dnaHuman, false);

        // It calls the mutant endpoint
        ResultActions resultActions = mockMvc.perform(post(URI).contentType(CONTENT_TYPE_JSON).content(mockHumanBody));

        // After the Call to the Mutant Endpoint, the response must be empty with a 403 status code (Forbidden)
        MvcResult result = resultActions.andExpect(status().isForbidden()).andReturn();
        Assert.isTrue(result.getResponse().getContentAsString().isEmpty(), "Response body must be empty");
    }

    /**
     * This method test when the mutantsService receives an invalidDNA it returns a 400 status code
     *
     * @throws Exception
     */
    @Test
    public void testAnalizeMutantCandidateIsBadRequest() throws Exception {

        String mockAlienBody = "{\"dna\":[\"ABGCGA\",\"CAGTGC\",\"TTATGG\",\"AGAAGG\",\"CCCCTA\"]}";

        // Alien DNA to analyze with the Mutant Service that is mocked
        Mockito.doThrow(new InputValidationException("Alien DNA")).when(mutantsService).isMutant(invalidDNA);

        // It calls the mutant endpoint
        ResultActions resultActions = mockMvc.perform(post(URI).contentType(CONTENT_TYPE_JSON).content(mockAlienBody));

        // After the Call to the Mutant Endpoint, the response must be empty with a 400 status code (Bad Request)
        MvcResult result = resultActions.andExpect(status().isBadRequest()).andReturn();
        Assert.isTrue(result.getResponse().getContentAsString().isEmpty(), "Response body must be empty");
    }

    /**
     * Method that is use to mock the mutantsService from which we get the response that tells if a DNA is Mutant or not
     *
     * @param dna            DNA Sequence to analyze
     * @param expectedResult it gives True if its Mutant and False if its not
     * @throws InputValidationException
     * @throws ServiceException
     */
    private void mockIsMutantService(String[] dna, boolean expectedResult) throws InputValidationException, ServiceException {
        Mockito.when(mutantsService.isMutant(dna)).thenReturn(expectedResult);
    }

}
