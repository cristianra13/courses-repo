package org.app.testing.mockito;

import org.app.testing.mockito.models.Exam;

import java.util.Arrays;
import java.util.List;

public class Data {
    public final static List<Exam> EXAMS = Arrays.asList(
            new Exam(1L, "Exam1"),
            new Exam(2L, "Exam2"),
            new Exam(3L, "Exam3"),
            new Exam(4L, "Exam4"),
            new Exam(5L, "Exam5")
    );

    public final static List<Exam> EXAMS_ID_NULL = Arrays.asList(
            new Exam(null, "Exam1"),
            new Exam(null, "Exam2"),
            new Exam(null, "Exam3"),
            new Exam(null, "Exam4"),
            new Exam(null, "Exam5")
    );

    public final static List<Exam> EXAMS_ID_NEGATIVE = Arrays.asList(
            new Exam(-6L, "Exam1"),
            new Exam(-7L, "Exam2"),
            new Exam(-8L, "Exam3"),
            new Exam(-9L, "Exam4"),
            new Exam(-10L, "Exam5")
    );

    public final static List<String> QUESTIONS = Arrays.asList("Question1", "Question2", "Question3", "Question4", "Question5");
}
