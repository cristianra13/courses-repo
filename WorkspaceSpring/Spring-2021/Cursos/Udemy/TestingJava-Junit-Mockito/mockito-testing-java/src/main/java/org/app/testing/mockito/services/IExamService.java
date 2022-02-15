package org.app.testing.mockito.services;

import org.app.testing.mockito.models.Exam;

import java.util.Optional;

public interface IExamService {
    Optional<Exam> findExamByName(String name);

    Exam findExamByNameWithQuestions(String name);

    Exam save(Exam exam);
}
