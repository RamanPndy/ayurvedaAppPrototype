package com.kanopytech.www.ayurveda_build_1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends AppCompatActivity {
    EditText name,age,email;
    RadioGroup gender;
    RadioButton male,female;
    Button SubmitButton;

    public static final String MyPREFERENCES = "AppPrefs" ;
    public static final String UserName = "userNameKey";
    public static  boolean my_first_time = true;
    public static final String questionId = null;
    public static final String score_v_present = "score_v_present";
    public static final String score_p_present = "score_p_present";
    public static final String score_k_present = "score_k_present";
    public static final String score_v_lifetime = "score_v_lifetime";
    public static final String score_p_lifetime = "score_p_lifetime";
    public static final String score_k_lifetime = "score_k_lifetime";
    public static final String present_selected_id = null;
    public static final String lifetime_selected_id = null;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);

            name = (EditText) findViewById(R.id.edituname);
            age = (EditText) findViewById(R.id.edituage);
            email = (EditText) findViewById(R.id.edituemail);

            gender = (RadioGroup) findViewById(R.id.gender);

            SubmitButton = (Button) findViewById(R.id.submit);
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

            SubmitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String uname = name.getText().toString();
                    String uage = age.getText().toString();
                    String uemail = email.getText().toString();

                    //RadioGroup rg = (RadioGroup)findViewById(R.id.youradio);
                    String radiovalue = ((RadioButton) findViewById(gender.getCheckedRadioButtonId())).getText().toString();

                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(UserName, uname);
                    editor.commit();

                    Toast.makeText(MainActivity.this, "Username : " + uname + " Age : " + uage + " Email : " + uemail + " Gender : " + radiovalue, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(i);
                }
            });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
