package ar.com.mercadolibre.mutants.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class that models the subjects
 *
 * @author vfuentes
 */
@Document(collection = "subjects")
public class Subject {

    public String subjectType;
    @Indexed(unique = true)
    private String dnaSequence;

    public Subject(String[] dnaSequence, String subjectType) {
        this.dnaSequence = String.join("", dnaSequence);
        this.subjectType = subjectType;
    }


}
