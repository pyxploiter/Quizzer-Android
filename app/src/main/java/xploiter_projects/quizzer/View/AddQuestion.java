package xploiter_projects.quizzer.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

import xploiter_projects.quizzer.R;

public class AddQuestion extends AppCompatActivity {
    EditText question_inp, answer_inp;
    EditText option1_inp, option2_inp, option3_inp, option4_inp;
    Button addNewQuestion_btn, submitQuiz_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        String quiz_id_str = getIntent().getStringExtra("quiz_id");
        int quiz_id = Integer.parseInt(quiz_id_str);

        question_inp = (EditText)findViewById(R.id.question_inp);
        option1_inp = (EditText)findViewById(R.id.option1_inp);
        option2_inp = (EditText)findViewById(R.id.option2_inp);
        option3_inp = (EditText)findViewById(R.id.option3_inp);
        option4_inp = (EditText)findViewById(R.id.option4_inp);
        answer_inp = (EditText)findViewById(R.id.answer_inp);
        addNewQuestion_btn = (Button)findViewById(R.id.addNewQuestion_btn);
        submitQuiz_btn = (Button)findViewById(R.id.submitQuiz_btn);

        TabHost host = (TabHost)findViewById(R.id.add_question_tabHost);
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
    }

}
