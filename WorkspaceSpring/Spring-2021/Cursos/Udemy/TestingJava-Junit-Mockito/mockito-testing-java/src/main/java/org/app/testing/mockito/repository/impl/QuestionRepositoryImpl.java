package org.app.testing.mockito.repository.impl;

import org.app.testing.mockito.Data;
import org.app.testing.mockito.repository.IQuestionsRepository;

import java.util.List;

public class QuestionRepositoryImpl implements IQuestionsRepository {

    @Override
    public List<String> findQuestionsByExamId(Long id) {
        System.out.println("QuestionRepositoryImpl.findQuestionsByExamId");
        return Data.QUESTIONS;
    }

    @Override
    public void saveQuestions(List<String> question) {

    }
}
