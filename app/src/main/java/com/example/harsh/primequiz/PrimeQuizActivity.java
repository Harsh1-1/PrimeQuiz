package com.example.harsh.primequiz;


/*
Notes:
On every correct answer player gets one point
and [IMPORTANT]on any wrong answer score will reset to 0 

 */
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.TextView;

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

        YesButton = (Button)findViewById(R.id.yesbutton);
        NoButton = (Button)findViewById(R.id.nobutton);
        NextButton = (Button)findViewById(R.id.nextbutton);
        Question = (TextView)findViewById(R.id.question);
        Score = (TextView)findViewById(R.id.score);
        AnswerStatus = (TextView)findViewById(R.id.answerstatus);

        //for hiding the status of answer
        AnswerStatus.setAlpha(0.0f);


        if(savedInstanceState != null)
        {
            //retrieving saved random number and score from bundle
            random_number = savedInstanceState.getInt("saved_random_number");
            nscore = savedInstanceState.getInt("saved_score");
        }
        else{
            //for generating a random number
            random_number = (int)(Math.random()*1001);
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
                if(i==0)
                {
                    AnswerStatus.setText(correct_answer);
                    //changing the color of answer status
                    AnswerStatus.setTextColor(Color.GREEN);
                    AnswerStatus.setAlpha(1.0f);
                    nscore = nscore + 1;
                }
                else
                {
                    AnswerStatus.setText(incorrect_answer);
                    AnswerStatus.setTextColor(Color.RED);
                    AnswerStatus.setAlpha(1.0f);
                    nscore = 0;
                }
                Score.setText("Your Score: " + nscore);
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
                if(i==0)
                {
                    AnswerStatus.setText(incorrect_answer);
                    AnswerStatus.setTextColor(Color.RED);
                    AnswerStatus.setAlpha(1.0f);
                    nscore = 0;
                }
                else
                {
                    AnswerStatus.setText(correct_answer);
                    AnswerStatus.setTextColor(Color.GREEN);
                    AnswerStatus.setAlpha(1.0f);
                    nscore = nscore + 1;
                }
                Score.setText("Your Score: " + nscore);
                Log.d(Tag, "No got clicked");
            }
        });


        //when next button gets clicked
        NextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AnswerStatus.setAlpha(0.0f);
                //for enabling both yes and no buttons on next question
                YesButton.setClickable(true);
                YesButton.setEnabled(true);
                NoButton.setClickable(true);
                NoButton.setEnabled(true);
                random_number = (int)(Math.random()*1001);
                Question.setText("is " + random_number +" a prime number ?");
                Score.setText("Score: " + nscore);
                Log.d(Tag, "Next got clicked");
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
        Log.i(Tag, "Inside onSaveInstance");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(Tag,"Inside OnREsume");

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
