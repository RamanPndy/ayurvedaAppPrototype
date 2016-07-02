package com.kanopytech.www.ayurveda_build_1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;


public class ResultActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    TextView userNameText;
    ImageView presentVImage,presentPImage,presentKImage,lifetimeVImage,lifetimePImage,lifetimeKImage;
    public int present_v_score,present_p_score,present_k_score,lifetime_v_score,lifetime_p_score,lifetime_k_score,present_max_score,
    present_min_score,present_mid_score,lifetime_max_score,lifetime_min_score,lifetime_mid_score;
    public int Threshold1 = 7,Threshold2 = 5,digTypePresent=0,digTypeLifetime=0;
    ArrayList<Integer> present_score, lifetime_score;
    Enumeration digestionTypeVariables;
    Vector digetionTypes = new Vector();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        userNameText = (TextView)findViewById(R.id.username_res);

        presentVImage = (ImageView)findViewById(R.id.presentVdigestionType);
        presentPImage = (ImageView)findViewById(R.id.presentPdigestionType);
        presentKImage = (ImageView)findViewById(R.id.presentKdigestionType);

        lifetimeVImage = (ImageView)findViewById(R.id.lifetimeVdigestionType);
        lifetimePImage = (ImageView)findViewById(R.id.lifetimePdigestionType);
        lifetimeKImage = (ImageView)findViewById(R.id.lifetimeKdigestionType);

        sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, MODE_PRIVATE);
        userNameText.setText(sharedpreferences.getString(MainActivity.UserName, ""));

        present_v_score = Integer.parseInt(sharedpreferences.getString(MainActivity.score_v_present,""));
        present_p_score = Integer.parseInt(sharedpreferences.getString(MainActivity.score_p_present, ""));
        present_k_score = Integer.parseInt(sharedpreferences.getString(MainActivity.score_k_present, ""));
        lifetime_v_score = Integer.parseInt(sharedpreferences.getString(MainActivity.score_v_lifetime, ""));
        lifetime_p_score = Integer.parseInt(sharedpreferences.getString(MainActivity.score_p_lifetime, ""));
        lifetime_k_score = Integer.parseInt(sharedpreferences.getString(MainActivity.score_k_lifetime, ""));

        digetionTypes.add(1);
        digetionTypes.add(2);
        digetionTypes.add(3);
        digetionTypes.add(4);
        digetionTypes.add(5);
        digetionTypes.add(6);
        digetionTypes.add(7);

        digestionTypeVariables = digetionTypes.elements();//Enumerated all digestion types

        present_score = new ArrayList<Integer>();
        lifetime_score = new ArrayList<Integer>();

        present_score.add(present_v_score);
        present_score.add(present_p_score);
        present_score.add(present_k_score);

        lifetime_score.add(lifetime_v_score);
        lifetime_score.add(lifetime_p_score);
        lifetime_score.add(lifetime_k_score);

        present_max_score = Collections.max(present_score);
        present_min_score = Collections.min(present_score);
        present_mid_score = (present_v_score+present_p_score+present_k_score) - present_min_score - present_max_score;

        lifetime_max_score = Collections.max(lifetime_score);
        lifetime_min_score = Collections.min(lifetime_score);
        lifetime_mid_score = (lifetime_v_score+lifetime_p_score+lifetime_k_score) - lifetime_min_score - lifetime_max_score;

        if ((present_max_score - present_mid_score) > Threshold1)
        {
            //return type from this blog can either of 1,2,3
            if(present_max_score == present_v_score) {
                digTypePresent = 1;
                presentVImage.setImageResource(R.drawable.v);

            }

            if(present_max_score == present_p_score) {
                digTypePresent = 2;
                presentPImage.setImageResource(R.drawable.p);
            }

            if(present_max_score == present_k_score) {
                digTypePresent = 3;
                presentKImage.setImageResource(R.drawable.k);
            }
        }
        else if ((present_max_score - present_min_score) > Threshold2)
        {
            // Binary Type
            if(present_min_score == present_k_score) {
                digTypePresent = 4;
                presentVImage.setImageResource(R.drawable.v);
                presentPImage.setImageResource(R.drawable.p);

            }

            if(present_min_score == present_v_score) {
                digTypePresent = 5;
                presentPImage.setImageResource(R.drawable.p);
                presentKImage.setImageResource(R.drawable.k);
            }

            if(present_min_score == present_p_score) {
                digTypePresent = 6;
                presentVImage.setImageResource(R.drawable.v);
                presentKImage.setImageResource(R.drawable.k);
            }
        }
        else
        {
            //Ternary Type
            digTypePresent = 7;
            presentVImage.setImageResource(R.drawable.v);
            presentPImage.setImageResource(R.drawable.p);
            presentKImage.setImageResource(R.drawable.k);
        }

        if ((lifetime_max_score - lifetime_mid_score) > Threshold1)
        {
            //return type from this blog can either of 1,2,3
            if(lifetime_max_score == lifetime_v_score) {
                digTypeLifetime = 1;
                lifetimeVImage.setImageResource(R.drawable.v);
            }

            if(lifetime_max_score == lifetime_p_score) {
                digTypeLifetime = 2;
                lifetimePImage.setImageResource(R.drawable.p);
            }

            if(lifetime_max_score == lifetime_k_score) {
                digTypeLifetime = 3;
                lifetimeKImage.setImageResource(R.drawable.k);
            }
        }
        else if ((lifetime_max_score - lifetime_min_score) > Threshold2)
        {
            // Binary Type
            if(lifetime_min_score == lifetime_k_score) {
                digTypeLifetime = 4;
                lifetimeVImage.setImageResource(R.drawable.v);
                lifetimePImage.setImageResource(R.drawable.p);
            }

            if(lifetime_min_score == lifetime_v_score) {
                digTypeLifetime = 5;
                lifetimePImage.setImageResource(R.drawable.p);
                lifetimeKImage.setImageResource(R.drawable.k);
            }

            if(lifetime_min_score == lifetime_p_score) {
                digTypeLifetime = 6;
                lifetimeVImage.setImageResource(R.drawable.v);
                lifetimeKImage.setImageResource(R.drawable.k);
            }
        }
        else
        {
            //Ternary Type
            digTypeLifetime = 7;
            lifetimeVImage.setImageResource(R.drawable.v);
            lifetimePImage.setImageResource(R.drawable.p);
            lifetimeKImage.setImageResource(R.drawable.k);
        }

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
