package org.app.testing.mockito.repository.impl;

import org.app.testing.mockito.Data;
import org.app.testing.mockito.models.Exam;
import org.app.testing.mockito.repository.IExamRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExamOtherRepositoryImpl implements IExamRepository {

    /*
        Este método nunca se invocaría ya que se está simulando en el test
     */
    @Override
    public List<Exam> findAll() {
        System.out.println("ExamOtherRepositoryImpl.findAll");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Data.EXAMS;
    }

    @Override
    public Exam save(Exam exam) {
        System.out.println("ExamOtherRepositoryImpl.save");
        return Data.EXAMS.get(exam.getId().intValue());
    }
}
