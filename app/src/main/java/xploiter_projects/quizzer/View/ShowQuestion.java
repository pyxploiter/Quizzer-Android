package xploiter_projects.quizzer.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.List;

import xploiter_projects.quizzer.Controller.QuizController;
import xploiter_projects.quizzer.Model.Question;
import xploiter_projects.quizzer.R;

public class ShowQuestion extends AppCompatActivity {
    QuizController quizController = new QuizController();
    RadioButton option1_rbtn, option2_rbtn, option3_rbtn, option4_rbtn;
    Button submitAns_btn;
    TextView question_txt;
    EditText numeric_answer_inp;
    int question_no = 0;
    String question_no_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_question);

        final String quiz_id_str = getIntent().getStringExtra("quiz_id");
        final int quiz_id = Integer.parseInt(quiz_id_str);

        question_no_str = getIntent().getStringExtra("question_no");
        question_no = Integer.parseInt(question_no_str);

        List<Question> questions = quizController.getAllQuestion(quiz_id);

        question_txt = (TextView)findViewById(R.id.question_inp);
        option1_rbtn = (RadioButton)findViewById(R.id.option1_rbtn);
        option2_rbtn = (RadioButton)findViewById(R.id.option2_rbtn);
        option3_rbtn = (RadioButton)findViewById(R.id.option3_rbtn);
        option4_rbtn = (RadioButton)findViewById(R.id.option4_rbtn);
        numeric_answer_inp = (EditText) findViewById(R.id.numeric_answer_inp);
        submitAns_btn = (Button)findViewById(R.id.submitQuiz_btn);

        Log.v(question_no_str,"error");
        try {
            Question question = questions.get(question_no);
            question_txt.setText(question.getQuestion());
            if (question.getQuestionType().equals("MCQ")) {
                option1_rbtn.setText(question.getOption1());
                option2_rbtn.setText(question.getOption2());
                option3_rbtn.setText(question.getOption3());
                option4_rbtn.setText(question.getOption4());
            } else if (question.getQuestionType().equals("True/False")) {
                option1_rbtn.setText(question.getOption1());
                option2_rbtn.setText(question.getOption2());
                option3_rbtn.setVisibility(View.INVISIBLE);
                option4_rbtn.setVisibility(View.INVISIBLE);
            } else {
                option1_rbtn.setVisibility(View.INVISIBLE);
                option2_rbtn.setVisibility(View.INVISIBLE);
                option3_rbtn.setVisibility(View.INVISIBLE);
                option4_rbtn.setVisibility(View.INVISIBLE);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        question_no++;
        submitAns_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowQuestion.this,ShowQuestion.class);
                intent.putExtra("quiz_id", quiz_id_str);
                intent.putExtra("question_no",Integer.toString(question_no));
                startActivity(intent);
            }
        });
    }
}
