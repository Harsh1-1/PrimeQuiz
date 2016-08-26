package com.example.harsh.primequiz;


/*
Notes:
On every correct answer player gets one point
and [IMPORTANT]on any wrong answer score will reset to 0

 */

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class PrimeQuizActivity extends AppCompatActivity {

    private Button YesButton;
    private Button NoButton;
    private static int random_number;
    private Button NextButton;                          //Resources
    protected TextView AnswerStatus;
    protected TextView Question;
    private static final String Tag = "PrimeQuizActivity";
    protected static int nscore;
    protected TextView Score;
    private boolean isanswered;
    private Button CheatButton;
    private Button HintButton;
    private static boolean ischeattaken;
    public final static String Cheat_Message = "cheat_answer";
    public final static String Hint_Message = "get_hint";


    //function to check whether a number is prime or not
    public int checkprime(int number)
    {
        int primeflag = 0;
        for(int i=2;i<=number/2;i++)
        {
            if (number%i == 0)
            {
                primeflag=1;
                break;
            }
        }
        return primeflag;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_quiz);

        Log.d(Tag,"Inside Oncreate");

        final String correct_answer = getResources().getString(R.string.correct);
        final String incorrect_answer = getResources().getString(R.string.incorrect);

        //getting ids of all the components
        YesButton = (Button)findViewById(R.id.yesbutton);
        NoButton = (Button)findViewById(R.id.nobutton);
        NextButton = (Button)findViewById(R.id.nextbutton);
        Question = (TextView)findViewById(R.id.question);
        Score = (TextView)findViewById(R.id.score);
        AnswerStatus = (TextView)findViewById(R.id.answerstatus);
        CheatButton = (Button) findViewById(R.id.cheat);
        HintButton = (Button) findViewById(R.id.hint);

        //for hiding the status of answer
        AnswerStatus.setAlpha(0.0f);


        if(savedInstanceState != null)
        {
            //retrieving saved random number and score and isanswered and ischeattaken flagfrom bundle
            random_number = savedInstanceState.getInt("saved_random_number");
            nscore = savedInstanceState.getInt("saved_score");
            isanswered = savedInstanceState.getBoolean("saved_isanswered");
            ischeattaken = savedInstanceState.getBoolean("saved_ischeattaken");
        }
        else{
            //for generating a random number
            random_number = (int)(Math.random()*1001);
            ischeattaken = false;
            isanswered = false;
        }



        Question.setText("is " + random_number +" a prime number ?");
        Score.setText("Your Score: " + nscore);

        //Handling when yes button gets clicked
        YesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int i=checkprime(random_number);
                //Disabling "no" button when yes button gets clicked
                NoButton.setClickable(false);
                NoButton.setEnabled(false);
                //if user answers the quetion and click Yes button again , it will not be allowed
                if (isanswered)
                {
                    AnswerStatus.setText("Already Answered");
                    AnswerStatus.setAlpha(1.0f);
                    //if user cheats and tries to click yes button
                } else if (ischeattaken) {
                    AnswerStatus.setText("Already Cheated!!");
                    AnswerStatus.setAlpha(1.0f);
                } else {

                    if (i == 0) {
                        AnswerStatus.setText(correct_answer);
                        //changing the color of answer status
                        AnswerStatus.setTextColor(Color.GREEN);
                        AnswerStatus.setAlpha(1.0f);
                        nscore = nscore + 1;
                        isanswered = true;
                    } else {
                        AnswerStatus.setText(incorrect_answer);
                        AnswerStatus.setTextColor(Color.RED);
                        AnswerStatus.setAlpha(1.0f);
                        nscore = 0;
                        isanswered = true;
                    }
                    Score.setTextColor(Color.BLUE);
                    Score.setText("Your Score: " + nscore);
                }
                Log.d(Tag, "Yes got clicked");
            }
        });

        //handling when "no" button gets clicked
        NoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int i=checkprime(random_number);
                //Disabling yes button when no button gets clicked
                YesButton.setClickable(false);
                YesButton.setEnabled(false);
                //if already answered
                if (isanswered)
                {
                    AnswerStatus.setText("Already Answered");
                    AnswerStatus.setAlpha(1.0f);
                    //if already cheated
                } else if (ischeattaken) {
                    AnswerStatus.setText("Already Cheated!!");
                    AnswerStatus.setAlpha(1.0f);
                } else {

                    if (i == 0) {
                        AnswerStatus.setText(incorrect_answer);
                        AnswerStatus.setTextColor(Color.RED);
                        AnswerStatus.setAlpha(1.0f);
                        nscore = 0;
                        isanswered = true;
                    } else {
                        AnswerStatus.setText(correct_answer);
                        AnswerStatus.setTextColor(Color.GREEN);
                        AnswerStatus.setAlpha(1.0f);
                        nscore = nscore + 1;
                        isanswered = true;
                    }
                    Score.setTextColor(Color.BLUE);
                    Score.setText("Your Score: " + nscore);
                }
                Log.d(Tag, "No got clicked");
            }
        });


        //when next button gets clicked
        NextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ischeattaken = false;
                isanswered = false;
                AnswerStatus.setAlpha(0.0f);
                //for enabling both yes and no buttons on next question
                YesButton.setClickable(true);
                YesButton.setEnabled(true);
                NoButton.setClickable(true);
                NoButton.setEnabled(true);
                random_number = (int)(Math.random()*1001);
                Question.setText("is " + random_number +" a prime number ?");
                Score.setTextColor(Color.BLUE);
                Score.setText("Score: " + nscore);
                Log.d(Tag, "Next got clicked");
            }
        });


        CheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if already answered cheating is not allowed
                if (isanswered) {
                    AnswerStatus.setText("Already Answered");
                    AnswerStatus.setAlpha(1.0f);
                } else {
                    ischeattaken = true;
                    Intent intent = new Intent(getApplicationContext(), CheatActivity.class);
                    intent.putExtra(Cheat_Message, random_number);
                    startActivityForResult(intent, 1);
                }
                Log.d(Tag, "Cheat got clicked");
            }
        });


        HintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isanswered) {
                    AnswerStatus.setText("Already Answered");
                    AnswerStatus.setAlpha(1.0f);
                    //if already cheated or answered then hint taking will not be allowed
                } else if (ischeattaken) {
                    AnswerStatus.setText("Already Cheated!!");
                    AnswerStatus.setAlpha(1.0f);
                } else {
                    Intent intent = new Intent(getApplicationContext(), HintActivity.class);
                    intent.putExtra(Hint_Message, random_number);
                    startActivityForResult(intent, 3);
                }
                Log.d(Tag, "Hint got clicked");
            }
        });




    }


    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(Tag, "Inside OnStart");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(Tag,"Inside onPause");
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        //saving random number and score in bundle
        savedInstanceState.putInt("saved_random_number",random_number);
        savedInstanceState.putInt("saved_score",nscore);
        savedInstanceState.putBoolean("saved_isanswered", isanswered);
        savedInstanceState.putBoolean("saved_ischeattaken", ischeattaken);
        Log.i(Tag, "Inside onSaveInstance");
    }

    @Override
    public void onResume(){
        super.onResume();
        if (ischeattaken) {
            Toast.makeText(getApplicationContext(), "Cheat taken!!", Toast.LENGTH_SHORT).show();
        }
        Log.d(Tag,"Inside OnREsume");

    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            Log.d("onactivityprimequiz", "inside onActivityResult in request code if");
            if (resultCode == RESULT_OK)
                ischeattaken = intent.getBooleanExtra("Cheat_Taken", false);
        }
        Log.d("onactivityprimequiz", "inside onActivityResult");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(Tag, "Inside OnSTop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(Tag, "Inside OnDestroy");
    }

}
