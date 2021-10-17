package com.example.anotherversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.DataTruncation;

public class CreatePassword extends AppCompatActivity {
        EditText etPass1,etPass2;
        Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        btnCreate = (Button) findViewById(R.id.btnCreate);
        etPass1 = (EditText) findViewById(R.id.etPass1);
        etPass2 = (EditText) findViewById(R.id.etPass2);


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit1 = etPass1.getText().toString();
                String edit2 = etPass2.getText().toString();

                if (edit1.equals("") || edit2.equals("")){
                    Toast.makeText(getApplicationContext(),"Вы не ввели пароль!",Toast.LENGTH_SHORT).show();

                } else if (edit1.equals(edit2)) {
                    SharedPreferences pref = getSharedPreferences("prefs",0);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("password", edit1);
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), ControlQuestions.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(),"Несовпадение пароля", Toast.LENGTH_SHORT).show();
                }

            }
        });





    }
}