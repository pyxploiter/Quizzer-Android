package xploiter_projects.quizzer.Controller;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Asad on 12/24/2017.
 */

public class LoginController extends AsyncTask {

    public LoginController(){}

    @Override
    protected Object doInBackground(Object[] objects) {
        String link = "http://10.99.30.62/quizzer/login.php?username="+(String)objects[0]+"& password="+(String)objects[1]+"&type="+(String)objects[2];
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
                Log.v("failure:",json.getString("message"));
                return new Boolean(false);
            } else {
                Log.v("success",json.getString("message"));
                return new Boolean(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
