package ar.com.mercadolibre.mutants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Class where you have all the configurations for the app.
 * You can find this configuration in thie file named config.properties that is located on the Resources folder.
 *
 * @author vfuentes
 */
public class Configuration {
    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

    private static final String CONFIG_FILE = "/config.properties";
    private static final String MIN_SEQUENCES_TO_BE_MUTANT = "min-sequences-to-be-mutant";
    private static final String SEQUENCES_LENGTH = "sequences-length";


    private Integer minSequencesToBeMutant;
    private Integer sequencesLength;


    public Configuration() {
        super();
        Properties properties = this.loadConfig(CONFIG_FILE);

        this.minSequencesToBeMutant = Integer.parseInt(properties.getProperty(MIN_SEQUENCES_TO_BE_MUTANT));
        this.sequencesLength = Integer.parseInt(properties.getProperty(SEQUENCES_LENGTH));

    }


    public Integer getMinSequencesToBeMutant() {
        return minSequencesToBeMutant;
    }

    public Integer getSequencesLength() {
        return sequencesLength;
    }

    /**
     * This method loads the config.properties so the parameters can be read.
     *
     * @return object properties.
     */
    private Properties loadConfig(String propertiesFileName) {
        Properties configFile = new Properties();
        try {
            configFile.load(getClass().getResourceAsStream(propertiesFileName));
        } catch (IOException e) {
            logger.error("Problem loading the configuation file.", e);
        }
        return configFile;
    }


}