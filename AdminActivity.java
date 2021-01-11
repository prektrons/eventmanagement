package com.example.eventmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {
Button update,back;
    EditText name, eventtype, eventname;


    protected void onCreate(Bundle savedInstanceState) {
       ProjectDataBaseHelper db = new ProjectDataBaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        back= (Button)findViewById(R.id.back);
        update=(Button)findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=name.getText().toString();
                String eeventname=eventname.getText().toString();
                String eeventtype=eventtype.getText().toString();

                    Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                    if (uname.equals("") || eeventname.equals("") || eventtype.equals(""))
                        Toast.makeText(AdminActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                    else {
                        if (eeventname.equals(eeventtype)) {
                            Boolean checkregistername = db.checkregistername(uname);
                            if (checkregistername == false) {
                                Boolean insert = db.insertData(uname, eeventname);
                                if (insert == true) {
                                    Toast.makeText(AdminActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(AdminActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(AdminActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(AdminActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                        }


                    }
                    startActivity(intent);
                }
            });


 back.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         Intent intent = new Intent(AdminActivity.this, MainActivity.class);
         startActivity(intent);
     }
 });
    }


}