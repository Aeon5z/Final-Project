package com.example.aeonz.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditItemActivity extends AppCompatActivity {
    private static final String TAG = "EditItemActivity";

    databaseHelper mDatabaseHelper;

    private Button btnSaveItem;
    private Button btnDeleteItem;
    private EditText txtItemName;

    private String itemName;
    private int    itemID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);
        btnSaveItem = (Button) findViewById(R.id.btnSaveItem);
        btnDeleteItem = (Button) findViewById(R.id.btnDeleteItem);
        txtItemName = (EditText) findViewById(R.id.txtEditItem);
        mDatabaseHelper = new databaseHelper(this);

        Intent recivedDataIntent = getIntent();

        itemID = recivedDataIntent.getIntExtra("id" , -1);
        itemName = recivedDataIntent.getStringExtra("name");

        txtItemName.setText(itemName);

        btnSaveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedItem = txtItemName.getText().toString();

                if (!updatedItem.equals("")){
                mDatabaseHelper.updateItem(updatedItem, itemID, itemName);
                }
                else{
                    toastMessage("Item text field cannot be left empty.");
                }

            }


        });

        btnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mDatabaseHelper.deleteItem(itemID, itemName);
            txtItemName.setText("");
            toastMessage("Item was removed");

            }
        });

    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
