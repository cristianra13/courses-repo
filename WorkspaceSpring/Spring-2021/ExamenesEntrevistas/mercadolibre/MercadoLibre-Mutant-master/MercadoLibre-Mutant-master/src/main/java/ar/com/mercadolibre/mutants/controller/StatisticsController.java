package ar.com.mercadolibre.mutants.controller;

import ar.com.mercadolibre.mutants.exception.ServiceException;
import ar.com.mercadolibre.mutants.model.Statistics;
import ar.com.mercadolibre.mutants.service.StatisticsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the StatisticsService
 *
 * @author vfuentes
 */
@RestController
public class StatisticsController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MutantsController.class);

    @Autowired
    private StatisticsService statisticsService;

    /**
     * Method that returns the Statistics of the adn that where analyzed.
     *
     * @return a json document with the statistics which are quantity of Humans, Mutants and the Ratio
     */
    @RequestMapping(value = "/stats", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Statistics getStatics() {

        Statistics stats = null;

        try {
            stats = statisticsService.getStats();
            logger.info("La cantidad de Humanos es: {}, la de Mutantes es: {} y el Ratio es de: {}  ", statisticsService.getStats().getCount_human_dna(), statisticsService.getStats().getCount_mutant_dna(), statisticsService.getStats().getRatio());
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }

        return stats;
    }
}