package com.example.aeonz.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class userListView extends AppCompatActivity {

    private static final String TAG = "userListView";
    databaseHelper mDatabaseHelper;
    databaseHelper mDatabasehelper2;
    private ListView mListView;
    EditText userEditList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        userEditList = (EditText) findViewById(R.id.txtinsertListName);
        mDatabasehelper2 = new databaseHelper(this);

       mListView = (ListView) findViewById(R.id.userListView);
        mDatabaseHelper = new databaseHelper(this);
        populateView();


        Button btnSaveList = (Button) findViewById(R.id.button);
        btnSaveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = userEditList.getText().toString();


                if (userEditList.length() != 0){
                    addData(userInput);
                    userEditList.setText("");
                }
                else {
                    DBmessage("Please do not leave item name field empty");
                }

                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        Button btnPick = (Button) findViewById(R.id.btnPickForMe);
        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int min = 1;
            int max = mDatabaseHelper.countDBrows().intValue();
            Random numGen = new Random();

            int numGenerated = (numGen.nextInt(max - min + 1) );





           DBmessage("Item Picker would recommend: " +  mListView.getItemAtPosition(numGenerated));

            }
        });


    }

    private void populateView(){
        Log.d(TAG, "Displaying items in list View");

        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listUser = new ArrayList<>();
        while (data.moveToNext()) {
            listUser.add(data.getString(1));
        }
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listUser);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String listPosition = parent.getItemAtPosition(position).toString();
                Cursor data = mDatabaseHelper.getItemID(listPosition);



                int itemID = -1;
                while (data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Intent editItemsintent = new Intent(userListView.this, EditItemActivity.class);
                    editItemsintent.putExtra("id",itemID);
                    editItemsintent.putExtra("name", listPosition);
                    startActivity(editItemsintent);
                    DBmessage(itemID + listPosition);
                }
                else{
                    DBmessage("No ID associated with that name");
                }
            }
        });
    }

    public void addData(String newEntry){
        boolean insertData = mDatabasehelper2.addData(newEntry);

        if (insertData){
            DBmessage("Item was saved");
        }
        else {
            DBmessage("Item was not saved correctly");
        }

    }

    private void DBmessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


}
