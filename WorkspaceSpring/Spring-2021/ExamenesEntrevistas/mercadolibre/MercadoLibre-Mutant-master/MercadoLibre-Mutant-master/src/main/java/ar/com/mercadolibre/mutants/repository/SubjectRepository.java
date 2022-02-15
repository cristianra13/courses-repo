package ar.com.mercadolibre.mutants.repository;

import ar.com.mercadolibre.mutants.model.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRepository extends MongoRepository<Subject, String> {

    Long countBySubjectType(String subjectType);
}
