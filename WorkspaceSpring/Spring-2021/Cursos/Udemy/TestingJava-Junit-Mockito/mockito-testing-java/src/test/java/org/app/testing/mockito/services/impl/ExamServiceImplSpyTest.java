package org.app.testing.mockito.services.impl;

import org.app.testing.mockito.models.Exam;
import org.app.testing.mockito.repository.IExamRepository;
import org.app.testing.mockito.repository.IQuestionsRepository;
import org.app.testing.mockito.repository.impl.ExamRepositoryImpl;
import org.app.testing.mockito.repository.impl.QuestionRepositoryImpl;
import org.app.testing.mockito.services.IExamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExamServiceImplSpyTest {

    // estos dos objetos se va a mockear
//    @Mock
//    private IExamRepository examRepository;
//
//    @Mock
//    private IQuestionsRepository questionsRepository;

    @Spy
    private ExamRepositoryImpl examRepository;

    @Spy
    private QuestionRepositoryImpl questionsRepository;

    /*
        Se crea la instancia de service y se injectan los objetos anteriores vía constructor
     */
    @InjectMocks
    // private IExamService examService;
    private ExamServiceImpl examService;

    @Captor
    ArgumentCaptor<Long> captor;

    /*
    En este test se invocan los métodos reales de los clases.
    La implementación del spy() debe hacerse con clase concretas ya que requiere el método real
     */
    @Test
    void spyTest() {
        Exam exam = examService.findExamByNameWithQuestions("Exam1");
        assertNotNull(exam);
        assertEquals(1L, exam.getId());
        assertEquals("Exam1", exam.getName());
        assertTrue(exam.getQuestions().size() > 0);
    }

    /*
        En este caso se mockea el método de consulta de preguntas,
        los demás, se llama a los métodos reales
     */
    @Test
    void spyMockFindQuestionsTest() {
        List<String> questions = Arrays.asList("Questions1");

        // Con spy(), es mejor usar doReturn() para evitar el llamado al método real
        doReturn(questions).when(questionsRepository).findQuestionsByExamId(anyLong());

        // Pero en el service, ya se llama es el mock
        Exam exam = examService.findExamByNameWithQuestions("Exam1");
        assertNotNull(exam);
        assertEquals(1L, exam.getId());
        assertEquals("Exam1", exam.getName());
        assertTrue(exam.getQuestions().size() > 0);
    }

    /**
     * Method that return a exams list
     * @return
     */
    private List<Exam> getExams() {
        return Arrays.asList(
                new Exam(1L, "Exam1"),
                new Exam(2L, "Exam2"),
                new Exam(3L, "Exam3"),
                new Exam(4L, "Exam4"),
                new Exam(5L, "Exam5"),
                new Exam(6L, "Exam6")
        );
    }

    private List<Exam> getExamsNegativeId() {
        return Arrays.asList(
                new Exam(-1L, "Exam-1"),
                new Exam(-2L, "Exam-2")
        );
    }

    private List<Exam> getExamsWithIdNull() {
        return Arrays.asList(
                new Exam(null, "Exam1"),
                new Exam(null, "Exam2"),
                new Exam(null, "Exam3"),
                new Exam(null, "Exam4"),
                new Exam(null, "Exam5"),
                new Exam(null, "Exam6")
        );
    }

    /**
     * Method that return a questions list
     * @return
     */
    private List<String> getQuestions() {
        return Arrays.asList("Question1", "Question2", "Question3", "Question4", "Question5");
    }

    public final Exam EXAM = new Exam(null, "Exam7");
}