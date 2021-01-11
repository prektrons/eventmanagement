package com.example.eventmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  Button eventbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventbutton= (Button) findViewById(R.id.eventbutton);
        TextView textmain = (TextView) findViewById(R.id.textmain);
        textmain.setText("WELCOME TO THE OFFICIAL APPLICATION. ALL THE INFORMATION RELATED TO EVENTS WILL BE UPDATED HERE\n"
                + "\n"
                + "App- Intent");
        eventbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,EventActivity.class);

                // start the activity connect to the specified class
                startActivity(intent);
            }
        });
    }

}