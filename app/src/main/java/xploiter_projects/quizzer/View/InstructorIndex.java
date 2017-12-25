package xploiter_projects.quizzer.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xploiter_projects.quizzer.Controller.QuizController;
import xploiter_projects.quizzer.Model.Quiz;
import xploiter_projects.quizzer.R;

public class InstructorIndex extends AppCompatActivity {
    QuizController quizController = new QuizController();
    Button newquiz_btn;
    ListView quiz_list;
    List<String> quizTitleList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_index);

        List<Quiz> quizzes = quizController.getAllQuiz();

        for (int i = 0; i < quizzes.size(); i++){
           quizTitleList.add(quizzes.get(i).getTitle());
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, quizTitleList);

        quiz_list = (ListView)findViewById(R.id.quiz_list);
        quiz_list.setAdapter(adapter);

        newquiz_btn = (Button) findViewById(R.id.newquiz_btn);
        newquiz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InstructorIndex.this,AddQuiz.class);
                startActivity(intent);
            }
        });
    }
}
