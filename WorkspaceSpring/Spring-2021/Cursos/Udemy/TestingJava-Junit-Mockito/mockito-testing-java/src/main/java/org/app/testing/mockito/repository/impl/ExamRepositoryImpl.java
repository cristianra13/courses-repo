package org.app.testing.mockito.repository.impl;

import org.app.testing.mockito.models.Exam;
import org.app.testing.mockito.repository.IExamRepository;

import java.util.Arrays;
import java.util.List;

public class ExamRepositoryImpl implements IExamRepository {

    @Override
    public List<Exam> findAll() {
        return Arrays.asList(
                new Exam(1L, "Exam1"),
                new Exam(2L, "Exam2"),
                new Exam(3L, "Exam3"),
                new Exam(4L, "Exam4"),
                new Exam(5L, "Exam5"),
                new Exam(6L, "Exam6")
        );
    }

    @Override
    public Exam save(Exam exam) {
        return null;
    }
}
