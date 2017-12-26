package xploiter_projects.quizzer.Controller;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import xploiter_projects.quizzer.Model.Question;
import xploiter_projects.quizzer.Model.Quiz;

/**
 * Created by Asad on 12/24/2017.
 */

public class QuizController {
    //add quiz to database
    public boolean AddQuiz(Quiz quiz){
        try {
            return (boolean) new AddQuizTask().execute(quiz).get();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean AddQuestion(Question question, int quiz_id){
        try{
            return (boolean) new AddQuestionTask().execute(question, quiz_id).get();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    //get all quizzes from server
    public List<Quiz> getAllQuiz(){
        try{
            return (List<Quiz>) new GetAllQuizTask().execute().get();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    //get single quiz from server
    public Quiz getQuiz(String title){
        try{
            return (Quiz) new GetQuizTask().execute(title).get();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public List<Question> getAllQuestion(int quiz_id){
        try{
            return (List<Question>)new GetAllQuestionTask().execute(quiz_id).get();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    //asynchronous task for adding quiz
    public class AddQuizTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            Quiz quiz = (Quiz) objects[0];
            String link = "http://10.99.0.116/quizzer/add_quiz.php?id=" + quiz.getId() + "&title=" + quiz.getTitle() + "&description=" + quiz.getDescription();
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

    //asynchronous task for adding question
    public class AddQuestionTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            Question question = (Question) objects[0];
            int quiz_id = (int)objects[1];
            String link = "http://10.99.0.116/quizzer/add_question.php?quiz_id="+quiz_id+"&question="+question.getQuestion()+"&question_type="+question.getQuestionType()+"&option1="+question.getOption1()+"&option2="+question.getOption2()+"&option3="+question.getOption3()+"&option4="+question.getOption4()+"&expected_answer="+question.getExpectedAnswer();
            Log.v("link", link);

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

    //asynchronous task for getting all quizzes from server
    public class GetAllQuizTask extends AsyncTask {
        JSONArray quizzes = null;

        @Override
        protected Object doInBackground(Object[] objects) {
            String link = "http://10.99.0.116/quizzer/get_all_quiz.php";

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

    //asynchronous task for getting all quizzes from server
    public class GetAllQuestionTask extends AsyncTask {
        JSONArray questionsJsonArray = null;

        @Override
        protected Object doInBackground(Object[] objects) {
            int quiz_id = (int)objects[0];
            String link = "http://10.99.0.116/quizzer/get_all_question.php?quiz_id="+quiz_id;

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
                    List<Question> questionList = new ArrayList<Question>();
                    questionsJsonArray = json.getJSONArray("questions");

                    for (int i=0; i < questionsJsonArray.length(); i++){
                        JSONObject questionJson = questionsJsonArray.getJSONObject(i);
                        Question question = new Question();

                        //Set quiz object attributes from JSON quiz
                        question.setQuestion(questionJson.getString("question"));
                        question.setQuestionType(questionJson.getString("question_type"));
                        question.setOption1(questionJson.getString("option1"));
                        question.setOption2(questionJson.getString("option2"));
                        question.setOption3(questionJson.getString("option3"));
                        question.setOption4(questionJson.getString("option4"));
                        question.setExpectedAnswer(questionJson.getString("expected_answer"));

                        //add quiz into List of Quiz
                        questionList.add(question);
                    }
                    return questionList;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    //asynchronous task for getting single quiz from title
    public class GetQuizTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            String quiz_title = (String)objects[0];
            String link = "http://10.99.0.116/quizzer/get_quiz.php?title="+quiz_title;

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
                    Quiz quiz = new Quiz();

                    //Set quiz object attributes from JSON quiz
                    quiz.setId(json.getInt("id"));
                    quiz.setTitle(json.getString("title"));
                    quiz.setDescription(json.getString("description"));

                    return quiz;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
