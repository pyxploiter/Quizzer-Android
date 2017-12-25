package xploiter_projects.quizzer.Controller;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import xploiter_projects.quizzer.Model.Quiz;

/**
 * Created by Asad on 12/24/2017.
 */

public class QuizController {

    public boolean AddQuiz(Quiz quiz){
        try {
            return (boolean) new AddQuizTask().execute(quiz).get();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public List<Quiz> getAllQuiz(){
        try{
            return (List<Quiz>) new GetAllQuizTask().execute().get();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public class AddQuizTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            Quiz quiz = (Quiz) objects[0];
            String link = "http://10.99.30.62/quizzer/add_quiz.php?id=" + quiz.getId() + "&title=" + quiz.getTitle() + "&description=" + quiz.getDescription();
            //Log.v("link", link);

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(link)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String result = response.body().string();
                JSONObject json = new JSONObject(result);

                int success = json.getInt("success");

                if (success == 0) {
                    Log.v("response:", json.getString("message"));
                    return new Boolean(false);
                } else {
                    Log.v("response", json.getString("message"));
                    return new Boolean(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class GetAllQuizTask extends AsyncTask {
        JSONArray quizzes = null;

        @Override
        protected Object doInBackground(Object[] objects) {
            String link = "http://10.99.30.62/quizzer/get_all_quiz.php";

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(link)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String result = response.body().string();

                JSONObject json = new JSONObject(result);

                int success = json.getInt("success");

                if (success == 0) {
                    Log.v("response:", json.getString("message"));
                    return null;
                } else {
                    List<Quiz> quizList = new ArrayList<Quiz>();
                    quizzes = json.getJSONArray("quizzes");

                    for (int i=0; i < quizzes.length(); i++){
                        JSONObject quizJson = quizzes.getJSONObject(i);
                        Quiz quiz = new Quiz();

                        //Set quiz object attributes from JSON quiz
                        quiz.setId(quizJson.getInt("id"));
                        quiz.setTitle(quizJson.getString("title"));
                        quiz.setDescription(quizJson.getString("description"));

                        //add quiz into List of Quiz
                        quizList.add(quiz);
                    }
                    return quizList;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
