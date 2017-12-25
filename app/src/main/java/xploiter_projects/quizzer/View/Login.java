package xploiter_projects.quizzer.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import xploiter_projects.quizzer.R;
import xploiter_projects.quizzer.Controller.LoginController;

public class Login extends AppCompatActivity {
    AdView mAdView;
    Button login_btn, account_btn;
    RadioButton selected_user_type;
    EditText name_inp,password_inp;
    RadioGroup usertype_rgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_btn = (Button)findViewById(R.id.login_btn);
        account_btn = (Button)findViewById(R.id.account_btn);
        name_inp = (EditText)findViewById(R.id.name_inp);
        password_inp = (EditText)findViewById(R.id.password_inp);
        usertype_rgroup = (RadioGroup)findViewById(R.id.user_type);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int user_type_id = usertype_rgroup.getCheckedRadioButtonId();
                selected_user_type = (RadioButton)findViewById(user_type_id);
                try {
                    boolean flag = (boolean)new LoginController().execute(name_inp.getText().toString(), password_inp.getText().toString(), selected_user_type.getText().toString()).get();
                    if (flag){
                        Toast.makeText(getApplicationContext(), "Logged in",Toast.LENGTH_SHORT).show();
                        if (selected_user_type.getText().toString().equals("Student")){
                            Intent intent=new Intent(Login.this,StudentIndex.class);
                            startActivity(intent);
                        } else {
                            Intent intent=new Intent(Login.this,InstructorIndex.class);
                            startActivity(intent);
                        }
                    } else{
                        Toast.makeText(getApplicationContext(), "Login Failed",Toast.LENGTH_SHORT).show();
                        name_inp.setVisibility(View.VISIBLE);
                        name_inp.setBackgroundColor(Color.RED);
                        password_inp.setBackgroundColor(Color.RED);
                    }
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        account_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });

        //MobileAds.initialize(this,"ca-app-pub-2511646558397353~9014565267");

        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
