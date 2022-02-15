package org.app.testing.mockito.services.impl;

import org.app.testing.mockito.Data;
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
public class ExamServiceImplTest {

    // estos dos objetos se va a mockear
//    @Mock
//    private IExamRepository examRepository;
//
//    @Mock
//    private IQuestionsRepository questionsRepository;

    @Mock
    private ExamRepositoryImpl examRepository;

    @Mock
    private QuestionRepositoryImpl questionsRepository;

    /*
        Se crea la instancia de service y se injectan los objetos anteriores vía constructor
     */
    @InjectMocks
    // private IExamService examService;
    private ExamServiceImpl examService;

    @Captor
    ArgumentCaptor<Long> captor;

    // Ejecuta antes de cada test
    @BeforeEach
    void setup() {
        // primera forma de habilitar el uso de mocks por inyección de dependencias
        MockitoAnnotations.openMocks(this);

        // Creamos el mock del repository
//        examRepository = mock(ExamOtherRepository.class);
//        questionsRepository = mock(IQuestionsRepository.class);
//        examService = new ExamServiceImpl(examRepository, questionsRepository);
    }

    @Test
    void findExamByNameTest() {
        // Le decimos a mockito que cuando se llame el metodo del repo
        when(examRepository.findAll()).thenReturn(getExams());

        Optional<Exam> exam = examService.findExamByName("Exam1");
        assertTrue(exam.isPresent());
        assertEquals("Exam1", exam.orElseThrow().getName());
    }

    @Test
    void findExamByNameEmptyListTest() {
        // Le decimos a mockito que cuando se llame el metodo del repo
        when(examRepository.findAll()).thenReturn(Collections.emptyList());

        Optional<Exam> exam = examService.findExamByName("Exam1");
        assertTrue(exam.isEmpty());
        assertThrows(NoSuchElementException.class, exam::orElseThrow);
    }

    @Test
    void questionsExamTest() {
        when(examRepository.findAll()).thenReturn(getExams());
        when(questionsRepository.findQuestionsByExamId(anyLong())).thenReturn(getQuestions());
        Exam exam = examService.findExamByNameWithQuestions("Exam1");
        assertNotNull(exam);
        assertTrue(exam.getQuestions().size() > 0);
        assertTrue(exam.getQuestions().contains("Question3"));
    }

    @Test
    void questionsExamVerifyTest() {
        when(examRepository.findAll()).thenReturn(getExams());
        when(questionsRepository.findQuestionsByExamId(anyLong())).thenReturn(getQuestions());
        Exam exam = examService.findExamByNameWithQuestions("Exam1");
        assertNotNull(exam);
        assertTrue(exam.getQuestions().size() > 0);
        assertTrue(exam.getQuestions().contains("Question3"));
        verify(examRepository).findAll();
        verify(questionsRepository).findQuestionsByExamId(anyLong());
    }

    @Test
    void questionsExamVerifyEmptyListTest() {
        when(examRepository.findAll()).thenReturn(Collections.emptyList());
        when(questionsRepository.findQuestionsByExamId(anyLong())).thenReturn(getQuestions());
        Exam exam = examService.findExamByNameWithQuestions("Exam1");
        assertNull(exam);
        verify(examRepository).findAll();
        verify(questionsRepository).findQuestionsByExamId(anyLong());
    }


    @Test
    void saveExamTest() {
        /*
            Desarrollo orientado a BDD -> Behavior Drive Development
            Dentro de esto se usa:
            Given -> dado
            When -> cuando
            Then -> entonces
         */

        // Given -> precondiciones del entorno de prueba
        when(examRepository.save(any(Exam.class))).then(new Answer<Exam>(){
            Long sequence = 7l;

            /*
                Dentro de invocationOnMock podemos obtener el objeto que se está enviando a guardar
             */
            @Override
            public Exam answer(InvocationOnMock invocationOnMock) throws Throwable {
                // obtenemos el examen
                Exam exam = invocationOnMock.getArgument(0);
                exam.setId(sequence++);
                return exam;
            }
        });

        EXAM.setQuestions(getQuestions());

        // When -> cuando
        Exam savedExam = examService.save(EXAM);

        // Then -> Validaciones de pruebas
        assertNotNull(savedExam);
        assertNotNull(savedExam.getId());
        assertEquals(7L, savedExam.getId());

        // verify invocations save methods
        verify(examRepository).save(any());
        verify(questionsRepository).saveQuestions(anyList());
    }

    @Test
    void handleException() {
        when(examRepository.findAll()).thenReturn(getExamsWithIdNull());
        when(questionsRepository.findQuestionsByExamId(isNull())).thenThrow(IllegalArgumentException.class);
        Exception exam6Throw = assertThrows(IllegalArgumentException.class, () -> examService.findExamByNameWithQuestions("Exam6"));
        assertEquals(IllegalArgumentException.class, exam6Throw.getClass());
        verify(examRepository).findAll();
        verify(questionsRepository).findQuestionsByExamId(isNull());
    }

