package ar.com.mercadolibre.mutants.controller;

import ar.com.mercadolibre.mutants.model.Statistics;
import ar.com.mercadolibre.mutants.service.StatisticsServiceImpl;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Class that test the StatisticsController
 *
 * @author vfuentes
 */
public class StatisticsControllerTest {

    private static final String URI = "/stats";
    @InjectMocks
    private StatisticsController controller;
    @Mock
    private StatisticsServiceImpl statisticsService;
    private MockMvc mockMvc;


    /**
     * This method initialize the Mock Server so we can test the Mutant Endpoint
     */
    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    /**
     * This method test that the Statistics Controller returns a 200 status code and a Json with the stats
     *
     * @throws Exception
     */
    @Test
    public void testStatisticsController() throws Exception {

        // Stats retrieved with the statistics Service that is mocked
        Mockito.when(statisticsService.getStats()).thenReturn(new Statistics(1, 10));

        // It calls the stats endpoint
        ResultActions resultActions = mockMvc.perform(get(URI));

        // After the Call to the Mutant Endpoint, the response must be empty with a 200 status code (OK)
        MvcResult result = resultActions.andExpect(status().isOk()).andReturn();
        Assert.isTrue(!result.getResponse().getContentAsString().isEmpty(), "Response body must not be empty");

    }


}
