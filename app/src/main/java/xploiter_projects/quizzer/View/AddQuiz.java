package xploiter_projects.quizzer.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import xploiter_projects.quizzer.Controller.QuizController;
import xploiter_projects.quizzer.Model.Quiz;
import xploiter_projects.quizzer.R;

public class AddQuiz extends AppCompatActivity {
    EditText quizid_inp, title_inp, description_inp;
    Button addquestion_btn;
    QuizController quizController = new QuizController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);

        quizid_inp = (EditText)findViewById(R.id.quizid_inp);
        title_inp = (EditText)findViewById(R.id.title_inp);
        description_inp = (EditText)findViewById(R.id.description_inp);
        addquestion_btn = (Button)findViewById(R.id.addquestion_btn);

        //[Add Questions] button listener
        addquestion_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quiz quiz = new Quiz();
                //populate quiz object with user inputs
                quiz.setId(Integer.parseInt(quizid_inp.getText().toString()));
                quiz.setTitle(title_inp.getText().toString());
                quiz.setDescription(description_inp.getText().toString());

                //let the controller handle the communication with server
                boolean flag = quizController.AddQuiz(quiz);
                //boolean flag = (boolean) new QuizController().execute(quiz).get();

                Intent intent=new Intent(AddQuiz.this,AddQuestion.class);
                intent.putExtra("quiz_id", quizid_inp.getText().toString());
                startActivity(intent);
            }
        });
    }
}
