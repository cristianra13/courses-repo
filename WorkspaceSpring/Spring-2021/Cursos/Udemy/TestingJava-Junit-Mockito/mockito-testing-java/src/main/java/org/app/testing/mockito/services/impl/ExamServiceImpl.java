package org.app.testing.mockito.services.impl;

import org.app.testing.mockito.models.Exam;
import org.app.testing.mockito.repository.IExamRepository;
import org.app.testing.mockito.repository.IQuestionsRepository;
import org.app.testing.mockito.services.IExamService;

import java.util.List;
import java.util.Optional;

public class ExamServiceImpl implements IExamService {

    private IExamRepository examRepository;
    private IQuestionsRepository questionsRepository;

    public ExamServiceImpl(IExamRepository examRepository, IQuestionsRepository questionsRepository) {
        this.examRepository = examRepository;
        this.questionsRepository = questionsRepository;
    }

    @Override
    public Optional<Exam> findExamByName(String name) {
        System.out.println("ExamServiceImpl.findExamByName");
        return examRepository.findAll().stream()
                .filter(exam -> exam.getName().equals(name))
                .findFirst();
    }

    @Override
    public Exam findExamByNameWithQuestions(String name) {
        System.out.println("ExamServiceImpl.findExamByNameWithQuestions");
        Optional<Exam>  examOptional = findExamByName(name);
        Exam exam = null;
        if (examOptional.isPresent()) {
            exam = examOptional.orElseThrow();
            // se mockea el llamado a este metodo del repository
            List<String> questions = questionsRepository.findQuestionsByExamId(exam.getId());
            questionsRepository.findQuestionsByExamId(exam.getId());
            exam.setQuestions(questions);
        }

        return exam;
    }

    @Override
    public Exam save(Exam exam) {
        System.out.println("ExamServiceImpl.save");
        if (!exam.getQuestions().isEmpty()) {
            questionsRepository.saveQuestions(exam.getQuestions());
        }

        return examRepository.save(exam);
    }
}
