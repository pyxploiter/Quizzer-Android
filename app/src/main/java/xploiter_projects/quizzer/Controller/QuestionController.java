package xploiter_projects.quizzer.Controller;

import xploiter_projects.quizzer.Model.Question;

/**
 * Created by hp on 12/24/2017.
 */

public class QuestionController extends Question {
    public void showQuestion() {
        System.out.println(question);
        System.out.println(questionType);
        System.out.println(expectedAnswer);
    }
}
