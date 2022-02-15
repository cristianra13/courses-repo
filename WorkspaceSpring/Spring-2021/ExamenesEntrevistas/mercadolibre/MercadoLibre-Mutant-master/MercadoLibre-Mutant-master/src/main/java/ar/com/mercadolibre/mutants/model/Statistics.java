package ar.com.mercadolibre.mutants.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class that models the Statistics
 *
 * @author vfuentes
 */
public class Statistics {


    private BigDecimal count_mutant_dna;
    private BigDecimal count_human_dna;
    private BigDecimal ratio;

    public Statistics(Integer mutantCount, Integer humanCount) {
        count_mutant_dna = new BigDecimal(mutantCount);
        count_human_dna = new BigDecimal(humanCount);
        ratio = (count_human_dna.compareTo(BigDecimal.ZERO) != 0) ? count_mutant_dna.divide(count_human_dna, 2, RoundingMode.UNNECESSARY) : new BigDecimal("0.00");

    }

    public BigDecimal getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public BigDecimal getCount_human_dna() {
        return count_human_dna;
    }

    public BigDecimal getRatio() {
        return ratio;
    }


}
