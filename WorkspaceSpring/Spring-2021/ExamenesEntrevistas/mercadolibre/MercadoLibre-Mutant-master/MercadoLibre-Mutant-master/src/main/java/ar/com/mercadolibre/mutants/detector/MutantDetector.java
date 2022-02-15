package ar.com.mercadolibre.mutants.detector;

import ar.com.mercadolibre.mutants.exception.InputValidationException;

import java.util.regex.Pattern;

/**
 * Class that implementes the Detector Interface
 *
 * @author vfuentes
 */
public class MutantDetector implements Detector {

    Utils utils = new Utils();

    private char[][] dnaSequence;


    /**
     * Determines from a given adn if the subject is human or mutant
     *
     * @param dna the sequence of adn to be evaluated to see if the subject is mutant or not
     * @return True if the subject is mutant or False if its human
     * @throws InputValidationException if the adn is alien
     */
    public boolean isMutant(String[] dna) throws InputValidationException {

        dnaSequence = populateDnaSequence(dna);

        if (dnaSequence.length < 4) {
            return false;
        }

        return analyzeDNA();
    }

    /**
     * The decision to use several IF Statements was made to fit the request of the maximum efficiency of the algorithm.
     * The strategy pattern could be used, but because the strategies are always the same and would not change in the future
     * it becomes unnecessary to implement it because of the complexity and the several new Java Class that would be necessary.
     *
     * @return a boolean value that determinate if the subject is Human or Mutant
     */
    private Boolean analyzeDNA() {


        if (utils.horizontalRead(dnaSequence)) return true;
        if (utils.verticalRead(dnaSequence)) return true;
        if (utils.bottomDiagonalsFromLeftReadWithoutMainDiagonal(dnaSequence)) return true;
        if (utils.bottomDiagonalsFromRightReadWithoutMainDiagonal(dnaSequence)) return true;
        if (utils.topDiagonalsFromLeftReadWithMainDiagonal(dnaSequence)) return true;
        return utils.topDiagonalsFromRightReadWithMainDiagonal(dnaSequence);

    }

    /**
     * While it populates the array, checks that the dna sequences is correct with the validations that are set.
     *
     * @param dna the dna sequence to be analyzed
     * @return the sequence represented on a two-dimensional array
     * @throws InputValidationException if the dna is alien (the dna is invalid)
     */
    private char[][] populateDnaSequence(String[] dna) throws InputValidationException {

        int dnaSequenceRange = dna.length;
        Pattern pattern = Pattern.compile("[acgt]+", Pattern.CASE_INSENSITIVE);

        dnaSequence = new char[dnaSequenceRange][dnaSequenceRange];

        for (int range = 0; range < dnaSequenceRange; range++) {

            if (dna[range].length() != dnaSequenceRange) {
                throw new InputValidationException("El largo de la secuencia de ADN debe ser igual al numero de secuencias");
            } else if (!pattern.matcher(dna[range]).matches()) {
                throw new InputValidationException("El ADN es Alienigena. El ADN humano y mutante contiene unicamente los caracteres A, C, G y T.");
            } else {
                dnaSequence[range] = dna[range].toUpperCase().toCharArray();
            }
        }

        return dnaSequence;
    }

}