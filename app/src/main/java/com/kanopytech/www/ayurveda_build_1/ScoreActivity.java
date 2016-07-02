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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class ScoreActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    TextView v_present,p_present,k_present,v_lifetime,p_lifetime,k_lifetime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        v_present = (TextView)findViewById(R.id.VScorePresent);
        v_lifetime = (TextView)findViewById(R.id.VScoreLifetime);

        p_present = (TextView)findViewById(R.id.PScorePresent);
        p_lifetime = (TextView)findViewById(R.id.PScoreLifetime);

        k_present = (TextView)findViewById(R.id.KScorePresent);
        k_lifetime = (TextView)findViewById(R.id.KScoreLifetime);

        sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, MODE_PRIVATE);
        v_present.setText(sharedpreferences.getString(MainActivity.score_v_present,""));
        v_lifetime.setText(sharedpreferences.getString(MainActivity.score_v_lifetime,""));
        p_present.setText(sharedpreferences.getString(MainActivity.score_p_present,""));
        p_lifetime.setText(sharedpreferences.getString(MainActivity.score_p_lifetime,""));
        k_present.setText(sharedpreferences.getString(MainActivity.score_k_present,""));
        k_lifetime.setText(sharedpreferences.getString(MainActivity.score_k_lifetime,""));
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
