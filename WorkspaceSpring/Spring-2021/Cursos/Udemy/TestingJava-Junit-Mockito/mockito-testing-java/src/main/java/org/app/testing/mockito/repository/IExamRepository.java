package org.app.testing.mockito.repository;

import org.app.testing.mockito.models.Exam;
import java.util.List;

public interface IExamRepository {
    List<Exam> findAll();

    Exam save(Exam exam);
}
