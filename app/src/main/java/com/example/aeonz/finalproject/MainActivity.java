package com.example.aeonz.finalproject;
//--------------------------------------------------------------------------
// Author            : Henry Gutierrez
// Company           : N/A
// Date              : 06/19/2018
//--------------------------------------------------------------------------
// Revision          :
// Dependencies      :
// Description       :
// Simulation Notes  :
// Synthesis Notes   :
// Application Notes :
// Simulator         :
// Parameters        :
//--------------------------------------------------------------------------
// Revision History  :
//--------------------------------------------------------------------------

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnHelp = (Button) findViewById(R.id.btnHelp);
       btnHelp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final Intent helpIntent = new Intent(getApplicationContext() , HelpActivity.class);
               startActivity(helpIntent);
           }
       });

        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent startIntent = new Intent(MainActivity.this , userListView.class);
                startActivity(startIntent);
            }
        });

        Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent MapIntent = new Intent(getApplicationContext() , MapsActivity.class);
                startActivity(MapIntent);
            }
        });




    }



}


