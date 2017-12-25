package xploiter_projects.quizzer.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xploiter_projects.quizzer.Controller.QuizController;
import xploiter_projects.quizzer.Model.Quiz;
import xploiter_projects.quizzer.R;

public class StudentIndex extends AppCompatActivity {
    QuizController quizController = new QuizController();
    ListView quiz_list;
    List<String> quizTitleList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_index);

        List<Quiz> quizzes = quizController.getAllQuiz();

        for (int i = 0; i < quizzes.size(); i++){
            quizTitleList.add(quizzes.get(i).getTitle());
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, quizTitleList);

        quiz_list = (ListView)findViewById(R.id.quiz_list);
        quiz_list.setAdapter(adapter);

        quiz_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)quiz_list.getItemAtPosition(position);
                Intent intent=new Intent(StudentIndex.this,ShowQuiz.class);
                intent.putExtra("quiz_title", item);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), item,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
