package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btna1,btna2, btna3,btna4;
    private TextView tvQuestion;
    private TextView tvQuestionNumber,tvPoints,tvGameOver;
    private Collection2 collection;
    private Question q;
    private int points;
    String backgroundColor;

    private LinearLayout ll;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ll = findViewById(R.id.activity_game);


        backgroundColor = getIntent().getStringExtra("background_color");
        setBackgroundColor(backgroundColor);



        tvQuestion = findViewById(R.id.tvQuestion);
        btna1 = findViewById(R.id.btna1);
        btna2 = findViewById(R.id.btna2);
        btna3 = findViewById(R.id.btna3);
        btna4 = findViewById(R.id.btna4);

        btna1.setOnClickListener(this);
        btna2.setOnClickListener(this);
        btna3.setOnClickListener(this);
        btna4.setOnClickListener(this);

        tvPoints = findViewById(R.id.tvPoints);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvGameOver=findViewById(R.id.tvGameOver);

        tvGameOver.setVisibility(View.INVISIBLE);

        collection = new Collection2();
        collection.initQuestions();

        nextQuestion();

    }

    private void nextQuestion() {

        if(collection.isNotLastQuestion())
        {
            q= collection.getNextQuestions();
            tvQuestion.setText(q.getQuestion());
            btna1.setText(q.getA1());
            btna2.setText(q.getA2());
            btna3.setText(q.getA3());
            btna4.setText(q.getA4());

        }
        else
        {
            tvGameOver.setVisibility(View.VISIBLE);
            createDialog();
        }

    }

    private void createDialog() {

        CustomDialog customDialog = new CustomDialog(this);
        customDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v==btna1)
        {
            if (q.getCorrect()==1)
                points++;
        }

        if (v==btna2)
        {
            if (q.getCorrect()==2)
                points++;
        }if (v==btna3)
        {
            if (q.getCorrect()==3)
                points++;
        }if (v==btna4)
        {
            if (q.getCorrect()==4)
                points++;
        }
        tvPoints.setText("points: " + points);
        if (collection.isNotLastQuestion())
        {
            tvQuestionNumber.setText("Question number: " +(collection.getIndex()+1 ));
        }
        nextQuestion();
    }

    public void reset() {
        this.points=0;
        collection.initQuestions();
        tvPoints.setText("points: " + 0);
        tvQuestionNumber.setText("Question number: " + 1);
        tvGameOver.setVisibility(View.INVISIBLE);
        this.nextQuestion();
    }

    public void setBackgroundColor(String color) {
        switch (color) {
            case "Red": {
                ll.setBackgroundColor(Color.RED);
                break;
            }
            case "Blue": {
                ll.setBackgroundColor(Color.BLUE);
                break;
            }
            case "Yellow": {
                ll.setBackgroundColor(Color.YELLOW);
                break;
            }
            case "Pink": {
                ll.setBackgroundColor(Color.argb(100, 100, 100, 100));
                break;
            }
            default: {
                ll.setBackgroundColor(Color.WHITE);
            }
        }
    }


    //ניסיון שלי

}