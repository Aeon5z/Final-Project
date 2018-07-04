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
    }
}