    @Test
    void argumentMatchersTest() {
        when(examRepository.findAll()).thenReturn(getExams());
        when(questionsRepository.findQuestionsByExamId(anyLong())).thenReturn(getQuestions());

        examService.findExamByNameWithQuestions("Exam1");
        verify(examRepository).findAll();
        // verify(questionsRepository).findQuestionsByExamId(argThat( arg -> arg != null && arg.equals(1L)));
        verify(questionsRepository).findQuestionsByExamId(eq(1L));
    }

    @Test
    void argumentMatchersTest2() {
        when(examRepository.findAll()).thenReturn(getExamsNegativeId());
        when(questionsRepository.findQuestionsByExamId(anyLong())).thenReturn(getQuestions());

        examService.findExamByNameWithQuestions("Exam-2");
        verify(examRepository).findAll();
        verify(questionsRepository).findQuestionsByExamId(argThat(new MyArgumtMatchers()));
    }

    @Test
    void argumentMatchersTest3() {
        when(examRepository.findAll()).thenReturn(getExamsNegativeId());
        when(questionsRepository.findQuestionsByExamId(anyLong())).thenReturn(getQuestions());

        examService.findExamByNameWithQuestions("Exam-2");
        verify(examRepository).findAll();
        verify(questionsRepository).findQuestionsByExamId(argThat(argument -> argument!= null && argument > 0));
    }

    // Inner class
    public static class MyArgumtMatchers implements ArgumentMatcher<Long> {
        private Long argument;
        // implement method
        @Override
        public boolean matches(Long argument) {
            this.argument = argument;
            return argument != null && argument > 0;
        }

        @Override
        public String toString() {
            return "This is to custom error message the print mockito when this test is failed\n" +
                    argument + " - It's must be a positive integer number +";
        }
    }

    // Argument capture


    @Test
    void argumentCaptorTest() {
        when(examRepository.findAll()).thenReturn(getExams());
        // vamos a capturar el valor que se pasa al metodo del repositorio
        when(questionsRepository.findQuestionsByExamId(anyLong())).thenReturn(getQuestions());

        examService.findExamByNameWithQuestions("Exam1");
        // ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(questionsRepository).findQuestionsByExamId(captor.capture());

        assertEquals(1L, captor.getValue());
    }


    @Test
    void doThrowTest() {
        Exam exam = getExams().get(0);
        exam.setQuestions(getQuestions());
        doThrow(IllegalArgumentException.class).when(questionsRepository).saveQuestions(anyList());
        assertThrows(IllegalArgumentException.class, () -> examService.save(exam));
    }

    @Test
    void doAnswerTest() {
        when(examRepository.findAll()).thenReturn(getExams());
        // when(questionsRepository.findQuestionsByExamId(anyLong())).thenReturn(getQuestions());
        doAnswer(invocationOnMock -> {
           // capture id
            Long idExam = invocationOnMock.getArgument(0); // we only  pass one argument
            return idExam == 5L ? getQuestions() : Collections.EMPTY_LIST; // if idExam eq to 5 then return questions, else return null
        }).when(questionsRepository).findQuestionsByExamId(anyLong());

        Exam exam5 = examService.findExamByNameWithQuestions("Exam5");
        assertEquals(5, exam5.getId());
        assertEquals("Exam5", exam5.getName());
        assertEquals(5, exam5.getQuestions().size());
        assertTrue(exam5.getQuestions().contains("Question5"));

        verify(questionsRepository).findQuestionsByExamId(anyLong());
    }

    @Test
    void saveExamDoAnswerTest() {
        doAnswer(new Answer<Exam>(){
            Long sequence = 7l;
            /*
                Dentro de invocationOnMock podemos obtener el objeto que se está enviando a guardar
             */
            @Override
            public Exam answer(InvocationOnMock invocationOnMock) throws Throwable {
                // obtenemos el examen
                Exam exam = invocationOnMock.getArgument(0);
                exam.setId(sequence++);
                return exam;
            }
        }).when(examRepository).save(any(Exam.class));

        EXAM.setQuestions(getQuestions());

        // When -> cuando
        Exam savedExam = examService.save(EXAM);

        // Then -> Validaciones de pruebas
        assertNotNull(savedExam);
        assertNotNull(savedExam.getId());
        assertEquals(7L, savedExam.getId());

        // verify invocations save methods
        verify(examRepository).save(any());
        verify(questionsRepository).saveQuestions(anyList());
    }
    
    /*
        doCallRealMethod
        Permite llamar el metodo real del objeto,
        este es muy similar a un spy.
        Permite llamar por partes los llamados
     */

    @Test
    void doCallRealMethodTest() {
        // Llamamos al mock
        when(examRepository.findAll()).thenReturn(getExams());
        //when(questionsRepository.findQuestionsByExamId(anyLong())).thenReturn(getQuestions());
        // llamamos al método real
        doCallRealMethod().when(questionsRepository).findQuestionsByExamId(anyLong());

        Exam exam = examService.findExamByNameWithQuestions("Exam1");
        assertNotNull(exam);
        assertEquals(1L, exam.getId());
        assertEquals("Exam1", exam.getName());
    }

