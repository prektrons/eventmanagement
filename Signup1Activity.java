package com.example.eventmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup1Activity extends AppCompatActivity {
Button previous,signup;
EditText name,password,confirmPassword;
    ProjectDataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        name=(EditText)findViewById(R.id.name);
        confirmPassword=(EditText)findViewById(R.id.confirmPassword);
        password = (EditText)findViewById(R.id.password);
        previous = (Button)findViewById(R.id.previous);
        signup=(Button)findViewById(R.id.signup);
        db=new ProjectDataBaseHelper(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = name.getText().toString();
                String pass = password.getText().toString();
                String repass = confirmPassword.getText().toString();
                if (user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(Signup1Activity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = db.checkname(user);
                        if (checkuser == false) {
                            Boolean insert = db.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(Signup1Activity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Signup1Activity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Signup1Activity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Signup1Activity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup1Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}