package com.example.eventmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Button logout, register;
    EditText name, eventtype, eventname;
    ProjectDataBaseHelper db = new ProjectDataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.name);
        eventname = (EditText) findViewById(R.id.eventname);
        eventtype = (EditText) findViewById(R.id.eventtype);
        register = (Button) findViewById(R.id.register);
        logout = (Button) findViewById(R.id.logout);
        db = new ProjectDataBaseHelper(this);
        register.setOnClickListener(new View.OnClickListener() {
            String uname=name.getText().toString();
            String eeventname=eventname.getText().toString();
            String eeventtype=eventtype.getText().toString();
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                if (uname.equals("") || eeventname.equals("") || eventtype.equals(""))
                    Toast.makeText(RegisterActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                else {
                    if (eeventname.equals(eeventtype)) {
                        Boolean checkregistername = db.checkregistername(uname);
                        if (checkregistername == false) {
                            Boolean insert = db.insertData(uname, eeventname);
                            if (insert == true) {
                                Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }


                }
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}