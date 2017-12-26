package xploiter_projects.quizzer.Model;

import java.io.Serializable;

/**
 * Created by Asad on 12/24/2017.
 */

public class Question {
    public String questionType;
    public String question;
    public String option1;
    public String option2;
    public String option3;
    public String option4;
    public String expectedAnswer;

    public Question(){
        option1 = "";
        option2 = "";
        option3 = "";
        option4 = "";
    }

    //Setters
    public void setQuestion(String question) {
        this.question = question;
    }
    public void setQuestionType(String type){ this.questionType = type; }
    public void setOption1(String opt1) { this.option1 = opt1; }
    public void setOption2(String opt2) { this.option2 = opt2; }
    public void setOption3(String opt3) { this.option3 = opt3; }
    public void setOption4(String opt4) { this.option4 = opt4; }
    public void setExpectedAnswer(String expectedAnswer) {
        this.expectedAnswer = expectedAnswer;
    }

    //Getters
    public String getQuestion() {
        return question;
    }
    public String getQuestionType(){ return this.questionType; }
    public String getOption1(){ return this.option1; }
    public String getOption2(){ return this.option2; }
    public String getOption3(){ return this.option3; }
    public String getOption4(){ return this.option4; }
    public String getExpectedAnswer() {
        return expectedAnswer;
    }
}
