package com.example.anotherversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ControlQuestions extends AppCompatActivity implements View.OnClickListener{

    private TextView quest;
    private Button btnNext;
    private EditText etAns;
    private DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_questions);
        quest = (TextView) findViewById(R.id.quest);
        etAns = (EditText)findViewById(R.id.etAns);
        btnNext = (Button)findViewById(R.id.btnNext);
        db = new DbHelper(this);
        btnNext.setOnClickListener(this);
    }
    private void save (){
        String ques = quest.getText().toString();
        String ans = etAns.getText().toString();
        if (ans.isEmpty() && ques.isEmpty()){
            Toast.makeText(getApplicationContext(),"Вы не ответили на вопрос!",Toast.LENGTH_SHORT).show();
        } else {
            db.addAnswers(ques,ans);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        save();
        startActivity(new Intent(ControlQuestions.this, MainActivity.class));
        finish();
    }
}