    /*
    En este test se invocan los métodos reales de los clases.
    La implementación del spy() debe hacerse con clase concretas ya que requiere el método real
     */
    @Test
    void spyTest() {
        IExamRepository examRepository = spy(ExamRepositoryImpl.class);
        IQuestionsRepository questionsRepository = spy(QuestionRepositoryImpl.class);
        IExamService examService = new ExamServiceImpl(examRepository, questionsRepository);

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
        IExamRepository examRepository = spy(ExamRepositoryImpl.class);
        IQuestionsRepository questionsRepository = spy(QuestionRepositoryImpl.class);
        IExamService examService = new ExamServiceImpl(examRepository, questionsRepository);
        List<String> questions = Arrays.asList("Questions1");
        // acá se llama el método real findQuestionsByExamId(anyLong())
        //when(questionsRepository.findQuestionsByExamId(anyLong())).thenReturn(questions);

        // Con spy(), es mejor usar doReturn() para evitar el llamado al método real
        doReturn(questions).when(questionsRepository).findQuestionsByExamId(anyLong());

        // Pero en el service, ya se llama es el mock
        Exam exam = examService.findExamByNameWithQuestions("Exam1");
        assertNotNull(exam);
        assertEquals(1L, exam.getId());
        assertEquals("Exam1", exam.getName());
        assertTrue(exam.getQuestions().size() > 0);
    }

    /*
        order Invocation methods
     */

    @Test
    void orderInvocationsTest() {
        when(examRepository.findAll()).thenReturn(Data.EXAMS);

        examService.findExamByNameWithQuestions("Exam1");
        examService.findExamByNameWithQuestions("Exam2");

        // Pasamos por argumento el o los mocks para validar el orden
        InOrder inOrder = inOrder(questionsRepository);
        // primero se invoca Exam1 y luego EXam2
        inOrder.verify(questionsRepository).findQuestionsByExamId(1L);
        inOrder.verify(questionsRepository).findQuestionsByExamId(2L);
    }

    @Test
    void orderInvocationsTest2() {
        when(examRepository.findAll()).thenReturn(Data.EXAMS);

        examService.findExamByNameWithQuestions("Exam1");
        examService.findExamByNameWithQuestions("Exam2");

        // Pasamos por argumento el o los mocks para validar el orden
        InOrder inOrder = inOrder(examRepository, questionsRepository);
        // primero se invoca findAll y  Exam1
        inOrder.verify(examRepository).findAll();
        inOrder.verify(questionsRepository).findQuestionsByExamId(1L);

        // Y luego findAll y EXam2
        inOrder.verify(examRepository).findAll();
        inOrder.verify(questionsRepository).findQuestionsByExamId(2L);
    }

    /**
     * Número de invocaciones por método
     */
    @Test
    void numberOfInvocationsTest() {
        when(examRepository.findAll()).thenReturn(Data.EXAMS);

        examService.findExamByNameWithQuestions("Exam1");

        // Cuantas veces
        verify(questionsRepository, times(1)).findQuestionsByExamId(1L);
        // Al menos
        verify(questionsRepository, atLeast(1)).findQuestionsByExamId(1L);
        // al menos una vez
        verify(questionsRepository, atLeastOnce()).findQuestionsByExamId(1L);
        // Como máximo
        verify(questionsRepository, atMost(1)).findQuestionsByExamId(1L);
        // Como máximo una vez
        verify(questionsRepository, atMostOnce()).findQuestionsByExamId(1L);
    }

    @Test
    void numberOfInvocations2Test() {
        when(examRepository.findAll()).thenReturn(Data.EXAMS);

        examService.findExamByNameWithQuestions("Exam1");

        // Cuantas veces
        verify(questionsRepository, times(2)).findQuestionsByExamId(1L);
        // Al menos
        verify(questionsRepository, atLeast(2)).findQuestionsByExamId(1L);
        // al menos una vez
        verify(questionsRepository, atLeastOnce()).findQuestionsByExamId(1L);
        // Como máximo
        verify(questionsRepository, atMost(2)).findQuestionsByExamId(1L);
        // Como máximo una vez
        // verify(questionsRepository, atMostOnce()).findQuestionsByExamId(1L); // falla
    }

    @Test
    void numberOfInvocations3Test() {
        when(examRepository.findAll()).thenReturn(Collections.emptyList());

        examService.findExamByNameWithQuestions("Exam1");

        // Con never() validamos que nunca se invoque un método de un mock
        verify(questionsRepository, never()).findQuestionsByExamId(1L);
        // Verifica no interacciones
        verifyNoInteractions(questionsRepository);

        verify(examRepository).findAll();
        verify(examRepository, times(1)).findAll();
        verify(examRepository, atLeast(1)).findAll();
        verify(examRepository, atLeast(1)).findAll();
        verify(examRepository, atMost(1)).findAll();
        verify(examRepository, atMostOnce()).findAll();
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