package xploiter_projects.quizzer.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import xploiter_projects.quizzer.Controller.QuizController;
import xploiter_projects.quizzer.Model.Question;
import xploiter_projects.quizzer.R;

public class AddQuestion extends AppCompatActivity {
    QuizController quizController = new QuizController();
    EditText question_inp, answer_inp;
    EditText option1_inp, option2_inp, option3_inp, option4_inp;
    Button addNewQuestion_btn, submitQuiz_btn;
    TabHost host;
    Question question = new Question();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        final String quiz_id_str = getIntent().getStringExtra("quiz_id");
        final int quiz_id = Integer.parseInt(quiz_id_str);

        question_inp = (EditText)findViewById(R.id.question_inp);
        option1_inp = (EditText)findViewById(R.id.option1_inp);
        option2_inp = (EditText)findViewById(R.id.option2_inp);
        option3_inp = (EditText)findViewById(R.id.option3_inp);
        option4_inp = (EditText)findViewById(R.id.option4_inp);
        answer_inp = (EditText)findViewById(R.id.answer_inp);
        addNewQuestion_btn = (Button)findViewById(R.id.addNewQuestion_btn);
        submitQuiz_btn = (Button)findViewById(R.id.submitQuiz_btn);

        host = (TabHost)findViewById(R.id.add_question_tabHost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("MCQ");
        spec.setContent(R.id.mcq_tab);
        spec.setIndicator("MCQ");
        host.addTab(spec);

        spec = host.newTabSpec("True/False");
        spec.setContent(R.id.tf_tab);
        spec.setIndicator("True/False");
        host.addTab(spec);

        spec = host.newTabSpec("Numeric");
        spec.setContent(R.id.numeric_tab);
        spec.setIndicator("Numeric");
        host.addTab(spec);

        addNewQuestion_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //populating question object
                question.setQuestion(question_inp.getText().toString());
                question.setQuestionType(host.getCurrentTabTag().toString());

                if (host.getCurrentTabTag().toString().equals("MCQ")) {
                    question.setOption1(option1_inp.getText().toString());
                    question.setOption2(option2_inp.getText().toString());
                    question.setOption3(option3_inp.getText().toString());
                    question.setOption4(option4_inp.getText().toString());
                } else if (host.getCurrentTabTag().toString().equals("True/False")){
                    question.setOption1("True");
                    question.setOption2("False");
                }

                question.setExpectedAnswer(answer_inp.getText().toString());

                //let the controller handle the communication with server
                boolean flag = quizController.AddQuestion(question, quiz_id);

                Intent intent=new Intent(AddQuestion.this,AddQuestion.class);
                intent.putExtra("quiz_id", quiz_id_str);
                startActivity(intent);
            }
        });

        submitQuiz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Quiz successfully created.",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AddQuestion.this,InstructorIndex.class);
                startActivity(intent);
            }
        });
    }
}
