package org.app.testing.mockito.repository;

import java.util.List;

public interface IQuestionsRepository {
    List<String> findQuestionsByExamId(Long id);
    void saveQuestions(List<String> question);
}
