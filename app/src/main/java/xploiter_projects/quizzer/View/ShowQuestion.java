package xploiter_projects.quizzer.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import xploiter_projects.quizzer.R;

public class ShowQuestion extends AppCompatActivity {
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_question);

        TabHost host = (TabHost)findViewById(R.id.show_question_tabHost);
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
