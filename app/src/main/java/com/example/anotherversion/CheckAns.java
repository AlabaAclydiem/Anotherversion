package com.example.anotherversion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CheckAns extends AppCompatActivity implements View.OnClickListener {
    private TextView quest;
    private Button btnNext;
    private EditText etAns;
    private DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_ans);
        quest = (TextView) findViewById(R.id.quest);
        etAns = (EditText)findViewById(R.id.etAns);
        btnNext = (Button)findViewById(R.id.btnNext);
        db = new DbHelper(this);
        btnNext.setOnClickListener(this);
    }

    private void check(){
        String ans = etAns.getText().toString();
        String ques = quest.getText().toString();

        if(db.getAnswers(ques,ans)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CheckAns.this);
            builder.setTitle("Сброс пароля")
                    .setNegativeButton("Нет",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getApplicationContext(), EnterPassword.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                    .setMessage("Вы уверены, что хотите сбросить пароль?")
                    .setPositiveButton("Да",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getApplicationContext(), CreatePassword.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        } else{
            Toast.makeText(getApplicationContext(), "Ответ неверный. Попробуйте еще раз!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        check();
    }

}
