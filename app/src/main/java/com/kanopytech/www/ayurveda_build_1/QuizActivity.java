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

import java.util.ArrayList;
import java.util.Stack;


public class QuizActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    TextView Question,QuestionId,username;
    Button PrevBtn,NextBtn;
    RadioGroup Present,Lifetime;
    RadioButton present_opt1,present_opt2,present_opt3,lifetime_opt1,lifetime_opt2,lifetime_opt3;
    public static int que_flag = 0;
    public int present_v_score = 0;
    public int present_p_score = 0;
    public int present_k_score = 0;
    public int lifetime_v_score = 0;
    public int lifetime_p_score = 0;
    public int lifetime_k_score = 0;
    public int totalQuestions = 39;
    public int selected_present=0;
    public int selected_lifetime=0;
    public boolean option_from_present_selected = false;
    public boolean option_from_lifetime_selected = false;

    public Stack<Integer> present_selected_id,lifetime_selected_id,que_id;
    public static String [] ques_id = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30",
            "31","32","33","34","35","36","37","38","39"};
    public static String [] questions = {"What is Your Body Frame ?","What is Your Body Weight ?","What is Your Skin Texture ?","What is Your Hair Quality ?","What is Your Face Shape ?",
            "What is size of your Teeth ?","What is the Color of Gums ?","What is the Width of Tongue ?","What is the Quality of Hands ?","How is Your Finger Nails ?",
            "What is Your Digestive Strength ?","What is Your Digestive Disturbances From Problem Foods ?","What is Your Food Cravings ?","What is Your Eating habits ?",
            "What is Your Food Sentivity ?","What is Your Urination ?","What is Your Feces ?","What is Your Sweat and Body Odor ?","What is Your Blood Circulation ?",
            "What is Your Appetite(Agni) ?","What is Your Activitues ?","What is Your Strength and Endurance ?","What is Your Senstivity to Environment ?","What is Your Resistance to Disease ?",
            "What is Your Disease Tendency ?","What is Your Speech Habits ?","What is Your Mental Nature ?","What is Your Emotional Response ?","What is Your Emotional Tendencies ?",
            "What is Your Psychological Tendencies ?","What is Your Social Relations ?","What is Your Mental Reactions to Objects ?","What is Your relationship to Money ?",
            "What is Your relationship To Spending Money ?","What are Your Friends ?","What is Your Love Realationships ?","What is Your Neurotic Tendencies ?","What are Your Life Goals ?",
            "What is Your Sleep ?"};
    public static String [] option1 = {"V-Tall or Short","V-Light","V-Cold","V-Coarse","V-Small","V-Small","V-Dark","V-Narrower than teeth","V-Fine","V-Thin","V-variable or weak",
            "V-Intestinal Gas","V-Dry","V-Binges","V-Beans","V-Two to Four times per day","V-Dry","V-Little sweat with no smell","V-poor","V-variable","V-quick","V-poor endurance",
            "V-dislike of cold,dryness","V-poor","V-nervous system diseases","V-quick","V-quick","V-quick but sooon over","V-anxious","V-creative","V-relates easily","V-not very important",
            "V-not very important","V-spends easily","V-has money, but not deep","V-tends to have many partners","V-hysteria","V-change frequently","V-light"};
    public static String [] option2 = {"P-Medium Height","P-Moderate","P-Warm","P-Fine","P-Medium","P-Medium","P-Red","P-Same width as teeth","P-Symmetrical","P-Strong","P-Strong",
            "P-Acidity","P-Spicy","P-Likes regular","P-Onions","P-Four to Six times per day","P-Abundant","P-strong sweating,strong smell","P-good","P-strong","P-motivated","P-moderate",
            "P-dislike of heat or direct sun","P-medium","P-febrile disease","P-moderate","P-factual","P-hot","P-frustrated","P-helpful","P-relates well","P-to know about","P-useful to gain control or respect",
            "P-spends for purpose","P-has close or several","P-tends to marry for Position or Looks","P-extreme temper","P-determined","P-moderate"};
    public static String [] option3 = {"K-Stout stocky","K-Heavy","K-Cool","K-Abundant","K-Large","K-Large","K-Soft","K-Wider than teeth","K-Large","K-Thick","K-Medium or slow",
            "K-Bloated","K-Sweet","K-Eats Constantly","K-Dairy","K-Three to Five times per day","K-Moderate","K-moderate sweating,neutral smell","K-slow but steady","K-constant but low",
            "K-slow","K-strong","K-dislike of cold","K-good","K-respiratory system diseases","K-slow","K-slow","K-slow","K-calm","K-caring","K-relates with difficulty","K-important to have or own practical",
            "K-very important","K-spends with difficulty","K-has few","K-single partner","K-sorrow","K-fixed","K-heavy"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que);

        sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        present_selected_id = new Stack<>();
        lifetime_selected_id = new Stack<>();
        que_id = new Stack<>();

        QuestionId = (TextView)findViewById(R.id.questionId);
        Question = (TextView)findViewById(R.id.question);

        Present = (RadioGroup)findViewById(R.id.presentOptions);
        Lifetime = (RadioGroup)findViewById(R.id.LifetimeOptions);

        present_opt1 = (RadioButton)findViewById(R.id.present_opt1);
        present_opt2 = (RadioButton)findViewById(R.id.present_opt2);
        present_opt3 = (RadioButton)findViewById(R.id.present_opt3);

        lifetime_opt1 = (RadioButton)findViewById(R.id.lifetime_opt1);
        lifetime_opt2 = (RadioButton)findViewById(R.id.lifetime_opt2);
        lifetime_opt3 = (RadioButton)findViewById(R.id.lifetime_opt3);

        PrevBtn = (Button)findViewById(R.id.prevQuestion);
        NextBtn = (Button)findViewById(R.id.nextQuestion);

        username = (TextView)findViewById(R.id.username);
        username.setText(sharedpreferences.getString(MainActivity.UserName,""));

        PrevBtn.setVisibility(View.INVISIBLE);

        QuestionId.setText(ques_id[0]+"/39");
        Question.setText(questions[0]);

        present_opt1.setText(option1[0]);
        present_opt2.setText(option2[0]);
        present_opt3.setText(option3[0]);

        lifetime_opt1.setText(option1[0]);
        lifetime_opt2.setText(option2[0]);
        lifetime_opt3.setText(option3[0]);

        selected_present = Present.getCheckedRadioButtonId();
        selected_lifetime = Lifetime.getCheckedRadioButtonId();

        if(selected_lifetime < 1 || selected_present < 1 ){
            NextBtn.setEnabled(false);
            selected_lifetime = 0;
            selected_present = 0;
        }

        Present.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                option_from_present_selected = true;

                int first_chk = Lifetime.getCheckedRadioButtonId();
                if(first_chk>0)
                    NextBtn.setEnabled(true);

            }
        });

        Lifetime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                option_from_lifetime_selected = true;

                if(option_from_present_selected && option_from_lifetime_selected) {
                    NextBtn.setEnabled(true);

                    option_from_present_selected = false;
                    option_from_lifetime_selected = false;
                }
            }
        });

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (que_flag <= 39) {
                    ++que_flag;
                    int selected_present = Present.getCheckedRadioButtonId();
                    int selected_lifetime = Lifetime.getCheckedRadioButtonId();

                    que_id.push(que_flag);
                    present_selected_id.push(selected_present);
                    lifetime_selected_id.push(selected_lifetime);

                    switch(selected_present)
                    {
                        case R.id.present_opt1:
                            ++present_v_score;
                            option_from_present_selected = false;
                            option_from_lifetime_selected = false;
                            break;

                        case R.id.present_opt2:
                            ++present_p_score;
                            option_from_present_selected = false;
                            option_from_lifetime_selected = false;
                            break;

                        case R.id.present_opt3:
                            ++present_k_score;
                            option_from_present_selected = false;
                            option_from_lifetime_selected = false;
                            break;
                    }

                    switch(selected_lifetime)
                    {
                        case R.id.lifetime_opt1:
                            ++lifetime_v_score;
                            option_from_present_selected = false;
                            option_from_lifetime_selected = false;
                            break;

                        case R.id.lifetime_opt2:
                            ++lifetime_p_score;
                            option_from_present_selected = false;
                            option_from_lifetime_selected = false;
                            break;

                        case R.id.lifetime_opt3:
                            ++lifetime_k_score;
                            option_from_present_selected = false;
                            option_from_lifetime_selected = false;
                            break;
                    }
                    updateQuestions(que_flag,present_v_score,present_p_score,present_k_score,lifetime_v_score,lifetime_p_score,lifetime_k_score);

                    Present.clearCheck();
                    Lifetime.clearCheck();

                    int chk_selected_present = Present.getCheckedRadioButtonId();
                    int chk_selected_lifetime = Lifetime.getCheckedRadioButtonId();

                    if((chk_selected_present < 1) && (chk_selected_lifetime < 1)) {
                        NextBtn.setEnabled(false);
                    }

                    PrevBtn.setVisibility(View.VISIBLE);


//                    Toast.makeText(QuizActivity.this, "Present :- " + present_v_score + "," + present_p_score + "," + present_k_score + "Lifetime :- "
//                            + lifetime_v_score + "," + lifetime_p_score + "," + lifetime_k_score, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this, "Quiz Completed!!!", Toast.LENGTH_SHORT).show();
               }
            }
        });

        PrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((que_flag > 0) && (que_flag < 39)) {
                    --que_flag;

//                    sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, MODE_PRIVATE);
                    if((!present_selected_id.empty()) || (!lifetime_selected_id.empty()))
                    {
                        int selected_present = present_selected_id.pop();
                        int selected_lifetime = lifetime_selected_id.pop();

                        switch(selected_present)
                        {
                            case R.id.present_opt1:
                                --present_v_score;
                                break;

                            case R.id.present_opt2:
                                --present_p_score;
                                break;

                            case R.id.present_opt3:
                                --present_k_score;
                                break;
                        }
                        switch (selected_lifetime)
                        {
                            case R.id.lifetime_opt1:
                                --lifetime_v_score;
                                break;

                            case R.id.lifetime_opt2:
                                --lifetime_p_score;
                                break;

                            case R.id.lifetime_opt3:
                                --lifetime_k_score;
                                break;
                        }
                        Present.check(selected_present);
                        Lifetime.check(selected_lifetime);


                        updateQuestions(que_flag,present_v_score,present_p_score,present_k_score,lifetime_v_score,lifetime_p_score,lifetime_k_score);

                        PrevBtn.setVisibility(View.VISIBLE);
//                        Toast.makeText(QuizActivity.this, "Present :- " + present_v_score + "," + present_p_score + "," + present_k_score + "Lifetime :- "
//                                + lifetime_v_score + "," + lifetime_p_score + "," + lifetime_k_score, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public void updateQuestions(int que_flag,int present_v_score,int present_p_score,int present_k_score,int lifetime_v_score,int lifetime_p_score,int lifetime_k_scorel){
        if((que_flag<totalQuestions) && (que_flag>=0)) {
            QuestionId.setText(ques_id[que_flag]+"/39");
            Question.setText(questions[que_flag]);

            present_opt1.setText(option1[que_flag]);
            present_opt2.setText(option2[que_flag]);
            present_opt3.setText(option3[que_flag]);

            lifetime_opt1.setText(option1[que_flag]);
            lifetime_opt2.setText(option2[que_flag]);
            lifetime_opt3.setText(option3[que_flag]);
            if(que_flag == 0)
                PrevBtn.setVisibility(View.INVISIBLE);

        }
        else
        {
            Toast.makeText(QuizActivity.this,"There are No More Question!!!",Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString(MainActivity.score_v_present, Integer.toString(present_v_score));
            editor.putString(MainActivity.score_p_present, Integer.toString(present_p_score));
            editor.putString(MainActivity.score_k_present, Integer.toString(present_k_score));
            editor.putString(MainActivity.score_v_lifetime, Integer.toString(lifetime_v_score));
            editor.putString(MainActivity.score_p_lifetime, Integer.toString(lifetime_p_score));
            editor.putString(MainActivity.score_k_lifetime, Integer.toString(lifetime_k_score));
            editor.commit();

            que_flag = 0;
            NextBtn.setEnabled(true);

            Intent scoreActivity = new Intent(QuizActivity.this,ResultActivity.class);
            startActivity(scoreActivity);
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
