package ar.com.mercadolibre.mutants.detector;

import ar.com.mercadolibre.mutants.Configuration;

/**
 * This class handles all the Matrix Operations that are necesary to determinate if the DNA Sequence is Human or Mutant.
 *
 * @author vfuentes
 */
public class Utils {

    private Configuration configuration = new Configuration();
    private char[][] dnaSequence;
    private int sequenceCount = 0; // Counter for found sequences
    private char lastCharacter;

    /**
     * This method read the rows of the matrix that forms the DNA Sequence
     *
     * @return a boolean value that determinate if the subject is Human or Mutant
     */
    Boolean horizontalRead(char[][] dnaSequence) {
        this.dnaSequence = dnaSequence;

        for (int row = 0; row < dnaSequence.length; row++) {
            lastCharacter = dnaSequence[row][0];
            if (readHorizontalOrVertical(ReadDirections.HORIZONTAL, lastCharacter, row)) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method reads the columns of the matrix that forms the DNA Sequence
     *
     * @return a boolean value that determinate if the subject is Human or Mutant
     */
    Boolean verticalRead(char[][] dnaSequence) {
        this.dnaSequence = dnaSequence;

        for (int col = 0; col < dnaSequence.length; col++) {
            lastCharacter = dnaSequence[0][col];
            if (readHorizontalOrVertical(ReadDirections.VERTICAL, lastCharacter, col)) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method reads from left to right all the bottom diagonals that forms the matrix that forms the DNA Sequence
     * without the main Diagonal.
     *
     * @return a boolean value that determinate if the subject is Human or Mutant
     */
    Boolean bottomDiagonalsFromLeftReadWithoutMainDiagonal(char[][] dnaSequence) {
        this.dnaSequence = dnaSequence;

        for (int row = 1; row < dnaSequence.length; row++) {
            lastCharacter = dnaSequence[row][0];
            if (readDiagonals(DiagonalReadDirections.FROM_LEFT, DiagonalReadType.BELOW_MAIN_DIAGONAL, row, 0)) {
                return true;
            }

        }
        return false;
    }

    /**
     * This method reads from right to left all the bottom diagonals that forms the matrix that forms the DNA Sequence
     * without the main Diagonal.
     *
     * @return a boolean value that determinate if the subject is Human or Mutant
     */
    Boolean bottomDiagonalsFromRightReadWithoutMainDiagonal(char[][] dnaSequence) {
        this.dnaSequence = dnaSequence;

        for (int row = 1; row < dnaSequence.length; row++) {
            lastCharacter = dnaSequence[row][dnaSequence.length - 1];
            if (readDiagonals(DiagonalReadDirections.FROM_RIGHT, DiagonalReadType.BELOW_MAIN_DIAGONAL, row, dnaSequence.length - 1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method reads from left to right all the top diagonals that forms the matrix that forms the DNA Sequence
     * including the main Diagonal.
     *
     * @return a boolean value that determinate if the subject is Human or Mutant
     */
    Boolean topDiagonalsFromLeftReadWithMainDiagonal(char[][] dnaSequence) {
        this.dnaSequence = dnaSequence;

        for (int col = 0; col < dnaSequence.length; col++) {
            lastCharacter = dnaSequence[0][col];
            if (readDiagonals(DiagonalReadDirections.FROM_LEFT, DiagonalReadType.ABOVE_MAIN_DIAGONAL_INCLUDING, 0, col)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method reads from right to left all the top diagonals that forms the matrix that forms the DNA Sequence
     * including the main Diagonal.
     *
     * @return a boolean value that determinate if the subject is Human or Mutant
     */
    Boolean topDiagonalsFromRightReadWithMainDiagonal(char[][] dnaSequence) {
        this.dnaSequence = dnaSequence;

        for (int col = 1; col < dnaSequence.length; col++) {
            lastCharacter = dnaSequence[0][dnaSequence.length - col];
            if (readDiagonals(DiagonalReadDirections.FROM_RIGHT, DiagonalReadType.ABOVE_MAIN_DIAGONAL_INCLUDING, 0, dnaSequence.length - col)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to search sequences in a single row or column depending of a base point and a given direction.
     *
     * @param direction        If I read column by column or row by row.
     * @param initialCharacter The first char read.
     * @param index            The iteration provided to this method in order to scan row by row or column by column.
     * @return true if when this method returns, the number of sequences to be considered a mutant has been reached.
     * Note: Maybe the number of sequences was reached by this method and a previous one.
     */
    private boolean readHorizontalOrVertical(ReadDirections direction, char initialCharacter, int index) {
        int sameCharactersCount = 1;
        char lastCharacter = initialCharacter;
        char currentCharacter;

        for (int subindex = 1;
             dnaSequence.length - subindex + sameCharactersCount >= configuration.getSequencesLength() && subindex < dnaSequence.length;
             subindex++) {

            currentCharacter = (ReadDirections.HORIZONTAL.equals(direction) ? dnaSequence[index][subindex] : dnaSequence[subindex][index]);
            if (lastCharacter == currentCharacter) {
                sameCharactersCount++;
                if (sameCharactersCount == configuration.getSequencesLength()) {
                    sequenceCount++;
                    sameCharactersCount = 0;
                    if (sequenceCount == configuration.getMinSequencesToBeMutant()) {
                        // If a find the minimum sequences to determine if it is Mutant, I finish the search.
                        return true;
                    }
                }
            } else {
                lastCharacter = currentCharacter;
                sameCharactersCount = 1;
            }
        }
        // The Subject is Human
        return false;
    }

    /***
     * This method is used to search sequences in a single diagonal depending the base point, direction and if read
     * above (including) or below the main diagonal.
     *
     * @param leftOrRight The direction of the scan
     * @param aboveOrBelow If I read the diagonals above the main diagonal (including it) or below the main diagonal.
     * @param baseN the starting row
     * @param baseM the starting column
     *
     * @return true if when this method returns, the number of sequences to be considered a mutant has been reached.
     * Note: Maybe the number of sequences was reached by this method and a previous one.
     */
    private boolean readDiagonals(DiagonalReadDirections leftOrRight, DiagonalReadType aboveOrBelow, int baseN, int baseM) {
        int offset = 1;

        int sameCharactersCount = 1;

        char lastCharacter = dnaSequence[baseN][baseM];
        char currentCharacter;

        // This condition is valid when you can still read below the main diagonal
        boolean bottomReadCondition = baseN + offset < dnaSequence.length;

        // This condition is valid when you can still read from the main diagonal to the top.
        boolean topReadCondition = (leftOrRight.equals(DiagonalReadDirections.FROM_LEFT) && baseM + offset < dnaSequence.length ||
                leftOrRight.equals(DiagonalReadDirections.FROM_RIGHT) && baseM - offset >= 0);

        // From the verification of the previous conditions it decides if it is correct to continue reading the diagonal.
        while ((aboveOrBelow.equals(DiagonalReadType.ABOVE_MAIN_DIAGONAL_INCLUDING) && topReadCondition) ||
                (aboveOrBelow.equals(DiagonalReadType.BELOW_MAIN_DIAGONAL) && bottomReadCondition)) {

            currentCharacter = (leftOrRight.equals(DiagonalReadDirections.FROM_LEFT)) ? dnaSequence[baseN + offset][baseM + offset] :
                    dnaSequence[baseN + offset][baseM - offset];
            if (lastCharacter == currentCharacter) {
                sameCharactersCount++;
                if (sameCharactersCount == configuration.getSequencesLength()) {
                    sequenceCount++;
                    sameCharactersCount = 0;
                    if (sequenceCount >= configuration.getMinSequencesToBeMutant()) {
                        // If a find the minimum sequences to determine if it is Mutant, I finish the search.
                        return true;
                    }
                }
            } else {
                lastCharacter = currentCharacter;
                sameCharactersCount = 1;
            }

            offset++;

            // I will verify the read conditions.
            bottomReadCondition = baseN + offset < dnaSequence.length;

            topReadCondition = (leftOrRight.equals(DiagonalReadDirections.FROM_LEFT) && baseM + offset < dnaSequence.length ||
                    leftOrRight.equals(DiagonalReadDirections.FROM_RIGHT) && baseM - offset >= 0);
        }
        // The subject is Human
        return false;
    }

    public enum ReadDirections {
        HORIZONTAL, VERTICAL
    }

    public enum DiagonalReadDirections {
        FROM_RIGHT, FROM_LEFT
    }

    public enum DiagonalReadType {
        BELOW_MAIN_DIAGONAL, ABOVE_MAIN_DIAGONAL_INCLUDING
    }

}
