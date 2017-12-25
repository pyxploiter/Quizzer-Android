package xploiter_projects.quizzer.Model;

import java.io.Serializable;

/**
 * Created by Asad on 12/24/2017.
 */

public class Question {
    public String questionType;
    public int questionNo;
    public String question;
    public String option1;
    public String option2;
    public String option3;
    public String option4;
    public String expectedAnswer;

    public Question(){
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setQuestType(int index) {
        if (index == 0) {
            questionType = "MCQ";
        }
        else if (index == 1) {
            questionType = "Numeric";
        }
        else {
            questionType = "True/False";
        }
    }

    public String getExpectedAnswer() {
        return expectedAnswer;
    }

    public void setExpectedAnswer(String expectedAnswer) {
        this.expectedAnswer = expectedAnswer;
    }

    public void setQuestionNo(int num) {
        this.questionNo = num;
    }

    public void setOption1(String opt1) { this.option1 = opt1; }
    public void setOption2(String opt2) { this.option1 = opt2; }
    public void setOption3(String opt3) { this.option1 = opt3; }
    public void setOption4(String opt4) { this.option1 = opt4; }
}
