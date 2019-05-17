package com.example.loginanddatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.appComponentFactory;
import static android.R.attr.inset;
import static android.R.attr.name;

public class RegisterActivity extends AppCompatActivity {
    EditText etUsermane2, etPassword2, etComfp;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsermane2 = findViewById(R.id.etUsername2);
        etPassword2=  findViewById(R.id.etPassword2);
        etComfp=  findViewById(R.id.etComfp);
        btnAdd = findViewById(R.id.btnAdd);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();

            }
        });

    }

    private void insert()
    {
        try
        {
            String username= etUsermane2.getText().toString();
            String password = etPassword2.getText().toString();
            String comfpass = etComfp.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("register",Context.MODE_PRIVATE,  null);
            db.execSQL("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR, pass VARCHAR, comfpass VARCHAR)");

            if (!password.equals(comfpass)){
                Toast.makeText(RegisterActivity.this, "password not macthing", Toast.LENGTH_LONG).show();
            }
            else {
                String sql = "insert into user(username,pass,comfpass)values(?,?,?)";
                SQLiteStatement statement= db.compileStatement(sql);
                statement.bindString(1, username);
                statement.bindString(2, password);
                statement.bindString(3, comfpass);
                statement.execute();
                Toast.makeText(this, "Record added", Toast.LENGTH_LONG).show();

                etUsermane2.setText("");
                etPassword2.setText("");
                etComfp.setText("");
                etUsermane2.requestFocus();
            }
        }
        catch (Exception ex){

        }
    }
}
