package com.example.finalprojectodc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {

    SQLiteHelper(Context context,
                 String name,
                 SQLiteDatabase.CursorFactory factory,
                 int version){
        super(context,name,factory,version);

    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

// i do this to insert data

    public void insertData(String title, String description,String price, byte[] image) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO RECORD VALUES(NULL, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,title);
        statement.bindString(2,description);
        statement.bindString(3,price);
        statement.bindBlob(4,image);

        statement.executeInsert();


    }

    // i do this to update data

    public void updateData(String title, String description,String price, byte[] image,int id){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE RECORD SET title=?,description=?,price=?,image=? WHERE id=?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1,title);
        statement.bindString(2,description);
        statement.bindString(3,price);
        statement.bindBlob(4,image);
        statement.bindDouble(5,(double) id);

        statement.execute();
        database.close();

    }

// i do this to delete data

    public void deleteData(int id){
        SQLiteDatabase database= getWritableDatabase();
        String sql = "DELETE FROM RECORD WHERE id=?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1,(double) id);

        statement.execute();
        database.close();
    }
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}


