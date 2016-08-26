package com.example.harsh.primequiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HintActivity extends AppCompatActivity {

    private Button HintButton;
    private static boolean hinttaken;
    private TextView HintTextView;
    private static int random_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        HintButton = (Button) findViewById(R.id.hint);
        HintTextView = (TextView) findViewById(R.id.hinttextview);

        if (savedInstanceState != null) {
            random_number = savedInstanceState.getInt("saved_random_number");
            hinttaken = savedInstanceState.getBoolean("saved_hinttaken_status");
        } else {
            Intent intent = getIntent();
            random_number = intent.getIntExtra(PrimeQuizActivity.Hint_Message, 2);
            hinttaken = false;
        }

        if (hinttaken) {
            HintTextView.setTextColor(Color.GREEN);
            HintTextView.setText("Check if the " + random_number + " is divisible by any number between 1 and " + (int) Math.sqrt((double) random_number));
        }


        HintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hinttaken = true;
                HintTextView.setTextColor(Color.GREEN);
                HintTextView.setText("Check if the " + random_number + " is divisible by any number between 1 and " + (int) Math.sqrt((double) random_number));
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
