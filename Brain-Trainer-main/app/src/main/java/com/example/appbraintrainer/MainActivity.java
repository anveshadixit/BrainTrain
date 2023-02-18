package com.example.appbraintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goo;
    TextView ques;
    int rightblock, score = 0, attempts = 0;
    boolean gameactive = true;
    TextView textView5;
    TextView textView2, textView3;
    Button button2, button3, button4, button5, button6;


    public void goB(View view) {
        goo.setVisibility(View.INVISIBLE);
        ques.setVisibility(View.VISIBLE);
        textView5.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
        textView3.setVisibility(View.VISIBLE);
        generateQ();
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisecondsUntilDone) {
                textView3.setText(Long.toString(millisecondsUntilDone / 1000));
            }

            @Override
            public void onFinish() {
                textView5.setText("Your Score: " + (Integer.toString(score) + "/" + Integer.toString(attempts)));
                button6.setVisibility(View.VISIBLE);
                gameactive = false;
            }
        }.start();

    }

    public void playAgain(View view) {
        button6.setVisibility(view.INVISIBLE);
        textView5.setText("");
        score = 0;
        attempts = 0;
        gameactive = true;
        textView2.setText("0/0");
        generateQ();

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisecondsUntilDone) {
                textView3.setText(Long.toString(millisecondsUntilDone / 1000));
            }

            @Override
            public void onFinish() {
                textView5.setText("Your Score: " + (Integer.toString(score) + "/" + Integer.toString(attempts)));
                button6.setVisibility(View.VISIBLE);
                gameactive=false;
            }
        }.start();

    }

    public void mathSol(View view) {
        attempts++;
        if (gameactive == true) {
            if (Integer.parseInt(view.getTag().toString()) == rightblock) {
                textView5.setText("Correct!");
                score++;
                textView2.setText(Integer.toString(score) + "/" + Integer.toString(attempts));
                generateQ();
            } else {
                textView5.setText("Incorrect!");
                textView2.setText(Integer.toString(score) + "/" + Integer.toString(attempts));
            }
            Log.i("erry", (view.getTag().toString()));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goo = (Button) findViewById(R.id.button);
        ques = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button4);
        button4 = (Button) findViewById(R.id.button3);
        button5 = (Button) findViewById(R.id.button5);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView5 = findViewById(R.id.textView5);
        button6 = (Button) findViewById(R.id.button6);


    }


    public void generateQ() {
        if (gameactive == true) {
            ArrayList<Integer> answer = new ArrayList<Integer>();

            Random rand = new Random();
            int a = rand.nextInt(21);
            int b = rand.nextInt(21);

            ques.setText(Integer.toString(a) + "+" + Integer.toString(b));
            rightblock = rand.nextInt(4);
            int incorrect;
            Log.d("buttooo", Integer.toString(rightblock));
            for (int i = 0; i < 4; i++) {
                if (i == rightblock) {
                    answer.add(a + b);

                } else {
                    incorrect = rand.nextInt(41);
                    while (incorrect == (a + b))
                        incorrect = rand.nextInt(41);
                    answer.add(incorrect);
                }
            }

            button2.setText(Integer.toString(answer.get(0)));
            Log.i("butto2", ((Integer.toString(answer.get(0)))));

            button4.setText(Integer.toString(answer.get(1)));
            Log.i("butto3", ((Integer.toString(answer.get(1)))));

            button3.setText(Integer.toString(answer.get(2)));

            Log.i("butto4", ((Integer.toString(answer.get(2)))));

            button5.setText(Integer.toString(answer.get(3)));
            Log.i("butto5", ((Integer.toString(answer.get(3)))));

        }

    }
}


