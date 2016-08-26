package com.example.harsh.primequiz;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CheatActivity extends AppCompatActivity {

    private Button CheatButton;
    private boolean cheated;
    private static int random_number;

    public int checkprime(int number) {
        int primeflag = 0;
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                primeflag = 1;
                break;
            }
        }
        return primeflag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        CheatButton = (Button) findViewById(R.id.cheat);

        if (savedInstanceState != null) {
            random_number = savedInstanceState.getInt("saved_random_number");
            cheated = savedInstanceState.getBoolean("saved_cheated_status");
        } else {
            Intent intent = getIntent();
            random_number = intent.getIntExtra(PrimeQuizActivity.Cheat_Message, 2);
            cheated = false;
        }

        Toast.makeText(getApplicationContext(), "Cheat will be considered whether or not you see the answer", Toast.LENGTH_LONG).show();

        CheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cheated = true;
                int i = checkprime(random_number);
                if (i == 0) {
                    Toast.makeText(getApplicationContext(), "Yes its prime!!", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "its not prime!!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Log.d("CheatActivity", "inside onBackPressed in Cheated activity");
        Intent intent = new Intent();
        intent.putExtra("Cheat_Taken", cheated);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("saved_cheated_status", cheated);
        outState.putInt("saved_random_number", random_number);
    }
}
