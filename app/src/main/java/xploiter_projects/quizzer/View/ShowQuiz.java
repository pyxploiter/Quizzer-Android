package xploiter_projects.quizzer.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import xploiter_projects.quizzer.Controller.QuizController;
import xploiter_projects.quizzer.Model.Quiz;
import xploiter_projects.quizzer.R;

public class ShowQuiz extends AppCompatActivity {
    QuizController quizController = new QuizController();
    TextView quizno_txt, quiztitle_txt, quizdesc_txt;
    Button startquiz_btn;
    Quiz quiz = new Quiz();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_quiz);

        //getting selected quiz title from parent activity.
        String selected_quiz = getIntent().getStringExtra("quiz_title");

        quiz = quizController.getQuiz(selected_quiz);

        quizno_txt = (TextView)findViewById(R.id.quizno_txt);
        quiztitle_txt = (TextView)findViewById(R.id.quiztitle_txt);
        quizdesc_txt = (TextView)findViewById(R.id.quizdesc_txt);
        startquiz_btn = (Button)findViewById(R.id.startquiz_btn);

        quizno_txt.setText("Quiz No "+Integer.toString(quiz.getId()));
        quiztitle_txt.setText(quiz.getTitle());
        quizdesc_txt.setText(quiz.getDescription());

        startquiz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowQuiz.this,ShowQuestion.class);
                intent.putExtra("quiz_id", Integer.toString(quiz.getId()));
                intent.putExtra("question_no","0");
                startActivity(intent);
            }
        });
    }
}