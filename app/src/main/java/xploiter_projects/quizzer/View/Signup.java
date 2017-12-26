package xploiter_projects.quizzer.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import xploiter_projects.quizzer.Controller.SignupController;
import xploiter_projects.quizzer.R;
import xploiter_projects.quizzer.Model.User;

public class Signup extends AppCompatActivity {
    AdView mAdView;
    Button signup_btn, account_btn;
    RadioButton selected_user_type;
    EditText name_inp,email_inp,password_inp;
    RadioGroup usertype_rgroup;
    //LoginController userController = new LoginController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_btn = (Button)findViewById(R.id.signup_btn);
        account_btn = (Button)findViewById(R.id.account_btn);
        name_inp = (EditText)findViewById(R.id.name_inp);
        email_inp = (EditText)findViewById(R.id.email_inp);
        password_inp = (EditText)findViewById(R.id.password_inp);
        usertype_rgroup = (RadioGroup)findViewById(R.id.user_type);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int user_type_id = usertype_rgroup.getCheckedRadioButtonId();
                selected_user_type = (RadioButton)findViewById(user_type_id);

                User user = new User();
                //populate user object with user inputs
                user.setUserName(name_inp.getText().toString());
                user.setType(selected_user_type.getText().toString());
                user.setEmail(email_inp.getText().toString());
                user.setPassword(password_inp.getText().toString());

                try{
                    //let the controller handle the communication with server
                    boolean flag = (boolean)new SignupController().execute(user).get();
                    if (flag){
                        Toast.makeText(getApplicationContext(), "Signed up successfully.",Toast.LENGTH_SHORT).show();
                        if (selected_user_type.getText().toString().equals("Student")){
                            Intent intent=new Intent(Signup.this,StudentIndex.class);
                            startActivity(intent);
                        } else {
                            Intent intent=new Intent(Signup.this,InstructorIndex.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Signup Failed",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        account_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(Signup.this,Login.class);
                startActivity(intent);
            }
        });

        //MobileAds.initialize(this,"ca-app-pub-2511646558397353~9014565267");

//        mAdView = (AdView)findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
    }
}
