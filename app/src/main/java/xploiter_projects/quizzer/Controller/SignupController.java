package xploiter_projects.quizzer.Controller;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import xploiter_projects.quizzer.Model.User;

/**
 * Created by Asad on 12/24/2017.
 */

public class SignupController extends AsyncTask {

    public SignupController(){}

    @Override
    protected Object doInBackground(Object[] objects) {
        User user = (User)objects[0];
        String link = "http://10.99.0.116/quizzer/signup.php?username="+user.getUserName()+"& type="+user.getType()+"&email="+user.getEmail()+"&password="+user.getPassword();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(link)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            JSONObject json = new JSONObject(result);

            int success = json.getInt("success");

            if (success == 0){
                Log.v("response:",json.getString("message"));
                return new Boolean(false);
            } else {
                Log.v("response",json.getString("message"));
                return new Boolean(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
