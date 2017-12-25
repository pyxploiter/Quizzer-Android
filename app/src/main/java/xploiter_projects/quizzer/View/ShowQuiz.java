package xploiter_projects.quizzer.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_quiz);

        String selected_quiz = getIntent().getStringExtra("quiz_title");
        //Toast.makeText(getApplicationContext(), selected_quiz,Toast.LENGTH_SHORT).show();
        Quiz quiz = quizController.getQuiz(selected_quiz);

        quizno_txt = (TextView)findViewById(R.id.quizno_txt);
        quiztitle_txt = (TextView)findViewById(R.id.quiztitle_txt);
        quizdesc_txt = (TextView)findViewById(R.id.quizdesc_txt);
        startquiz_btn = (Button)findViewById(R.id.startquiz_btn);

        Toast.makeText(getApplicationContext(), quiz.getTitle(),Toast.LENGTH_SHORT).show();
        quizno_txt.setText("Quiz No "+Integer.toString(quiz.getId()));
        quiztitle_txt.setText(quiz.getTitle());
        quizdesc_txt.setText(quiz.getDescription());
    }
}
