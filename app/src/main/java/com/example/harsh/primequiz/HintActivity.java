package com.example.harsh.primequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HintActivity extends AppCompatActivity {

    private Button HintButton;
    private boolean hinttaken;
    private static int random_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        HintButton = (Button) findViewById(R.id.cheat);

        if (savedInstanceState != null) {
            random_number = savedInstanceState.getInt("saved_random_number");
            hinttaken = savedInstanceState.getBoolean("saved_hinttaken_status");
        } else {
            Intent intent = getIntent();
            random_number = intent.getIntExtra(PrimeQuizActivity.Hint_Message, 2);
            hinttaken = false;
        }


        HintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hinttaken = true;
                Toast.makeText(getApplicationContext(), "its not prime!!", Toast.LENGTH_SHORT).show();

            }
        });


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("saved_hinttaken_status", hinttaken);
        outState.putInt("saved_random_number", random_number);
    }


}
