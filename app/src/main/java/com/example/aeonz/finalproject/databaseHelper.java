package com.example.aeonz.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class databaseHelper extends SQLiteOpenHelper {


    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "User_Table";
   // private static final String TABLE_NAME1 = "Item_Table";
    private static final String Colum_1 = "ID";
    private static final String Colum_2 = "userlist";





    public databaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1 );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , " + Colum_2  + " TEXT)";

        db.execSQL(createTable);
    }

    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ContentValues contentValues1 = new ContentValues();
        contentValues.put(Colum_2, item);


        Log.d(TAG, "addData: adding " + item + "to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME,null, contentValues);

        if (result == 1){
            return  false;
        }
        else {
            return true;
        }


    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + Colum_1 + " FROM " + TABLE_NAME +
                " WHERE " + Colum_2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateItem(String newItemName, int id , String oldItemName){
        SQLiteDatabase db = this.getWritableDatabase();
        String updateQuery = "UPDATE " + TABLE_NAME + " SET " + Colum_2 +
                " = '" + newItemName + "' WHERE " + Colum_1 + " = '" + id + "'" +
                " AND " + Colum_2 + " = '" + oldItemName + "'";

                db.execSQL(updateQuery);
    }

    public void deleteItem(int id , String itemName){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + TABLE_NAME + " WHERE "
                + Colum_1 + " = '" + id + "'" +
                " AND " + Colum_2 + " = '" + itemName + "'";
            db.execSQL(deleteQuery);
    }

    public Long countDBrows(){
        SQLiteDatabase db = getReadableDatabase();
        Long NumRow = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return NumRow;
    }



}

